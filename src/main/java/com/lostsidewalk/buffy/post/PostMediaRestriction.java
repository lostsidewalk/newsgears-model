package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Restriction.Relationship;
import com.rometools.modules.mediarss.types.Restriction.Type;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a media restriction associated with a post's media content.
 * This class defines attributes related to the type, relationship, and value of a media restriction.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaRestriction implements Serializable {

    @Serial
    private static final long serialVersionUID = 99133354944442343L;

    /**
     * The type of media restriction.
     */
    Type type;

    /**
     * The relationship of the media restriction.
     */
    Relationship relationship;

    /**
     * The value associated with the media restriction.
     */
    String value;

    /**
     * Constructs a new PostMediaRestriction instance with the provided parameters.
     *
     * @param type         The type of media restriction.
     * @param relationship The relationship of the media restriction.
     * @param value        The value associated with the media restriction.
     */
    PostMediaRestriction(Type type, Relationship relationship, String value) {
        this.type = type;
        this.relationship = relationship;
        this.value = value;
    }

    /**
     * Creates a PostMediaRestriction instance from a given Restriction object.
     *
     * @param t The Restriction object to create a PostMediaRestriction from.
     * @return A new PostMediaRestriction instance created from the provided Restriction.
     */
    public static PostMediaRestriction from(Restriction t) {
        return new PostMediaRestriction(t.getType(), t.getRelationship(), t.getValue());
    }

    /**
     * Converts the PostMediaRestriction to a corresponding Restriction from the media RSS module.
     *
     * @return A Restriction instance corresponding to the PostMediaRestriction.
     */
    public Restriction toModule() {
        return new Restriction(relationship, type, value);
    }
}
