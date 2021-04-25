package fr.kelig.springboot.boilerplate.application.authentication.dto;

import fr.kelig.springboot.boilerplate.infrastructure.common.annotation.StrongPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegisterDTO {

    @Email
    private final String email;

    @Size(min = 6, max = 25)
    private final String username;

    @StrongPassword
    private final String password;

    public RegisterDTO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
