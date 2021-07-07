package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.persistence.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WebRestauranteController {
    @Autowired
    private RestauranteRepository restRepository;

    @Autowired
    private AdService adService;

    @GetMapping ("/restauranteAgregarBuscador.html/{AdminId}")
    public String restauranteAgregarAdminBuscador(@PathVariable("AdminId") int adminId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("usuario",usuario);
        return "restauranteAgregarBuscador";
    }

    @PostMapping("/restauranteAgregarBuscador.html/{AdminId}")
    public  String registerRestaurante(@ModelAttribute("rest") Rest restaurante, @PathVariable("AdminId") int adminId){

        restRepository.save(restaurante);
        return "restauranteAgregarBuscador";
    }
    @ModelAttribute("rest")
    public Rest restaurante() {
        return new Rest();
    }
}

