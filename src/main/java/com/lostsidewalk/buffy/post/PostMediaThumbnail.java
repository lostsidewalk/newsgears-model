package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a thumbnail associated with media in a post.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaThumbnail implements Serializable {

    @Serial
    private static final long serialVersionUID = 23546732943L;

    /**
     * Height of the thumbnail.
     */
    Integer height;

    /**
     * Width of the thumbnail.
     */
    Integer width;

    /**
     * Time associated with the thumbnail.
     */
    Time time;

    /**
     * URL of the thumbnail.
     */
    URI url;

    /**
     * Creates a new instance of PostMediaThumbnail.
     *
     * @param height Height of the thumbnail.
     * @param width  Width of the thumbnail.
     * @param time   Time associated with the thumbnail.
     * @param url    URL of the thumbnail.
     */
    PostMediaThumbnail(Integer height, Integer width, Time time, URI url) {
        this.height = height;
        this.width = width;
        this.time = time;
        this.url = url;
    }

    /**
     * Creates a new instance of PostMediaThumbnail from a Thumbnail object.
     *
     * @param t The Thumbnail object to convert.
     * @return A new PostMediaThumbnail instance.
     */
    public static PostMediaThumbnail from(Thumbnail t) {
        return new PostMediaThumbnail(t.getHeight(), t.getWidth(), t.getTime(), t.getUrl());
    }

    /**
     * Converts the PostMediaThumbnail instance to a Thumbnail object.
     *
     * @return A Thumbnail object.
     */
    public Thumbnail toModule() {
        return new Thumbnail(url, width, height, time);
    }
}
