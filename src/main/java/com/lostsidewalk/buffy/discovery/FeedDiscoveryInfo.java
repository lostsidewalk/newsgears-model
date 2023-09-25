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
import java.util.Date;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

/**
 * Represents information about the discovery of a feed, including various details and error types.
 */
@Slf4j
@Data
public class FeedDiscoveryInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 112341230982130984L;

    /**
     * The unique identifier for the feed discovery.
     */
    Long id;

    /**
     * The URL of the feed.
     */
    @NotBlank
    String feedUrl;

    /**
     * The HTTP status code received when accessing the feed URL.
     */
    Integer httpStatusCode;

    /**
     * The HTTP status message associated with the response from the feed URL.
     */
    String httpStatusMessage;

    /**
     * The URL to which the feed URL was redirected, if applicable.
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
     * The title of the feed.
     */
    ContentObject title;

    /**
     * The description of the feed.
     */
    ContentObject description;

    /**
     * The type of the feed (e.g., RSS, Atom).
     */
    String feedType;

    /**
     * The author of the feed.
     */
    String author;

    /**
     * The copyright information of the feed.
     */
    String copyright;

    /**
     * The documentation URL for the feed format.
     */
    String docs;

    /**
     * The character encoding used by the feed.
     */
    String encoding;

    /**
     * The feed generator information.
     */
    String generator;

    /**
     * The feed's image information.
     */
    FeedDiscoveryImageInfo image;

    /**
     * The feed's icon information.
     */
    FeedDiscoveryImageInfo icon;

    /**
     * The language of the feed.
     */
    String language;

    /**
     * The link associated with the feed.
     */
    String link;

    /**
     * The managing editor of the feed.
     */
    String managingEditor;

    /**
     * The published date of the feed.
     */
    Date publishedDate;

    /**
     * The feed's stylesheet information.
     */
    String styleSheet;

    /**
     * A list of supported content types in the feed.
     */
    List<String> supportedTypes;

    /**
     * The webmaster of the feed.
     */
    String webMaster;

    /**
     * The URI associated with the feed.
     */
    String uri;

    /**
     * The categories associated with the feed.
     */
    List<String> categories;

    /**
     * A list of sample entries from the feed.
     */
    List<StagingPost> sampleEntries;

    /**
     * Indicates whether the feed URL is upgradable (can be redirected to a more secure URL).
     */
    boolean isUrlUpgradable;

    /**
     * The type of feed discovery exception.
     */
    FeedDiscoveryExceptionType errorType;

    /**
     * Additional details about the feed discovery error.
     */
    String errorDetail;
    /**
     * A list of foreign markup strings encountered in the feed's content.
     */
    @Setter(PRIVATE)
    @Getter(PACKAGE)
    List<String> feedForeignMarkupStrs;
    /**
     * A set of foreign markup strings encountered in the post content.
     */
    @Setter(PRIVATE)
    @Getter(PACKAGE)
    Set<String> postForeignMarkupStrs;

    //
    //
    //

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
            boolean isUrlUpgradable) {
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

    /**
     * Creates and returns a new instance of {@link FeedDiscoveryInfo} with the specified parameters.
     *
     * @param feedUrl                   The URL of the feed.
     * @param httpStatusCode            The HTTP status code received when accessing the feed URL.
     * @param httpStatusMessage         The HTTP status message associated with the response from the feed URL.
     * @param redirectFeedUrl           The URL to which the feed URL was redirected, if applicable.
     * @param redirectHttpStatusCode    The HTTP status code received when accessing the redirected URL, if applicable.
     * @param redirectHttpStatusMessage The HTTP status message associated with the response from the redirected URL, if applicable.
     * @param title                     The title of the feed.
     * @param description               The description of the feed.
     * @param feedType                  The type of the feed (e.g., RSS, Atom).
     * @param author                    The author of the feed.
     * @param copyright                 The copyright information of the feed.
     * @param docs                      The documentation URL for the feed format.
     * @param encoding                  The character encoding used by the feed.
     * @param generator                 The feed generator information.
     * @param image                     The feed's image information.
     * @param icon                      The feed's icon information.
     * @param language                  The language of the feed.
     * @param link                      The link associated with the feed.
     * @param managingEditor            The managing editor of the feed.
     * @param publishedDate             The published date of the feed.
     * @param styleSheet                The feed's stylesheet information.
     * @param supportedTypes            A list of supported content types in the feed.
     * @param webMaster                 The webmaster of the feed.
     * @param uri                       The URI associated with the feed.
     * @param categories                The categories associated with the feed.
     * @param sampleEntries             A list of sample entries from the feed.
     * @param isUrlUpgradable           Indicates whether the feed URL is upgradable (can be redirected to a more secure URL).
     * @return A new instance of {@link FeedDiscoveryInfo} with the specified parameters.
     */
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
                                         boolean isUrlUpgradable) {
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

    /**
     * Enumerates different types of exceptions that can occur during feed discovery.
     */
    @SuppressWarnings("unused")
    public enum FeedDiscoveryExceptionType {
        /**
         * Indicates a "File Not Found" exception occurred during feed discovery.
         */
        FILE_NOT_FOUND_EXCEPTION,

        /**
         * Indicates an "SSL Handshake" exception occurred during feed discovery.
         */
        SSL_HANDSHAKE_EXCEPTION,

        /**
         * Indicates an "IO" exception occurred during feed discovery.
         */
        IO_EXCEPTION,

        /**
         * Indicates an "Unknown Host" exception occurred during feed discovery.
         */
        UNKNOWN_HOST_EXCEPTION,

        /**
         * Indicates a "Socket Timeout" exception occurred during feed discovery.
         */
        SOCKET_TIMEOUT_EXCEPTION,

        /**
         * Indicates a "Socket" exception occurred during feed discovery.
         */
        SOCKET_EXCEPTION,

        /**
         * Indicates a "Connect" exception occurred during feed discovery.
         */
        CONNECT_EXCEPTION,

        /**
         * Indicates an "Illegal Argument" exception occurred during feed discovery.
         */
        ILLEGAL_ARGUMENT_EXCEPTION,

        /**
         * Indicates a "Parsing Feed" exception occurred during feed discovery.
         */
        PARSING_FEED_EXCEPTION,

        /**
         * Indicates an unsecured redirection occurred during feed discovery.
         */
        UNSECURE_REDIRECT,

        /**
         * Indicates too many redirections occurred during feed discovery.
         */
        TOO_MANY_REDIRECTS,

        /**
         * Indicates an HTTP client error occurred during feed discovery.
         */
        HTTP_CLIENT_ERROR,

        /**
         * Indicates an HTTP server error occurred during feed discovery.
         */
        HTTP_SERVER_ERROR,

        /**
         * Indicates any other type of exception occurred during feed discovery.
         */
        OTHER
    }

    /**
     * Represents an exception related to feed discovery, providing details about the exception type, associated URLs,
     * and HTTP status codes.
     */
    @SuppressWarnings("unused")
    public static class FeedDiscoveryException extends Exception {

        /**
         * The URL of the feed that triggered the exception.
         */
        public final String feedUrl;

        /**
         * The HTTP status code associated with the feed request.
         */
        public final Integer httpStatusCode;

        /**
         * The HTTP status message associated with the feed request.
         */
        public final String httpStatusMessage;

        /**
         * The URL to which the feed request was redirected.
         */
        public final String redirectUrl;

        /**
         * The HTTP status code associated with the redirect, if any.
         */
        public final Integer redirectHttpStatusCode;

        /**
         * The HTTP status message associated with the redirect, if any.
         */
        public final String redirectHttpStatusMessage;

        /**
         * The type of feed discovery exception.
         */
        public final FeedDiscoveryExceptionType exceptionType;

        /**
         * Constructs a FeedDiscoveryException with detailed information about the exception.
         *
         * @param feedUrl                   The URL of the feed that triggered the exception.
         * @param httpStatusCode            The HTTP status code associated with the feed request.
         * @param httpStatusMessage         The HTTP status message associated with the feed request.
         * @param redirectUrl               The URL to which the feed request was redirected.
         * @param redirectHttpStatusCode    The HTTP status code associated with the redirect, if any.
         * @param redirectHttpStatusMessage The HTTP status message associated with the redirect, if any.
         * @param exceptionType             The type of feed discovery exception.
         */
        public FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
                               String redirectUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage,
                               FeedDiscoveryExceptionType exceptionType) {
            super(exceptionType.name());
            this.feedUrl = feedUrl;
            this.httpStatusCode = httpStatusCode;
            this.httpStatusMessage = httpStatusMessage;
            this.redirectUrl = redirectUrl;
            this.redirectHttpStatusCode = redirectHttpStatusCode;
            this.redirectHttpStatusMessage = redirectHttpStatusMessage;
            this.exceptionType = exceptionType;
        }

        /**
         * Constructs a FeedDiscoveryException with detailed information about the exception derived from an underlying
         * exception.
         *
         * @param feedUrl                   The URL of the feed that triggered the exception.
         * @param httpStatusCode            The HTTP status code associated with the feed request.
         * @param httpStatusMessage         The HTTP status message associated with the feed request.
         * @param redirectUrl               The URL to which the feed request was redirected.
         * @param redirectHttpStatusCode    The HTTP status code associated with the redirect, if any.
         * @param redirectHttpStatusMessage The HTTP status message associated with the redirect, if any.
         * @param exception                 The underlying exception that caused this FeedDiscoveryException.
         */
        public FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
                               String redirectUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage,
                               Exception exception) {
            super(exception.getMessage(), exception);
            this.feedUrl = feedUrl;
            this.httpStatusCode = httpStatusCode;
            this.httpStatusMessage = httpStatusMessage;
            this.redirectUrl = redirectUrl;
            this.redirectHttpStatusCode = redirectHttpStatusCode;
            this.redirectHttpStatusMessage = redirectHttpStatusMessage;

            // Determine the exception type based on the underlying exception class.
            if (exception instanceof FileNotFoundException) {
                this.exceptionType = FeedDiscoveryExceptionType.FILE_NOT_FOUND_EXCEPTION;
            } else if (exception instanceof SSLHandshakeException) {
                this.exceptionType = FeedDiscoveryExceptionType.SSL_HANDSHAKE_EXCEPTION;
            } else if (exception instanceof UnknownHostException) {
                this.exceptionType = FeedDiscoveryExceptionType.UNKNOWN_HOST_EXCEPTION;
            } else if (exception instanceof SocketTimeoutException) {
                this.exceptionType = FeedDiscoveryExceptionType.SOCKET_TIMEOUT_EXCEPTION;
            } else if (exception instanceof ConnectException) {
                this.exceptionType = FeedDiscoveryExceptionType.CONNECT_EXCEPTION;
            } else if (exception instanceof SocketException) {
                this.exceptionType = FeedDiscoveryExceptionType.SOCKET_EXCEPTION;
            } else if (exception instanceof IllegalArgumentException) {
                this.exceptionType = FeedDiscoveryExceptionType.ILLEGAL_ARGUMENT_EXCEPTION;
            } else if (exception instanceof ParsingFeedException) {
                this.exceptionType = FeedDiscoveryExceptionType.PARSING_FEED_EXCEPTION;
            } else if (exception instanceof IOException) {
                this.exceptionType = FeedDiscoveryExceptionType.IO_EXCEPTION;
            } else {
                this.exceptionType = FeedDiscoveryExceptionType.OTHER;
            }
        }
    }
}
