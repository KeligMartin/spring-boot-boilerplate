package fr.kelig.springboot.boilerplate.domain.profile;

import fr.kelig.springboot.boilerplate.domain.role.Role;

import java.util.List;

public class Profile {

    private String id;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;

    public Profile(String id, String email, String username, String password, List<Role> roles){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Profile(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
