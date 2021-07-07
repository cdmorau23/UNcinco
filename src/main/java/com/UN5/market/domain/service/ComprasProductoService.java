package com.UN5.market.domain.service;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.repository.AdRepository;
import com.UN5.market.persistence.crud.CompraProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprasProductoService {
    @Autowired
    private CompraProductoCrudRepository CrudCompraProducto;



    public void  removeproducts (int productoId){
        CrudCompraProducto.removeCompraProducto(productoId);
        CrudCompraProducto.removeProducto(productoId);
    }
    public void  removeproducts2 (int productoId){ CrudCompraProducto.removeProducto(productoId);}

}
