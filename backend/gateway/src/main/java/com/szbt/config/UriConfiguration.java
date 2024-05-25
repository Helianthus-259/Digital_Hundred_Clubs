package com.szbt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
public class UriConfiguration {
    private String authServer = "lb://auth-server";
    private String studentServer = "lb://student-server";

    public String getAuthServer() {
        return authServer;
    }

    public void setAuthServer(String authServer) {
        this.authServer = authServer;
    }

    public String getStudentServer() {
        return studentServer;
    }

    public void setStudentServer(String studentServer) {
        this.studentServer = studentServer;
    }
}
