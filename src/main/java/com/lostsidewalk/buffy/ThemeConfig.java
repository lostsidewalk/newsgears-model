package com.lostsidewalk.buffy;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

/**
 * Represents the configuration for themes in the application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThemeConfig {

    /**
     * Configuration map for the light theme.
     */
    Map<String, String> lightTheme;

    /**
     * Configuration map for the dark theme.
     */
    Map<String, String> darkTheme;
}
