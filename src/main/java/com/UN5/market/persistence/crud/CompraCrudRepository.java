package com.UN5.market.persistence.crud;


import com.UN5.market.persistence.entity.Compra;
import com.UN5.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra,Integer> {
    List<Compra> findByIdRestauranteOrderByFechaAsc(int idRestaurante);

}
