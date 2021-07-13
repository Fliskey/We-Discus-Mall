package com.mallBuilding.controller;


import com.mallBuilding.dao.PurchaseGoodsDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.PurchaseGoods;
import com.mallBuilding.mapper.PurchaseGoodsMapper;
import com.mallBuilding.service.PurchaseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public boolean add(@RequestBody PurchaseGoods purchaseGoods){
        return this.purchaseGoodsService.save(purchaseGoods);
    }

    @GetMapping("/findByUserId/{id}")
    public List<GmGoods> findByUserId(@PathVariable("id") Integer id) {return this.purchaseGoodsDao.queryGMGoodsByUserId(id);}

    @PostMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer id){
        return this.purchaseGoodsMapper.deleteById(id);
    }

}

