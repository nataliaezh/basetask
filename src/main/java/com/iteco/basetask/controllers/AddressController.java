package com.iteco.basetask.controllers;

import com.iteco.basetask.entities.Address;
import com.iteco.basetask.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public List<Address> getAllProducts() {
        return addressService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        addressService.deleteById(id);
    }

}
