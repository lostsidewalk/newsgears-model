package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Status;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStatus implements Serializable {

    @Serial
    private static final long serialVersionUID = 23549342344343412L;

    private String reason;

    private Status.State state;

    PostMediaStatus(String reason, Status.State state) {
        this.reason = reason;
        this.state = state;
    }

    public static PostMediaStatus from(Status t) {
        return new PostMediaStatus(t.getReason(), t.getState());
    }

    public Status toModule() {
        Status s = new Status();
        s.setReason(reason);
        s.setState(state);

        return s;
    }
}
