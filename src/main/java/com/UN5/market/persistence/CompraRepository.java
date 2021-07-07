package com.UN5.market.persistence;

import com.UN5.market.domain.Purchase;
import com.UN5.market.domain.PurchaseItem;
import com.UN5.market.domain.repository.PurchaseRepository;
import com.UN5.market.persistence.crud.CompraCrudRepository;
import com.UN5.market.persistence.crud.CompraProductoCrudRepository;
import com.UN5.market.persistence.entity.Compra;
import com.UN5.market.persistence.jpa.CompraJpaRepository;
import com.UN5.market.persistence.mapper.PurchaseItemMapper;
import com.UN5.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private CompraJpaRepository compraJpaRepository;
    @Autowired
    private CompraProductoCrudRepository compraProductoCrudRepository;
    @Autowired
    private PurchaseMapper mapper;
    @Autowired
    private PurchaseItemMapper mapperitem;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));


        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public List<Purchase> getByRest(int restId) {
        return mapper.toPurchases(compraCrudRepository.findByIdRestauranteOrderByFechaAsc(restId));
    }

    @Override
    public List<PurchaseItem> getByPurchase(int purchaseId) {
        return mapperitem.toPurchaseItems(compraProductoCrudRepository.findByIdIdCompraOrderByProductoNombreAsc(purchaseId));
    }


}