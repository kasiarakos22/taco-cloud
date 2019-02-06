package com.kasiatakos.tacocloud.domain.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kasiatakos.tacocloud.controllers.api.IngredientController;
import com.kasiatakos.tacocloud.domain.Ingredient;
import com.kasiatakos.tacocloud.domain.resource.IngredientResource;

@Component
public class IngredientResourceAssembler extends
    ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toResource(Ingredient ingredient) {
        return createResourceWithId(ingredient.getId(), ingredient);
    }

    @Override
    protected IngredientResource instantiateResource(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

}
