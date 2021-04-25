package fr.kelig.springboot.boilerplate.infrastructure.common.mapper;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.role.Role;
import fr.kelig.springboot.boilerplate.infrastructure.profile.persitence.JpaProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileMapper implements ObjectMapper<Profile, JpaProfile> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Profile toDomain(JpaProfile jpaProfile) {
        return new Profile(jpaProfile.getId(),
                jpaProfile.getEmail(),
                jpaProfile.getUsername(),
                jpaProfile.getPassword(),
                List.of(Role.USER)
        );
    }

    public JpaProfile toEntity(Profile profile) {
        return new JpaProfile(
                profile.getId(),
                profile.getEmail(),
                profile.getUsername(),
                passwordEncoder.encode(profile.getPassword()),
                profile.getRoles()
        );
    }
}
