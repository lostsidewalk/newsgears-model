package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaThumbnail implements Serializable {

    @Serial
    private static final long serialVersionUID = 23546732943L;

    Integer height;

    Integer width;

    Time time;

    URI url;

    PostMediaThumbnail(Integer height, Integer width, Time time, URI url) {
        this.height = height;
        this.width = width;
        this.time = time;
        this.url = url;
    }


    public static PostMediaThumbnail from(Thumbnail t) {
        return new PostMediaThumbnail(t.getHeight(), t.getWidth(), t.getTime(), t.getUrl());
    }

    public Thumbnail toModule() {
        return new Thumbnail(url, width, height, time);
    }
}
