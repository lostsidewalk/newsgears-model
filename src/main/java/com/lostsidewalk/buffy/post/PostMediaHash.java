package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Hash;
import lombok.Data;

@Data
public class PostMediaHash {

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
