package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class ContentObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354987329183759L;

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
