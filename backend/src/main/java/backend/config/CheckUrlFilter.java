package backend.config;

import backend.dao.RoleDao;
import backend.dao.UrlRoleDao;
import backend.dao.UserDAO;
import backend.dao.UserRoleDao;
import backend.entity.Role;
import backend.entity.RoleUrl;
import backend.entity.User;
import backend.entity.UserRole;
import io.jsonwebtoken.Jwts;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static backend.config.SecurityConstants.*;

public class CheckUrlFilter extends BasicAuthenticationFilter {
    private UserRoleDao userRoleDao;
    private RoleDao roleDao;
    private UserDAO userDAO;
    private UrlRoleDao urlRoleDao;

    public CheckUrlFilter(AuthenticationManager authManager, UserRoleDao userRoleDao, RoleDao roleDao, UserDAO userDAO, UrlRoleDao urlRoleDao) {
        super(authManager);
        this.userRoleDao = userRoleDao;
        this.roleDao = roleDao;
        this.userDAO = userDAO;
        this.urlRoleDao = urlRoleDao;
    }

    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            Role role = roleDao.findByRoleName(grantedAuthority.getAuthority());
            if (role != null) {
                List<RoleUrl> roleUrls = urlRoleDao.findAllByRoleId(role.getId());
                for (RoleUrl roleUrl : roleUrls)
                    authorities.add(roleUrl.getUrl());
            }
        }
        Boolean status = false;
        for (String url : authorities)
            if (url.equals(req.getServletPath()))
                status = true;
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities())
            if (grantedAuthority.getAuthority().equals("admin"))
                status = true;
        if (!status)
            throw new PermissionDeniedDataAccessException("Error", new Throwable());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user.equals("admin")) {
                authorities.add(new SimpleGrantedAuthority("admin"));
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            } else if (user != null) {
                User k = userDAO.findByUserName(user);
                if (k != null) {
                    for (UserRole userRole : userRoleDao.findAllByUserId(k.getId())) {
                        Optional<Role> role = roleDao.findById(userRole.getRoleId());
                        if (!role.isEmpty())
                            authorities.add(new SimpleGrantedAuthority(role.get().getRoleName()));
                    }
                }
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
            return null;
        }
        return null;
    }
}