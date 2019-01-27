package com.kasiatakos.tacocloud.repositories;

import com.kasiatakos.tacocloud.domain.Order;

public interface OrderRepository {
    Order save(Order order);

}
