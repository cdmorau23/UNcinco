package com.UN5.market.web.frontController;

import com.UN5.market.domain.Admin;
import com.UN5.market.persistence.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebRegisterController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AdministradorRepository administradorRepository;

    @GetMapping("/register.html")
    public String register(){
        return "register";
    }

    @PostMapping("/register.html")
    public  String registerUserAccount(@ModelAttribute("admin") Admin admin, ModelMap model){
        try {
            Admin admon= new Admin(admin.getAdminId(), admin.getAdminname(),admin.getAdmincorreo()
                    ,passwordEncoder.encode(admin.getAdmincontrasenia()),admin.getAdminconfcontrasenia(),admin.getAdminrole());
            administradorRepository.createAdmin(admin);
            model.addAttribute("admin", new Admin());
            return "redirect:/register.html?success";
        } catch (Exception e) {
            model.addAttribute("admin", admin);
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("fromtab", "active");
            return "/register.html";
        }

    }
    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }
}
