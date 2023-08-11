package com.lostsidewalk.buffy.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApiKey {

    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private String apiKey;

    @NotBlank
    private String apiSecret;

    ApiKey(Long id, Long userId, String apiKey, String apiSecret) {
        this.id = id;
        this.userId = userId;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    ApiKey(Long userId, String apiKey, String apiSecret) {
        this.userId = userId;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public static ApiKey from(Long id, Long userId, String apiKey, String apiSecret) {
        return new ApiKey(id, userId, apiKey, apiSecret);
    }

    public static ApiKey from(Long userId, String apiKey, String apiSecret) {
        return new ApiKey(userId, apiKey, apiSecret);
    }
}
