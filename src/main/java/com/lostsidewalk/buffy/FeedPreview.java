package com.lostsidewalk.buffy;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeedPreview {

    Long feedId;

    Serializable previewArtifact;

    private FeedPreview(Long feedId, Serializable previewArtifact) {
        this.feedId = feedId;
        this.previewArtifact = previewArtifact;
    }

    @SuppressWarnings("unused")
    public static FeedPreview from(Long feedId, Serializable previewArtifact) {
        return new FeedPreview(feedId, previewArtifact);
    }
}
