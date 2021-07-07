package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Purchase;
import com.UN5.market.domain.PurchaseItem;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.domain.service.ProductService;
import com.UN5.market.domain.service.PurchaseService;
import com.UN5.market.domain.service.RestService;
import com.UN5.market.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class EmpleadoController {

    @Autowired
    private com.UN5.market.domain.service.ProductService productService;

    @Autowired
    private com.UN5.market.domain.service.RestService restService;

    @Autowired
    private AdService adService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/empleadorestaurantesBuscador.html/{AdminId}")
    public String restaurantesAdminBuscador(@PathVariable("AdminId") int adminId, Model model) {
        List<Rest> restaurantes = restService.getByAdmin(adminId);
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("usuario",usuario);
        return "empleadorestaurantesBuscador";
    }

    @GetMapping(value= "/empleadocuentaBuscador.html/{AdminId}")
    public String cuentaBuscador(@PathVariable("AdminId") int adminId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("usuario",usuario);
        return "empleadocuentaBuscador";
    }

    @PostMapping("/empleadocuentaBuscador.html/{AdminId}")
    public String actualizarCuentaBuscador(@ModelAttribute("usuario") Admin admin, @PathVariable("AdminId") int adminId){
        adService.updateAdmin(admin.getAdminId(),admin.getAdminname(),admin.getAdmincorreo(),admin.getAdmincontrasenia());
        String redirect= "redirect:/empleadocuentaBuscador.html/"+ adminId + "?success";
        return redirect;
    }

    @GetMapping ("/empleadocuenta.html/{AdminId}/{RestId}")
    public String cuentaAdmin(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante= restService.getRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante",restaurante);
        return "empleadocuenta";
    }

    @PostMapping("/empleadocuenta.html/{AdminId}/{RestId}")
    public String actualizarCuenta(@ModelAttribute("usuario") Admin admin, @PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId){
        adService.updateAdmin(admin.getAdminId(),admin.getAdminname(),admin.getAdmincorreo(),admin.getAdmincontrasenia());
        String redirect= "redirect:/empleadocuenta.html/"+ adminId + "/" + restId + "?success";
        return redirect;
    }

    @ModelAttribute("usuario")
    public Admin admin(){
        return new Admin();
    }

    @GetMapping("/empleadolocalDatosBuscador.html/{AdminId}/{RestId}")
    public String localDatosAdminBuscador(@PathVariable("RestId") int restId, @PathVariable("AdminId") int adminId, Model model) {
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("restaurante", restaurante);
        return "empleadolocalDatosBuscador";
    }

    @GetMapping("/empleadolocalDatos.html/{AdminId}/{RestId}")
    public String localDatosAdmin(@PathVariable("RestId") int restId, @PathVariable("AdminId") int adminId, Model model) {
        Rest restaurante = restService.getRest(restId);
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("usuario", usuario);
        return "empleadolocalDatos";
    }

    @ModelAttribute("restaurante")
    public Rest rest() {
        return new Rest();
    }

    @GetMapping("/empleadopedidos.html/{AdminId}/{RestId}")
    public String pedidos(@PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        List<Purchase> pedidos= purchaseService.getByRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("pedidos",pedidos);
        return "empleadopedidos";
    }

    @GetMapping ("/empleadopedidoDetalles.html/{AdminId}/{RestId}/{PurchaseId}")
    public String pedidoDetallesEmpleado(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId ,@PathVariable("PurchaseId") int purchaseId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        List<PurchaseItem> itemsPedido = purchaseService.getByPurchase(purchaseId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("itemsPedido",itemsPedido);
        model.addAttribute("idCompra",purchaseId);
        return "empleadopedidoDetalles";
    }

    @PostMapping("/empleadopedidoDetalles.html/{AdminId}/{RestId}/{PurchaseId}")
    public String actualizarEstado(@ModelAttribute("pedido") Compra compra, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId, @PathVariable("PurchaseId") int purchaseId){
        purchaseService.updateState(purchaseId);
        String redirect= "redirect:/empleadopedidoDetalles.html/"+ adminId + "/" + restId + "/" + purchaseId + "?success";
        return redirect;
    }

    @ModelAttribute("pedido")
    public Compra compra(){
        return new Compra();
    }


}
