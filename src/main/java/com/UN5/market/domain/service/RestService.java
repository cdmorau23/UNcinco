package com.UN5.market.domain.service;

import com.UN5.market.domain.Rest;
import com.UN5.market.domain.repository.ProductRepository;
import com.UN5.market.domain.repository.RestRepository;
import com.UN5.market.persistence.crud.CompraCrudRepository;
import com.UN5.market.persistence.crud.RestauranteCrudRepository;
import com.UN5.market.persistence.entity.Compra;
import com.UN5.market.persistence.entity.Producto;
import com.UN5.market.persistence.jpa.ProductoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestService {
    @Autowired
    private RestRepository restRepository;
    private ProductoJpaRepository jpaProducto;
    private ProductRepository productRepository;
    private CompraCrudRepository compraCrudRepository;
    private RestauranteCrudRepository restCrudRepository;

    public Rest getRest(int restId){
        return restRepository.getRest(restId);
    }


    public Rest save(Rest rest){
        return restRepository.save(rest);
    }

    public List<Rest> getByAdmin(int adminId) {return restRepository.adminRests(adminId);}

    public void updateRest(String nombre, String slogan, int tableTotal, int restId){
        restRepository.updateRest(nombre,slogan,tableTotal,restId);
    }

    public void removeRestaurant(int restaurantId) throws Exception {

        List<Producto> ProductosDeRestaurante = jpaProducto.findByIdRestauranteOrderByNombreAsc(restaurantId);
        for (Producto a :ProductosDeRestaurante) {
            jpaProducto.removeProducto(a.getIdProducto());
        }
        List<Compra> ComprasRestaurante = compraCrudRepository.findByIdRestauranteOrderByFechaAsc(restaurantId);
        for (Compra a :ComprasRestaurante) {
            jpaProducto.removeProducto(a.getIdCompra());
        }
        restCrudRepository.deleteById(restaurantId);

    }
}
