package com.lostsidewalk.buffy.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostUrl implements Serializable {

    public static final long serialVersionUID = 2314224223343L;

    private String title;

    private String type;

    private String href;

    private String hreflang;

    private String rel;
}
