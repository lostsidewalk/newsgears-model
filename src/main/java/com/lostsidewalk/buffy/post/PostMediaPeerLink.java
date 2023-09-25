package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.PeerLink;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a peer link associated with media in a post.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaPeerLink implements Serializable {

    @Serial
    private static final long serialVersionUID = 18092731098L;

    /**
     * Type of the peer link.
     */
    String type;

    /**
     * URL of the peer link.
     */
    URL href;

    /**
     * Creates a new instance of PostMediaPeerLink.
     *
     * @param type Type of the peer link.
     * @param href URL of the peer link.
     */
    PostMediaPeerLink(String type, URL href) {
        this.type = type;
        this.href = href;
    }

    /**
     * Creates a new instance of PostMediaPeerLink from a PeerLink object.
     *
     * @param t The PeerLink object to convert.
     * @return A new PostMediaPeerLink instance.
     */
    public static PostMediaPeerLink from(PeerLink t) {
        return new PostMediaPeerLink(t.getType(), t.getHref());
    }

    /**
     * Converts the PostMediaPeerLink instance to a PeerLink object.
     *
     * @return A PeerLink object.
     */
    public PeerLink toModule() {
        PeerLink p = new PeerLink();
        p.setType(type);
        p.setHref(href);

        return p;
    }
}
