package com.UN5.market.web.frontController;

import com.UN5.market.domain.*;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.domain.service.PurchaseService;
import com.UN5.market.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class WebPedidoController {

    @Autowired
    private com.UN5.market.domain.service.RestService restService;

    @Autowired
    private AdService adService;

    @Autowired
    private com.UN5.market.domain.service.ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/pedidos.html/{AdminId}/{RestId}")
    public String pedidos(@PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        List<Purchase> pedidos= purchaseService.getByRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("pedidos",pedidos);
        return "pedidos";
    }

    @GetMapping ("/pedidoDetalles.html/{AdminId}/{RestId}/{PurchaseId}")
    public String pedidoDetallesEmpleado(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId ,@PathVariable("PurchaseId") int purchaseId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        List<PurchaseItem> itemsPedido = purchaseService.getByPurchase(purchaseId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("itemsPedido",itemsPedido);
        model.addAttribute("idCompra",purchaseId);
        return "pedidoDetalles";
    }

    @PostMapping("/pedidoDetalles.html/{AdminId}/{RestId}/{PurchaseId}")
    public String actualizarEstado(@ModelAttribute("pedido") Compra compra, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId, @PathVariable("PurchaseId") int purchaseId){
        purchaseService.updateState(purchaseId);
        String redirect= "redirect:/pedidoDetalles.html/"+ adminId + "/" + restId + "/" + purchaseId + "?success";
        return redirect;
    }

    @ModelAttribute("pedido")
    public Compra compra(){
        return new Compra();
    }
}
