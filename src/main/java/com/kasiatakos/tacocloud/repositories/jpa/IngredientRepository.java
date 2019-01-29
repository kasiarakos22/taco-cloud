package com.kasiatakos.tacocloud.repositories.jpa;

import org.springframework.data.repository.RepositoryDefinition;

import com.kasiatakos.tacocloud.domain.Ingredient;

@RepositoryDefinition(idClass = String.class, domainClass = Ingredient.class)
public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
