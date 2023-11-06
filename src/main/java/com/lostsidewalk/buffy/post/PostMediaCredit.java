package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Credit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents media credit associated with a post's media.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaCredit implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823012319823L;

    /**
     * Name of the contributor.
     */
    String name;

    /**
     * Role of the contributor.
     */
    String role;

    /**
     * Scheme specifying the role of the contributor.
     */
    String scheme;

    /**
     * Constructor to create a PostMediaCredit object.
     *
     * @param name   Name of the contributor.
     * @param role   Role of the contributor.
     * @param scheme Scheme specifying the role of the contributor.
     */
    PostMediaCredit(String name, String role, String scheme) {
        this.name = name;
        this.role = role;
        this.scheme = scheme;
    }

    /**
     * Creates a PostMediaCredit object from a Credit instance.
     *
     * @param credit The Credit instance to convert from.
     * @return A PostMediaCredit object with data from the Credit instance.
     */
    public static PostMediaCredit from(Credit credit) {
        return new PostMediaCredit(credit.getName(), credit.getRole(), credit.getScheme());
    }

    /**
     * Converts the PostMediaCredit object to a Credit instance.
     *
     * @return A Credit instance representing the PostMediaCredit data.
     */
    public final Credit toModule() {
        return new Credit(scheme, role, name);
    }
}
