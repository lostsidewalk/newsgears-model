package com.lostsidewalk.buffy.publisher;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeedPreview {

    Long queueId;

    Serializable previewArtifact;

    private FeedPreview(Long queueId, Serializable previewArtifact) {
        this.queueId = queueId;
        this.previewArtifact = previewArtifact;
    }

    @SuppressWarnings("unused")
    public static FeedPreview from(Long queueId, Serializable previewArtifact) {
        return new FeedPreview(queueId, previewArtifact);
    }
}
