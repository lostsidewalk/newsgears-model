package com.lostsidewalk.buffy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a configuration for the framework, including user-specific settings for notifications and display.
 */
@SuppressWarnings("unused")
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FrameworkConfig {

    /**
     * Default constructor; initializes the object.
     */
    @SuppressWarnings("RedundantNoArgConstructor") // for javadoc
    public FrameworkConfig() {

    }

    /**
     * The key for the username field in the configuration.
     */
    public static final String USERNAME = "username";
    /**
     * The key for the notifications configuration in the framework settings.
     */
    public static final String NOTIFICATION_CONFIG = "notifications";
    /**
     * The key for the display configuration in the framework settings.
     */
    public static final String DISPLAY_CONFIG = "display";
    Long userId;
    Map<String, String> notifications = new HashMap<>(16);
    Map<String, String> display = new HashMap<>(16);
}
