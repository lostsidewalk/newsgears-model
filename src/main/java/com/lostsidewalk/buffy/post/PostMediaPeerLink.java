package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.PeerLink;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaPeerLink implements Serializable {

    @Serial
    private static final long serialVersionUID = 18092731098L;

    private String type;

    private URL href;

    PostMediaPeerLink(String type, URL href) {
        this.type = type;
        this.href = href;
    }

    public static PostMediaPeerLink from(PeerLink t) {
        return new PostMediaPeerLink(t.getType(), t.getHref());
    }

    public PeerLink toModule() {
        PeerLink p = new PeerLink();
        p.setType(type);
        p.setHref(href);

        return p;
    }
}
