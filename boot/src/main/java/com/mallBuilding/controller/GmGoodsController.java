package com.mallBuilding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallBuilding.dao.GoodsDao;
import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.mapper.GmGoodsMapper;
import com.mallBuilding.service.GmGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private GmGoodsMapper gmGoodsMapper;

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

    @GetMapping("/findList/{id}")
    public List<GmGoods> findByGoodsId(@PathVariable("id") Integer[] id)
    {
        List<GmGoods> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++)
        {
            list.add(this.goodsDao.queryGoodsByGoodsId(id[i]));
        }
        return list;
    }


    @GetMapping("/selectPage/{current}/{pageSize}")
    public Page<GmGoods> selectPage(@PathVariable("current") Integer current, @PathVariable("pageSize") Integer pageSize) //@Param(Constants.WRAPPER) Wrapper<GmGoods> queryWrapper
    {
        LambdaQueryWrapper<GmGoods> gmGoodsLambdaQueryWrapper = Wrappers.lambdaQuery();

        Page<GmGoods> goodsPage = new Page<>(current ,pageSize);
        IPage<GmGoods> goodsIPage = gmGoodsMapper.selectPage(goodsPage , gmGoodsLambdaQueryWrapper);
        return goodsPage;
    }


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
    public List<GmGoods> findByUserId(@PathVariable("id") Integer id) {return this.goodsDao.queryGoodsByUserId(id);}

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id )
    {
        return this.gmGoodsService.removeById(id);
    }

    @GetMapping("/findQuantity/{id}")
    public Integer findQuantity(@PathVariable("id") Integer id)
    {
        return this.goodsDao.findQuantityById(id);
    }

//    @PutMapping("/reduceStock")
//    public boolean reduceStock(@RequestBody GmGoods gmGoods)
//    {
//        return this.gmGoodsService.updateById(gmGoods);
//    }



}

