package com.zrq.bean;

import lombok.Data;

@Data
public class ImageMsg {
    private int success;
    private String message;
    private String url;

    public ImageMsg(int success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = "https://www.whitefoal.com/images/"+url;
    }
    
}
