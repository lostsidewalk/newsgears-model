package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Represents a URL associated with a post.
 */
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostUrl implements Serializable {

    @Serial
    private static final long serialVersionUID = 2314224223343L;
    private static final String ENTITY_PREFIX = "pu";
    /**
     * A unique identifier for this entity.
     */
    String ident;

    /**
     * The title of the URL.
     */
    String title;

    /**
     * The type of the URL.
     */
    String type;

    /**
     * The actual URL (href) value.
     */
    String href;

    /**
     * The language of the content at the URL.
     */
    String hreflang;

    /**
     * The relationship of the URL to the current document.
     */
    String rel;

    /**
     * Creates a new PostUrl with the specified properties.
     *
     * @param ident    A unique identifier for this entity.
     * @param title    The title of the URL.
     * @param type     The type of the URL.
     * @param href     The actual URL (href) value.
     * @param hreflang The language of the content at the URL.
     * @param rel      The relationship of the URL to the current document.
     */
    PostUrl(String ident, String title, String type, String href, String hreflang, String rel) {
        this.ident = ident;
        this.title = title;
        this.type = type;
        this.href = href;
        this.hreflang = hreflang;
        this.rel = rel;
    }

    /**
     * Static factory method to create a PostUrl object.
     *
     * @param title    The title of the URL.
     * @param type     The type of the URL.
     * @param href     The actual URL (href) value.
     * @param hreflang The language of the content at the URL.
     * @param rel      The relationship of the URL to the current document.
     * @return A new PostUrl instance.
     */
    public static PostUrl from(String title, String type, String href, String hreflang, String rel) {
        return new PostUrl(
                ENTITY_PREFIX + "_" + randomAlphanumeric(8),
                title,
                type,
                href,
                hreflang,
                rel
        );
    }

    /**
     * Updates the properties of this PostUrl.
     *
     * @param title    The title of the URL.
     * @param type     The type of the URL.
     * @param href     The actual URL (href) value.
     * @param hreflang The language of the content at the URL.
     * @param rel      The relationship of the URL to the current document.
     */
    @SuppressWarnings("unused")
    public void update(String title, String type, String href, String hreflang, String rel) {
        this.title = title;
        this.type = type;
        this.href = href;
        this.hreflang = hreflang;
        this.rel = rel;
    }
}
