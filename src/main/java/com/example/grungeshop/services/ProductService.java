package com.example.grungeshop.services;

import com.example.grungeshop.model.DTO.OrderDTO;
import com.example.grungeshop.model.DTO.ProductDTO;
import com.example.grungeshop.model.entities.OrderEntity;
import com.example.grungeshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public boolean findProductById(Long id) {
        return productRepository.existsById(id);
    }

    public List<OrderEntity> getAllProducts() {
        return getAllProducts().stream().toList();
    }

    public boolean buyProducts(ProductDTO productDTO) {
        return productRepository.count() > 0;
    }

}
