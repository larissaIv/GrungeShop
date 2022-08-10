package com.example.grungeshop.services;

import com.example.grungeshop.model.DTO.UserRegisterDTO;
import com.example.grungeshop.model.entities.UserEntity;
import com.example.grungeshop.mapper.UserMapper;
import com.example.grungeshop.session.LoggedUser;
import com.example.grungeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final PasswordEncoder passwordEncoder;


    private UserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, LoggedUser userSession, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
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

}
