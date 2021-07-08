package com.mallBuilding.controller;


import com.mallBuilding.dao.AdminDao;
import com.mallBuilding.entity.Admin;
import com.mallBuilding.entity.UmUser;
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

    @GetMapping("/list")
    public List<Admin> list()
    {
        return this.adminService.list();
    }


    @PutMapping("/login")//登录接口
    public boolean login(@RequestBody Admin admin)
    {
        Admin queryUser = adminDao.queryAdminById(admin.getId(),admin.getPassword());
        // System.out.println(queryUser.getName()+queryUser.getPassword());
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

}

