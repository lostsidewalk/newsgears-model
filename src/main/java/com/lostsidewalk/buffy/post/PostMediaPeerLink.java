package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.PeerLink;
import lombok.Data;

import java.net.URL;

@Data
public class PostMediaPeerLink {

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
