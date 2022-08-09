package com.example.grungeshop.services;

import com.example.grungeshop.model.DTO.LoginDTO;
import com.example.grungeshop.model.DTO.UserRegisterDTO;
import com.example.grungeshop.model.entities.UserEntity;
import com.example.grungeshop.session.LoggedUser;
import com.example.grungeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoggedUser userSession;

    public UserService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        UserEntity user = new UserEntity(
                userRegisterDTO.getEmail(),
                userRegisterDTO.getPassword(),
                userRegisterDTO.getConfirmPassword(),
                userRegisterDTO.getUsername());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<UserEntity> user = this.userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (user.isEmpty()) {
            return false;
        }

        this.userSession.login(user.get());
        return true;
    }

    public boolean isLoggedIn() {
            return this.userSession.getId() > 0;
        }

    public void logout() {
        this.userSession.logout();
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}
