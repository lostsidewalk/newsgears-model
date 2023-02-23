package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Statistics;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaStatistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 2354934231231233L;

    Integer favorites;

    Integer views;

    PostMediaStatistics(Integer favorites, Integer views) {
        this.favorites = favorites;
        this.views = views;
    }

    public static PostMediaStatistics from(Statistics statistics) {
        return new PostMediaStatistics(statistics.getFavorites(), statistics.getViews());
    }

    public Statistics toModule() {
        Statistics s = new Statistics();
        s.setFavorites(getFavorites());
        s.setViews(getViews());

        return s;
    }
}
