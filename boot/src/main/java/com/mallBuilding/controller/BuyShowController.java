package com.mallBuilding.controller;

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


    //牛逼的接口
    @GetMapping("/buylist/{id}/{hasPayed}/{hasShipped}/{hasConfirmed}")
    public List<BuyShow> listByBuyerId(@PathVariable("id") Integer buyerId,@PathVariable("hasPayed") Integer hasPayed,  @PathVariable("hasShipped") Integer hasShipped, @PathVariable("hasConfirmed") Integer hasConfirmed)
    {
        return this.omOrderDao.queryByBuyerId(buyerId, hasPayed,hasShipped, hasConfirmed);
    }

    @GetMapping("/confirm/{orderId}")
    public boolean update(@PathVariable("orderId") Integer id)
    {
        return this.omOrderDao.confirmBuyOrder(id);
    }

    @GetMapping("/buylistSimple/{id}") //简单的接口
    public List<BuyShow> listByBuyerId(@PathVariable("id") Integer buyerId)
    {
        return this.omOrderDao.queryByBuyerIdSimple(buyerId);
    }



}
