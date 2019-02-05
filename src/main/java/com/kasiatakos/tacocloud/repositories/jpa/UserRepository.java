package com.kasiatakos.tacocloud.repositories.jpa;

import org.springframework.data.repository.RepositoryDefinition;

import com.kasiatakos.tacocloud.domain.User;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    User findByUsername(String username);

    User save(User user);

}
