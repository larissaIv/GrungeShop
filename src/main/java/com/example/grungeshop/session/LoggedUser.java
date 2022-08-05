package com.example.grungeshop.session;


import com.example.grungeshop.model.entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private long id;
    private String email;

    public void login(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public void logout() {
        this.id = 0;
        this.email = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }
}