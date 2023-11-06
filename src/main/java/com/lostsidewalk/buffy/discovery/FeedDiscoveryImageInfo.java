package com.lostsidewalk.buffy.discovery;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents information about an image discovered in a feed, including title, description, dimensions, link, and URL.
 */
@Slf4j
@Data
public class FeedDiscoveryImageInfo implements Serializable {

    /**
     * The serial version UID for serialization compatibility.
     */
    @Serial
    private static final long serialVersionUID = 1201230982540983644L;

    /**
     * The title of the discovered image.
     */
    String title;

    /**
     * The description of the discovered image.
     */
    String description;

    /**
     * The height of the discovered image.
     */
    Integer height;

    /**
     * The width of the discovered image.
     */
    Integer width;

    /**
     * The link associated with the discovered image.
     */
    String link;

    /**
     * The transport identifier of the discovered image.
     */
    String transportIdent;

    /**
     * The URL of the discovered image.
     */
    String url;

    /**
     * Constructs a FeedDiscoveryImageInfo with the specified attributes.
     *
     * @param title          The title of the discovered image.
     * @param description    The description of the discovered image.
     * @param height         The height of the discovered image.
     * @param width          The width of the discovered image.
     * @param link           The link associated with the discovered image.
     * @param transportIdent The transport identifier of the discovered image.
     * @param url            The URL of the discovered image.
     */
    FeedDiscoveryImageInfo(String title, String description, Integer height, Integer width, String link, String transportIdent, String url) {
        this.title = title;
        this.description = description;
        this.height = height;
        this.width = width;
        this.link = link;
        this.transportIdent = transportIdent;
        this.url = url;
    }

    /**
     * Creates a FeedDiscoveryImageInfo instance with the specified attributes.
     *
     * @param title          The title of the discovered image.
     * @param description    The description of the discovered image.
     * @param height         The height of the discovered image.
     * @param width          The width of the discovered image.
     * @param link           The link associated with the discovered image.
     * @param transportIdent The transport identifier of the discovered image.
     * @param url            The URL of the discovered image.
     * @return A FeedDiscoveryImageInfo instance.
     */
    public static FeedDiscoveryImageInfo from(String title, String description, Integer height, Integer width, String link, String transportIdent, String url) {
        return new FeedDiscoveryImageInfo(
                title,
                description,
                height,
                width,
                link,
                transportIdent,
                url);
    }
}
