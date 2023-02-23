package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Rating;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaRating implements Serializable {

    @Serial
    private static final long serialVersionUID = 411442343L;

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
