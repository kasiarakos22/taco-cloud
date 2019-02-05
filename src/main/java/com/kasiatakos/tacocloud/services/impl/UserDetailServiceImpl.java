package com.kasiatakos.tacocloud.services.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kasiatakos.tacocloud.repositories.jpa.UserRepository;
import com.kasiatakos.tacocloud.services.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println();
        return Optional.ofNullable(userRepository.findByUsername(username))
                       .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}
