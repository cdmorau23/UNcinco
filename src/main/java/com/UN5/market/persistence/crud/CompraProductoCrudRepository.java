package com.UN5.market.persistence.crud;

import com.UN5.market.persistence.entity.ComprasProducto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CompraProductoCrudRepository extends CrudRepository<ComprasProducto, Integer> {

    List<ComprasProducto> findByIdIdCompraOrderByProductoNombreAsc(int idCompra);
    List<ComprasProducto> findByIdIdProductoOrderByProductoNombreAsc(int idProducto);

    @Modifying()
    @Query(value = "DELETE FROM compras_productos WHERE id_producto =:product",nativeQuery = true)
    @Transactional
    void removeCompraProducto(@Param("product") int productoId);

    @Modifying()
    @Query(value = "DELETE FROM productos WHERE id_producto =:product",nativeQuery = true)
    @Transactional
    void removeProducto(@Param("product")int productoId);



}
