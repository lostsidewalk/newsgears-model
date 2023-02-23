package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.StarRating;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStarRating implements Serializable {

    @Serial
    private static final long serialVersionUID = 222133354944442343L;

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
