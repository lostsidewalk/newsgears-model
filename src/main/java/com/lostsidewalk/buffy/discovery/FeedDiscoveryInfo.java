package com.lostsidewalk.buffy.discovery;

import com.lostsidewalk.buffy.post.ContentObject;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.ParsingFeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Element;

import javax.net.ssl.SSLHandshakeException;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Stream;

import static com.lostsidewalk.buffy.discovery.FeedDiscoveryInfo.FeedDiscoveryExceptionType.*;
import static java.lang.Math.min;
import static java.net.URI.create;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.*;

@Slf4j
@Data
public class FeedDiscoveryInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 112341230982130984L;

    Long id;

    FeedDiscoveryExceptionType error;

    @NotBlank
    String feedUrl;

    Integer httpStatusCode;

    String httpStatusMessage;

    String redirectFeedUrl;

    Integer redirectHttpStatusCode;

    String redirectHttpStatusMessage;

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

    List<FeedDiscoverySampleItem> sampleEntries;

    boolean isUrlUpgradable;

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
            List<FeedDiscoverySampleItem> sampleEntries,
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
                                         List<FeedDiscoverySampleItem> sampleEntries,
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

    public static class FeedDiscoveryException extends Exception {

        public final String feedUrl;

        public final Integer httpStatusCode;

        public final String httpStatusMessage;

        public final String redirectUrl;

        public final Integer redirectHttpStatusCode;

        public final String redirectHttpStatusMessage;

        public final FeedDiscoveryExceptionType exceptionType;

        // url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage,
        FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
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

        FeedDiscoveryException(String feedUrl, Integer httpStatusCode, String httpStatusMessage,
                               String redirectUrl, Integer redirectHttpStatusCode, String redirectHttpStatusMessage,
                               Exception exception)
        {
            super(exception);
            this.feedUrl = feedUrl;
            this.httpStatusCode = httpStatusCode;
            this.httpStatusMessage = httpStatusMessage;
            this.redirectUrl = redirectUrl;
            this.redirectHttpStatusCode = redirectHttpStatusCode;
            this.redirectHttpStatusMessage = redirectHttpStatusMessage;
            if (exception instanceof FileNotFoundException) {
                exceptionType = FILE_NOT_FOUND_EXCEPTION;
            } else if (exception instanceof SSLHandshakeException) {
                exceptionType = SSL_HANDSHAKE_EXCEPTION;
            } else if (exception instanceof UnknownHostException) {
                exceptionType = UNKNOWN_HOST_EXCEPTION;
            } else if (exception instanceof SocketTimeoutException) {
                exceptionType = SOCKET_TIMEOUT_EXCEPTION;
            }else if (exception instanceof ConnectException) {
                exceptionType = CONNECT_EXCEPTION;
            } else if (exception instanceof SocketException) {
                exceptionType = SOCKET_EXCEPTION;
            } else if (exception instanceof IllegalArgumentException) {
                exceptionType = ILLEGAL_ARGUMENT_EXCEPTION;
            } else if (exception instanceof ParsingFeedException) {
                exceptionType = PARSING_FEED_EXCEPTION;
            } else if (exception instanceof IOException) {
                exceptionType = IO_EXCEPTION;
            } else {
                exceptionType = OTHER;
            }
        }
    }

    private static final String FEED_URL_FIELD_NAME = "feed_url";

    private static final String STATUS_MESSAGE_FIELD_NAME = "http_status_message";

    private static final String REDIRECT_URL_FIELD_NAME = "redirect_feed_url";

    private static final String REDIRECT_STATUS_MESSAGE_FIELD_NAME = "redirect_http_status_message";

    private static final String TITLE_FIELD_NAME = "title";

    private static final String DESCRIPTION_FIELD_NAME = "description";

    private static final String FEED_TYPE_FIELD_NAME = "feed_type";

    private static final String AUTHOR_FIELD_NAME = "author";

    private static final String COPYRIGHT_FIELD_NAME = "copyright";

    private static final String DOCS_FIELD_NAME = "docs";

    private static final String ENCODING_FIELD_NAME = "encoding";

    private static final String GENERATOR_FIELD_NAME = "generator";

    private static final String LANGUAGE_FIELD_NAME = "language";

    private static final String LINK_FIELD_NAME = "link";

    private static final String MANAGING_EDITOR_FIELD_NAME = "managing_editor";

    private static final String WEB_MASTER_FIELD_NAME = "web_master";

    private static final String URI_FIELD_NAME = "uri";

    private static final String CATEGORIES_FIELD_NAME = "categories";

    @SuppressWarnings("unused")
    public static FeedDiscoveryInfo discoverUrl(String url) throws FeedDiscoveryException {
        return discoverUrl(url, null, null);
    }

    public static FeedDiscoveryInfo discoverUrl(String url, String username, String password) throws FeedDiscoveryException {
        return discoverUrl(url, username, password, false, 0);
    }

    private static final String FEED_DISCOVERY_USER_AGENT = "Lost Sidewalk FeedGears RSS Aggregator v.0.3 feed discovery process";

    public static FeedDiscoveryInfo discoverUrl(String url, String username, String password, boolean followUnsecureRedirects, int depth) throws FeedDiscoveryException {
        log.debug("Performing feed discovery for URL={}", url);
        Integer statusCode = null;
        String statusMessage = null;
        String redirectUrl = null;
        Integer redirectStatusCode = null;
        String redirectStatusMessage = null;
        try {
            // setup the initial connection
            HttpURLConnection feedConnection = openFeedConnection(url);
            // add authentication, if any
            boolean hasAuthenticationHeaders = addAuthenticationHeader(feedConnection, username, password);
            // add the UA header
            addUserAgentHeader(feedConnection);
            // get the (initial) status response
            statusCode = getStatusCode(feedConnection);
            // get the (initial) status message
            statusMessage = getStatusMessage(feedConnection);
            // if this is a redirect...
            if (isRedirect(statusCode)) {
                // get the redirect location URL
                redirectUrl = feedConnection.getHeaderField("Location");
                // (check to broken redirect setups, e.g. http://www.virtualr.net/feed)
                if (depth > 2) {
                    throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, redirectStatusCode, null, OTHER); // (caught in an idiot loop of some sort)
                }
                // check for unsecure redirect
                boolean isUnsecureRedirect = "http".equalsIgnoreCase(feedConnection.getURL().getProtocol());
                // if this is an unsecure redirect and we have auth, bail
                // if this is an unsecure redirect (no auth), but we have been instructed not to trust such redirects, bail
                if (isUnsecureRedirect && (hasAuthenticationHeaders || !followUnsecureRedirects)) {
                    throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, null, null, UNSECURE_REDIRECT); // (http URL got redirected)
                }
                // open the redirect connection
                feedConnection = openFeedConnection(redirectUrl);
                // add authentication to the redirect, if any
                addAuthenticationHeader(feedConnection, username, password);
                // add the UA header to the redirect
                addUserAgentHeader(feedConnection);
                // get the redirect status response
                redirectStatusCode = getStatusCode(feedConnection);
                // get the redirect status message
                redirectStatusMessage = getStatusMessage(feedConnection);
                // if *this* is also a redirect...
                if (isRedirect(redirectStatusCode)) {
                    // TOO_MANY_REDIRECTS
                    throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage, TOO_MANY_REDIRECTS); // (redirect got redirected)
                }
                // if the redirect ends in CLIENT ERROR (response status 4xx)
                if (isClientError(redirectStatusCode)) {
                    // DISCOVERY_CLIENT_ERROR
                    throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage, HTTP_CLIENT_ERROR); // (client error status on redirect)
                    // DISCOVERY_SERVER_ERROR
                } else if (isServerError(redirectStatusCode)) {
                    throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage, HTTP_SERVER_ERROR); // (server error status on redirect)
                }
            } else if (isClientError(statusCode)) { // otherwise, if this is a client error (4xx)
                // CLIENT_ERROR
                throw new FeedDiscoveryException(url, statusCode, statusMessage, null, null, null, HTTP_CLIENT_ERROR);
            } else if (isServerError(statusCode)) { // otherwise, if this is a server error (5xx)
                // SERVER_ERROR
                throw new FeedDiscoveryException(url, statusCode, statusMessage, null, null, null, HTTP_SERVER_ERROR);
            }  // otherwise (this is a success response)

            boolean isUrlUpgradable = false;
            try {
                // non-redirected HTTP call which resulted in success
                boolean isSuccess = isSuccess(statusCode);
                boolean isHttp = "http".equalsIgnoreCase(feedConnection.getURL().getProtocol());
                if (isSuccess && isHttp) {
                    // attempt HTTPS
                    isUrlUpgradable = isUrlUpgradable(url, username, password, depth);
                // redirected HTTP call which resulted in success
                } else {
                    boolean isRedirect = redirectStatusCode != null;
                    boolean isRedirectSuccess = isRedirect && isSuccess(redirectStatusCode);
                    boolean isRedirectHttp = isRedirect && "http".equalsIgnoreCase(create(redirectUrl).toURL().getProtocol());
                    if (isRedirect && isRedirectSuccess && isRedirectHttp) {
                        // attempt HTTPS
                        isUrlUpgradable = isUrlUpgradable(redirectUrl, username, password, depth);
                    }
                }
            } catch (Exception ignored) {
                // not upgradable
            }

            byte[] allBytes = feedConnection.getInputStream().readAllBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(allBytes);
            XmlReader xmlReader = new XmlReader(bais);
            SyndFeedInput input = new SyndFeedInput();
            input.setAllowDoctypes(true);
            SyndFeed feed = input.build(xmlReader);
            FeedDiscoveryInfo i = FeedDiscoveryInfo.from(
                    trimToLength(FEED_URL_FIELD_NAME, url, 1024),
                    statusCode, // http status code
                    trimToLength(STATUS_MESSAGE_FIELD_NAME, statusMessage, 512), // http status message
                    trimToLength(REDIRECT_URL_FIELD_NAME, redirectUrl, 1024), // redirect url
                    redirectStatusCode, // redirect status code
                    trimToLength(REDIRECT_STATUS_MESSAGE_FIELD_NAME, redirectStatusMessage, 512), // redirect status message
                    convertToContentObject(feed.getTitleEx()),
                    convertToContentObject(feed.getDescriptionEx()),
                    trimToLength(FEED_TYPE_FIELD_NAME, feed.getFeedType(), 64),
                    trimToLength(AUTHOR_FIELD_NAME, feed.getAuthor(), 256),
                    trimToLength(COPYRIGHT_FIELD_NAME, feed.getCopyright(), 1024),
                    trimToLength(DOCS_FIELD_NAME, feed.getDocs(), 1024),
                    trimToLength(ENCODING_FIELD_NAME, feed.getEncoding(), 64),
                    trimToLength(GENERATOR_FIELD_NAME, feed.getGenerator(), 512),
                    buildFeedImage(feed.getImage()),
                    buildFeedImage(feed.getIcon()),
                    trimToLength(LANGUAGE_FIELD_NAME, feed.getLanguage(), 16),
                    trimToLength(LINK_FIELD_NAME, feed.getLink(), 1024),
                    trimToLength(MANAGING_EDITOR_FIELD_NAME, feed.getManagingEditor(), 256),
                    feed.getPublishedDate(),
                    feed.getStyleSheet(),
                    feed.getSupportedFeedTypes(),
                    trimToLength(WEB_MASTER_FIELD_NAME, feed.getWebMaster(), 256),
                    trimToLength(URI_FIELD_NAME, feed.getUri(), 1024),
                    firstFiveCategories(feed.getCategories())
                            .map(SyndCategory::getName)
                            .map(n -> trimToLength(CATEGORIES_FIELD_NAME, n, 256))
                            .collect(toList()),
                    firstFiveEntries(feed.getEntries())
                            .map(e -> FeedDiscoverySampleItem.from(e.getTitle(), e.getUri(), e.getLink(), e.getUpdatedDate()))
                            .collect(toList()),
                    // is upgradable
                    isUrlUpgradable
            );
            i.collectForeignMarkup(feed);

            return i;
        } catch (Exception e) {
            throw new FeedDiscoveryException(url, statusCode, statusMessage, redirectUrl, redirectStatusCode, redirectStatusMessage, e);
        }
    }

    private static HttpURLConnection openFeedConnection(String url) throws IOException {
        URL feedUrl = new URL(url);
        return (HttpURLConnection) feedUrl.openConnection();
    }

    private static boolean addAuthenticationHeader(HttpURLConnection feedConnection, String username, String password) {
        if (username != null && password != null) {
            feedConnection.setRequestProperty("Authorization",
                    "Basic " + new String(Base64.getEncoder().encode((username + ":" + password).getBytes(UTF_8)))
            );
            return true;
        }

        return false;
    }

    private static void addUserAgentHeader(HttpURLConnection feedConnection) {
        feedConnection.setRequestProperty("User-Agent", FEED_DISCOVERY_USER_AGENT);
    }

    private static int getStatusCode(HttpURLConnection feedConnection) throws IOException {
        feedConnection.setInstanceFollowRedirects(true);
        return feedConnection.getResponseCode();
    }

    public static boolean isSuccess(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_OK;
    }

    public static boolean isRedirect(int statusCode) {
        return (isTermporaryRedirect(statusCode) || isPermanentRedirect(statusCode)
                || statusCode == HttpURLConnection.HTTP_SEE_OTHER);
    }

    public static boolean isTermporaryRedirect(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_MOVED_TEMP;
    }

    public static boolean isPermanentRedirect(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_MOVED_PERM;
    }

    public static boolean isClientError(int statusCode) {
        return statusCode >= HttpURLConnection.HTTP_BAD_REQUEST && statusCode < HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public static boolean isServerError(int statusCode) {
        return statusCode >= HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    private static String getStatusMessage(HttpURLConnection feedConnection) throws IOException {
        return feedConnection.getResponseMessage();
    }

    @Setter(PRIVATE)
    @Getter(PACKAGE)
    List<String> feedForeignMarkupStrs;

    @Setter(PRIVATE)
    @Getter(PACKAGE)
    Set<String> postForeignMarkupStrs;

    private static final Set<String> KNOWN_FEED_FOREIGN_NAMESPACE_URIS = new HashSet<>();
    static {
        KNOWN_FEED_FOREIGN_NAMESPACE_URIS.add("http://purl.org/rss/1.0/modules/syndication/"); // sy:*
        KNOWN_FEED_FOREIGN_NAMESPACE_URIS.add("http://a9.com/-/spec/opensearchrss/1.0/"); // openSearch:*
        KNOWN_FEED_FOREIGN_NAMESPACE_URIS.add("com-wordpress:feed-additions:1");
        KNOWN_FEED_FOREIGN_NAMESPACE_URIS.add("http://www.youtube.com/xml/schemas/2015"); // yt:*
    }

    private static final Set<String> KNOWN_POST_FOREIGN_NAMESPACE_URIS = new HashSet<>();
    static {
        KNOWN_POST_FOREIGN_NAMESPACE_URIS.add("http://wellformedweb.org/CommentAPI/"); // wfw:*
        KNOWN_POST_FOREIGN_NAMESPACE_URIS.add("http://www.georss.org/georss"); // georss:*
        KNOWN_POST_FOREIGN_NAMESPACE_URIS.add("http://purl.org/syndication/thread/1.0"); // thr:*
        KNOWN_POST_FOREIGN_NAMESPACE_URIS.add("com-wordpress:feed-additions:1");
        KNOWN_POST_FOREIGN_NAMESPACE_URIS.add("http://www.youtube.com/xml/schemas/2015"); // yt:*
    }

    private void collectForeignMarkup(SyndFeed feed) {
        List<Element> feedForeignMarkup = feed.getForeignMarkup();
        if (isNotEmpty(feedForeignMarkup)) {
            this.feedForeignMarkupStrs = feedForeignMarkup.stream()
                    .filter(e -> !KNOWN_FEED_FOREIGN_NAMESPACE_URIS.contains(e.getNamespaceURI()))
                    .map(Element::toString)
                    .collect(toList());
        }
        Set<String> postForeignMarkup = new HashSet<>();
        firstFiveEntries(feed.getEntries())
                .filter(e -> isNotEmpty(e.getForeignMarkup()))
                .forEach(e -> postForeignMarkup.addAll(e.getForeignMarkup().stream()
                        .filter(elem -> !KNOWN_POST_FOREIGN_NAMESPACE_URIS.contains(elem.getNamespaceURI()))
                        .map(Element::toString)
                        .collect(toSet()))
                );
        this.postForeignMarkupStrs = postForeignMarkup;
    }

    private static String trimToLength(String fieldName, String str, int len) {
        if (str == null) {
            return null;
        }
        String t = trim(str);
        if (t.length() > len) {
            log.error("Field length overrun, fieldName={}, len={}", fieldName, len);
        }
        return t.substring(0, min(len, t.length()));
    }

    private static FeedDiscoveryImageInfo buildFeedImage(SyndImage img) {
        if (img == null) {
            return null;
        }
        String url = img.getUrl();
        if (isNotBlank(url)) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ignored) {
                // ignored
            }
            String transportIdent = computeThumbnailHash(md, url);

            return FeedDiscoveryImageInfo.from(
                    img.getTitle(),
                    img.getDescription(),
                    img.getHeight(),
                    img.getWidth(),
                    img.getLink(),
                    transportIdent,
                    img.getUrl());
        }

        return null;
    }

    private static String computeThumbnailHash(MessageDigest md, String feedImgUrl) {
        return isNotBlank(feedImgUrl) ? printHexBinary(md.digest(serialize(feedImgUrl))) : null;
    }

    private static Stream<SyndCategory> firstFiveCategories(List<SyndCategory> l) {
        return l == null ? Stream.of() : l.subList(0, min(l.size(), 5)).stream();
    }

    private static Stream<SyndEntry> firstFiveEntries(List<SyndEntry> l) {
        return l == null ? Stream.of() : l.subList(0, min(l.size(), 5)).stream();
    }

    private static ContentObject convertToContentObject(SyndContent syndContent) {
        if (syndContent == null) {
            return null;
        }
        return ContentObject.from(syndContent.getType(), syndContent.getValue());
    }

    private static boolean isUrlUpgradable(String url, String username, String password, int depth) {
        if (url.startsWith("http") && !url.startsWith("https")) {
            String newUrl = replaceOnce(url, "http", "https");
            try {
                FeedDiscoveryInfo newFeedDiscoveryInfo = discoverUrl(newUrl, username, password, false, depth + 1);
                if (
                        // either initial discovery or redirected discovery produced an HTTP 200
                        (isSuccess(newFeedDiscoveryInfo.getHttpStatusCode()) || isSuccess(newFeedDiscoveryInfo.getRedirectHttpStatusCode()))
                        // and the feed parsed without an exception
                        && newFeedDiscoveryInfo.getError() == null)
                {
                    return true;
                }
            } catch (FeedDiscoveryException ignored) {
                // not upgradable due to e
            }
        }

        return false;
    }
}
