package com.szbt.fileserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.util.StatusCode;
import org.example.vo.UploadSuccess;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import org.example.util.Result;
import org.example.util.ResultCode;

@RequestMapping("/file")
@RestController
@Slf4j
public class FileController {

    String baseRoot = System.getProperty("user.dir") + "/backend/file-server/storage/";

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "pathPrefix") String pathPrefix) {
        System.out.println("fileType:" + pathPrefix);
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
        File dest = new File(baseRoot + pathPrefix + filenameWithoutExtension + "_" + uuid + suffix);
        try {
            file.transferTo(dest);
            // 获取文件的规范路径
            String absolutePath = dest.getCanonicalPath();
            System.out.println(absolutePath);
            // 获取相对路径
            String relativePath = absolutePath.substring(baseRoot.length());
            // 返回相对路径
            return relativePath;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件（二进制数据）下载
     * @param filePath 文件路径
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestPart(value = "filePath") String filePath, HttpServletRequest request ) {
        System.out.println(request.getParameter("filePath"));
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
}
