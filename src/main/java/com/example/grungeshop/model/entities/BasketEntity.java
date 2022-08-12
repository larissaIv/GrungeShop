package com.example.grungeshop.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "baskets")
public class BasketEntity extends BaseEntity{

    @NotBlank
    private BigDecimal value;

    @NotBlank
    private BigDecimal delivery;

    @OneToOne
    private UserEntity client;

    @OneToMany
    private List<ProductEntity> products;


    public BigDecimal getValue() {
        return value;
    }

    public BasketEntity setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public BigDecimal getDelivery() {
        return delivery;
    }

    public BasketEntity setDelivery(BigDecimal delivery) {
        this.delivery = delivery;
        return this;
    }

    public UserEntity getClient() {
        return client;
    }

    public BasketEntity setClient(UserEntity client) {
        this.client = client;
        return this;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public BasketEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }
}
