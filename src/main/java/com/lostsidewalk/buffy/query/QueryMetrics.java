package com.lostsidewalk.buffy.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class QueryMetrics implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231123323L;

    Long id;

    @NotBlank
    Long queryId;

    // HTTP results
    Integer httpStatusCode;
    String httpStatusMessage;
    String redirectFeedUrl;
    Integer redirectHttpStatusCode;
    String redirectHttpStatusMessage;

    // timestamp
    Date importTimestamp;

    // import ct
    Integer importCt;

    // persist ct
    Integer persistCt;

    // error type
    QueryExceptionType errorType;

    // error details
    String errorDetail;

    private QueryMetrics(Long queryId, Integer httpStatusCode, String httpStatusMessage, String redirectFeedUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage, Date importTimestamp, Integer importCt) {
        this.queryId = queryId;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMessage = httpStatusMessage;
        this.redirectFeedUrl = redirectFeedUrl;
        this.redirectHttpStatusCode = redirectHttpStatusCode;
        this.redirectHttpStatusMessage = redirectHttpStatusMessage;
        this.importTimestamp = importTimestamp;
        this.importCt = importCt;
    }

    @SuppressWarnings("unused")
    public static QueryMetrics from(Long queryId, Integer httpStatusCode, String httpStatusMessage, String redirectFeedUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage, Date importTimestamp, Integer importCt) {
        return new QueryMetrics(queryId, httpStatusCode, httpStatusMessage, redirectFeedUrl, redirectHttpStatusCode, redirectHttpStatusMessage, importTimestamp, importCt);
    }

    @SuppressWarnings("unused")
    public static QueryMetrics from(Long queryId, Date importTimestamp, Integer importCt) {
        return new QueryMetrics(queryId, null, null, null, null, null, importTimestamp, importCt);
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
