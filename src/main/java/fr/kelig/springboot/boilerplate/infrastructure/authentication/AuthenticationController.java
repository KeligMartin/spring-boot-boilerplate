package fr.kelig.springboot.boilerplate.infrastructure.authentication;

import fr.kelig.springboot.boilerplate.application.authentication.Login;
import fr.kelig.springboot.boilerplate.application.authentication.Register;
import fr.kelig.springboot.boilerplate.application.authentication.dto.LoginDTO;
import fr.kelig.springboot.boilerplate.application.authentication.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final Register register;
    private final Login login;

    @Autowired
    public AuthenticationController(Register register, Login login) {
        this.register = register;
        this.login = login;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO){
        URI uri = register.execute(registerDTO);
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        HttpHeaders httpHeaders = login.execute(loginDTO);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
