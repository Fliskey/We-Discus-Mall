package com.mallBuilding.controller;


import com.mallBuilding.dao.UserAddressDao;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.entity.UserAddress;
import com.mallBuilding.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2021-07-16
 */
@RestController
@RequestMapping("//userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserAddressDao userAddressDao;

    @GetMapping("/list")
    public List<UserAddress> list()
    {
        return this.userAddressService.list();
    }

    @GetMapping("/getAddress/{id}")
    public UserAddress queryById(@PathVariable("id") Integer id)
    {
        return this.userAddressService.getById(id);

    }

    @PostMapping("/add")
    public boolean add(@RequestBody UserAddress userAddress){
        return this.userAddressService.save(userAddress);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody UserAddress userAddress)
    {
        return this.userAddressService.updateById(userAddress);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id )
    {
        return this.userAddressService.removeById(id);
    }

}

