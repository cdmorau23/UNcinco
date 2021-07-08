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

import java.util.*;
import java.util.stream.Collectors;

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





    @GetMapping ("/estadisticas.html/{AdminId}/{RestId}")
    public String estadisticasAdmin(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = RestService.getRest(restId);
        List<String> FormaPago = purchaseService.getByRest(restId).stream().map(x ->x.getPaymentMethod()).collect(Collectors.toList());

        Map<String, Integer> result = new HashMap<>();

        for(String unique : new HashSet<>(FormaPago)) {
            result.put(unique, Collections.frequency(FormaPago, unique));
        }
        List<Integer> Cantidad = new ArrayList<Integer>(result.values());
        int size = Cantidad.size();

        int M1 = Cantidad.get(0);
        int M2 = Cantidad.get(1);


        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("M1",M1);
        model.addAttribute("M2",M2);

        return "estadisticas";
    }

    @GetMapping ("/estadisticas1.html/{AdminId}/{RestId}")
    public String estadisticas1Admin(@PathVariable("AdminId") int adminId,@PathVariable("RestId") int restId, Model model){
        Admin usuario = adService.getAdministrador(adminId);
        Rest restaurante = RestService.getRest(restId);
        List<String> NameList = ProductService.getByRest(restId).stream().map(x ->x.getName()).collect(Collectors.toList());

        List<Integer> StockList = ProductService.getByRest(restId).stream().map(x ->x.getStock()).collect(Collectors.toList());

        model.addAttribute("usuario",usuario);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("Nombre",NameList);
        model.addAttribute("Stock", StockList);
        return "estadisticas1";
    }



}
