package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostUrl implements Serializable {

    @Serial
    private static final long serialVersionUID = 2314224223343L;

    private String title;

    private String type;

    private String href;

    private String hreflang;

    private String rel;
}
