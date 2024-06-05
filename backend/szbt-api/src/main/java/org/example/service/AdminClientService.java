package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "admin-server")
public interface AdminClientService {

    @GetMapping("/getAdminInfo")
    public String getAdminInfo(@RequestParam("adminId") Integer adminId);
}
