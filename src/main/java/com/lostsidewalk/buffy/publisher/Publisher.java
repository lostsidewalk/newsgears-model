package com.lostsidewalk.buffy.publisher;

import com.lostsidewalk.buffy.post.StagingPost;
import com.lostsidewalk.buffy.queue.QueueDefinition;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * An interface representing a publisher responsible for publishing posts to various formats and destinations.
 */
@SuppressWarnings({"unused", "InterfaceNeverImplemented"})
public interface Publisher {

    /**
     * Publishes a feed to a destination based on the provided queue definition and staging posts.
     *
     * @param queueDefinition The definition of the publication queue.
     * @param posts           List of staging posts to publish.
     * @param pubDate         The publication date.
     * @return A map of publication results, with destination IDs as keys and PubResult objects as values.
     */
    Map<String, PubResult> publishFeed(QueueDefinition queueDefinition, List<StagingPost> posts, Date pubDate);

    /**
     * Gets the unique identifier of the publisher.
     *
     * @return The publisher's unique identifier.
     */
    String getPublisherId();

    /**
     * Checks if the publisher supports a specific publication format.
     *
     * @param format The publication format to check.
     * @return True if the publisher supports the format, false otherwise.
     */
    boolean supportsFormat(PubFormat format);

    /**
     * Generates previews of posts in a specified format for a given user.
     *
     * @param username The username of the user generating the preview.
     * @param posts    List of staging posts to preview.
     * @param format   The publication format for the preview.
     * @return List of feed previews.
     * @throws Exception If an error occurs during the preview generation.
     */
    @SuppressWarnings("ProhibitedExceptionDeclared")
    List<FeedPreview> doPreview(String username, List<StagingPost> posts, PubFormat format) throws Exception;

    /**
     * Enumeration of supported publication formats.
     */
    enum PubFormat {
        /**
         * RSS publication format.
         */
        RSS,
        /**
         * ATOM publication format.
         */
        ATOM,
        /**
         * Markdown publication format.
         */
        MD,
        /**
         * JSON publication format.
         */
        JSON,
        /**
         * CSV publication format.
         */
        CSV;

        /**
         * Get the PubFormat enum value by its name.
         *
         * @param name The name of the publication format.
         * @return The corresponding PubFormat enum value, or null if not found.
         */
        public static PubFormat byName(String name) {
            for (PubFormat p : values()) {
                if (p.name().equalsIgnoreCase(name)) {
                    return p;
                }
            }

            return null;
        }
    }

    /**
     * Represents the result of a publication operation, including the publication URL, errors, and publication date.
     */
    @SuppressWarnings("InnerClassOfInterface")
    @Data
    class PubResult {

        /**
         * The publication URL.
         */
        String transportUrl;

        /**
         * The user-ident URL.
         */
        String userIdentUrl;

        /**
         * List of errors encountered during publication.
         */
        List<Throwable> errors;

        /**
         * The publication date.
         */
        Date pubDate;

        private PubResult(String transportUrl, String userIdentUrl, List<Throwable> errors, Date pubDate) {
            this.errors = errors;
            this.pubDate = pubDate;
            this.transportUrl = transportUrl;
            this.userIdentUrl = userIdentUrl;
        }

        /**
         * Creates a PubResult instance with the specified URL, errors, and publication date.
         *
         * @param transportUrl     The publication transport URL.
         * @param userIdentUrl     The publication user-ident URL.
         * @param errors           List of errors encountered during publication.
         * @param pubDate          The publication date.
         * @return A PubResult instance.
         */
        public static PubResult from(String transportUrl, String userIdentUrl, List<Throwable> errors, Date pubDate) {
            return new PubResult(transportUrl, userIdentUrl, errors, pubDate);
        }
    }
}
