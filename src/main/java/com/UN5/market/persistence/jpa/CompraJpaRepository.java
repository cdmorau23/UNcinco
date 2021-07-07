package com.UN5.market.persistence.jpa;

import com.UN5.market.persistence.entity.Compra;
import com.UN5.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CompraJpaRepository extends JpaRepository<Compra,Integer> {



    @Query(value = "DELETE FROM public.compras_productos " +
            "WHERE id_compra =:idcompra;\n " +
            "DELETE FROM public.compras " +
            "WHERE id_compra =:idcompra;", nativeQuery=true)
    @Transactional
    void removeCompra(@Param("idcompra") int CompraId);




}
