package com.kasiatakos.tacocloud.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {

    private Long id;
    private LocalDateTime createdAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotEmpty(message = "The list of ingredients should not be empty")
    @Size(min=1, message="You must choose at least 1 ingredient")
    List<Ingredient> ingredients;

}
