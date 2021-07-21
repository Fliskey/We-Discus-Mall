package com.mallBuilding.controller;


import com.mallBuilding.dao.OmOrderDao;
import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import com.mallBuilding.service.OmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/order/{id}")
    public OmOrder omOrderById(@PathVariable("id") Integer id)
    {
        return this.omOrderService.getById(id);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody OmOrder omOrder){
        return this.omOrderService.save(omOrder);
    }

    @GetMapping("/delete/{gid}/{bid}")
    public boolean queryWantBy(@PathVariable("gid") Integer gid,@PathVariable("bid") Integer bid){

        return this.omOrderDao.deleteByUserId(gid,bid);
        //集合
    }

    @PutMapping("/update")
    public boolean update (@RequestBody OmOrder omOrder){
        return  this.omOrderService.updateById(omOrder);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id )
    {
        Integer newId = Integer.valueOf(id);
        return this.omOrderService.removeById(newId);
    }
}

