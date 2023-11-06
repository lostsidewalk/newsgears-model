package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Rating;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a media rating associated with a post's media.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaRating implements Serializable {

    @Serial
    private static final long serialVersionUID = 411442343L;

    /**
     * The scheme of the rating.
     */
    String scheme;

    /**
     * The value of the rating.
     */
    String value;

    /**
     * Constructor to create a PostMediaRating object.
     */
    PostMediaRating(String scheme, String value) {
        this.scheme = scheme;
        this.value = value;
    }

    /**
     * Creates a PostMediaRating object from a Rating instance.
     *
     * @param t The Rating instance to convert from.
     * @return A PostMediaRating object with data from the Rating instance.
     */
    public static PostMediaRating from(Rating t) {
        return new PostMediaRating(t.getScheme(), t.getValue());
    }

    /**
     * Converts the PostMediaRating object to a Rating instance.
     *
     * @return A Rating instance representing the PostMediaRating data.
     */
    public final Rating toModule() {
        return new Rating(scheme, value);
    }
}
