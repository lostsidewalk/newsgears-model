package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Reference;
import com.rometools.modules.mediarss.types.UrlReference;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaReference implements Serializable {

    @Serial
    private static final long serialVersionUID = 45452133354944442L;

    private URI uri;

    PostMediaReference(URI uri) {
        this.uri = uri;
    }

    public static PostMediaReference from(Reference reference) {
        if (reference instanceof UrlReference) {
            return new PostMediaReference(((UrlReference) reference).getUrl());
        } else if (reference instanceof PlayerReference) {
            return new PostMediaReference(((PlayerReference) reference).getUrl());
        }

        return null;
    }

    public Reference toUrlModule() {
        return new UrlReference(uri);
    }

    @SuppressWarnings("unused") // incl for the sake of completeness
    public Reference toPlayerModule() {
        return new PlayerReference(uri);
    }
}
