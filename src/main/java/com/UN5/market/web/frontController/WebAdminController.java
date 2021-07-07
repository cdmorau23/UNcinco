package com.UN5.market.web.frontController;

import com.UN5.market.domain.*;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.domain.service.ProductService;
import com.UN5.market.domain.service.PurchaseService;
import com.UN5.market.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class WebAdminController {

    @Autowired
    private ProductService ProductService;

    @Autowired
    private RestService RestService;

    @Autowired
    private AdService adService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping ("/pedidos.html/{AdminId}/{RestId}")
    public String pedidos(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = RestService.getRest(restId);
        List<Purchase> pedidos= purchaseService.getByRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("pedidos",pedidos);
        return "pedidos";
    }

    @GetMapping ("/pedidoDetalles.html/{AdminId}/{RestId}/{PurchaseId}")
    public String pedidoDetallesEmpleado(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId ,@PathVariable("PurchaseId") int purchaseId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = RestService.getRest(restId);
        List<PurchaseItem> itemsPedido = purchaseService.getByPurchase(purchaseId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("itemsPedido",itemsPedido);
        return "pedidoDetalles";
    }

    @GetMapping ("/estadisticas.html/{AdminId}/{RestId}")
    public String estadisticasAdmin(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = RestService.getRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        return "estadisticas";
    }



}
