package com.example.grungeshop.model.entities;

import com.example.grungeshop.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{


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
    private OrderEntity order;

    public OrderEntity getOrder() {
        return order;
    }

    public ProductEntity setOrder(OrderEntity order) {
        this.order = order;
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
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryEnum=" + categoryEnum +
                ", price=" + price +
                '}';
    }
}
