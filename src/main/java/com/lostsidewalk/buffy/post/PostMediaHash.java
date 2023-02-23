package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Hash;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaHash implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231231249823L;

    private String hashStr;

    private String algorithm;

    PostMediaHash(String hashStr, String algorithm) {
        this.hashStr = hashStr;
        this.algorithm = algorithm;
    }

    public static PostMediaHash from(Hash t) {
        return new PostMediaHash(t.getValue(), t.getAlgorithm());
    }

    public Hash toModule() {
        return new Hash(algorithm, hashStr);
    }
}
