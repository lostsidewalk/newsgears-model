package com.lostsidewalk.buffy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FrameworkConfig {

    public static final String USERNAME = "username";

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //
    //
    //

    public static final String NOTIFICATION_CONFIG = "notifications";

    private Map<String, String> notifications = new HashMap<>();

    public Map<String, String> getNotifications() {
        return notifications;
    }

    public void setNotifications(Map<String, String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "Framework Configuration {" +
                " notifications=" + notifications +
                "}";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FrameworkConfig)) {
            return false;
        }

        FrameworkConfig otherFrameworkConfig = (FrameworkConfig) other;
        boolean isUsernameEq = Optional.ofNullable(this.userId)
                .map(u -> u.equals(otherFrameworkConfig.getUserId()))
                .orElse(false);
        if (!isUsernameEq) {
            return false;
        }

        return Optional.ofNullable(this.notifications)
                .map(m -> m.equals(otherFrameworkConfig.getNotifications()))
                .orElse(false);
    }
}
