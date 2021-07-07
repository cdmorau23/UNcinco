package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.domain.Rest;
import com.UN5.market.domain.service.AdService;
import com.UN5.market.persistence.crud.AdminCrudRepository;
import com.UN5.market.persistence.entity.Administrador;
import com.UN5.market.persistence.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class WebBuscadorController {

    @Autowired
    private com.UN5.market.domain.service.RestService RestService;

    @Autowired
    private AdService adService;

    @Autowired
    private AdminCrudRepository adminCrudRepository;

    private HttpServletRequest httpServletRequest;

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/carrito.html")
    public String gorestaurante(@ModelAttribute("correo")Admin usuario){
        usuario= adminMapper.toAdmin(adminCrudRepository.findByCorreo(usuario.getAdmincorreo()));
        return "redirect:/empleadorestaurantesBuscador.html/"+usuario.getAdminId();
    }

    @GetMapping ("/carrito.html")
    public String carrito(){
        return "carrito";
    }

    @GetMapping("/restaurantesBuscador.html/{AdminId}")
    public String restaurantesAdminBuscador(@PathVariable("AdminId") int adminId, Model model) {
        List<Rest> restaurantes = RestService.getByAdmin(adminId);
        Admin usuario = adService.getAdministrador(adminId);
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("usuario",usuario);
        return "restaurantesBuscador";
    }


    @ModelAttribute("correo")
    public Admin admin(){
        return new Admin();
    }

}
