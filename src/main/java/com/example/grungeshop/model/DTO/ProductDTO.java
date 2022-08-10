package com.example.grungeshop.model.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductDTO {

    @Positive
    private long id;

    @NotNull
    private String name;
    private String image;

    @NotNull
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public ProductDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
