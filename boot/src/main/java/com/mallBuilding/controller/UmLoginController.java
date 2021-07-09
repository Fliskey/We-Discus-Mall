package com.mallBuilding.controller;


import com.mallBuilding.dao.UmLoginDao;
import com.mallBuilding.entity.UmLogin;
import com.mallBuilding.service.UmLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.MessageDigest;

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
        umLogin.setPassword(umLogin.getPassword());
    }

    public class DigestUtil {
        private String DEFAULT_ENCODING = "UTF-8";
        private String SHA_256 = "SHA-256";

        public String sha256Digest(String str) {
            return Digest.digest(str, SHA_256, DEFAULT_ENCODING);
        }
    }

    public class Digest {
        public String digest(String str, String alg, String charencoding) {
            try {
                byte[] data = str.getBytes(charencoding);
                MessageDigest md = MessageDigest.getInstance(alg);
                return Hex.toHex(md.digest(data));
            } catch (Exception var5) {
                throw new RuntimeException("digest fail!", var5);
            }
        }
    }*/

}

