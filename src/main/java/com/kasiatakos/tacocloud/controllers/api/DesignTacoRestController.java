package com.kasiatakos.tacocloud.controllers.api;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kasiatakos.tacocloud.controllers.DesignTacoController;
import com.kasiatakos.tacocloud.domain.Taco;
import com.kasiatakos.tacocloud.domain.assembler.TacoResourceAssembler;
import com.kasiatakos.tacocloud.domain.resource.TacoResource;
import com.kasiatakos.tacocloud.repositories.jpa.TacoRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/api/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoRestController {
    private final TacoRepository tacoRepository;
    private final TacoResourceAssembler tacoResourceAssembler;

    public DesignTacoRestController(TacoRepository tacoRepo, TacoResourceAssembler tacoResourceAssembler) {
        this.tacoRepository = tacoRepo;
        this.tacoResourceAssembler = tacoResourceAssembler;
    }

    @GetMapping("/recent")
    public Resources<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        Page<Taco> tacos = tacoRepository.findAll(page);

        List<TacoResource> tacoResources =tacoResourceAssembler.toResources(tacos.getContent());

        Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
        recentResources.add(
            linkTo(methodOn(DesignTacoRestController.class).recentTacos())
                .withRel("recents"));
        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }


    @PutMapping("/{id}")
    public Taco putOrder(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable Long id) {
        try {
            tacoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }


}
