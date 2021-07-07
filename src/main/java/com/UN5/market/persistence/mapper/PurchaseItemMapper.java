package com.UN5.market.persistence.mapper;

import com.UN5.market.domain.PurchaseItem;
import com.UN5.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "producto", target = "product"),
            @Mapping(source = "id.idCompra", target = "purchaseId"),
            @Mapping(source = "compra", target = "purchase"),
            @Mapping(source = "cantidad", target = "quantity"),





    })




    PurchaseItem toPurchaseItem(ComprasProducto producto);
    List<PurchaseItem> toPurchaseItems(List<ComprasProducto> compras);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);


}
