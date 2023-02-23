package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostEnclosure implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549873L;

    private String url;

    private String type;

    private Long length;
}
