package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a content object within a post.
 *
 * <p>A content object is an entity that can be included within a post to represent various types of content,
 * such as text, images, links, or multimedia. It typically includes a unique identifier, a type that describes
 * the content format, and the actual content value.
 *
 * @since [Include the version or date when this class was introduced]
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class ContentObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354987329183759L;
    /**
     * A unique identifier for this content object.
     */
    String ident;
    /**
     * The type of the content object, describing its format (e.g., text, image, link).
     */
    String type;
    /**
     * The actual value or content of the content object.
     */
    String value;

    /**
     * Constructs a new ContentObject with the specified type and value.
     *
     * @param ident The unique identifier of this entity.
     * @param type  The type of the content object.
     * @param value The value of the content object.
     */
    ContentObject(String ident, String type, String value) {
        this.ident = ident;
        this.type = type;
        this.value = value;
    }

    /**
     * Static factory method to create a ContentObject with a random identifier.
     *
     * @param ident The unique identifier of this entity.
     * @param type  The type of the content object.
     * @param value The value of the content object.
     * @return A ContentObject instance with a unique identifier.
     */
    public static ContentObject from(String ident, String type, String value) {
        return new ContentObject(ident, type, value);
    }

    /**
     * Updates the type and value of the content object.
     *
     * @param type  The new type for the content object.
     * @param value The new value for the content object.
     */
    @SuppressWarnings("unused")
    public final void update(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
