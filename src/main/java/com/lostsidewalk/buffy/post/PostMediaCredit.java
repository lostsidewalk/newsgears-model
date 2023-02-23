package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Credit;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaCredit implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823012319823L;

    private String name;

    private String role;

    private String scheme;

    PostMediaCredit(String name, String role, String scheme) {
        this.name = name;
        this.role = role;
        this.scheme = scheme;
    }

    public static PostMediaCredit from(Credit t) {
        return new PostMediaCredit(t.getName(), t.getRole(), t.getScheme());
    }

    public Credit toModule() {
        return new Credit(scheme, role, name);
    }
}
