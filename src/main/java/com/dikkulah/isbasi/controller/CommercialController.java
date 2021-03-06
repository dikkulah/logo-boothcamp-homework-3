package com.dikkulah.isbasi.controller;

import com.dikkulah.isbasi.model.Commercial;
import com.dikkulah.isbasi.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/commercial")
public class CommercialController {

    @Autowired
    private CommercialService commercialService;
    // commercial kayıt etme
    @PostMapping
    public Commercial createCommercial(@RequestBody Commercial request) {
        return commercialService.createCommercial(request);
    }
    //Hepsini listele
    @GetMapping
    public List<Commercial> getAllCommercials() {
        return commercialService.getAllCommercials();
    }

    //id ile arama
    @GetMapping(value = "/{id}")
    public Commercial getCommercialById(@PathVariable Long id) {
        return commercialService.getCommercialById(id);
    }

    //id ile bulup gönderilen body ile update etme
    @PutMapping("/{id}")
    public Commercial updateCommercial(@PathVariable Long id, @RequestBody Commercial request) {
        return commercialService.updateCommercial(id, request);
    }

    // id ile silme
    @DeleteMapping(value = "/{id}")
    public Boolean deleteCommercialById(@PathVariable Long id) {
        return commercialService.deleteCommercialById(id);
    }

    // aktif- pasif listeleme
    @GetMapping(value = "state/{isActive}")
    public List<Commercial> getCommercialByIsActive(@PathVariable String isActive) {
        return commercialService.getCommercialByIsActive(Boolean.valueOf(isActive));
    }


}
