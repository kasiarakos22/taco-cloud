package com.kasiatakos.tacocloud.repositories.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

import com.kasiatakos.tacocloud.domain.Order;
import com.kasiatakos.tacocloud.domain.User;

@RepositoryDefinition(idClass = Long.class, domainClass = Order.class)
public interface OrderRepository  {

    Order save(Order order);

    Iterable<Order> findByZip(String zip);

    List<Order> findByZipAndPlacedAtBetween(String deliveryZip, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
