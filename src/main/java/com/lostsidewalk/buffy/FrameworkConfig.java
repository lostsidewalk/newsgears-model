package com.lostsidewalk.buffy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FrameworkConfig {

    public static final String USERNAME = "username";

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //
    //
    //

    public static final String FEED_CONFIG = "feed";

    private Map<String, String> feed = new HashMap<>();

    public Map<String, String> getFeed() {
        return feed;
    }

    public void setFeed(Map<String, String> feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "Framework Configuration {" +
                " feed=" + feed +
                "}";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FrameworkConfig)) {
            return false;
        }

        FrameworkConfig otherFrameworkConfig = (FrameworkConfig) other;
        boolean isUsernameEq = Optional.ofNullable(this.username).map(u -> u.equals(otherFrameworkConfig.getUsername())).orElse(false);
        if (!isUsernameEq) {
            return false;
        }
        boolean isFeedEq = Optional.ofNullable(this.feed).map(m -> m.equals(otherFrameworkConfig.getFeed())).orElse(false);
        if (!isFeedEq) {
            return false;
        }

        return true;
    }
}
