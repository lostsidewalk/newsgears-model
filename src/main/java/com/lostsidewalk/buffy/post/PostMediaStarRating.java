package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.StarRating;
import lombok.Data;

@Data
public class PostMediaStarRating {

    Double average;

    Integer count;

    Integer max;

    Integer min;

    PostMediaStarRating(Double average, Integer count, Integer max, Integer min) {
        this.average = average;
        this.count = count;
        this.max = max;
        this.min = min;
    }

    public static PostMediaStarRating from(StarRating starRating) {
        return new PostMediaStarRating(starRating.getAverage(), starRating.getCount(), starRating.getMax(), starRating.getMin());
    }

    public StarRating toModule() {
        StarRating starRating = new StarRating();
        starRating.setAverage(getAverage());
        starRating.setCount(getCount());
        starRating.setMax(getMax());
        starRating.setMin(getMin());

        return starRating;
    }
}
