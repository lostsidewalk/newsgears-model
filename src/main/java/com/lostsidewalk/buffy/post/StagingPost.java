package com.lostsidewalk.buffy.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static lombok.AccessLevel.PUBLIC;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
public class StagingPost implements Serializable {

    @SuppressWarnings("unused")
    public enum PostStatus {
        PUB_PENDING, DEPUB_PENDING, IGNORED, PURGE_PENDING
    }

    private Long id;

    @NotBlank
    private final String importerId;

    private final String feedIdent;

    private final String importerDesc;

    @NotNull
    private final Serializable sourceObj;

    private final String sourceName;

    private final String sourceUrl;

    @NotBlank
    private final String postTitle;

    @NotBlank
    @Setter(AccessLevel.PACKAGE)
    private String postDesc;

    @NotBlank
    private final String postUrl;

    private final String postImgUrl;

    private final String postImgTransportIdent;

    @NotBlank
    private final String postHash;

    @NotBlank
    private final String username;

    private String postComment;

    @Setter(PUBLIC) //  newsgears-data/StagingPostDao
    private PostStatus postStatus;

    private String postRights;

    private String xmlBase;

    private String contributorName;

    private String contributorEmail;

    private String authorName;

    private String authorEmail;

    private String postCategory;

    private String enclosureUrl;

    @NotNull
    private final Date importTimestamp;

    private Date publishTimestamp;

    private Date expirationTimestamp;

    private Date lastUpdatedTimestamp;

    @Getter
    boolean isPublished;

    StagingPost(String importerId,
                String feedIdent,
                String importerDesc,
                Serializable sourceObj,
                String sourceName,
                String sourceUrl,
                String postTitle,
                String postDesc,
                String postUrl,
                String postImgUrl,
                String postImgTransportIdent,
                Date importTimestamp,
                String postHash,
                String username,
                String postComment,
                boolean isPublished,
                String postRights,
                String xmlBase,
                String contributorName,
                String contributorEmail,
                String authorName,
                String authorEmail,
                String postCategory,
                Date publishTimestamp,
                Date expirationTimestamp,
                String enclosureUrl,
                Date lastUpdatedTimestamp
                ) {
        this.importerId = importerId;
        this.feedIdent = feedIdent;
        this.importerDesc = importerDesc;
        this.sourceObj = sourceObj;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postUrl = postUrl;
        this.postImgUrl = postImgUrl;
        this.postImgTransportIdent = postImgTransportIdent;
        this.importTimestamp = importTimestamp;
        this.postHash = postHash;
        this.username = username;
        this.postComment = postComment;
        this.isPublished = isPublished;
        this.postRights = postRights;
        this.xmlBase = xmlBase;
        this.contributorName = contributorName;
        this.contributorEmail = contributorEmail;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.postCategory = postCategory;
        this.publishTimestamp = publishTimestamp;
        this.expirationTimestamp = expirationTimestamp;
        this.enclosureUrl = enclosureUrl;
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    @SuppressWarnings("unused")
    public static StagingPost from(
            String importerId,
            String feedIdent,
            String importerDesc,
            Serializable sourceObj,
            String sourceName,
            String sourceUrl,
            String postTitle,
            String postDesc,
            String postUrl,
            String postImgUrl,
            String postImgTransportIdent,
            Date importTimestamp,
            String postHash,
            String username,
            String postComment,
            boolean isPublished,
            String postRights,
            String xmlBase,
            String contributorName,
            String contributorEmail,
            String authorName,
            String authorEmail,
            String postCategory,
            Date publishTimestamp,
            Date expirationTimestamp,
            String enclosureUrl,
            Date lastUpdatedTimestamp)
    {
        return new StagingPost(
                importerId,
                feedIdent,
                importerDesc,
                sourceObj,
                sourceName,
                sourceUrl,
                postTitle,
                postDesc,
                postUrl,
                postImgUrl,
                postImgTransportIdent,
                importTimestamp,
                postHash,
                username,
                postComment,
                isPublished,
                postRights,
                xmlBase,
                contributorName,
                contributorEmail,
                authorName,
                authorEmail,
                postCategory,
                publishTimestamp,
                expirationTimestamp,
                enclosureUrl,
                lastUpdatedTimestamp
        );
    }

    @SuppressWarnings("unused")
    public static StagingPost from(
            String importerId,
            String feedIdent,
            String importerDesc,
            Serializable sourceObj,
            String sourceName,
            String sourceUrl,
            String postTitle,
            String postDesc,
            String postUrl,
            String postImgUrl,
            // no transport ident
            Date importTimestamp,
            String postHash,
            String username,
            String postComment,
            boolean isPublished,
            String postRights,
            String xmlBase,
            String contributorName,
            String contributorEmail,
            String authorName,
            String authorEmail,
            String postCategory,
            Date publishTimestamp,
            Date expirationTimestamp,
            String enclosureUrl,
            Date lastUpdatedTimestamp
    ) {
        String postImgTransportIdent = getPostImgUrlHash(postImgUrl);
        return new StagingPost(
                importerId,
                feedIdent,
                importerDesc,
                sourceObj,
                sourceName,
                sourceUrl,
                postTitle,
                postDesc,
                postUrl,
                postImgUrl,
                postImgTransportIdent, // added
                importTimestamp,
                postHash,
                username,
                postComment,
                isPublished,
                postRights,
                xmlBase,
                contributorName,
                contributorEmail,
                authorName,
                authorEmail,
                postCategory,
                publishTimestamp,
                expirationTimestamp,
                enclosureUrl,
                lastUpdatedTimestamp
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

    static String computeThumbnailHash(MessageDigest md, String postImgUrl) {
        return isNotEmpty(postImgUrl) ? printHexBinary(md.digest(serialize(postImgUrl))) : null;
    }

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
            false,
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
}
