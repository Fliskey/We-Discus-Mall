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

    @PutMapping("/login")//登录接口
    public boolean login(@RequestBody UmUser umUser)
    {
        try {
            UmUser queryUser = userDao.queryUserByName(umUser.getId(), umUser.getPassword());
            // System.out.println(queryUser.getName()+queryUser.getPassword());
            if (queryUser == null) {
                System.out.println("404");
                return false;
            } else {
                System.out.println("200");
                return true;
            }
        }
        catch (Exception e){
            //do nothing
        }
        return false;

    }


}

