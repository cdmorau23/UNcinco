package com.UN5.market.persistence.jpa;

import com.UN5.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductoJpaRepository extends JpaRepository <Producto, Integer> {

    @Modifying()
    @Query(value = "UPDATE productos SET nombre =:name, descripcion =:description, precio_venta =:price, cantidad_stock =:stock WHERE id_producto =:productoid", nativeQuery=true)
    @Transactional
    void updateProduct(@Param("name") String name, @Param("description") String description, @Param("price") double price, @Param("stock") int stock, @Param("productoid") int productId);

    @Modifying()
    @Query(value = "INSERT INTO productos (nombre,descripcion,precio_venta,cantidad_stock, restaurante_id_restaurante) VALUES (:name,:description,:price,:stock,:restId)", nativeQuery=true)
    @Transactional
    void insertProduct(@Param("name") String name, @Param("description") String description, @Param("price") double price, @Param("stock") int stock, @Param("restId") int restId);

    List<Producto> findByIdRestauranteOrderByNombreAsc(int idRestaurante);

    @Query(value = "DELETE FROM public.compras_productos " +
            "WHERE id_producto =:idproducto;\n " +
            "DELETE FROM public.productos " +
            "WHERE id_producto =:idproducto;", nativeQuery=true)
    @Transactional
    void removeProducto(@Param("idproducto") int productoId);


    @Query(value = "DELETE FROM public.productos " +
            "WHERE restaurante_id_restaurante =:idrestaurante;", nativeQuery=true)
    @Transactional
    void removeProductoByRestaurante(@Param("idrestaurante") int restauranteId);


}
