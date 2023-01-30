package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Statistics;
import lombok.Data;

@Data
public class PostMediaStatistics {

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
