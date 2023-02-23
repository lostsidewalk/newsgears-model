package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Scene;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaScene implements Serializable {

    @Serial
    private static final long serialVersionUID = 88882213335442343L;

    String description;

    String title;

    Time startTime;

    Time endTime;

    PostMediaScene(String description, String title, Time startTime, Time endTime) {
        this.description = description;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static PostMediaScene from(Scene t) {
        return new PostMediaScene(t.getDescription(), t.getTitle(), t.getStartTime(), t.getEndTime());
    }

    public Scene toModule() {
        Scene s = new Scene();
        s.setDescription(description);
        s.setTitle(title);
        s.setStartTime(startTime);
        s.setEndTime(endTime);

        return s;
    }
}
