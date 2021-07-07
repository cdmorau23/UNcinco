package com.UN5.market.persistence.crud;

import com.UN5.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdRestauranteOrderByNombreAsc(int idRestaurante);

    List<Producto> findById(int id);
    @Query(value = "DELETE FROM public.productos WHERE id_producto = ?;",nativeQuery = true)
    void removeProductoporfa(int productoId);


}
