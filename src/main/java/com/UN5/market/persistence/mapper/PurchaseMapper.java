package com.UN5.market.persistence.mapper;

import com.UN5.market.domain.Purchase;
import com.UN5.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses ={PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "idCompra",target = "purchaseId"),
            @Mapping(source = "fecha",target = "date"),
            @Mapping(source = "medioPago",target = "paymentMethod"),
            @Mapping(source = "estado",target = "state"),
            @Mapping(source = "mesa", target = "table"),
            @Mapping(source = "restaurante", target = "rest"),
            @Mapping(source = "idRestaurante", target = "restId")



    })

    Purchase toPurchase(Compra compra);
    List<Purchase>toPurchases(List<Compra> compras);
    @InheritInverseConfiguration
    Compra toCompra(Purchase purchase);
}
