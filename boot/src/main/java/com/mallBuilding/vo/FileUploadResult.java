package com.mallBuilding.vo;


import lombok.Data;

/**
 * <p>Title: 阿里云oss通用结果返回类 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: http://www.ubisys.com.cn/</p>
 *
 * @Auther: cw
 * @Date: 2020/9/3 20:41
 */
@Data
public class FileUploadResult {


    // 文件唯一标识
    private String uid;
    // 文件名
    private String name;
    // 状态有：uploading done error removed
    private String status;
    // 服务端响应内容，如：'{"status": "success"}'
    private String response;

    private String key;

}
