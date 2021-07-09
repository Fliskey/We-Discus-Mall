package com.mallBuilding.controller;


import com.mallBuilding.dao.UmLoginDao;
import com.mallBuilding.entity.UmLogin;
import com.mallBuilding.service.UmLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.MessageDigest;
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
        this.umLoginService.save(umLogin);
        return this.umLoginDao.queryLoginByPassword(umLogin.getPassword());
    }


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
    }

}

