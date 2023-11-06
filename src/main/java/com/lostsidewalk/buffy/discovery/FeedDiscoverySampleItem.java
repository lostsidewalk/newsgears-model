package com.lostsidewalk.buffy.discovery;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents an item sampled from a feed during feed discovery, containing basic information about the item.
 *
 * <p>This class is used to store sampled items from feeds discovered during the feed discovery process.
 * It includes properties such as the item's title, URI, link, and update date.
 *
 * @since [Include the version or date when this class was introduced]
 */
@Slf4j
@Data
public class FeedDiscoverySampleItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1209382540983644L;

    /**
     * The title of the sampled item.
     */
    String title;

    /**
     * The URI associated with the sampled item.
     */
    String uri;

    /**
     * The link associated with the sampled item.
     */
    String link;

    /**
     * The date when the sampled item was last updated.
     */
    Date updateDate;

    private FeedDiscoverySampleItem(String title, String uri, String link, Date updateDate) {
        this.title = title;
        this.uri = uri;
        this.link = link;
        this.updateDate = updateDate;
    }

    /**
     * Constructs a new instance of {FeedDiscoverySampleItem} with the specified properties.
     *
     * @param title      The title of the sampled item.
     * @param uri        The URI associated with the sampled item.
     * @param link       The link associated with the sampled item.
     * @param updateDate The date when the sampled item was last updated.
     * @return A new instance of {FeedDiscoverySampleItem} with the specified properties.
     */
    public static FeedDiscoverySampleItem from(String title, String uri, String link, Date updateDate) {
        return new FeedDiscoverySampleItem(title, uri, link, updateDate);
    }
}
