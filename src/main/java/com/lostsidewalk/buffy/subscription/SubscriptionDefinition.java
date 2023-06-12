package com.lostsidewalk.buffy.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class SubscriptionDefinition implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823234223L;

    Long id;

    @NotNull
    Long queueId;

    @NotBlank
    String username;

    String title;

    String imgUrl;

    @NotBlank
    String url;

    @NotBlank
    String queryType;

    String importSchedule;

    private Serializable queryConfig;

    private SubscriptionDefinition(Long queueId, String username, String title, String imgUrl, String url, String queryType, String importSchedule, Serializable queryConfig) {
        this.queueId = queueId;
        this.username = username;
        this.title = title;
        this.imgUrl = imgUrl;
        this.url = url;
        this.queryType = queryType;
        this.importSchedule = importSchedule;
        this.queryConfig = queryConfig;
    }

    @SuppressWarnings("unused")
    public static SubscriptionDefinition from(Long queueId, String username, String title, String url, String queryType, String importSchedule, Serializable queryConfig) {
        return new SubscriptionDefinition(queueId, username, title, null, url, queryType, importSchedule, queryConfig);
    }

    @SuppressWarnings("unused")
    public static SubscriptionDefinition from(Long queueId, String username, String title, String imgUrl, String url, String queryType, String importSchedule, Serializable queryConfig) {
        return new SubscriptionDefinition(queueId, username, title, imgUrl, url, queryType, importSchedule, queryConfig);
    }
}
