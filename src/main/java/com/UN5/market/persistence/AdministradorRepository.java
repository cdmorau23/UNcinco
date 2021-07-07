package com.UN5.market.persistence;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.repository.AdRepository;
import com.UN5.market.persistence.crud.AdminCrudRepository;
import com.UN5.market.persistence.entity.Administrador;
import com.UN5.market.persistence.entity.Restaurante;
import com.UN5.market.persistence.jpa.AdminJpaRepository;
import com.UN5.market.persistence.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class AdministradorRepository implements AdRepository {

    @Autowired
    private AdminCrudRepository adminCrudRepository;
    @Autowired
    private AdminJpaRepository adminJpaRepository;
    @Autowired
    private AdminMapper mapper;


    @Override
    public Admin findByAdmincorreo(String correo) {
        Admin admin = mapper.toAdmin(adminCrudRepository.findByCorreo(correo));
        return  admin;
    }

    @Override
    public Admin getAdmin(int adminId){
        return mapper.toAdmin(adminJpaRepository.getOne(adminId));
    }

    @Override
    public Admin save(Admin admin){
        Administrador administrador = mapper.toAdministrador(admin);
        return mapper.toAdmin(adminCrudRepository.save(administrador));

    }
    @Override
    public void delete(int adminId){
        adminCrudRepository.deleteById(adminId);
    }

    @Override
    public Admin createAdmin(Admin admin) throws Exception {
        if (checkPasswordValid(admin)) {
            Administrador administrador = mapper.toAdministrador(admin);
            admin= mapper.toAdmin(adminCrudRepository.save(administrador));
        }
        return admin;
    }

    @Override
    public void updateAdmin(int adminId, String name, String email, String password) {
        adminJpaRepository.updateAdmin(name,email,password,adminId);
    }

    private boolean checkPasswordValid(Admin user) throws Exception {
        if ( !user.getAdmincontrasenia().equals(user.getAdminconfcontrasenia())) {
            throw new Exception("Las contraseñas no coinciden.");
        }
        return true;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=mapper.toAdmin(adminCrudRepository.findByCorreo(username));
        if(admin==null) throw new UsernameNotFoundException("Usuario y/o contraseña incorrecta");
        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(admin.getAdminrole());
        grantList.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(admin.getAdmincorreo(),passwordEncoder.encode(admin.getAdmincontrasenia()),grantList);
    }
}
