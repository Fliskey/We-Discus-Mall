package com.mallBuilding.controller;


import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.entity.ShippedGoods;
import com.mallBuilding.service.ShippedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2021-07-21
 */
@RestController
@RequestMapping("//shippedGoods")
public class ShippedGoodsController {

    @Autowired
    private ShippedGoodsService shippedGoodsService;

    @PostMapping("/add")
    public boolean add(@RequestBody ShippedGoods shippedGoods){
        return this.shippedGoodsService.save(shippedGoods);
    }
}

