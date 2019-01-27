package com.kasiatakos.tacocloud.repositories;

import com.kasiatakos.tacocloud.domain.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
