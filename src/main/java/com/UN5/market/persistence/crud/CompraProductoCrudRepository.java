package com.UN5.market.persistence.crud;

import com.UN5.market.persistence.entity.ComprasProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CompraProductoCrudRepository extends CrudRepository<ComprasProducto, Integer> {

    List<ComprasProducto> findByIdIdCompraOrderByProductoNombreAsc(int idCompra);
    List<ComprasProducto> findByIdIdProductoOrderByProductoNombreAsc(int idProducto);

    @Query(value = "DELETE FROM public.compras_productos WHERE id_producto = ?;",nativeQuery = true)
    void removeCompraProducto(int productoId);

    @Query(value = "DELETE FROM public.productos WHERE id_producto = ?;",nativeQuery = true)
    void removeProducto(int productoId);



}
