package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

@Data
public class PostMediaText {

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
