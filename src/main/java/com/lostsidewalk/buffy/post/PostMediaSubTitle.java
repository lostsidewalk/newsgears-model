package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.SubTitle;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a media subtitle associated with a post's media.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaSubTitle implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549342344433L;

    /**
     * The type of the subtitle.
     */
    String type;

    /**
     * The URL of the subtitle.
     */
    URL href;

    /**
     * The language of the subtitle.
     */
    String lang;

    /**
     * Constructor to create a PostMediaSubTitle object.
     */
    PostMediaSubTitle(String type, URL href, String lang) {
        this.type = type;
        this.href = href;
        this.lang = lang;
    }


    /**
     * Creates a PostMediaSubTitle object from a SubTitle instance.
     *
     * @param t The SubTitle instance to convert from.
     * @return A PostMediaSubTitle object with data from the SubTitle instance.
     */
    public static PostMediaSubTitle from(SubTitle t) {
        return new PostMediaSubTitle(t.getType(), t.getHref(), t.getLang());
    }

    /**
     * Converts the PostMediaSubTitle object to a SubTitle instance.
     *
     * @return A SubTitle instance representing the PostMediaSubTitle data.
     */
    public final SubTitle toModule() {
        SubTitle title = new SubTitle();
        title.setType(type);
        title.setHref(href);
        title.setLang(lang);

        return title;
    }
}
