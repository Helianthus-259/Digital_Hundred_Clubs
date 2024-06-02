package org.example.vo;

import lombok.Data;

@Data
public class FileVO {
    String url;
    String flag;
    public FileVO(String url, String flag)
    {
        this.url = url;
        this.flag = flag;
    }
}
