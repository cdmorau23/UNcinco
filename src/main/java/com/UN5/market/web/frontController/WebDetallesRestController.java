package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WebDetallesRestController {

    @Autowired
    private com.UN5.market.domain.service.RestService restService;

    @Autowired
    private AdService adService;

    @GetMapping("/localDatosBuscador.html/{AdminId}/{RestId}")
    public String localDatosAdminBuscador(@PathVariable("RestId") int restId, @PathVariable("AdminId") int adminId, Model model) {
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = restService.getRest(restId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("restaurante", restaurante);
        return "localDatosBuscador";
    }

    @PostMapping("/localDatosBuscador.html/{AdminId}/{RestId}")
    public String actualizarLocalBuscador(@ModelAttribute("restaurante") Rest rest, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId) {
        restService.updateRest(rest.getNoombrerest(),rest.getSloganrest(),rest.getTotalTablerest(),rest.getIdrest());
        String redirect = "redirect:/localDatosBuscador.html/" + adminId + "/" + restId + "?success";
        return redirect;
    }

    @GetMapping("/localDatos.html/{AdminId}/{RestId}")
    public String localDatosAdmin(@PathVariable("RestId") int restId, @PathVariable("AdminId") int adminId, Model model) {
        Rest restaurante = restService.getRest(restId);
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("usuario", usuario);
        return "localDatos";
    }

    @PostMapping("/localDatos.html/{AdminId}/{RestId}")
    public String actualizarLocal(@ModelAttribute("restaurante") Rest rest, @PathVariable("AdminId") int adminId, @PathVariable("RestId") int restId) {
        restService.updateRest(rest.getNoombrerest(),rest.getSloganrest(),rest.getTotalTablerest(),rest.getIdrest());
        String redirect = "redirect:/localDatos.html/" + adminId + "/" + restId + "?success";
        return redirect;
    }

    @ModelAttribute("restaurante")
    public Rest rest() {
        return new Rest();
    }
}
