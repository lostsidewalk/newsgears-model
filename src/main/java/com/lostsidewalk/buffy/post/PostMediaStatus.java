package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents the status of a media associated with a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStatus implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549342344343412L;

    /**
     * The reason for the status.
     */
    String reason;

    /**
     * The state of the status.
     */
    Status.State state;

    /**
     * Constructor to create a PostMediaStatus object.
     *
     * @param reason The reason for the status.
     * @param state  The state of the status.
     */
    PostMediaStatus(String reason, Status.State state) {
        this.reason = reason;
        this.state = state;
    }

    /**
     * Creates a PostMediaStatus object from a Status instance.
     *
     * @param status The Status instance to convert from.
     * @return A PostMediaStatus object with data from the Status instance.
     */
    public static PostMediaStatus from(Status status) {
        return new PostMediaStatus(status.getReason(), status.getState());
    }

    /**
     * Converts the PostMediaStatus object to a Status instance.
     *
     * @return A Status instance representing the PostMediaStatus data.
     */
    public final Status toModule() {
        Status status = new Status();
        status.setReason(reason);
        status.setState(state);

        return status;
    }
}
