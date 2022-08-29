package com.lostsidewalk.buffy;

import lombok.Data;

@Data
public class FeedDefinition {

    private Long id;

    private String ident;

    private String title;

    private String description;

    private String generator;

    private String transportIdent;

    FeedDefinition(String ident, String title, String description, String generator, String transportIdent) {
        this.ident = ident;
        this.title = title;
        this.description = description;
        this.generator = generator;
        this.transportIdent = transportIdent;
    }
}
