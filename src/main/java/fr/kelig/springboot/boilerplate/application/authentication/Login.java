package fr.kelig.springboot.boilerplate.application.authentication;

import fr.kelig.springboot.boilerplate.application.authentication.dto.LoginDTO;
import fr.kelig.springboot.boilerplate.application.profile.FindProfileByUsername;
import fr.kelig.springboot.boilerplate.infrastructure.common.security.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Login {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FindProfileByUsername findProfileByUsername;

    public Login(TokenProvider tokenProvider,
                 AuthenticationManagerBuilder authenticationManagerBuilder,
                 FindProfileByUsername findProfileByUsername) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.findProfileByUsername = findProfileByUsername;
    }

    public HttpHeaders execute(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        findProfileByUsername.execute(loginDTO.getUsername());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return httpHeaders;
    }
}
