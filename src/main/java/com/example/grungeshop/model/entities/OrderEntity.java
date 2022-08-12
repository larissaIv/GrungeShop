package com.example.grungeshop.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private UserEntity client;


    public String getImageUrl() {
        return imageUrl;
    }

    public OrderEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getClient() {
        return client;
    }

    public OrderEntity setClient(UserEntity client) {
        this.client = client;
        return this;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
