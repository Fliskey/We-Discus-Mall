package com.mallBuilding.controller;


import com.mallBuilding.dao.UmLoginDao;
import com.mallBuilding.entity.UmLogin;
import com.mallBuilding.entity.UmUser;
import com.mallBuilding.mapper.UmLoginMapper;
import com.mallBuilding.service.UmLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2021-07-08
 */
@RestController
@RequestMapping("//umLogin")
public class UmLoginController {

    @Autowired
    private UmLoginService umLoginService;

    @Autowired
    private UmLoginDao umLoginDao;

    @Autowired
    private UmLoginMapper umLoginMapper;
/*
    @PutMapping("/login")
    public boolean login(@RequestBody UmLogin umLogin){
        return true;
    }*/

    @GetMapping("/getSalt")
    public String getSalt(){
        String salt = UUID.randomUUID().toString();
        return salt;
    }

    @PostMapping("/addUser")
    public Integer addUser(@RequestBody UmLogin umLogin){
        try{
            boolean ret = this.umLoginService.save(umLogin);
            System.out.println("\nret:"+ret);
            System.out.println("\npassword:"+umLogin.getPassword());
            int getId = this.umLoginDao.queryLoginIdByPassword(umLogin.getPassword());
            System.out.printf("\nid:%d\n",getId);
            if(getId > 0){
                return getId;
            }
            else{
                return -1;
            }
        }
        catch (Exception e){
            //do nothing
        }
        return -1;
    }

    @PostMapping("/querySalt")
    public String querySalt(@RequestBody String id){
        try {
            int intId = Integer.valueOf(id.substring(0,id.indexOf('=')));
            String salt = this.umLoginDao.querySaltById(intId);
            return salt;
        }
        catch (Exception e){
            System.out.println("404");
            return "";
        }
    }

    @PutMapping("/login")//登录接口
    public boolean login(@RequestBody UmLogin umLogin)
    {
        try {
            UmLogin queryUser = umLoginDao.queryUmLoginByIdAndPassword(umLogin.getId(), umLogin.getPassword());
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
            System.err.println("404");
            //do nothing
        }
        return false;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody UmLogin umLogin){
        System.out.println("\numLogin:"+umLogin);
        return !(umLoginMapper.updateById(umLogin) == 0);

    }


/*

    public class DigestUtil {
        private String DEFAULT_ENCODING = "UTF-8";
        private String SHA_256 = "SHA-256";

        public String sha256Digest(String str) {
            return Digest.digest(str, SHA_256, DEFAULT_ENCODING);
        }
    }

    public static class Digest {
        public static String digest(String str, String alg, String charEncoding) {
            try {
                byte[] data = str.getBytes(charEncoding);
                MessageDigest md = MessageDigest.getInstance(alg);

                StringBuffer sb = new StringBuffer(data.length);
                String sTemp;
                byte[] bArray = md.digest(data);
                for (int i = 0; i < bArray.length; i++)
                {
                    sTemp = Integer.toHexString(0xFF & bArray[i]);
                    if (sTemp.length() < 2)
                        sb.append(0);
                    sb.append(sTemp.toUpperCase());
                }
                return sb.toString();
            } catch (Exception var5) {
                throw new RuntimeException("digest fail!", var5);
            }
        }
    }*/

}

