package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.License;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents the license information for media associated with a post.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaLicense implements Serializable {

    @Serial
    private static final long serialVersionUID = 22228231231557823L;

    /**
     * The type of the license.
     */
    String type;

    /**
     * The URL to the license.
     */
    URL href;

    /**
     * The value of the license.
     */
    String value;

    /**
     * Creates a new instance of PostMediaLicense.
     *
     * @param type  The type of the license.
     * @param href  The URL to the license.
     * @param value The value of the license.
     */
    PostMediaLicense(String type, URL href, String value) {
        this.type = type;
        this.href = href;
        this.value = value;
    }

    /**
     * Creates a new instance of PostMediaLicense from a License object.
     *
     * @param t The License object to convert.
     * @return A new PostMediaLicense instance.
     */
    public static PostMediaLicense from(License t) {
        return new PostMediaLicense(t.getType(), t.getHref(), t.getValue());
    }

    /**
     * Converts the PostMediaLicense instance to a License object.
     *
     * @return A License object.
     */
    public License toModule() {
        License l = new License();
        l.setType(type);
        l.setHref(href);
        l.setValue(value);

        return l;
    }
}
