package com.UN5.market.persistence;

import com.UN5.market.domain.Product;
import com.UN5.market.domain.repository.ProductRepository;
import com.UN5.market.domain.service.ComprasProductoService;
import com.UN5.market.persistence.crud.ProductoCrudRepository;
import com.UN5.market.persistence.entity.ComprasProductoPK;
import com.UN5.market.persistence.jpa.ProductoJpaRepository;
import com.UN5.market.persistence.mapper.ProductMapper;
import com.UN5.market.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoRepository implements ProductRepository {


    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductoJpaRepository productoJpaRepository;

    @Autowired
    private ComprasProductoService productoCRUDRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Product getProduct(int productId) {
        return mapper.toProduct(productoJpaRepository.getOne(productId));
    }


    public List<Product> getByRest(int restId) {
        List<Producto> productos = productoCrudRepository.findByIdRestauranteOrderByNombreAsc(restId);
        return mapper.toProducts(productos);
    }


    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCRUDRepository.removeproducts(productId);
        productoCrudRepository.removeProductoporfa(productId);
    }

    @Override
    public void updateProduct(String name, String description, double price, int stock, int productId) {
        productoJpaRepository.updateProduct(name,description,price,stock,productId);
    }

    @Override
    public void insertProduct(String name, String description, double price, int stock, int restId) {
        productoJpaRepository.insertProduct(name,description,price,stock,restId);
    }
}
