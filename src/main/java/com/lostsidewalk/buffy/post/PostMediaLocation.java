package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Location;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaLocation implements Serializable {

    @Serial
    private static final long serialVersionUID = 223123124554823L;

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
