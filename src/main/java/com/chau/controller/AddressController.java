package com.chau.controller;

import com.chau.entity.Address;
import com.chau.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 地址控制器类
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * 根据用户ID获取地址列表
     *
     * @param userId 用户ID
     * @return 用户的所有地址列表
     */
    @GetMapping("/user/{userId}")
    public List<Address> getAddressesByUserId(@PathVariable Long userId) {
        return addressService.getAddressesByUserId(userId);
    }

    /**
     * 创建新的地址
     *
     * @param address 地址对象
     * @return 成功创建消息
     */
    @PostMapping
    public String createAddress(@RequestBody Address address) {
        addressService.createAddress(address);
        return "地址创建成功";
    }

    /**
     * 更新地址信息
     *
     * @param addressId 地址ID
     * @param address 地址对象
     * @return 成功更新消息
     */
    @PutMapping("/{addressId}")
    public String updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        address.setId(addressId);
        addressService.updateAddress(address);
        return "地址更新成功";
    }
}
