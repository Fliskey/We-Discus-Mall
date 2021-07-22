package com.mallBuilding.controller;


import com.mallBuilding.dao.AdminDao;
import com.mallBuilding.entity.Admin;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.mapper.AdminMapper;
import com.mallBuilding.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Fliskey
 * @since 2021-07-07
 */
@RestController
@RequestMapping("//admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/telToId")
    public Integer telToId(@RequestBody String tel){
        try {
            int ret = this.adminDao.queryIdByTel(tel.substring(0,tel.indexOf('=')));
            return ret;
        }
        catch(Exception e){
            System.err.println("404");
        }
        return 0;
    }


    @PostMapping("/querySalt")
    public String querySalt(@RequestBody String id){    //换了int就报错 by@Fliskey
        try {
            int intId = Integer.valueOf(id.substring(0,id.indexOf('=')));
            String salt = this.adminDao.querySaltById(intId);
            return salt;
        }
        catch (Exception e){
            System.out.println("404");
            return "";
        }
    }

    @PostMapping("/getAdmin")
    public Admin getAdmin(@RequestBody String id){
        return this.adminMapper.selectById(id.substring(0,id.indexOf('=')));
    }


    @GetMapping("/list")
    public List<Admin> list()
    {
        return this.adminService.list();
    }


    @PutMapping("/login")//登录接口
    public boolean login(@RequestBody Admin admin)
    {
        Admin queryUser = adminDao.queryAdminById(admin.getId(),admin.getPassword());
        if(queryUser==null)
        {
            System.out.println("404");
            return false;
        }
        else
        {
            System.out.println("200");
            return true;
        }
    }

//    //实现模糊查询 用于管理员页面查询历史订单
//    @GetMapping("/likeQueryName")

}

