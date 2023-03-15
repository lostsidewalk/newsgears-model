package com.lostsidewalk.buffy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FrameworkConfig {

    public static final String USERNAME = "username";

    Long userId;
    //
    // notifications
    //
    public static final String NOTIFICATION_CONFIG = "notifications";

    Map<String, String> notifications = new HashMap<>();
    //
    // display
    //
    public static final String DISPLAY_CONFIG = "display";

    Map<String, String> display = new HashMap<>();
}
