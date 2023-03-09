package com.lostsidewalk.buffy.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class QueryDefinition implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823234223L;

    Long id;

    @NotNull
    Long feedId;

    @NotBlank
    String username;

    String queryTitle;

    String queryImageUrl;

    @NotBlank
    String queryText;

    @NotBlank
    String queryType;

    private Serializable queryConfig;

    private QueryDefinition(Long feedId, String username, String queryTitle, String queryImageUrl, String queryText, String queryType, Serializable queryConfig) {
        this.feedId = feedId;
        this.username = username;
        this.queryTitle = queryTitle;
        this.queryImageUrl = queryImageUrl;
        this.queryText = queryText;
        this.queryType = queryType;
        this.queryConfig = queryConfig;
    }

    @SuppressWarnings("unused")
    public static QueryDefinition from(Long feedId, String username, String queryTitle, String queryText, String queryType, Serializable queryConfig) {
        return new QueryDefinition(feedId, username, queryTitle, null, queryText, queryType, queryConfig);
    }

    @SuppressWarnings("unused")
    public static QueryDefinition from(Long feedId, String username, String queryTitle, String queryImageUrl, String queryText, String queryType, Serializable queryConfig) {
        return new QueryDefinition(feedId, username, queryTitle, queryImageUrl, queryText, queryType, queryConfig);
    }
}
