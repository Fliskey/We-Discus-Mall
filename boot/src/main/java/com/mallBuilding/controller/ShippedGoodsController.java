package com.mallBuilding.controller;


import com.mallBuilding.dao.ShippedGoodsDao;
import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.entity.ShippedGoods;
import com.mallBuilding.service.ShippedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    @Autowired
    private ShippedGoodsDao shippedGoodsDao;

    @PostMapping("/add")
    public boolean add(@RequestBody ShippedGoods shippedGoods){
        return this.shippedGoodsService.save(shippedGoods);
    }
    @GetMapping("/hasShipped/{id}")
    public boolean hasShipped(@PathVariable("id") Integer sId)
    {
        ShippedGoods s = this.shippedGoodsDao.hasShipped(sId);
        if(s == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

