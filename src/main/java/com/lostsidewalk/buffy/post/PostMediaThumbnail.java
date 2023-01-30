package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.net.URI;

@Data
public class PostMediaThumbnail {

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
