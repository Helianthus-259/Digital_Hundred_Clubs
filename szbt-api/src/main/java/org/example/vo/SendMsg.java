package org.example.vo;

import lombok.Data;

@Data
public class SendMsg {
    String msg;

    public SendMsg(String m) {
        this.msg = m;
    }
}
