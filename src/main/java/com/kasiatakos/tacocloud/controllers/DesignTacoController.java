package com.kasiatakos.tacocloud.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kasiatakos.tacocloud.domain.Ingredient;
import com.kasiatakos.tacocloud.domain.Order;
import com.kasiatakos.tacocloud.domain.Taco;
import com.kasiatakos.tacocloud.repositories.jpa.IngredientRepository;
import com.kasiatakos.tacocloud.repositories.jpa.TacoRepository;

import lombok.extern.slf4j.Slf4j;

import static com.kasiatakos.tacocloud.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        addIngredientsToModel(model);
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, @ModelAttribute Order order, Errors errors, Model model){

        if(errors.hasErrors()){
            addIngredientsToModel(model);
            return "design";
        }
        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
        return  ingredients.stream()
                           .filter(ingredient -> ingredient.getType().toString().equalsIgnoreCase(type))
                           .collect(Collectors.toList());
    }

    private void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = StreamSupport.stream(ingredientRepo.findAll().spliterator(),false)
                                                    .collect(Collectors.toList());

        List<String> ingredientTypes = Arrays.stream(Ingredient.Type.values())
                                             .map(Type::toString)
                                             .map(String::toLowerCase)
                                             .collect(Collectors.toList());

        model.addAttribute("ingredientTypes", ingredientTypes);

        for (String type : ingredientTypes) {
            model.addAttribute(type, filterByType(ingredients, type));
        }
    }
}
