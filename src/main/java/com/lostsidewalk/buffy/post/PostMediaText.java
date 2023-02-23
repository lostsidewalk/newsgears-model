package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaText implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354934334112L;

    String type;

    Time end;

    Time start;

    String value;

    PostMediaText(String type, Time end, Time start, String value) {
        this.type = type;
        this.end = end;
        this.start = start;
        this.value = value;
    }

    public static PostMediaText from(Text t) {
        return new PostMediaText(t.getType(), t.getEnd(), t.getStart(), t.getValue());
    }

    public Text toModule() {
        return new Text(type, value, start, end);
    }
}
