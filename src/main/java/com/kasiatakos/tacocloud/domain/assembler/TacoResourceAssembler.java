package com.kasiatakos.tacocloud.domain.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kasiatakos.tacocloud.controllers.api.DesignTacoRestController;
import com.kasiatakos.tacocloud.domain.Taco;
import com.kasiatakos.tacocloud.domain.resource.TacoResource;

@Component
public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoRestController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateResource(Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toResource(Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

}
