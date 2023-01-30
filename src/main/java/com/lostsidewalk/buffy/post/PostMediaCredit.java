package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Credit;
import lombok.Data;

@Data
public class PostMediaCredit {

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
