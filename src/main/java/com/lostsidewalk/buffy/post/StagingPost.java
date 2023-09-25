package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lostsidewalk.buffy.Auditable;
import com.lostsidewalk.buffy.subscription.SubscriptionDefinition;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.lang.Math.min;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PUBLIC;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Represents a staging post entity that holds information about a post being prepared for publication.
 * This class defines various attributes related to the post's content, metadata, and status.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class StagingPost implements Serializable, Auditable {

    /**
     * A special constant instance of StagingPost representing the end of an import.
     */
    public static final StagingPost END_IMPORT = new StagingPost(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );
    @Serial
    private static final long serialVersionUID = 295664561249823L;
    private static final String IMPORTER_DESC_FIELD_NAME = "importerDesc";
    private static final String POST_TITLE_FIELD_NAME = "postTitle";
    private static final String POST_DESC_FIELD_NAME = "postDesc";
    private static final String POST_URL_FIELD_NAME = "postUrl";
    private static final String POST_IMG_URL_FIELD_NAME = "postImgUrl";
    private static final String POST_COMMENT_FIELD_NAME = "postComment";
    private static final String POST_RIGHTS_FIELD_NAME = "postRights";
    private static final String ENCLOSURE_URL_FIELD_NAME = "enclosureUrl";
    /**
     * The unique identifier of the staging post.
     */
    Long id;
    /**
     * The identifier of the importer associated with this post.
     */
    @NotBlank
    String importerId;
    /**
     * The ID of the queue to which the post belongs.
     */
    @NotNull
    Long queueId;
    /**
     * A description of the importer.
     */
    String importerDesc;
    /**
     * The ID of the subscription associated with this post.
     */
    Long subscriptionId;
    /**
     * The title of the post.
     */
    @NotNull
    ContentObject postTitle;
    /**
     * The description of the post.
     */
    @NotNull
    @Setter(PACKAGE)
    ContentObject postDesc;
    /**
     * A list of ContentObject objects (the post content)
     */
    List<ContentObject> postContents;
    /**
     * The post media descriptor
     */
    PostMedia postMedia;
    /**
     * The post ITunes descriptor
     */
    PostITunes postITunes;
    /**
     * The (primary) post URL
     */
    String postUrl;
    /**
     * A list of alternate post URLs
     */
    List<PostUrl> postUrls;
    /**
     * The URL of the post thumbnail image
     */
    @JsonIgnore
    String postImgUrl;
    /**
     * The post image transport identifier
     */
    @JsonIgnore
    String postImgTransportIdent;
    /**
     * The timestamp of when the post was imported.
     */
    @NotNull
    Date importTimestamp;
    /**
     * The hash of the post content.
     */
    @NotBlank
    @JsonIgnore
    String postHash;
    /**
     * The username associated with the post.
     */
    @NotBlank
    @JsonIgnore
    String username;
    /**
     * The comment associated with the post.
     */
    String postComment;
    /**
     * The rights associated with the post.
     */
    String postRights;
    /**
     * The list of contributors to the post.
     */
    List<PostPerson> contributors;
    /**
     * The list of authors of the post.
     */
    List<PostPerson> authors;
    /**
     * The list of categories associated with the post.
     */
    List<String> postCategories;
    /**
     * The timestamp of when the post was published.
     */
    Date publishTimestamp;
    /**
     * The timestamp of when the post will expire.
     */
    Date expirationTimestamp;
    /**
     * The list of enclosures associated with the post.
     */
    List<PostEnclosure> enclosures;
    /**
     * The timestamp of the last update to the post.
     */
    Date lastUpdatedTimestamp;
    /**
     * Indicates whether the post is published.
     */
    @Setter(PUBLIC)
    boolean isPublished;
    /**
     * The publication status of the post.
     */
    @Setter(PUBLIC) // newsgears-data/StagingPostDao
            PostPubStatus postPubStatus;
    /**
     * The read status of the post.
     */
    @Setter(PUBLIC) // newsgears-data/StagingPostDao
            PostReadStatus postReadStatus;
    /**
     * The timestamp the post was created.
     */
    Date created;
    /**
     * The timestamp the post was last modified in any way.
     */
    Date lastModified;

    /**
     * Creates a new StagingPost with the specified properties.
     *
     * @param importerId            The identifier of the importer associated with this post.
     * @param queueId               The unique identifier of the queue.
     * @param importerDesc          The description of the importer.
     * @param subscriptionId        The unique identifier of the subscription.
     * @param postTitle             The title of the post.
     * @param postDesc              The description of the post.
     * @param postContents          The contents of the post.
     * @param postMedia             The media associated with the post.
     * @param postITunes            The iTunes-specific data for the post.
     * @param postUrl               The URL of the post.
     * @param postUrls              The list of URLs associated with the post.
     * @param postImgUrl            The URL of the post's image.
     * @param postImgTransportIdent The transport identifier of the post's image.
     * @param importTimestamp       The timestamp when the post was imported.
     * @param postHash              The hash value of the post.
     * @param username              The username associated with the post.
     * @param postComment           The comment associated with the post.
     * @param postRights            The rights associated with the post.
     * @param contributors          The list of contributors to the post.
     * @param authors               The list of authors of the post.
     * @param postCategories        The categories associated with the post.
     * @param publishTimestamp      The timestamp when the post is published.
     * @param expirationTimestamp   The timestamp when the post expires.
     * @param enclosures            The list of enclosures associated with the post.
     * @param lastUpdatedTimestamp  The timestamp when the post was last updated.
     * @param created               The timestamp when the post was created.
     * @param lastModified          The timestamp when the post was last modified.
     */
    StagingPost(String importerId,
                Long queueId,
                String importerDesc,
                Long subscriptionId,
                ContentObject postTitle,
                ContentObject postDesc,
                List<ContentObject> postContents,
                PostMedia postMedia,
                PostITunes postITunes,
                String postUrl,
                List<PostUrl> postUrls,
                String postImgUrl,
                String postImgTransportIdent,
                Date importTimestamp,
                String postHash,
                String username,
                String postComment,
                String postRights,
                List<PostPerson> contributors,
                List<PostPerson> authors,
                List<String> postCategories,
                Date publishTimestamp,
                Date expirationTimestamp,
                List<PostEnclosure> enclosures,
                Date lastUpdatedTimestamp,
                Date created,
                Date lastModified
    ) {
        this.importerId = importerId;
        this.queueId = queueId;
        this.importerDesc = trimToLength(IMPORTER_DESC_FIELD_NAME, importerDesc, 512); // 512
        this.subscriptionId = subscriptionId;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postContents = postContents;
        this.postMedia = postMedia;
        this.postITunes = postITunes;
        this.postUrl = trimToLength(POST_URL_FIELD_NAME, postUrl, 1024); // 1024
        this.postUrls = postUrls; // json
        this.postImgUrl = trimToLength(POST_IMG_URL_FIELD_NAME, postImgUrl, 1024); // 1024
        this.postImgTransportIdent = postImgTransportIdent; // 256
        this.importTimestamp = importTimestamp; // required
        this.postHash = postHash; // 64 required
        this.username = username; // 100 required
        this.postComment = trimToLength(POST_COMMENT_FIELD_NAME, postComment, 2048); // 2048
        this.postRights = trimToLength(POST_RIGHTS_FIELD_NAME, postRights, 1024); // 1024
        this.contributors = contributors; // json
        this.authors = authors; // json
        this.postCategories = postCategories; // json
        this.publishTimestamp = publishTimestamp; // timestamp
        this.expirationTimestamp = expirationTimestamp; // timestamp
        this.enclosures = enclosures; // json
        this.lastUpdatedTimestamp = lastUpdatedTimestamp; // timestamp
        this.created = created;
        this.lastModified = lastModified;
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

    /**
     * Static factory method to create a StagingPost object.
     *
     * @param importerId            The identifier of the importer associated with this post.
     * @param queueId               The unique identifier of the queue.
     * @param importerDesc          The description of the importer.
     * @param subscriptionId        The unique identifier of the subscription.
     * @param postTitle             The title of the post.
     * @param postDesc              The description of the post.
     * @param postContents          The contents of the post.
     * @param postMedia             The media associated with the post.
     * @param postITunes            The iTunes-specific data for the post.
     * @param postUrl               The URL of the post.
     * @param postUrls              The list of URLs associated with the post.
     * @param postImgUrl            The URL of the post's image.
     * @param postImgTransportIdent The transport identifier of the post's image.
     * @param importTimestamp       The timestamp when the post was imported.
     * @param postHash              The hash value of the post.
     * @param username              The username associated with the post.
     * @param postComment           The comment associated with the post.
     * @param postRights            The rights associated with the post.
     * @param contributors          The list of contributors to the post.
     * @param authors               The list of authors of the post.
     * @param postCategories        The categories associated with the post.
     * @param publishTimestamp      The timestamp when the post is published.
     * @param expirationTimestamp   The timestamp when the post expires.
     * @param enclosures            The list of enclosures associated with the post.
     * @param lastUpdatedTimestamp  The timestamp when the post was last updated.
     * @param created               The timestamp when the post was created.
     * @param lastModified          The timestamp when the post was last modified.
     * @return A new StagingPost instance.
     */
    @SuppressWarnings("unused")
    public static StagingPost from(
            // Long id,
            String importerId,
            Long queueId,
            String importerDesc,
            Long subscriptionId,
            ContentObject postTitle,
            ContentObject postDesc,
            List<ContentObject> postContents,
            PostMedia postMedia,
            PostITunes postITunes,
            String postUrl,
            List<PostUrl> postUrls,
            String postImgUrl,
            String postImgTransportIdent,
            Date importTimestamp,
            String postHash,
            String username,
            String postComment,
            String postRights,
            List<PostPerson> contributors,
            List<PostPerson> authors,
            List<String> postCategories,
            Date publishTimestamp,
            Date expirationTimestamp,
            List<PostEnclosure> enclosures,
            Date lastUpdatedTimestamp,
            Date created,
            Date lastModified) {
        return new StagingPost(
                importerId,
                queueId,
                importerDesc,
                subscriptionId,
                postTitle,
                postDesc,
                postContents,
                postMedia,
                postITunes,
                postUrl,
                postUrls,
                postImgUrl,
                postImgTransportIdent,
                importTimestamp,
                postHash,
                username,
                postComment,
                postRights,
                contributors,
                authors,
                postCategories,
                publishTimestamp,
                expirationTimestamp,
                enclosures,
                lastUpdatedTimestamp,
                created,
                lastModified
        );
    }

    /**
     * Static factory method to create a StagingPost object without an image transport identifier.
     *
     * @param importerId           The identifier of the importer associated with this post.
     * @param queueId              The unique identifier of the queue.
     * @param importerDesc         The description of the importer.
     * @param subscriptionId       The unique identifier of the subscription.
     * @param postTitle            The title of the post.
     * @param postDesc             The description of the post.
     * @param postContents         The contents of the post.
     * @param postMedia            The media associated with the post.
     * @param postITunes           The iTunes-specific data for the post.
     * @param postUrl              The URL of the post.
     * @param postUrls             The list of URLs associated with the post.
     * @param postImgUrl           The URL of the post's image.
     * @param importTimestamp      The timestamp when the post was imported.
     * @param postHash             The hash value of the post.
     * @param username             The username associated with the post.
     * @param postComment          The comment associated with the post.
     * @param postRights           The rights associated with the post.
     * @param contributors         The list of contributors to the post.
     * @param authors              The list of authors of the post.
     * @param postCategories       The categories associated with the post.
     * @param publishTimestamp     The timestamp when the post is published.
     * @param expirationTimestamp  The timestamp when the post expires.
     * @param enclosures           The list of enclosures associated with the post.
     * @param lastUpdatedTimestamp The timestamp when the post was last updated.
     * @return A new StagingPost instance without an image transport identifier.
     */
    @SuppressWarnings("unused")
    public static StagingPost from(
            String importerId,
            Long queueId,
            String importerDesc,
            Long subscriptionId,
            ContentObject postTitle,
            ContentObject postDesc,
            List<ContentObject> postContents,
            PostMedia postMedia,
            PostITunes postITunes,
            String postUrl,
            List<PostUrl> postUrls,
            String postImgUrl,
            // no img transport ident
            Date importTimestamp,
            String postHash,
            String username,
            String postComment,
            String postRights,
            List<PostPerson> contributors,
            List<PostPerson> authors,
            List<String> postCategories,
            Date publishTimestamp,
            Date expirationTimestamp,
            List<PostEnclosure> enclosures,
            Date lastUpdatedTimestamp
    ) {
        String postImgTransportIdent = getPostImgUrlHash(postImgUrl);
        return new StagingPost(
                importerId,
                queueId,
                importerDesc,
                subscriptionId,
                postTitle,
                postDesc,
                postContents,
                postMedia,
                postITunes,
                postUrl,
                postUrls,
                postImgUrl,
                postImgTransportIdent, // added
                importTimestamp,
                postHash,
                username,
                postComment,
                postRights,
                contributors,
                authors,
                postCategories,
                publishTimestamp,
                expirationTimestamp,
                enclosures,
                lastUpdatedTimestamp,
                new Date(),
                null
        );
    }

    /**
     * Static factory method to create a StagingPost object based on an existing post with a new post hash.
     *
     * @param copy                   The existing StagingPost to copy.
     * @param subscriptionDefinition The subscription definition associated with this post.
     * @param postHash               The new hash value of the post.
     * @return A new StagingPost instance with updated post hash.
     */
    public static StagingPost from(StagingPost copy, SubscriptionDefinition subscriptionDefinition, String postHash) {
        return new StagingPost(
                copy.importerId,
                subscriptionDefinition.getQueueId(),
                trimToEmpty(defaultString(subscriptionDefinition.getTitle(), subscriptionDefinition.getUrl())),
                subscriptionDefinition.getId(),
                copy.postTitle,
                copy.postDesc,
                copy.postContents,
                copy.postMedia,
                copy.postITunes,
                copy.postUrl,
                copy.postUrls,
                copy.postImgUrl,
                copy.postImgTransportIdent,
                copy.importTimestamp,
                postHash,
                subscriptionDefinition.getUsername(),
                copy.postComment,
                copy.postRights,
                copy.contributors,
                copy.authors,
                copy.postCategories,
                copy.publishTimestamp,
                copy.expirationTimestamp,
                copy.enclosures,
                copy.lastUpdatedTimestamp,
                copy.created,
                copy.lastModified
        );
    }

    private static String getPostImgUrlHash(String postImgUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return computeThumbnailHash(md, postImgUrl);
        } catch (NoSuchAlgorithmException ignored) {
            // ignored
        }

        return null;
    }

    /**
     * Computes the hash of the post's image URL using the provided message digest.
     *
     * @param md         The message digest algorithm instance.
     * @param postImgUrl The URL of the post's image.
     * @return The hash of the post's image URL.
     */
    static String computeThumbnailHash(MessageDigest md, String postImgUrl) {
        return isNotEmpty(postImgUrl) ? printHexBinary(md.digest(serialize(postImgUrl))) : null;
    }

    /**
     * Enum representing the publication status of a staging post.
     */
    @SuppressWarnings("unused")
    public enum PostPubStatus {
        /**
         * Unpublished (default) status.
         */
        UNPUBLISHED,
        /**
         * Pending publication status.
         */
        PUB_PENDING,
        /**
         * Pending de-publication status.
         */
        DEPUB_PENDING,
        /**
         * Archived publication status.
         */
        ARCHIVED,
        /**
         * Pending purging status.
         */
        PURGE_PENDING
    }

    /**
     * Enum representing the read status of a staging post.
     */
    @SuppressWarnings("unused")
    public enum PostReadStatus {
        /**
         * Unread read status.
         */
        UNREAD,
        /**
         * Read read status.
         */
        READ,
        /**
         * Read later read status.
         */
        READ_LATER
    }
}
