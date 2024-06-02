package com.szbt.fileserver.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.szbt.fileserver.util.ImageResponseUtils;
import com.szbt.fileserver.util.SessionUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.FileConstants;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.FileVO;
import org.example.vo.SendMsg;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

import static org.example.constants.FileConstants.*;


@RequestMapping("/file")
@RestController
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class FileController {

    @Resource(name = "captchaBean")
    private DefaultKaptcha defaultKaptcha;

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "flag") String flag) {
        long size = file.getSize();
        String contentType = file.getContentType();
        String name = file.getName();
        String orgFilename = file.getOriginalFilename();
        System.out.println("size:" + size);
        System.out.println("contentType:" + contentType);
        System.out.println("name:" + name);
        System.out.println("orgFilename:" + orgFilename);

        String suffix = orgFilename.substring(orgFilename.lastIndexOf("."));//后缀
        String filenameWithoutExtension = orgFilename.substring(0, orgFilename.lastIndexOf("."));//文件名前缀
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();//唯一文件名后缀
        File dest = new File(baseRoot + filePathPrefix + filenameWithoutExtension + "_" + uuid + suffix);
        try {
            file.transferTo(dest);
            // 获取文件的规范路径
            String absolutePath = dest.getCanonicalPath();
            System.out.println(absolutePath);
            // 获取相对路径
            String relativePath = absolutePath.substring(baseRoot.length());
            // 返回相对路径
            // 创建一个HashMap
            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("code", ResultCode.UPLOAD_FILE.getCode());
            dataMap.put("file", new FileVO(relativePath,flag));
            return Result.success(dataMap);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            Result.send(StatusCode.UPLOAD_FILE_ERROR,new SendMsg("上传文件失败"));
        }
        return Result.send(StatusCode.UPLOAD_FILE_ERROR,new SendMsg("上传文件失败"));
    }

    @PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadImage(@RequestPart(value = "image") MultipartFile file, @RequestParam(value = "flag") String flag) {
        long size = file.getSize();
        String contentType = file.getContentType();
        String name = file.getName();
        String orgFilename = file.getOriginalFilename();
        System.out.println("size:" + size);
        System.out.println("contentType:" + contentType);
        System.out.println("name:" + name);
        System.out.println("orgFilename:" + orgFilename);

        String suffix = orgFilename.substring(orgFilename.lastIndexOf("."));//后缀
        String filenameWithoutExtension = orgFilename.substring(0, orgFilename.lastIndexOf("."));//文件名前缀
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();//唯一文件名后缀
        File dest = new File(baseRoot + imagePathPrefix + filenameWithoutExtension + "_" + uuid + suffix);
        try {
            file.transferTo(dest);
            // 获取文件的规范路径
            String absolutePath = dest.getCanonicalPath();
            System.out.println(absolutePath);
            // 获取相对路径
            String relativePath = absolutePath.substring(baseRoot.length());
            // 返回相对路径
            // 创建一个HashMap
            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("code", ResultCode.UPLOAD_FILE.getCode());
            dataMap.put("image", new FileVO(relativePath,flag));
            return Result.success(dataMap);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            Result.send(StatusCode.UPLOAD_IMAGE_ERROR,new SendMsg("上传图片失败"));
        }
        return Result.send(StatusCode.UPLOAD_IMAGE_ERROR,new SendMsg("上传图片失败"));
    }


    /**
     * 文件（二进制数据）下载
     */
    @RequestMapping("/downloadFile/{folder}/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, @PathVariable String folder, @PathVariable String fileName) {
        String filePath = folder+"\\"+fileName;
        System.out.println("参数filePath: " + filePath);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> entity = null;
        InputStream in = null;
        try {
            File file = new File(baseRoot + filePath);
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            String Filename = file.getName();
            System.out.println("Filename:" + Filename);
            //处理IE下载文件的中文名称乱码的问题
            String header = request.getHeader("User-Agent").toUpperCase();
            // 对于不同浏览器，使用不同的编码方式处理文件名
            String encodedFilename;
            if (header.contains("MSIE") || header.contains("Trident") || header.contains("Edge")) {
                encodedFilename = URLEncoder.encode(Filename, "UTF-8").replace("+", "%20");
            } else if (header.contains("Firefox")) {
                encodedFilename = new String(Filename.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                encodedFilename = URLEncoder.encode(Filename, "UTF-8");
            }
            in.read(bytes);
            // 设置Content-Disposition头部信息
            Filename = encodedFilename;
            System.out.println("Filename:" + Filename);
            headers.add("Content-Disposition", "attachment;filename=" + Filename);
            entity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    /**
     * 生成图形验证码
     * @return
     * @throws Exception
     */
    @HystrixCommand(fallbackMethod = "getImageVerifyCodeFallBack", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "true"),
            // 是否开启超时降级
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "10000"),
            // 请求的超时时间，默认10000
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "true")
            // 当请求超时时，是否中断线程，默认true
    })
    @GetMapping("/getImageVerifyCode")
    public byte[] getImageVerifyCode() throws IOException {
        //if(true) throw new Exception("出问题了");//测试服务降级
        //System.out.println("来拿图形验证码了");
        //证码字符串生成验
        String text = defaultKaptcha.createText();
        System.out.println("text:" + text);
        //验证码存入session（可以将text加密储存）
        SessionUtil.session().setAttribute("key", text);
        //验证码转换
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        //定义响应值，写入byte
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ImageResponseUtils.buildImageRes(bytes);
        return bytes;
    }
    /**
     * 生成图形验证码的服务降级实现
     * @return
     */
    public byte[] getImageVerifyCodeFallBack(){
        System.out.println("getImageVerifyCodeFallBack");
        return new byte[0];
    }

    /**
     * 验证图形验证码
     * @param code
     * @return
     */
    @HystrixCommand(fallbackMethod = "checkImageVerifyCodeFallBack")
    @PostMapping("/checkImageVerifyCode")
    public Boolean checkImageVerifyCode(String code) {
        System.out.println("code："+code);
        HttpSession session = SessionUtil.session();
        String text = (String) session.getAttribute("key");
        System.out.println("text："+text);
        if (StringUtils.isBlank(text)) {
            return Boolean.FALSE;
        }
        if (!code.equals(text)) {
            return Boolean.FALSE;
        }
        //从session中移除验证码text
        session.removeAttribute("key");
        return Boolean.TRUE;
    }

    /**
     * 验证图形验证码的服务降级实现
     * @param code
     * @return
     */
    public Boolean checkImageVerifyCodeFallBack(String code) {
        return Boolean.FALSE;
    }

}
