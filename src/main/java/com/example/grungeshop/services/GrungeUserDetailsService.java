package com.example.grungeshop.services;

import com.example.grungeshop.model.entities.UserEntity;
import com.example.grungeshop.model.entities.UserRoleEntity;
import com.example.grungeshop.model.user.GrungeUserDetails;
import com.example.grungeshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GrungeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public GrungeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity user) {
        return new GrungeUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getUserRoles().stream().map(this::map).toList());
    }

    private GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getUserRole().name());
    }
}
