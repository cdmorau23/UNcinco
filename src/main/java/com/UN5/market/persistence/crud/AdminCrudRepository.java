package com.UN5.market.persistence.crud;

import com.UN5.market.domain.Admin;
import com.UN5.market.persistence.entity.Administrador;
import org.springframework.data.repository.CrudRepository;

public interface AdminCrudRepository extends CrudRepository<Administrador,Integer> {
    Administrador findByCorreo(String correo);
}
