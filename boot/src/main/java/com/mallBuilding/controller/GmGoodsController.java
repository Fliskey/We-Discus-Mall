package com.mallBuilding.controller;


import com.mallBuilding.dao.GoodsDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.service.GmGoodsService;
import com.mallBuilding.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-06
 */
@RestController
@RequestMapping("//gmGoods")
public class GmGoodsController {


    @Autowired
    private GmGoodsService gmGoodsService;

    @Autowired
    private GoodsDao goodsDao;

    @PostMapping("/add")  //填写商品信息接口
    public boolean add(@RequestBody GmGoods gmGoods){
        return this.gmGoodsService.save(gmGoods);
    }

    @GetMapping("/list")
    public List<GmGoods> list()
    {
        return this.gmGoodsService.list();
    }

    @GetMapping("/find/{id}")
    public GmGoods find(@PathVariable("id") Integer id) {return this.gmGoodsService.getById(id);}

    @GetMapping("/queryGoodsByType/{type}")
    public List<GmGoods> queryGoodsByType(@PathVariable("type") String type){
        List<GmGoods> lists = this.goodsDao.queryGoodsByType(type);
        return lists;
        //集合

    }

    @PutMapping("/update")
    public boolean update(@RequestBody GmGoods gmGoods)
    {
        return this.gmGoodsService.updateById(gmGoods);
    }

    @GetMapping("/findByUserId/{id}")
    public List<GmGoods> findByUserId(@PathVariable("id") String id) {return this.goodsDao.queryGoodsByUserId(id);}

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id )
    {
        return this.gmGoodsService.removeById(id);
    }


}

