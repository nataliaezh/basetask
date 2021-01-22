package com.iteco.basetask.services;

import com.iteco.basetask.entities.Address;

import java.util.List;

public interface AddressService {
    boolean save(Address address);
    List<Address> findAll();
    Address findById(Long id);
    void deleteById(Long id);
    Address getAddressByEmail(String email);

    boolean exists(String email);
}
