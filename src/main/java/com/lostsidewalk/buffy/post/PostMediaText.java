package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a text element associated with media in a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaText implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354934334112L;

    /**
     * Type of the text element.
     */
    String type;

    /**
     * End time of the text element.
     */
    Time end;

    /**
     * Start time of the text element.
     */
    Time start;

    /**
     * Value of the text element.
     */
    String value;

    /**
     * Creates a new instance of PostMediaText.
     *
     * @param type  Type of the text element.
     * @param end   End time of the text element.
     * @param start Start time of the text element.
     * @param value Value of the text element.
     */
    PostMediaText(String type, Time end, Time start, String value) {
        this.type = type;
        this.end = end;
        this.start = start;
        this.value = value;
    }

    /**
     * Creates a new instance of PostMediaText from a Text object.
     *
     * @param t The Text object to convert.
     * @return A new PostMediaText instance.
     */
    public static PostMediaText from(Text t) {
        return new PostMediaText(t.getType(), t.getEnd(), t.getStart(), t.getValue());
    }

    /**
     * Converts the PostMediaText instance to a Text object.
     *
     * @return A Text object.
     */
    public final Text toModule() {
        return new Text(type, value, start, end);
    }
}
