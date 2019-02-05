package com.kasiatakos.tacocloud.domain;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "TACO_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PLACEDAT")
    private LocalDateTime placedAt;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(name = "TACO_ORDER_TACOS",
        joinColumns = @JoinColumn(name = "TACOORDER"),
        inverseJoinColumns = @JoinColumn(name = "  TACO"))
    private List<Taco> tacos = new ArrayList<>();

    @Column(name = "DELIVERYNAME")
    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String street;

    @NotBlank(message="City is required")
    private String city;

    @NotBlank(message="State is required")
    @Size(max = 2)
    private String state;

    @NotBlank(message="Zip code is required")
    private String zip;

    @Column(name = "CCNUMBER")
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Column(name = "CCEXPIRATION")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @ManyToOne
    private User user;

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = LocalDateTime.now();
    }
}
