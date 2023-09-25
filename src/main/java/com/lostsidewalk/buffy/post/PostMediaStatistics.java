package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Statistics;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents statistics associated with media in a post.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStatistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354934231231233L;

    /**
     * Number of favorites for the media.
     */
    Integer favorites;

    /**
     * Number of views for the media.
     */
    Integer views;

    /**
     * Creates a new instance of PostMediaStatistics.
     *
     * @param favorites Number of favorites for the media.
     * @param views     Number of views for the media.
     */
    PostMediaStatistics(Integer favorites, Integer views) {
        this.favorites = favorites;
        this.views = views;
    }

    /**
     * Creates a new instance of PostMediaStatistics from a Statistics object.
     *
     * @param statistics The Statistics object to convert.
     * @return A new PostMediaStatistics instance.
     */
    public static PostMediaStatistics from(Statistics statistics) {
        return new PostMediaStatistics(statistics.getFavorites(), statistics.getViews());
    }

    /**
     * Converts the PostMediaStatistics instance to a Statistics object.
     *
     * @return A Statistics object.
     */
    public Statistics toModule() {
        Statistics s = new Statistics();
        s.setFavorites(getFavorites());
        s.setViews(getViews());

        return s;
    }
}
