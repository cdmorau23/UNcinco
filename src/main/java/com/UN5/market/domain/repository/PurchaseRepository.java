package com.UN5.market.domain.repository;

import com.UN5.market.domain.Purchase;
import com.UN5.market.domain.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Purchase save(Purchase purchase);
    List<Purchase> getByRest(int restId);
    List<PurchaseItem> getByPurchase(int purchaseId);
    void updateState(int purchaseId);
    /*
    - dado cierto restaurante obtener la lista de pedidos asociados a este
    - insertar un nuevo pedido dando los datos correspondientes, uso en el menu de carrito del cliente
    - modificar pedido dados los datos correspondientes, uso en el menu de detalles pedido del administrador
    - eliminar pedido uso en el menu de detalles pedido del administrador
     */
}
