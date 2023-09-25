package com.lostsidewalk.buffy.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The SubscriptionMetrics class represents metrics related to a subscription.
 */
@Data
@JsonInclude(NON_EMPTY)
public class SubscriptionMetrics implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231123323L;

    /**
     * The unique identifier for the subscription metrics.
     */
    Long id;

    /**
     * The ID of the associated subscription.
     */
    @NotBlank
    Long subscriptionId;

    /**
     * The HTTP status code received during the import process.
     */
    Integer httpStatusCode;

    /**
     * The HTTP status message received during the import process.
     */
    String httpStatusMessage;

    /**
     * The URL to which the feed was redirected during import.
     */
    String redirectFeedUrl;

    /**
     * The HTTP status code received after redirection during import.
     */
    Integer redirectHttpStatusCode;

    /**
     * The HTTP status message received after redirection during import.
     */
    String redirectHttpStatusMessage;

    /**
     * The timestamp of the import process.
     */
    Date importTimestamp;

    /**
     * The schedule of the import process.
     */
    String importSchedule;

    /**
     * The count of items imported.
     */
    Integer importCt;

    /**
     * The count of items persisted.
     */
    Integer persistCt;

    /**
     * The count of items skipped.
     */
    Integer skipCt;

    /**
     * The count of items archived.
     */
    Integer archiveCt;

    /**
     * The type of query exception, if an error occurred during the import process.
     */
    QueryExceptionType errorType;

    /**
     * Additional details about the error, if one occurred during the import process.
     */
    String errorDetail;

    private SubscriptionMetrics(Long subscriptionId, Integer httpStatusCode, String httpStatusMessage, String redirectFeedUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage, Date importTimestamp, String importSchedule, Integer importCt) {
        this.subscriptionId = subscriptionId;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMessage = httpStatusMessage;
        this.redirectFeedUrl = redirectFeedUrl;
        this.redirectHttpStatusCode = redirectHttpStatusCode;
        this.redirectHttpStatusMessage = redirectHttpStatusMessage;
        this.importTimestamp = importTimestamp;
        this.importSchedule = importSchedule;
        this.importCt = importCt;
    }

    /**
     * Static factory method to create a SubscriptionMetrics object with HTTP-related metrics.
     *
     * @param subscriptionId            The ID of the subscription.
     * @param httpStatusCode            The HTTP status code.
     * @param httpStatusMessage         The HTTP status message.
     * @param redirectFeedUrl           The redirected feed URL.
     * @param redirectHttpStatusCode    The HTTP status code after redirection.
     * @param redirectHttpStatusMessage The HTTP status message after redirection.
     * @param importTimestamp           The timestamp of the import.
     * @param importSchedule            The import schedule.
     * @param importCt                  The import count.
     * @return A new SubscriptionMetrics instance with HTTP-related metrics.
     */
    @SuppressWarnings("unused")
    public static SubscriptionMetrics from(Long subscriptionId, Integer httpStatusCode, String httpStatusMessage, String redirectFeedUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage, Date importTimestamp, String importSchedule, Integer importCt) {
        return new SubscriptionMetrics(subscriptionId, httpStatusCode, httpStatusMessage, redirectFeedUrl, redirectHttpStatusCode, redirectHttpStatusMessage, importTimestamp, importSchedule, importCt);
    }

    /**
     * Static factory method to create a SubscriptionMetrics object with import-related metrics.
     *
     * @param subscriptionId  The ID of the subscription.
     * @param importTimestamp The timestamp of the import.
     * @param importSchedule  The import schedule.
     * @param importCt        The import count.
     * @return A new SubscriptionMetrics instance with import-related metrics.
     */
    @SuppressWarnings("unused")
    public static SubscriptionMetrics from(Long subscriptionId, Date importTimestamp, String importSchedule, Integer importCt) {
        return new SubscriptionMetrics(subscriptionId, null, null, null, null, null, importTimestamp, importSchedule, importCt);
    }

    /**
     * An enumeration of query exception types.
     */
    @SuppressWarnings("unused")
    public enum QueryExceptionType {
        /**
         * File not found exception.
         */
        FILE_NOT_FOUND_EXCEPTION,
        /**
         * SSL handshake exception.
         */
        SSL_HANDSHAKE_EXCEPTION,
        /**
         * I/O exception.
         */
        IO_EXCEPTION,
        /**
         * Unknown host exception.
         */
        UNKNOWN_HOST_EXCEPTION,
        /**
         * Socket timeout exception.
         */
        SOCKET_TIMEOUT_EXCEPTION,
        /**
         * Socket exception.
         */
        SOCKET_EXCEPTION,
        /**
         * Connect exception.
         */
        CONNECT_EXCEPTION,
        /**
         * Illegal argument exception.
         */
        ILLEGAL_ARGUMENT_EXCEPTION,
        /**
         * Parsing feed exception.
         */
        PARSING_FEED_EXCEPTION,
        /**
         * Unsecure redirect.
         */
        UNSECURE_REDIRECT,
        /**
         * Too many redirects.
         */
        TOO_MANY_REDIRECTS,
        /**
         * HTTP client error.
         */
        HTTP_CLIENT_ERROR,
        /**
         * HTTP server error.
         */
        HTTP_SERVER_ERROR,
        /**
         * Permanently redirected.
         */
        PERMANENTLY_REDIRECTED,
        /**
         * Other error.
         */
        OTHER
    }
}
