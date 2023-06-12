package com.lostsidewalk.buffy.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class SubscriptionMetrics implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231123323L;

    Long id;

    @NotBlank
    Long subscriptionId;

    // HTTP results
    Integer httpStatusCode;
    String httpStatusMessage;
    String redirectFeedUrl;
    Integer redirectHttpStatusCode;
    String redirectHttpStatusMessage;

    // import timestamp
    Date importTimestamp;

    // import schedule
    String importSchedule;

    // import ct
    Integer importCt;

    // persist ct
    Integer persistCt;

    // skip ct
    Integer skipCt;

    // archive ct
    Integer archiveCt;

    // error type
    QueryExceptionType errorType;

    // error details
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

    @SuppressWarnings("unused")
    public static SubscriptionMetrics from(Long subscriptionId, Integer httpStatusCode, String httpStatusMessage, String redirectFeedUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage, Date importTimestamp, String importSchedule, Integer importCt) {
        return new SubscriptionMetrics(subscriptionId, httpStatusCode, httpStatusMessage, redirectFeedUrl, redirectHttpStatusCode, redirectHttpStatusMessage, importTimestamp, importSchedule, importCt);
    }

    @SuppressWarnings("unused")
    public static SubscriptionMetrics from(Long subscriptionId, Date importTimestamp, String importSchedule, Integer importCt) {
        return new SubscriptionMetrics(subscriptionId, null, null, null, null, null, importTimestamp, importSchedule, importCt);
    }

    //
    //
    //

    @SuppressWarnings("unused")
    public enum QueryExceptionType {
        FILE_NOT_FOUND_EXCEPTION,
        SSL_HANDSHAKE_EXCEPTION,
        IO_EXCEPTION,
        UNKNOWN_HOST_EXCEPTION,
        SOCKET_TIMEOUT_EXCEPTION,
        SOCKET_EXCEPTION,
        CONNECT_EXCEPTION,
        ILLEGAL_ARGUMENT_EXCEPTION,
        PARSING_FEED_EXCEPTION,
        UNSECURE_REDIRECT,
        TOO_MANY_REDIRECTS,
        HTTP_CLIENT_ERROR,
        HTTP_SERVER_ERROR,
        PERMANENTLY_REDIRECTED,
        OTHER
    }
}
