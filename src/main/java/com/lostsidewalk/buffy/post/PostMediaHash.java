package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Hash;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a hash associated with a post's media.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaHash implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231231249823L;

    /**
     * The hash value as a string.
     */
    String hashStr;

    /**
     * The algorithm used to generate the hash.
     */
    String algorithm;

    /**
     * Constructor to create a PostMediaHash object.
     *
     * @param hashStr   The hash value as a string.
     * @param algorithm The algorithm used to generate the hash.
     */
    PostMediaHash(String hashStr, String algorithm) {
        this.hashStr = hashStr;
        this.algorithm = algorithm;
    }

    /**
     * Creates a PostMediaHash object from a Hash instance.
     *
     * @param hash The Hash instance to convert from.
     * @return A PostMediaHash object with data from the Hash instance.
     */
    public static PostMediaHash from(Hash hash) {
        return new PostMediaHash(hash.getValue(), hash.getAlgorithm());
    }

    /**
     * Converts the PostMediaHash object to a Hash instance.
     *
     * @return A Hash instance representing the PostMediaHash data.
     */
    public final Hash toModule() {
        return new Hash(algorithm, hashStr);
    }
}
