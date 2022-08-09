package com.example.grungeshop.repository;

import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public BasketService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
}
