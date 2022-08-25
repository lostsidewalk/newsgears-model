package com.lostsidewalk.buffy;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class StagingPost implements Serializable {

    public enum PostStatus {
        PUB_PENDING, PURGE_PENDING
    }

    private Long id;

    @NotBlank
    private final String importerId;

    private final String tagName;

    private final String importerDesc;

    @NotNull
    private final Serializable sourceObj;

    @NotBlank
    private final String postTitle;

    @NotBlank
    @Setter(AccessLevel.PACKAGE)
    private String postDesc;

    @NotBlank
    private final String postUrl;

    @NotBlank
    private final String postImgUrl;

    @NotNull
    private final Date importTimestamp;

    @NotBlank
    private final String postHash;

    @Setter(AccessLevel.PUBLIC)
    private PostStatus postStatus;

    StagingPost(String importerId, String tagName, String importerDesc, String postTitle, String postDesc, String postUrl, String postImgUrl, Serializable sourceObj, Date importTimestamp, String postHash) {
        this.importerId = importerId;
        this.tagName = tagName;
        this.importerDesc = importerDesc;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postUrl = postUrl;
        this.postImgUrl = postImgUrl;
        this.sourceObj = sourceObj;
        this.importTimestamp = importTimestamp;
        this.postHash = postHash;
    }

    public static StagingPost from(String importerId, String tagName, String importerDesc, String postTitle, String postDesc, String postUrl, String postImgUrl, Serializable sourceObj, Date importTimestamp, String postHash) {
        return new StagingPost(importerId, tagName, importerDesc, postTitle, postDesc, postUrl, postImgUrl, sourceObj, importTimestamp, postHash);
    }
}
