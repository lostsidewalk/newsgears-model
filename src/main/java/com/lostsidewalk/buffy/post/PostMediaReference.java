package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Reference;
import com.rometools.modules.mediarss.types.UrlReference;
import lombok.Data;

import java.net.URI;

@Data
public class PostMediaReference {

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
