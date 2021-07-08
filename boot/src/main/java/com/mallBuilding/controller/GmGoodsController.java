package com.mallBuilding.controller;


import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.service.GmGoodsService;
import com.mallBuilding.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public List<GmGoods> list()
    {
        return this.gmGoodsService.list();
    }

    @GetMapping("/find/{id}")
    public GmGoods find(@PathVariable("id") Integer id) {return this.gmGoodsService.getById(id);}

}

