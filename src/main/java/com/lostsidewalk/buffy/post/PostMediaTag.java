package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Tag;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 22312349342343L;

    String name;

    Integer weight;

    PostMediaTag(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public static PostMediaTag from(Tag t) {
        return new PostMediaTag(t.getName(), t.getWeight());
    }
}
