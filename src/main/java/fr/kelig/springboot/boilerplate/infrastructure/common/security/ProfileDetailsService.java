package fr.kelig.springboot.boilerplate.infrastructure.common.security;

import fr.kelig.springboot.boilerplate.application.profile.FindProfileByUsername;
import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ProfileDetailsService implements UserDetailsService {

    private final FindProfileByUsername findProfileByUsername;

    public ProfileDetailsService(FindProfileByUsername findProfileByUsername) {
        this.findProfileByUsername = findProfileByUsername;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Profile profile = findProfileByUsername.execute(username);

        return User.builder()
                .username(profile.getUsername())
                .password(profile.getPassword())
                .roles("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
