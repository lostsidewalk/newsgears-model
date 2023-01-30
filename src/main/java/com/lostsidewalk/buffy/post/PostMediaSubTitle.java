package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.SubTitle;
import lombok.Data;

import java.net.URL;

@Data
public class PostMediaSubTitle {

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
