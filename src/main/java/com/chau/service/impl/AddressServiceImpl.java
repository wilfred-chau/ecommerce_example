package com.chau.service.impl;

import com.chau.entity.Address;
import com.chau.repository.AddressRepository;
import com.chau.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 地址服务实现类
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * 根据用户ID获取所有地址
     *
     * @param userId 用户ID
     * @return 用户的所有地址列表
     */
    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

    /**
     * 创建新的地址
     *
     * @param address 地址对象
     */
    @Override
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    /**
     * 更新地址信息
     *
     * @param address 地址对象
     */
    @Override
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }
}
