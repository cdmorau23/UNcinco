package com.UN5.market.domain.service;

import com.UN5.market.domain.Product;
import com.UN5.market.domain.repository.ProductRepository;
import com.UN5.market.persistence.crud.CompraProductoCrudRepository;
import com.UN5.market.persistence.entity.ComprasProducto;
import com.UN5.market.persistence.jpa.ProductoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private ProductoJpaRepository jpaProducto;
    private CompraProductoCrudRepository CrudCompraProducto;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public List<Product> getByRest(int restId){
        return productRepository.getByRest(restId);
    }

    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(String name, String description, double price, int stock, int productId){ productRepository.updateProduct(name,description,price,stock,productId);}

    public void insertProduct(String name, String description, double price, int stock, int restId){productRepository.insertProduct(name,description,price,stock,restId);}


    public void deleteProduct(int productId) throws Exception {

            Product producto = productRepository.getProduct(productId);
            productRepository.delete(productId);

    }
    public void removeProduct(int productId) throws Exception {

        List<ComprasProducto> muchasCompras = CrudCompraProducto.findByIdIdProductoOrderByProductoNombreAsc(productId);

        for (ComprasProducto a : muchasCompras) {
            CrudCompraProducto.delete(a);
        }

        jpaProducto.deleteById(productId);

    }

    /*public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
     */
}
