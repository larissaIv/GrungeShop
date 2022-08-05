package com.example.grungeshop.services;

import com.example.grungeshop.model.DTO.OrderDTO;
import com.example.grungeshop.model.entities.OrderEntity;
import com.example.grungeshop.repository.OrderRepository;
import com.example.grungeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    public List<OrderEntity> getAllProducts() {
        return orderRepository.findAll();
    }

    public boolean buyProducts(OrderDTO orderDTO) {
        return orderRepository.count() > 0;
    }
}
