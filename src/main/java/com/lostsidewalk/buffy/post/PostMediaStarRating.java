package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.StarRating;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a star rating associated with media in a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStarRating implements Serializable {

    @Serial
    private static final long serialVersionUID = 222133354944442343L;

    /**
     * The average value of the star rating.
     */
    Double average;

    /**
     * The count of ratings.
     */
    Integer count;

    /**
     * The maximum rating value.
     */
    Integer max;

    /**
     * The minimum rating value.
     */
    Integer min;

    /**
     * Creates a new instance of PostMediaStarRating.
     *
     * @param average The average value of the star rating.
     * @param count   The count of ratings.
     * @param max     The maximum rating value.
     * @param min     The minimum rating value.
     */
    PostMediaStarRating(Double average, Integer count, Integer max, Integer min) {
        this.average = average;
        this.count = count;
        this.max = max;
        this.min = min;
    }

    /**
     * Creates a new instance of PostMediaStarRating from a StarRating object.
     *
     * @param starRating The StarRating object to convert.
     * @return A new PostMediaStarRating instance.
     */
    public static PostMediaStarRating from(StarRating starRating) {
        return new PostMediaStarRating(starRating.getAverage(), starRating.getCount(), starRating.getMax(), starRating.getMin());
    }

    /**
     * Converts the PostMediaStarRating instance to a StarRating object.
     *
     * @return A StarRating object.
     */
    public final StarRating toModule() {
        StarRating starRating = new StarRating();
        starRating.setAverage(average);
        starRating.setCount(count);
        starRating.setMax(max);
        starRating.setMin(min);

        return starRating;
    }
}
