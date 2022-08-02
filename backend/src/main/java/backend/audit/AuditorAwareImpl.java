package backend.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null)
            return Optional.of("");
        if(authentication.getPrincipal().equals("anonymousUser"))
            return Optional.of("Server");
        else
            return Optional.ofNullable(authentication.getPrincipal().toString());
    }

}