package com.mallBuilding.controller;

import com.mallBuilding.dao.GoodsDao;
import com.mallBuilding.dao.OmOrderDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.entity.UmUser;

import com.mallBuilding.service.OmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.SocketOption;
import java.util.ArrayList;
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

    @Autowired
    private GoodsDao goodsDao;

    @GetMapping("/list/{id}")
    public List<GoodsAndBuyer> listBySellerId(@PathVariable("id") Integer sellerId)
    {
        return this.omOrderDao.queryBySellerId(sellerId);
    }

    @GetMapping("/order/{id}")
    public OmOrder omOrderById(@PathVariable("id") Integer id)
    {
        return this.omOrderService.getById(id);
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

    @GetMapping("/delete/{gid}/{bid}")
    public boolean queryWantBy(@PathVariable("gid") Integer gid,@PathVariable("bid") Integer bid){

        return this.omOrderDao.deleteByUserId(gid,bid);
        //集合
    }

    @PutMapping("/update")
    public boolean update (@RequestBody OmOrder omOrder) {
        return this.omOrderService.updateById(omOrder);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id )
    {
        Integer newId = Integer.valueOf(id);
        return this.omOrderService.removeById(newId);
    }

    @GetMapping("/findQuantity/{id}")
    public BigDecimal findQuantity(@PathVariable("id") Integer id)
    {
        OmOrder o = this.omOrderService.getById(id);
        return o.getActualPayAmount();
    }

    @GetMapping("/findList/{id}")
    public List<GoodsAndBuyer> findByGoodsId(@PathVariable("id") Integer[] id)
    {
        List<GoodsAndBuyer> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++)
        {
            list.add(this.goodsDao.queryGoodsByOrderId(id[i]));
        }
        return list;
    }
}

