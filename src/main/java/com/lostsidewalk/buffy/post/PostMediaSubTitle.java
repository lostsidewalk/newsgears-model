package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.SubTitle;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaSubTitle implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549342344433L;

    String type;

    URL href;

    String lang;

    PostMediaSubTitle(String type, URL href, String lang) {
        this.type = type;
        this.href = href;
        this.lang = lang;
    }

    public static PostMediaSubTitle from(SubTitle t) {
        return new PostMediaSubTitle(t.getType(), t.getHref(), t.getLang());
    }

    public SubTitle toModule() {
        SubTitle title = new SubTitle();
        title.setType(type);
        title.setHref(href);
        title.setLang(lang);

        return title;
    }
}
