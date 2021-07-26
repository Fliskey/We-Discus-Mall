package com.mallBuilding.controller;

import com.mallBuilding.dao.AddressDao;
import com.mallBuilding.dao.OmOrderDao;
import com.mallBuilding.entity.BuyShow;
import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.entity.UmUser;

import com.mallBuilding.service.OmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Controller;

import java.util.List;

@RestController
@RequestMapping("//buyShow")

public class BuyShowController {
    @Autowired
    private OmOrderService omOrderService;

    @Autowired
    private OmOrderDao omOrderDao;

    @Autowired
    private AddressDao addressDao;

    //牛逼的接口
    @GetMapping("/buylist/{id}/{hasPayed}")
    public List<BuyShow> listByBuyerId(@PathVariable("id") Integer buyerId,@PathVariable("hasPayed") Integer hasPayed)
    {
        return this.omOrderDao.queryByBuyerId(buyerId, hasPayed);
    }

    @GetMapping("/confirm/{orderId}")
    public boolean update(@PathVariable("orderId") String id)
    {
        Integer newId = Integer.valueOf(id);
        return this.omOrderDao.confirmBuyOrder(newId);
    }

    @GetMapping("/buylistSimple/{id}") //简单的接口
    public List<BuyShow> listByBuyerId(@PathVariable("id") Integer buyerId)
    {
        return this.omOrderDao.queryByBuyerIdSimple(buyerId);
    }

    @GetMapping("/queryAddId/{buyerName}/{phone}/{add}")
    public Integer queryAddressId(@PathVariable("buyerName") String buyerName, @PathVariable("phone") String phone, @PathVariable("add") String add)
    {
            return this.addressDao.queryAddId(buyerName,phone,add)[0];
    }



}
