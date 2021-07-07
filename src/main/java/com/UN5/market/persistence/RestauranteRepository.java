package com.UN5.market.persistence;

import com.UN5.market.domain.Rest;
import com.UN5.market.domain.repository.RestRepository;
import com.UN5.market.persistence.crud.RestauranteCrudRepository;
import com.UN5.market.persistence.entity.Restaurante;
import com.UN5.market.persistence.jpa.AdminJpaRepository;
import com.UN5.market.persistence.jpa.RestauranteJpaRepository;
import com.UN5.market.persistence.mapper.RestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RestauranteRepository implements RestRepository {
    @Autowired
    private RestauranteCrudRepository restauranteCrudRepository;
    @Autowired
    private RestauranteJpaRepository restauranteJpaRepository;
    @Autowired
    private AdminJpaRepository adminJpaRepository;
    @Autowired
    private RestMapper mapper;

    @Override
    public Rest getRest(int restId){
        return mapper.toRest(restauranteJpaRepository.getOne(restId));
    }

    @Override
    public Rest save(Rest rest){
        Restaurante restaurante = mapper.toRestaurante(rest);
        return mapper.toRest(restauranteCrudRepository.save(restaurante));
    }
    @Override
    public void delete(int restId){
        restauranteCrudRepository.deleteById(restId);
    }

    @Override
    public List<Rest> adminRests(int adminId) {
        List<Restaurante> restaurantes= adminJpaRepository.getOne(adminId).getRestaurantes();
        return mapper.toRests(restaurantes);
    }

    @Override
    public void updateRest(String name, String slogan, int tableTotal, int restId) {
        restauranteJpaRepository.updateRest(name,slogan,tableTotal,restId);
    }
}
