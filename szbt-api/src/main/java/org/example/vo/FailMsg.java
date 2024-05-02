package org.example.vo;

import lombok.Data;

@Data
public class FailMsg {
    String msg;

    public FailMsg(String m) {
        this.msg = m;
    }
}
