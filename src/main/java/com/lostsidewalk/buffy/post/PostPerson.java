package com.lostsidewalk.buffy.post;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostPerson implements Serializable {

    public static final long serialVersionUID = 23549342343L;

    private String name;

    private String email;

    private String uri;
}
