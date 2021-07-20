package com.mallBuilding.controller;


import com.mallBuilding.dao.OmOrderDao;
import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.service.OmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.net.SocketOption;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2021-07-18
 */
@RestController
@RequestMapping("//omOrder")
public class OmOrderController {
    @Autowired
    private OmOrderService omOrderService;

    @Autowired
    private OmOrderDao omOrderDao;

    @GetMapping("/list/{id}")
    public List<GoodsAndBuyer> listBySellerId(@PathVariable("id") Integer sellerId)
    {
        return this.omOrderDao.queryBySellerId(sellerId);
    }

    @PostMapping("/add")
    public Integer add(@RequestBody OmOrder omOrder){


        this.omOrderService.save(omOrder);

        return omOrder.getId();
    }

    @PutMapping("/updatePay/{id}")
    public boolean updatePay(@PathVariable("id") Integer id)
    {
        return this.omOrderDao.updatePay(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id)
    {
        return this.omOrderService.removeById(id);
    }

}

