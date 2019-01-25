package com.kasiatakos.tacocloud.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {

    private String name;
    List<Ingredient> ingredients;

}
