package com.kasiatakos.tacocloud.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kasiatakos.tacocloud.domain.Design;
import com.kasiatakos.tacocloud.domain.Ingredient;
import com.kasiatakos.tacocloud.domain.Taco;

import lombok.extern.slf4j.Slf4j;

import static com.kasiatakos.tacocloud.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        List<String> ingredientTypes = Arrays.stream(Ingredient.Type.values())
                                             .map(Type::toString)
                                             .map(String::toLowerCase)
                                             .collect(Collectors.toList());

        model.addAttribute("ingredientTypes", ingredientTypes);

        for (String type : ingredientTypes) {
            model.addAttribute(type, filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Design design){
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
        return  ingredients.stream()
                           .filter(ingredient -> ingredient.getType().toString().equalsIgnoreCase(type))
                           .collect(Collectors.toList());
    }
}
