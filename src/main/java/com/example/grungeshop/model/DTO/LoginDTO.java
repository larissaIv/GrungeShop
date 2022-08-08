package com.example.grungeshop.model.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class LoginDTO {

    @Email
    @Min(5)
    String email;

    @Min(5)
    String password;

    public String getEmail() {
        return email;
    }

    public LoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
