package com.UN5.market.web.frontController;

import com.UN5.market.domain.Product;
import com.UN5.market.domain.Rest;
import com.UN5.market.persistence.jpa.AdminJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class WebInitialAndClientController {

    @Autowired
    private com.UN5.market.domain.service.ProductService ProductService;

    @Autowired
    private com.UN5.market.domain.service.RestService RestService;

    @GetMapping("/clienteMenu.html/{RestId}")
    public String clienteMenu(@PathVariable("RestId") int restId, Model model) {
        List<Product> productos= ProductService.getByRest(restId);
        Rest restaurante = RestService.getRest(restId);
        model.addAttribute("productos",productos);
        model.addAttribute("restaurante",restaurante);
        return "clienteMenu";
    }



    @GetMapping ("/principal.html")
    public String principal(){
        return "principal";
    }

    @GetMapping ("/login.html")
    public String login(){
        return "login";
    }



}
