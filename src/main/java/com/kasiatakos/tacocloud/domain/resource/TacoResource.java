package com.kasiatakos.tacocloud.domain.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.kasiatakos.tacocloud.domain.Taco;
import com.kasiatakos.tacocloud.domain.assembler.IngredientResourceAssembler;

import lombok.Getter;


@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends ResourceSupport {


    private static IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final LocalDateTime createdAt;

    @Getter
    private final List<IngredientResource> ingredients;


    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
    }

}
