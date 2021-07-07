package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Product;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class WebProductoController {

    @Autowired
    private com.UN5.market.domain.service.RestService restService;

    @Autowired
    private AdService adService;

    @Autowired
    private com.UN5.market.domain.service.ProductService productService;

    @GetMapping("/productos.html/{AdminId}/{RestId}")
    public String productosAdmin(@PathVariable("RestId") int restId,@PathVariable("AdminId") int adminId, Model model){
        List<Product> productos= productService.getByRest(restId);
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        model.addAttribute("productos",productos);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);

        return "productos";
    }

    @GetMapping("/productoDetalles.html/{AdminId}/{RestId}/{ProductId}")
    public String productoDetallesAdmin(@PathVariable("RestId") int restId, @PathVariable("AdminId") int adminId, @PathVariable("ProductId") int productId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        Product producto = productService.getProduct(productId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("producto",producto);
        return "productoDetalles";
    }

    @PostMapping("/productoDetalles.html/{AdminId}/{RestId}/{ProductId}")
    public String actualizarProducto(@ModelAttribute("producto") Product product, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId, @PathVariable("ProductId") int productId){
        productService.updateProduct(product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getProductId());
        String redirect= "redirect:/productoDetalles.html/"+ adminId + "/" + restId + "/" + productId + "?success";
        return redirect;
    }

    @GetMapping ("/productoAgregar.html/{AdminId}/{RestId}")
    public String productoAgregarAdmin(@PathVariable("RestId") int restId,@PathVariable("AdminId") int adminId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        return "productoAgregar";
    }

    @PostMapping("/productoAgregar.html/{AdminId}/{RestId}")
    public String crearProducto(@ModelAttribute("producto") Product product, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId){
        productService.insertProduct(product.getName(),product.getDescription(),product.getPrice(),product.getStock(),restId);
        String redirect= "redirect:/productoAgregar.html/"+ adminId + "/" + restId + "?success";
        return redirect;
    }


    @ModelAttribute("producto")
    public Product product(){
        return new Product();
    }

}
