package com.lostsidewalk.buffy.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryDefinition {

    Long id;

    String feedIdent;

    String username;

    String queryText;

    String queryType;

    private Serializable queryConfig;

    private QueryDefinition(String feedIdent, String username, String queryText, String queryType, Serializable queryConfig) {
        this.feedIdent = feedIdent;
        this.username = username;
        this.queryText = queryText;
        this.queryType = queryType;
        this.queryConfig = queryConfig;
    }

    @SuppressWarnings("unused")
    public static QueryDefinition from(String feedIdent, String username, String queryText, String queryType, Serializable queryConfig) {
        return new QueryDefinition(feedIdent, username, queryText, queryType, queryConfig);
    }
}
