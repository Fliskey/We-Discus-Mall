package com.mallBuilding.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.mallBuilding.service.FileUploadService;
import com.mallBuilding.vo.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>Title:阿里云oss存储 controller </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: http://www.ubisys.com.cn/</p>
 *
 * @Auther: cw
 * @Date: 2020/9/4 21:31
 */
@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;


    /**
     * 文件上传到oss
     * @param uploadFile
     * @return
     * @throws Exception
     */
    @RequestMapping("file/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile uploadFile)
            throws Exception {
        return fileUploadService.upload(uploadFile);
    }

    /**
     * 根据文件名删除
     * @param objectName
     * @return
     * @throws Exception
     */
    @RequestMapping("file/delete")
    @ResponseBody
    public FileUploadResult delete(@RequestParam("fileName") String objectName)
            throws Exception {
        return fileUploadService.delete(objectName);
    }


    /**
     *  查看桶内所有文件
     * @return
     * @throws Exception
     */
    @RequestMapping("file/list")
    @ResponseBody
    public List<OSSObjectSummary> list()
            throws Exception {
        return fileUploadService.list();
    }

    /**
     *  根据文件名进行下载
     * @param objectName
     * @param response
     * @throws IOException
     */
    @RequestMapping("file/download")
    @ResponseBody
    public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) throws IOException {
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
        fileUploadService.exportOssFile(response.getOutputStream(),objectName);
    }


}
