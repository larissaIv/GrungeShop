package com.example.grungeshop.repository;

import com.example.grungeshop.model.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByUsernameIsContaining(String value);

    Optional<UserEntity> findByEmailIsContaining(String email);


}
