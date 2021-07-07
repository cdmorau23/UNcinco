package com.UN5.market.domain.repository;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends UserDetailsService {
    Admin findByAdmincorreo(String correo);
    Admin getAdmin(int adminId);
    Admin save(Admin admin);
    void delete(int adminId);
    public Admin createAdmin(Admin admin)throws  Exception;

    void updateAdmin(int adminId, String name, String email, String password);


    /*metodos necesarios:
    -Obtener admin por su nombre y contraseña para el login
    -Insercion de admin dados su nombre, correo y contraseña para el register
    -Modificacion de admin dados su nombre, correo y contraseña para actualizar perfil dentro de mi perfil
    -eliminacion de admin por si se desea eliminar la cuenta
     */
}
