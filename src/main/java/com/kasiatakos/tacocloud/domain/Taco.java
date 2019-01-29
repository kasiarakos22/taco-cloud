package com.kasiatakos.tacocloud.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotEmpty(message = "The list of ingredients should not be empty")
    @ManyToMany(targetEntity=Ingredient.class)
    @JoinTable(name = "taco_ingredients",
        joinColumns = @JoinColumn(name = "taco"),
        inverseJoinColumns = @JoinColumn(name = "ingredient"))
    @Size(min=1, message="You must choose at least 1 ingredient")
    List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}
