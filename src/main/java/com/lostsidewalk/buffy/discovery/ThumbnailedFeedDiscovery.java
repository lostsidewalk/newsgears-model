package com.lostsidewalk.buffy.discovery;

import com.lostsidewalk.buffy.post.ContentObject;
import com.lostsidewalk.buffy.post.StagingPost;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The ThumbnailedFeedDiscovery class represents a feed discovery with additional thumbnail-related information.
 */
@Slf4j
@Data
public class ThumbnailedFeedDiscovery implements Serializable {

    @Serial
    private static final long serialVersionUID = 112341230982130984L;

    /**
     * The unique identifier for the thumbnailed feed discovery.
     */
    Long id;

    /**
     * The URL of the feed.
     */
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
     * The feed recommendation information associated with the thumbnailed feed discovery.
     */
    FeedRecommendationInfo feedRecommendationInfo;

    private ThumbnailedFeedDiscovery(
            Long id,
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
            Collection<String> supportedTypes,
            String webMaster,
            String uri,
            Collection<String> categories,
            Collection<? extends StagingPost> sampleEntries,
            boolean isUrlUpgradable,
            FeedRecommendationInfo feedRecommendationInfo) {
        this.id = id;
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
        this.publishedDate = publishedDate == null ? null : new Date(publishedDate.getTime());
        this.styleSheet = styleSheet;
        this.supportedTypes = supportedTypes == null ? null : List.copyOf(supportedTypes);
        this.webMaster = webMaster;
        this.uri = uri;
        this.categories = categories == null ? null : List.copyOf(categories);
        this.sampleEntries = sampleEntries == null ? null : List.copyOf(sampleEntries);
        this.isUrlUpgradable = isUrlUpgradable;
        this.feedRecommendationInfo = feedRecommendationInfo;
    }

    /**
     * Creates a ThumbnailedFeedDiscovery object from the provided feed discovery information, feed image, feed icon, and feed recommendation information.
     *
     * @param feedDiscoveryInfo      The feed discovery information.
     * @param feedImage              The feed image information.
     * @param feedIcon               The feed icon information.
     * @param feedRecommendationInfo The feed recommendation information.
     * @return A ThumbnailedFeedDiscovery object.
     */
    public static ThumbnailedFeedDiscovery from(FeedDiscoveryInfo feedDiscoveryInfo,
                                                FeedDiscoveryImageInfo feedImage,
                                                FeedDiscoveryImageInfo feedIcon,
                                                FeedRecommendationInfo feedRecommendationInfo) {
        return new ThumbnailedFeedDiscovery(
                feedDiscoveryInfo.getId(),
                feedDiscoveryInfo.getFeedUrl(),
                feedDiscoveryInfo.getHttpStatusCode(),
                feedDiscoveryInfo.getHttpStatusMessage(),
                feedDiscoveryInfo.getRedirectFeedUrl(),
                feedDiscoveryInfo.getRedirectHttpStatusCode(),
                feedDiscoveryInfo.getRedirectHttpStatusMessage(),
                feedDiscoveryInfo.getTitle(),
                feedDiscoveryInfo.getDescription(),
                feedDiscoveryInfo.getFeedType(),
                feedDiscoveryInfo.getAuthor(),
                feedDiscoveryInfo.getCopyright(),
                feedDiscoveryInfo.getDocs(),
                feedDiscoveryInfo.getEncoding(),
                feedDiscoveryInfo.getGenerator(),
                feedImage,
                feedIcon,
                feedDiscoveryInfo.getLanguage(),
                feedDiscoveryInfo.getLink(),
                feedDiscoveryInfo.getManagingEditor(),
                feedDiscoveryInfo.getPublishedDate(),
                feedDiscoveryInfo.getStyleSheet(),
                feedDiscoveryInfo.getSupportedTypes(),
                feedDiscoveryInfo.getWebMaster(),
                feedDiscoveryInfo.getUri(),
                feedDiscoveryInfo.getCategories(),
                feedDiscoveryInfo.getSampleEntries(),
                feedDiscoveryInfo.isUrlUpgradable(),
                feedRecommendationInfo
        );
    }
}
