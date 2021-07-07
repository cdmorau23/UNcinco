package com.UN5.market.persistence.crud;

import com.UN5.market.persistence.entity.Restaurante;
import org.springframework.data.repository.CrudRepository;

public interface RestauranteCrudRepository extends CrudRepository<Restaurante,Integer> {
}
