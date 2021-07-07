package com.UN5.market.persistence.crud;

import com.UN5.market.persistence.entity.ComprasProducto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraProductoCrudRepository extends CrudRepository<ComprasProducto, Integer> {

    List<ComprasProducto> findByIdIdCompraOrderByProductoNombreAsc(int idCompra);
    List<ComprasProducto> findByIdIdProductoOrderByProductoNombreAsc(int idProducto);

}
