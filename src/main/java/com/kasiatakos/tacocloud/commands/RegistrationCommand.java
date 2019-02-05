package com.kasiatakos.tacocloud.commands;


import javax.persistence.Convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kasiatakos.tacocloud.domain.User;

import lombok.Data;

@Data
public class RegistrationCommand  {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
            username, passwordEncoder.encode(password),
            fullname, street, city, state, zip, phone);
    }

}
