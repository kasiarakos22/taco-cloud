package com.kasiatakos.tacocloud.domain.resource;

import org.springframework.hateoas.ResourceSupport;

import com.kasiatakos.tacocloud.domain.Ingredient;

import lombok.Getter;

public class IngredientResource extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Ingredient.Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
