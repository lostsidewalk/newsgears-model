package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a tag associated with media in a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 22312349342343L;

    /**
     * The name of the tag.
     */
    String name;

    /**
     * The weight of the tag.
     */
    Integer weight;

    /**
     * Creates a new instance of PostMediaTag.
     *
     * @param name   The name of the tag.
     * @param weight The weight of the tag.
     */
    PostMediaTag(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * Creates a new instance of PostMediaTag from a Tag object.
     *
     * @param t The Tag object to convert.
     * @return A new PostMediaTag instance.
     */
    public static PostMediaTag from(Tag t) {
        return new PostMediaTag(t.getName(), t.getWeight());
    }
}
