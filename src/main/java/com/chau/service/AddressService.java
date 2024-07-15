package com.chau.service;

import com.chau.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressesByUserId(Long userId);

    void createAddress(Address address);

    void updateAddress(Address address);
}
