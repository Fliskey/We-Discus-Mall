package com.mallBuilding.controller;


import com.mallBuilding.dao.UserDao;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("//umUser")
public class UmUserController {

    @Autowired
    private UmUserService umUserService;

    @Autowired
    private UserDao userDao;

    @PostMapping("/telToId")
    public Integer telToId(@RequestBody String tel){
        try {
            int ret = this.userDao.queryIdByTel(tel.substring(0,tel.indexOf('=')));
            return ret;
        }
        catch(Exception e){
            System.err.println("404");
        }
        return 0;
    }

    @GetMapping("/list")
    public List<UmUser> list()
    {
        return this.umUserService.list();
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id )
    {
        return this.umUserService.removeById(id);
    }

    @GetMapping("/find/{id}")
    public UmUser find(@PathVariable("id") Integer id)
    {
        return this.umUserService.getById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody UmUser umUser)
    {
        return this.umUserService.updateById(umUser);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody UmUser umUser){
        return this.umUserService.save(umUser);
    }


}

