package com.iteco.basetask.services;

import com.iteco.basetask.entities.Address;
import com.iteco.basetask.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {this.addressRepository = addressRepository;}

    public boolean save(Address address) {
         addressRepository.save(address);
         return true;
    }



    public List<Address> findAll() {
        return (List<Address>)addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).get();
    }

    public void deleteById(Long id) {  addressRepository.deleteById(id);   }

    public Address getAddressByEmail(String email) {
        return addressRepository.findAddressByEmail(email);    }

    public boolean exists(String email) { return false;   }
}
