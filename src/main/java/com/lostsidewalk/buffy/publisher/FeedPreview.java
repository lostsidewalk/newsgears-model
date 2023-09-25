package com.lostsidewalk.buffy.publisher;

import lombok.Data;

import java.io.Serializable;

/**
 * Represents a preview of a feed, including the queue identifier and the preview artifact.
 */
@Data
public class FeedPreview {

    /**
     * The identifier of the publication queue associated with this feed preview.
     */
    Long queueId;

    /**
     * The serializable preview artifact representing the feed preview.
     */
    Serializable previewArtifact;

    /**
     * Constructs a FeedPreview with the specified queue identifier and preview artifact.
     *
     * @param queueId         The identifier of the publication queue.
     * @param previewArtifact The serializable preview artifact.
     */
    private FeedPreview(Long queueId, Serializable previewArtifact) {
        this.queueId = queueId;
        this.previewArtifact = previewArtifact;
    }

    /**
     * Creates a FeedPreview instance with the specified queue identifier and preview artifact.
     *
     * @param queueId         The identifier of the publication queue.
     * @param previewArtifact The serializable preview artifact.
     * @return A FeedPreview instance.
     */
    @SuppressWarnings("unused")
    public static FeedPreview from(Long queueId, Serializable previewArtifact) {
        return new FeedPreview(queueId, previewArtifact);
    }
}
