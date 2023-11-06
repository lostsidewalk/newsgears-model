package com.lostsidewalk.buffy;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Represents the configuration for themes in the application.
 */
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThemeConfig {

    /**
     * Default constructor; initializes the object.
     */
    ThemeConfig() {
    }

    /**
     * Configuration map for the light theme.
     */
    Map<String, String> lightTheme;

    /**
     * Configuration map for the dark theme.
     */
    Map<String, String> darkTheme;
}
