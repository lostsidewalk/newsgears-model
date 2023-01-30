package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.License;
import lombok.Data;

import java.net.URL;

@Data
public class PostMediaLicense {

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
