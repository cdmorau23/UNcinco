package com.UN5.market.domain.service;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;


    public Admin getAdministrador(int adminId){
        return adRepository.getAdmin(adminId);
    }

    public Admin save(Admin admin){
        return adRepository.save(admin);
    }

    public void updateAdmin(int adminId,String name, String email,String password){adRepository.updateAdmin(adminId,name,email,password);}

    /*public boolean delete(int ad_ID){
        return getAdministrador(ad_ID).map(admin -> {
            adRepository.delete(ad_ID);
            return true;
        } ).orElse(false);    }
    */
}
