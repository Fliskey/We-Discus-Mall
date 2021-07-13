package com.mallBuilding.controller;


import com.mallBuilding.dao.WantDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.entity.WantGoods;
import com.mallBuilding.service.WantGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.nio.file.WatchService;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2021-07-08
 */
@RestController
@RequestMapping("//wantGoods")
public class WantGoodsController {
    @Autowired
    private WantGoodsService wantGoodsService;

    @Autowired
    private WantDao wantDao;

    @GetMapping("/list")
    public List<WantGoods> list()
    {
        return this.wantGoodsService.list();
    }

    @PostMapping("/add")
    public boolean add(@RequestBody WantGoods wantGoods){
        return this.wantGoodsService.save(wantGoods);
    }


    @GetMapping("/queryWant/{id}")
    public List<GmGoods> queryWant(@PathVariable("id") Integer id){
        List<GmGoods> lists = this.wantDao.queryByUserId(id);
        return lists;
        //集合
    }

    @GetMapping("/delete/{uid}/{gid}")
    public boolean queryWantBy(@PathVariable("uid") Integer uid,@PathVariable("gid") Integer gid){
        String id = this.wantDao.queryId(Integer.valueOf(uid),Integer.valueOf(gid));

        return this.wantDao.removeById(Integer.valueOf(id));
        //集合
    }



}

