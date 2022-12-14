package backend.security;

import backend.dao.UserDAO;
import backend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), emptyList());
    }
}