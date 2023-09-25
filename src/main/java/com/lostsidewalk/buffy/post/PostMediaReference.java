package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Reference;
import com.rometools.modules.mediarss.types.UrlReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a reference associated with media in a post.
 */
@Data
@EqualsAndHashCode
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaReference implements Serializable {

    @Serial
    private static final long serialVersionUID = 45452133354944442L;

    /**
     * URI of the reference.
     */
    URI uri;

    /**
     * Creates a new instance of PostMediaReference.
     *
     * @param uri URI of the reference.
     */
    PostMediaReference(URI uri) {
        this.uri = uri;
    }

    /**
     * Creates a new instance of PostMediaReference from a Reference object.
     *
     * @param reference The Reference object to convert.
     * @return A new PostMediaReference instance.
     */
    public static PostMediaReference from(Reference reference) {
        if (reference instanceof UrlReference) {
            return new PostMediaReference(((UrlReference) reference).getUrl());
        } else if (reference instanceof PlayerReference) {
            return new PostMediaReference(((PlayerReference) reference).getUrl());
        }

        return null;
    }

    /**
     * Converts the PostMediaReference instance to a UrlReference object.
     *
     * @return A UrlReference object.
     */
    public Reference toUrlModule() {
        return new UrlReference(uri);
    }

    /**
     * Converts the PostMediaReference instance to a PlayerReference object.
     *
     * @return A PlayerReference object.
     */
    public Reference toPlayerModule() {
        return new PlayerReference(uri);
    }
}
