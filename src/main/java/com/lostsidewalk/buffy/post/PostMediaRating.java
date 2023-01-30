package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Rating;
import lombok.Data;

@Data
public class PostMediaRating {

    private String scheme;

    private String value;

    PostMediaRating(String scheme, String value) {
        this.scheme = scheme;
        this.value = value;
    }

    public static PostMediaRating from(Rating t) {
        return new PostMediaRating(t.getScheme(), t.getValue());
    }

    public Rating toModule() {
        return new Rating(scheme, value);
    }
}
