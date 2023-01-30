package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Scene;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

@Data
public class PostMediaScene {

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
