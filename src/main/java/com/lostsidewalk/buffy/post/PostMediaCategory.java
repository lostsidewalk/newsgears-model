package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Category;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a media category associated with a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 209384029384L;

    /**
     * The label of the media category.
     */
    String label;

    /**
     * The scheme of the media category.
     */
    String scheme;

    /**
     * The value of the media category.
     */
    String value;

    /**
     * Private constructor for creating a PostMediaCategory object.
     *
     * @param label  The label of the media category.
     * @param scheme The scheme of the media category.
     * @param value  The value of the media category.
     */
    PostMediaCategory(String label, String scheme, String value) {
        this.label = label;
        this.scheme = scheme;
        this.value = value;
    }

    /**
     * Static factory method to create a PostMediaCategory object from a Category instance.
     *
     * @param t The Category instance to convert to a PostMediaCategory object.
     * @return A new PostMediaCategory object created from the given Category instance.
     */
    public static PostMediaCategory from(Category t) {
        return new PostMediaCategory(t.getLabel(), t.getScheme(), t.getValue());
    }

    /**
     * Converts the PostMediaCategory object to a Category instance.
     *
     * @return A Category instance representing the PostMediaCategory object.
     */
    public final Category toModule() {
        return new Category(scheme, label, value);
    }
}
