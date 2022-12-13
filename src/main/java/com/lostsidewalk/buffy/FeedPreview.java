package com.lostsidewalk.buffy;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeedPreview {

    String feedIdent;

    Serializable previewArtifact;

    private FeedPreview(String feedIdent, Serializable previewArtifact) {
        this.feedIdent = feedIdent;
        this.previewArtifact = previewArtifact;
    }

    @SuppressWarnings("unused")
    public static FeedPreview from(String feedIdent, Serializable previewArtifact) {
        return new FeedPreview(feedIdent, previewArtifact);
    }
}
