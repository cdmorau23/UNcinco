package com.UN5.market.web.backController;


import com.UN5.market.domain.Admin;
import com.UN5.market.domain.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Ad")
public class AdController {
    @Autowired
    private AdService adService;
    @GetMapping("/{Ad}")
    public Admin getAdmin(@PathVariable("Ad") int AdID){
        return adService.getAdministrador(AdID);
    }

    /*
    @PostMapping("/save")
    public ResponseEntity<Admin> save(@RequestBody Admin admin){
        return new ResponseEntity<>(adService.save(admin), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int adID){
        if( adService.delete(adID)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

     */


}
