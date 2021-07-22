package com.mallBuilding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallBuilding.dao.GoodsDao;
import com.mallBuilding.dao.PurchaseGoodsDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.PurchaseGoods;
import com.mallBuilding.mapper.GmGoodsMapper;
import com.mallBuilding.mapper.PurchaseGoodsMapper;
import com.mallBuilding.service.PurchaseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Fliskey
 * @since 2021-07-13
 */
@RestController
@RequestMapping("//purchaseGoods")
public class PurchaseGoodsController {

    @Autowired
    private PurchaseGoodsService purchaseGoodsService;

    @Autowired
    private PurchaseGoodsDao purchaseGoodsDao;

    @Autowired
    private PurchaseGoodsMapper purchaseGoodsMapper;

    @Autowired
    private GoodsDao goodsDao;


    @PostMapping("/add")
    public boolean add(@RequestBody PurchaseGoods purchaseGoods){
        PurchaseGoods p = this.purchaseGoodsDao.findQuantityById(purchaseGoods.getUserId(),purchaseGoods.getGoodsId());
        if(p != null)
        {
            p.setQuantity(p.getQuantity()+1);
            this.purchaseGoodsService.updateById(p);
            return true;
        }
        else
        {
            return this.purchaseGoodsService.save(purchaseGoods);
        }
    }

    @GetMapping("/findByUserId/{id}")
    public List<GmGoods> findByUserId(@PathVariable("id") Integer id) {return this.purchaseGoodsDao.queryGMGoodsByUserId(id);}


    @GetMapping("/list")
    public List<PurchaseGoods> list()
    {
        return this.purchaseGoodsService.list();
    }


    @GetMapping("/GtoP/{id}")
    public int GtoP(@PathVariable("id") Integer id)  { return this.purchaseGoodsDao.queryPidByGid(id); }


    @GetMapping("/delete/{uid}/{gid}")
    public boolean queryWantBy(@PathVariable("uid") Integer uid,@PathVariable("gid") Integer gid){

        return this.purchaseGoodsDao.deleteByUserId(uid,gid);
        //集合
    }

    @GetMapping("/update/{uid}/{gid}/{quantity}")
    public boolean update(@PathVariable("uid") Integer uid,@PathVariable("gid") Integer gid,@PathVariable("quantity") Integer quantity)
    {
        return this.purchaseGoodsDao.updateByUserId(uid,gid,quantity);
    }
}

