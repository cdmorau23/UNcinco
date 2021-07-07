package com.UN5.market.web.backController;

import com.UN5.market.domain.Product;
import com.UN5.market.domain.service.ProductService;
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
    public String deleteProduct(Model model, @PathVariable(name="id") int productId) {
        try {
            productService.removeProduct(productId);
        } catch (Exception e) {
            model.addAttribute("deleteError","The user product not be deleted.");
        }
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
