package fr.kelig.springboot.boilerplate.application.authentication;

import fr.kelig.springboot.boilerplate.application.authentication.dto.RegisterDTO;
import fr.kelig.springboot.boilerplate.application.profile.AddProfile;
import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class Register {

    private final AddProfile addProfile;

    @Autowired
    public Register(AddProfile addProfile) {
        this.addProfile = addProfile;
    }

    public URI execute(RegisterDTO registerDTO) {
        Profile profile = new Profile(registerDTO.getEmail(), registerDTO.getUsername(), registerDTO.getPassword());
        String id = addProfile.execute(profile);
        return ServletUriComponentsBuilder.fromPath("/api/users").path("/{id}").buildAndExpand(id).toUri();
    }
}
