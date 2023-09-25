package com.lostsidewalk.buffy.discovery;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Represents information about recommended feed URLs.
 */
@Slf4j
@Data
public class FeedRecommendationInfo implements Serializable {

    /**
     * The serial version UID for serialization compatibility.
     */
    @Serial
    private static final long serialVersionUID = 1123412392038744L;

    /**
     * The list of recommended feed URLs.
     */
    List<String> recommendedUrls;

    /**
     * Constructs a FeedRecommendationInfo with the specified list of recommended feed URLs.
     *
     * @param recommendedUrls The list of recommended feed URLs.
     */
    private FeedRecommendationInfo(List<String> recommendedUrls) {
        this.recommendedUrls = recommendedUrls;
    }

    /**
     * Creates a FeedRecommendationInfo instance with the specified list of recommended feed URLs.
     *
     * @param recommendedUrls The list of recommended feed URLs.
     * @return A FeedRecommendationInfo instance.
     */
    public static FeedRecommendationInfo from(List<String> recommendedUrls) {
        return new FeedRecommendationInfo(recommendedUrls);
    }
}
