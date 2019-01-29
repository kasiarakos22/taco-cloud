package com.kasiatakos.tacocloud.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.kasiatakos.tacocloud.domain.Taco;

@RepositoryDefinition(domainClass = Taco.class, idClass = Long.class)
public interface TacoRepository {

    Taco save(Taco taco);

    @Query("select taco from Taco taco where taco.name = 'kasiarakos'")
    List<Taco> findTacoByKasiarakos();
}
