package com.szbt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
public class UriConfiguration {
    private String authServer = "lb://auth-server";
    private String studentServer = "lb://student-server";

    private String fileServer = "lb://file-server";

    private String clubServer = "lb://club-server";

    private String adminServer = "lb://admin-server";

    private String activityServer = "lb://activity-server";

    private String springAdmin = "lb://spring-admin";

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

    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }

    public String getClubServer() {
        return clubServer;
    }

    public void setClubServer(String clubServer) {
        this.clubServer = clubServer;
    }

    public String getAdminServer() {
        return adminServer;
    }

    public void setAdminServer(String adminServer) {
        this.adminServer = adminServer;
    }

    public String getActivityServer() {
        return activityServer;
    }

    public void setActivityServer(String activityServer) {
        this.activityServer = activityServer;
    }

    public String getSpringAdmin() {
        return springAdmin;
    }

    public void setSpringAdmin(String springAdmin) {
        this.springAdmin = springAdmin;
    }
}
