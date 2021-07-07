package com.UN5.market.persistence.mapper;

import com.UN5.market.domain.Rest;
import com.UN5.market.persistence.entity.Restaurante;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RestMapper {
    @Mappings({
            @Mapping(source = "idRestaurante", target = "idrest"),
            @Mapping(source = "nombre", target = "noombrerest"),
            @Mapping(source = "slogan", target = "sloganrest"),
            @Mapping(source = "QRcode", target = "QRRest"),
            @Mapping(source = "totalMesas", target = "totalTablerest")


    })
    Rest toRest(Restaurante restaurante);
    List<Rest> toRests(List<Restaurante> restaurantes);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "administradores", ignore = true),
            @Mapping(target = "productos", ignore = true),
            @Mapping(target = "compras", ignore = true)

    })
    Restaurante toRestaurante(Rest rest);

}
