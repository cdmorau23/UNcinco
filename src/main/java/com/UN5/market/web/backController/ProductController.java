package com.UN5.market.web.backController;

import com.UN5.market.domain.Product;
import com.UN5.market.domain.service.ComprasProductoService;
import com.UN5.market.domain.service.ProductService;
import com.UN5.market.persistence.crud.CompraProductoCrudRepository;
import com.UN5.market.persistence.crud.ProductoCrudRepository;
import com.UN5.market.persistence.jpa.ProductoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ComprasProductoService  comprasProductoService;
    @Autowired
    private ProductoCrudRepository pcrudr;
    @Autowired
    private ProductoJpaRepository pJPA;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/save")
    public ResponseEntity<Product>save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/deleteProduct/{id}")
    public String deletedProduct(Model model, @PathVariable(name="id") int productId) throws Exception {
            pcrudr.removeProductoporfa(productId);
        return productService.getProduct(productId).getName();
    }

    @GetMapping("/deleteCProduct/{id}")
    public String deleteCProduct(Model model, @PathVariable(name="id") int productId) throws Exception {
        comprasProductoService.removeproducts(productId);
        return productService.getProduct(productId).getName();
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
     */
}
