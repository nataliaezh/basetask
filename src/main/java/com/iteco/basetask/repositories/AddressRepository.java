package com.iteco.basetask.repositories;

import com.iteco.basetask.entities.Address;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByEmail(String email);

     List<Address> addresses = new ArrayList<>();

     private Address findByEmail(String email) {
        Address address = addresses.stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return address;
    }

    @Cacheable(cacheNames="address")
        default Address findCacheByEmail(String email) {
        final Address address = findByEmail(email);
        return address;
    }
}

