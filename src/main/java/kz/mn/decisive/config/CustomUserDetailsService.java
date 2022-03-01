package kz.mn.decisive.config;

import kz.mn.decisive.model.Decider;
import kz.mn.decisive.service.DeciderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private DeciderService deciderService;

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Decider decider = deciderService.findByUsername(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(decider);
    }
}
