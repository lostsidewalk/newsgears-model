package com.lostsidewalk.buffy.discovery;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Slf4j
@Data
public class FeedRecommendationInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1123412392038744L;

    List<String> recommendedUrls;

    private FeedRecommendationInfo(List<String> recommendedUrls) {
        this.recommendedUrls = recommendedUrls;
    }

    public static FeedRecommendationInfo from(List<String> recommendedUrls) {
        return new FeedRecommendationInfo(recommendedUrls);
    }
}
