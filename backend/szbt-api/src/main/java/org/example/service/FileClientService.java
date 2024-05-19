package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-server")
public interface FileClientService {
    @PostMapping(value = "/file/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "pathPrefix") String pathPrefix);

    @GetMapping("/file/downloadFile")
    ResponseEntity<byte[]> downloadFile(@RequestParam(value = "filePath")  String filePath);
}
