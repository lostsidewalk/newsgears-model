package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Represents a person associated with a post.
 */
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549342343L;
    private static final String ENTITY_PREFIX = "pp";
    /**
     * A unique identifier for this entity.
     */
    String ident;

    /**
     * The name of the person.
     */
    String name;

    /**
     * The email address of the person.
     */
    String email;

    /**
     * The URI of the person.
     */
    String uri;

    /**
     * Creates a new PostPerson with the specified properties.
     *
     * @param ident A unique identifier for this entity.
     * @param name  The name of the person.
     * @param email The email address of the person.
     * @param uri   The URI of the person.
     */
    PostPerson(String ident, String name, String email, String uri) {
        this.ident = ident;
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    /**
     * Static factory method to create a PostPerson object.
     *
     * @param name  The name of the person.
     * @param email The email address of the person.
     * @param uri   The URI of the person.
     * @return A new PostPerson instance.
     */
    public static PostPerson from(String name, String email, String uri) {
        return new PostPerson(
                ENTITY_PREFIX + "_" + randomAlphanumeric(8),
                name,
                email,
                uri
        );
    }

    /**
     * Updates the state of this PostPerson object with the supplied parameters.
     *
     * @param name  The updated name attribute value.
     * @param email The updated email attribute value.
     * @param uri   The updated URI attribute value.
     */
    @SuppressWarnings("unused")
    public void update(String name, String email, String uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }
}
