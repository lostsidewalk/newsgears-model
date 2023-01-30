package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Status;
import lombok.Data;

@Data
public class PostMediaStatus {

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
