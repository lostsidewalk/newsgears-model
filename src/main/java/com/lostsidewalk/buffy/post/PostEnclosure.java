package com.lostsidewalk.buffy.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostEnclosure implements Serializable {

    public static final long serialVersionUID = 23549873L;

    private String url;

    private String type;

    private Long length;
}
