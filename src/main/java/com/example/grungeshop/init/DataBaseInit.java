package com.example.grungeshop.init;

import com.example.grungeshop.services.RoleService;
import com.example.grungeshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final UserService userService;

    public DataBaseInit(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.userService.initFirstUser();
    }
}
