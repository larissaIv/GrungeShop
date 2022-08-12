package com.example.grungeshop.model.DTO;

import javax.validation.constraints.NotBlank;

public class SearchDTO {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public SearchDTO setName(String name) {
        this.name = name;
        return this;
    }
}
