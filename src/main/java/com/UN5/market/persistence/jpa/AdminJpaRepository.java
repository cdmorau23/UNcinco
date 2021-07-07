package com.UN5.market.persistence.jpa;

import com.UN5.market.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

@Repository
public interface AdminJpaRepository extends JpaRepository<Administrador, Integer> {
    @Modifying()
    @Query(value = "UPDATE admin SET nombre =:name, correo =:email, contraseña=:password  WHERE id_admin =:adminid", nativeQuery=true)
    @Transactional
    void updateAdmin(@Param("name") String name, @Param("email") String email, @Param("password") String password, @Param("adminid") int adminId);
}
