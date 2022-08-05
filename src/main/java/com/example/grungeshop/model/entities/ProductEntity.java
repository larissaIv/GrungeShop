package com.example.grungeshop.model.entities;

import com.example.grungeshop.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum categoryEnum;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private OrderEntity offer;

    public OrderEntity getOffer() {
        return offer;
    }

    public ProductEntity setOffer(OrderEntity offer) {
        this.offer = offer;
        return this;
    }

    public long getId() {
        return id;
    }

    public ProductEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public ProductEntity setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryEnum=" + categoryEnum +
                ", price=" + price +
                '}';
    }
}
