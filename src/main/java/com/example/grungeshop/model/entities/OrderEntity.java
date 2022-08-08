package com.example.grungeshop.model.entities;

import com.example.grungeshop.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum categoryEnum;

    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private UserEntity client;

    public long getId() {
        return id;
    }

    public OrderEntity setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public OrderEntity setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
        return this;
    }

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
                "id=" + id +
                ", categoryEnum=" + categoryEnum +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
