package com.example.grungeshop.services;

import com.example.grungeshop.model.DTO.UserRegisterDTO;
import com.example.grungeshop.model.entities.UserEntity;
import com.example.grungeshop.session.LoggedUser;
import com.example.grungeshop.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository, LoggedUser userSession, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void register(UserRegisterDTO userRegisterDTO, Locale locale) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

//        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
//
//        if (byEmail.isPresent()) {
//            throw new RuntimeException("email.used");
//        }

        UserEntity user = new UserEntity(
                userRegisterDTO.getEmail(),
                userRegisterDTO.getPassword(),
                userRegisterDTO.getConfirmPassword(),
                userRegisterDTO.getUsername());

        this.userRepository.save(user);

    }

    public void login(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
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

    public void initFirstUser() {
    }

    public boolean findByUsernameIs(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsernameIsContaining(username);

        if (userEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findByEmailIs(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmailIsContaining(email);

        if (userEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
