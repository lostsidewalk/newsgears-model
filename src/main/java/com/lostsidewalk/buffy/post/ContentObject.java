package com.lostsidewalk.buffy.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContentObject implements Serializable {

    public static final long serialVersionUID = 2354987329183759L;

    private String type;

    private String value;

    private ContentObject(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static ContentObject from(String type, String value) {
        return new ContentObject(type, value);
    }
}
