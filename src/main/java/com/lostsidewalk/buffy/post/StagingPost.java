package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lostsidewalk.buffy.query.QueryDefinition;
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

@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class StagingPost implements Serializable {

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

    @SuppressWarnings("unused")
    public enum PostPubStatus {
        PUB_PENDING, DEPUB_PENDING, ARCHIVED, PURGE_PENDING
    }

    @SuppressWarnings("unused")
    public enum PostReadStatus {
        UNREAD, READ, READ_LATER
    }

    private Long id;

    @NotBlank
    private final String importerId;

    @NotNull
    private final Long feedId;

    private final String importerDesc;

    @NotNull
    @JsonIgnore
    private final Long queryId;

    @NotNull
    private final ContentObject postTitle;

    @NotNull
    @Setter(PACKAGE)
    private ContentObject postDesc;

    private List<ContentObject> postContents;

    private PostMedia postMedia;

    private PostITunes postITunes;

    @NotBlank
    private final String postUrl;

    private final List<PostUrl> postUrls;

    @JsonIgnore
    private final String postImgUrl; // TODO: rename this to 'postImageUrl'

    @JsonIgnore
    private final String postImgTransportIdent;

    @NotNull
    private final Date importTimestamp;

    @NotBlank
    @JsonIgnore
    private final String postHash;

    @NotBlank
    @JsonIgnore
    private final String username;

    private final String postComment;

    private final String postRights;

    private final List<PostPerson> contributors;

    private final List<PostPerson> authors;

    private final List<String> postCategories;

    private final Date publishTimestamp;

    private final Date expirationTimestamp;

    private final List<PostEnclosure> enclosures;

    private final Date lastUpdatedTimestamp;
    //
    // Note: the following fields are not in any c'tor
    //
    @Setter(PUBLIC) // newsgears-data/StagingPostDao
    private boolean isPublished;

    @Setter(PUBLIC) // newsgears-data/StagingPostDao
    private PostPubStatus postPubStatus;

    @Setter(PUBLIC) // newsgears-data/StagingPostDao
    private PostReadStatus postReadStatus;

    StagingPost(String importerId,
                Long feedId,
                String importerDesc,
                Long queryId,
                ContentObject postTitle,
                ContentObject postDesc,
                List<ContentObject> postContents,
                PostMedia postMedia,
                PostITunes postItunes,
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
                Date lastUpdatedTimestamp
    ) {
        this.importerId = importerId;
        this.feedId = feedId;
        this.importerDesc = trimToLength(IMPORTER_DESC_FIELD_NAME, importerDesc, 512); // 512
        this.queryId = queryId;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postContents = postContents;
        this.postMedia = postMedia;
        this.postITunes = postItunes;
        this.postUrl = defaultString(trimToLength(POST_URL_FIELD_NAME, postUrl, 1024)); // 1024 required
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
    //
    // has all args except Id
    //
    @SuppressWarnings("unused")
    public static StagingPost from(
            // Long id,
            String importerId,
            Long feedId,
            String importerDesc,
            Long queryId,
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
            Date lastUpdatedTimestamp)
    {
        return new StagingPost(
                importerId,
                feedId,
                importerDesc,
                queryId,
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
                lastUpdatedTimestamp
        );
    }
    //
    // missing Id, img transport ident
    //
    @SuppressWarnings("unused")
    public static StagingPost from(
            String importerId,
            Long feedId,
            String importerDesc,
            Long queryId,
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
                feedId,
                importerDesc,
                queryId,
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
                lastUpdatedTimestamp
        );
    }

    public static StagingPost from(StagingPost copy, QueryDefinition queryDefinition) {
        return new StagingPost(
                copy.importerId,
                queryDefinition.getFeedId(),
                queryDefinition.getQueryText(),
                queryDefinition.getId(),
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
                copy.postHash,
                queryDefinition.getUsername(),
                copy.postComment,
                copy.postRights,
                copy.contributors,
                copy.authors,
                copy.postCategories,
                copy.publishTimestamp,
                copy.expirationTimestamp,
                copy.enclosures,
                copy.lastUpdatedTimestamp
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
