package com.UN5.market.web.backController;

import com.UN5.market.domain.Purchase;
import com.UN5.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Purchase save(@RequestBody Purchase purchase){
        return purchaseService.save(purchase);
    }

    @GetMapping("/deleteCompra/{id}")
    public String deleteCompra(Model model, @PathVariable(name="id") int compraId) {
        try {
            purchaseService.removeCompra(compraId);
        } catch (Exception e) {
            model.addAttribute("deleteError","The user product not be deleted.");
        }
        return "BorrasteUnaCompra";
    }
}
