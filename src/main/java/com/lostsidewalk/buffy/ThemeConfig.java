package com.lostsidewalk.buffy;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ThemeConfig {

    Map<String, String> lightTheme;

    Map<String, String> darkTheme;
}
