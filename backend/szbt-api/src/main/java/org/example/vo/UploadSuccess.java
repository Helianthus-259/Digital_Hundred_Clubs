package org.example.vo;

import lombok.Data;

@Data
public class UploadSuccess {
    private String url;
    public UploadSuccess(String u)
    {
        this.url = u;
    }
}
