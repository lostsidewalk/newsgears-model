package com.lostsidewalk.buffy.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a subscription definition that specifies how to import feed data.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class SubscriptionDefinition implements Serializable {

    /**
     * The serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 29823234223L;

    /**
     * The unique identifier of the subscription definition.
     */
    Long id;

    /**
     * The unique identifier of the associated queue.
     */
    @NotNull
    Long queueId;

    /**
     * The username associated with the subscription.
     */
    @NotBlank
    String username;

    /**
     * The title of the subscription.
     */
    String title;

    /**
     * The URL of an image associated with the subscription.
     */
    String imgUrl;

    /**
     * The URL of the feed to subscribe to.
     */
    @NotBlank
    String url;

    /**
     * The type of query used for importing feed data.
     */
    @NotBlank
    String queryType;

    /**
     * The import schedule for feed data.
     */
    String importSchedule;

    /**
     * The query configuration for the subscription.
     */
    private Serializable queryConfig;

    /**
     * Private constructor for creating a SubscriptionDefinition instance with all properties.
     *
     * @param queueId        The unique identifier of the associated queue.
     * @param username       The username associated with the subscription.
     * @param title          The title of the subscription.
     * @param imgUrl         The URL of an image associated with the subscription.
     * @param url            The URL of the feed to subscribe to.
     * @param queryType      The type of query used for importing feed data.
     * @param importSchedule The import schedule for feed data.
     * @param queryConfig    The query configuration for the subscription.
     */
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

    /**
     * Static factory method to create a SubscriptionDefinition object with minimal information.
     *
     * @param queueId        The unique identifier of the associated queue.
     * @param username       The username associated with the subscription.
     * @param title          The title of the subscription.
     * @param url            The URL of the feed to subscribe to.
     * @param queryType      The type of query used for importing feed data.
     * @param importSchedule The import schedule for feed data.
     * @param queryConfig    The query configuration for the subscription.
     * @return A new SubscriptionDefinition instance with minimal information.
     */
    @SuppressWarnings("unused")
    public static SubscriptionDefinition from(Long queueId, String username, String title, String url, String queryType, String importSchedule, Serializable queryConfig) {
        return new SubscriptionDefinition(queueId, username, title, null, url, queryType, importSchedule, queryConfig);
    }

    /**
     * Static factory method to create a SubscriptionDefinition object with complete information.
     *
     * @param queueId        The unique identifier of the associated queue.
     * @param username       The username associated with the subscription.
     * @param title          The title of the subscription.
     * @param imgUrl         The URL of an image associated with the subscription.
     * @param url            The URL of the feed to subscribe to.
     * @param queryType      The type of query used for importing feed data.
     * @param importSchedule The import schedule for feed data.
     * @param queryConfig    The query configuration for the subscription.
     * @return A new SubscriptionDefinition instance with complete information.
     */
    @SuppressWarnings("unused")
    public static SubscriptionDefinition from(Long queueId, String username, String title, String imgUrl, String url, String queryType, String importSchedule, Serializable queryConfig) {
        return new SubscriptionDefinition(queueId, username, title, imgUrl, url, queryType, importSchedule, queryConfig);
    }
}
