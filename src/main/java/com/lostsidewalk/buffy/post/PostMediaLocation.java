package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Location;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

@Data
public class PostMediaLocation {

    private String description;

    private Time start;

    private Time end;

    PostMediaLocation(String description, Time start, Time end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public static PostMediaLocation from(Location t) {
        return new PostMediaLocation(t.getDescription(), t.getStart(), t.getEnd());
    }

    public Location toModule() {
        Location l = new Location();
        l.setDescription(description);
        l.setStart(start);
        l.setEnd(end);

        return l;
    }
}
