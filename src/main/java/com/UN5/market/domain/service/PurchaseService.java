package com.UN5.market.domain.service;

import com.UN5.market.domain.Purchase;
import com.UN5.market.domain.PurchaseItem;
import com.UN5.market.domain.repository.PurchaseRepository;
import com.UN5.market.persistence.crud.CompraCrudRepository;
import com.UN5.market.persistence.jpa.CompraJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    private CompraJpaRepository compraJpaRepository;
     public List<Purchase> getAll(){
         return purchaseRepository.getAll();
     }
     public Purchase save(Purchase purchase){
         return purchaseRepository.save(purchase);
     }
     public List<Purchase> getByRest(int restId) {return purchaseRepository.getByRest(restId);}
     public List<PurchaseItem> getByPurchase(int purchaseId) {return purchaseRepository.getByPurchase(purchaseId);}

    public void removeCompra(int compraId) throws Exception {

        compraJpaRepository.deleteById(compraId);

    }

    public void updateState(int compraId){purchaseRepository.updateState(compraId);}

}
