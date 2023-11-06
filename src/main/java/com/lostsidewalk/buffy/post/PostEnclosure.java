package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents an enclosure associated with a post.
 *
 * <p>An enclosure typically includes information about a file or media attachment associated with a post.
 * It includes a unique identifier, the URL of the enclosure, the type of the enclosure (e.g., audio, video),
 * and the length of the enclosure in bytes.
 *
 * @since [Include the version or date when this class was introduced]
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostEnclosure implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549873L;
    /**
     * A unique identifier for this enclosure entity.
     */
    String ident;
    /**
     * The URL of the enclosure.
     */
    String url;
    /**
     * The type of the enclosure, describing its format or media type (e.g., audio/mpeg, video/mp4).
     */
    String type;
    /**
     * The length of the enclosure in bytes.
     */
    Long length;

    /**
     * Constructs a new PostEnclosure with the specified URL, type, and length.
     *
     * @param ident  The unique identifier of this entity.
     * @param url    The URL of the enclosure.
     * @param type   The type of the enclosure.
     * @param length The length of the enclosure in bytes.
     */
    PostEnclosure(String ident, String url, String type, Long length) {
        this.ident = ident;
        this.url = url;
        this.type = type;
        this.length = length;
    }

    /**
     * Static factory method to create a PostEnclosure with a random identifier.
     *
     * @param ident  The unique identifier of this entity.
     * @param url    The URL of the enclosure.
     * @param type   The type of the enclosure.
     * @param length The length of the enclosure in bytes.
     * @return A PostEnclosure instance with a unique identifier.
     */
    public static PostEnclosure from(String ident, String url, String type, Long length) {
        return new PostEnclosure(ident, url, type, length);
    }

    /**
     * Updates the URL, type, and length of the enclosure.
     *
     * @param url    The new URL for the enclosure.
     * @param type   The new type for the enclosure.
     * @param length The new length for the enclosure in bytes.
     */
    @SuppressWarnings("unused")
    public final void update(String url, String type, Long length) {
        this.url = url;
        this.type = type;
        this.length = length;
    }
}
