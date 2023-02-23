package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.License;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaLicense implements Serializable {

    @Serial
    private static final long serialVersionUID = 22228231231557823L;

    private String type;

    private URL href;

    private String value;

    PostMediaLicense(String type, URL href, String value) {
        this.type = type;
        this.href = href;
        this.value = value;
    }

    public static PostMediaLicense from(License t) {
        return new PostMediaLicense(t.getType(), t.getHref(), t.getValue());
    }

    public License toModule() {
        License l = new License();
        l.setType(type);
        l.setHref(href);
        l.setValue(value);

        return l;
    }
}
