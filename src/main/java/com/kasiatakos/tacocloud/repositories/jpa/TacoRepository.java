package com.kasiatakos.tacocloud.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import com.kasiatakos.tacocloud.domain.Taco;
import com.sun.xml.internal.bind.v2.model.core.ID;

@RepositoryDefinition(domainClass = Taco.class, idClass = Long.class)
public interface TacoRepository {

    Taco save(Taco taco);

    @Query("select taco from Taco taco where taco.name = 'kasiarakos'")
    List<Taco> findTacoByKasiarakos();

    Page<Taco> findAll(Pageable pageable);

    Optional<Taco> findById(Long id);

    void deleteById(Long id);

}
