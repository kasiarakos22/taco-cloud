package com.kasiatakos.tacocloud.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasiatakos.tacocloud.domain.Ingredient;
import com.kasiatakos.tacocloud.repositories.jpa.IngredientRepository;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAll(){
        System.out.println("kasiarakos");
        return new ResponseEntity(ingredientRepository.findAll(), HttpStatus.OK);
    }
}
