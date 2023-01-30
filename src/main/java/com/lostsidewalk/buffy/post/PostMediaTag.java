package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Tag;
import lombok.Data;

@Data
public class PostMediaTag {

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
