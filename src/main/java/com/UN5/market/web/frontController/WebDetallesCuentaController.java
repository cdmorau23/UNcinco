package com.UN5.market.web.frontController;


import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WebDetallesCuentaController {

    @Autowired
    private com.UN5.market.domain.service.RestService RestService;

    @Autowired
    private AdService adService;

    @GetMapping(value= "/cuentaBuscador.html/{AdminId}")
    public String cuentaBuscador(@PathVariable("AdminId") int adminId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("usuario",usuario);
        return "cuentaBuscador";
    }

    @PostMapping("/cuentaBuscador.html/{AdminId}")
    public String actualizarCuentaBuscador(@ModelAttribute("usuario") Admin admin, @PathVariable("AdminId") int adminId){
        adService.updateAdmin(admin.getAdminId(),admin.getAdminname(),admin.getAdmincorreo(),admin.getAdmincontrasenia());
        String redirect= "redirect:/cuentaBuscador.html/"+ adminId + "?success";
        return redirect;
    }

    @GetMapping ("/cuenta.html/{AdminId}/{RestId}")
    public String cuentaAdmin(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante= RestService.getRest(restId);
        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante",restaurante);
        return "cuenta";
    }

    @PostMapping("/cuenta.html/{AdminId}/{RestId}")
    public String actualizarCuenta(@ModelAttribute("usuario") Admin admin, @PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId){
        adService.updateAdmin(admin.getAdminId(),admin.getAdminname(),admin.getAdmincorreo(),admin.getAdmincontrasenia());
        String redirect= "redirect:/cuenta.html/"+ adminId + "/" + restId + "?success";
        return redirect;
    }

    @ModelAttribute("usuario")
    public Admin admin(){
        return new Admin();
    }


}
