package com.lostsidewalk.buffy.discovery;

import com.lostsidewalk.buffy.post.ContentObject;
import com.lostsidewalk.buffy.post.StagingPost;
import com.rometools.rome.io.ParsingFeedException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLHandshakeException;
import javax.validation.constraints.NotBlank;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.*;

import static com.lostsidewalk.buffy.discovery.FeedDiscoveryInfo.FeedDiscoveryExceptionType.*;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Slf4j
@Data
public class FeedDiscoveryInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 112341230982130984L;

    Long id;

    @NotBlank
    String feedUrl;

    // HTTP results
    Integer httpStatusCode;
    String httpStatusMessage;
    String redirectFeedUrl;
    Integer redirectHttpStatusCode;
    String redirectHttpStatusMessage;

    // content
    ContentObject title;
    ContentObject description;
    String feedType;
    String author;
    String copyright;
    String docs;
    String encoding;
    String generator;
    FeedDiscoveryImageInfo image;
    FeedDiscoveryImageInfo icon;
    String language;
    String link;
    String managingEditor;
    Date publishedDate;
    String styleSheet;
    List<String> supportedTypes;
    String webMaster;
    String uri;
    List<String> categories;
    List<StagingPost> sampleEntries;
    boolean isUrlUpgradable;

    // errors
    FeedDiscoveryExceptionType errorType;

    String errorDetail;

    private FeedDiscoveryInfo(
            String feedUrl,
            Integer httpStatusCode,
            String httpStatusMessage,
            String redirectFeedUrl,
            Integer redirectHttpStatusCode,
            String redirectHttpStatusMessage,
            ContentObject title,
            ContentObject description,
            String feedType,
            String author,
            String copyright,
            String docs,
            String encoding,
            String generator,
            FeedDiscoveryImageInfo image,
            FeedDiscoveryImageInfo icon,
            String language,
            String link,
            String managingEditor,
            Date publishedDate,
            String styleSheet,
            List<String> supportedTypes,
            String webMaster,
            String uri,
            List<String> categories,
            List<StagingPost> sampleEntries,
            boolean isUrlUpgradable)
    {
        this.feedUrl = feedUrl;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMessage = httpStatusMessage;
        this.redirectFeedUrl = redirectFeedUrl;
        this.redirectHttpStatusCode = redirectHttpStatusCode;
        this.redirectHttpStatusMessage = redirectHttpStatusMessage;
        this.title = title;
        this.description = description;
        this.feedType = feedType;
        this.author = author;
        this.copyright = copyright;
        this.docs = docs;
        this.encoding = encoding;
        this.generator = generator;
        this.image = image;
        this.icon = icon;
        this.language = language;
        this.link = link;
        this.managingEditor = managingEditor;
        this.publishedDate = publishedDate;
        this.styleSheet = styleSheet;
        this.supportedTypes = supportedTypes;
        this.webMaster = webMaster;
        this.uri = uri;
        this.categories = categories;
        this.sampleEntries = sampleEntries;
        this.isUrlUpgradable = isUrlUpgradable;
    }

    public static FeedDiscoveryInfo from(String feedUrl,
                                         Integer httpStatusCode,
                                         String httpStatusMessage,
                                         String redirectFeedUrl,
                                         Integer redirectHttpStatusCode,
                                         String redirectHttpStatusMessage,
                                         ContentObject title,
                                         ContentObject description,
                                         String feedType,
                                         String author,
                                         String copyright,
                                         String docs,
                                         String encoding,
                                         String generator,
                                         FeedDiscoveryImageInfo image,
                                         FeedDiscoveryImageInfo icon,
                                         String language,
                                         String link,
                                         String managingEditor,
                                         Date publishedDate,
                                         String styleSheet,
                                         List<String> supportedTypes,
                                         String webMaster,
                                         String uri,
                                         List<String> categories,
                                         List<StagingPost> sampleEntries,
                                         boolean isUrlUpgradable)
    {
        return new FeedDiscoveryInfo(
                feedUrl,
                httpStatusCode,
                httpStatusMessage,
                redirectFeedUrl,
                redirectHttpStatusCode,
                redirectHttpStatusMessage,
                title,
                description,
                feedType,
                author,
                copyright,
                docs,
                encoding,
                generator,
                image,
                icon,
                language,
                link,
                managingEditor,
                publishedDate,
                styleSheet,
                supportedTypes,
                webMaster,
                uri,
                categories,
                sampleEntries,
                isUrlUpgradable
        );
    }

    //
    //
    //

    @SuppressWarnings("unused")
    public enum FeedDiscoveryExceptionType {
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
        OTHER
    }

    @SuppressWarnings("unused")
    public static class FeedDiscoveryException extends Exception {

        public final String feedUrl;

        public final Integer httpStatusCode;

        public final String httpStatusMessage;

        public final String redirectUrl;

        public final Integer redirectHttpStatusCode;

        public final String redirectHttpStatusMessage;

        public final FeedDiscoveryExceptionType exceptionType;

        // url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage,
        public FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
                               String redirectUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage,
                               FeedDiscoveryExceptionType exceptionType)
        {
            super(exceptionType.name());
            this.feedUrl = feedUrl;
            this.httpStatusCode = httpStatusCode;
            this.httpStatusMessage = httpStatusMessage;
            this.redirectUrl = redirectUrl;
            this.redirectHttpStatusCode = redirectHttpStatusCode;
            this.redirectHttpStatusMessage = redirectHttpStatusMessage;
            this.exceptionType = exceptionType;
        }

        public FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
                               String redirectUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage,
                               Exception exception)
        {
            super(exception.getMessage(), exception);
            this.feedUrl = feedUrl;
            this.httpStatusCode = httpStatusCode;
            this.httpStatusMessage = httpStatusMessage;
            this.redirectUrl = redirectUrl;
            this.redirectHttpStatusCode = redirectHttpStatusCode;
            this.redirectHttpStatusMessage = redirectHttpStatusMessage;
            if (exception instanceof FileNotFoundException) {
                this.exceptionType = FILE_NOT_FOUND_EXCEPTION;
            } else if (exception instanceof SSLHandshakeException) {
                this.exceptionType = SSL_HANDSHAKE_EXCEPTION;
            } else if (exception instanceof UnknownHostException) {
                this.exceptionType = UNKNOWN_HOST_EXCEPTION;
            } else if (exception instanceof SocketTimeoutException) {
                this.exceptionType = SOCKET_TIMEOUT_EXCEPTION;
            }else if (exception instanceof ConnectException) {
                this.exceptionType = CONNECT_EXCEPTION;
            } else if (exception instanceof SocketException) {
                this.exceptionType = SOCKET_EXCEPTION;
            } else if (exception instanceof IllegalArgumentException) {
                this.exceptionType = ILLEGAL_ARGUMENT_EXCEPTION;
            } else if (exception instanceof ParsingFeedException) {
                this.exceptionType = PARSING_FEED_EXCEPTION;
            } else if (exception instanceof IOException) {
                this.exceptionType = IO_EXCEPTION;
            } else {
                this.exceptionType = OTHER;
            }
        }
    }

    @Setter(PRIVATE)
    @Getter(PACKAGE)
    List<String> feedForeignMarkupStrs;

    @Setter(PRIVATE)
    @Getter(PACKAGE)
    Set<String> postForeignMarkupStrs;
}
