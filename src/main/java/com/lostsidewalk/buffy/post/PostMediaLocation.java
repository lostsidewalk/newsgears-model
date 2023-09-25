package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Location;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a location associated with media in a post.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaLocation implements Serializable {

    @Serial
    private static final long serialVersionUID = 223123124554823L;

    /**
     * Description of the location.
     */
    String description;

    /**
     * Start time of the location.
     */
    Time start;

    /**
     * End time of the location.
     */
    Time end;

    /**
     * Creates a new instance of PostMediaLocation.
     *
     * @param description Description of the location.
     * @param start       Start time of the location.
     * @param end         End time of the location.
     */
    PostMediaLocation(String description, Time start, Time end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new instance of PostMediaLocation from a Location object.
     *
     * @param t The Location object to convert.
     * @return A new PostMediaLocation instance.
     */
    public static PostMediaLocation from(Location t) {
        return new PostMediaLocation(t.getDescription(), t.getStart(), t.getEnd());
    }

    /**
     * Converts the PostMediaLocation instance to a Location object.
     *
     * @return A Location object.
     */
    public Location toModule() {
        Location l = new Location();
        l.setDescription(description);
        l.setStart(start);
        l.setEnd(end);

        return l;
    }
}
