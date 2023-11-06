package com.lostsidewalk.buffy.auth;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * Represents an API key associated with a user for authentication purposes.
 */
@Slf4j
@Data
public class ApiKey {

    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private String apiKey;

    @NotBlank
    private String apiSecret;

    /**
     * Constructs an ApiKey with the specified attributes.
     *
     * @param id        The unique identifier for the API key.
     * @param userId    The identifier of the user associated with the API key.
     * @param apiKey    The API key value.
     * @param apiSecret The API secret value.
     */
    ApiKey(Long id, Long userId, String apiKey, String apiSecret) {
        this.id = id;
        this.userId = userId;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     * Creates an ApiKey instance without specifying an ID.
     *
     * @param userId    The identifier of the user associated with the API key.
     * @param apiKey    The API key value.
     * @param apiSecret The API secret value.
     */
    ApiKey(Long userId, String apiKey, String apiSecret) {
        this.userId = userId;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     * Creates an ApiKey instance with the specified attributes.
     *
     * @param id        The unique identifier for the API key.
     * @param userId    The identifier of the user associated with the API key.
     * @param apiKey    The API key value.
     * @param apiSecret The API secret value.
     * @return An ApiKey instance.
     */
    public static ApiKey from(Long id, Long userId, String apiKey, String apiSecret) {
        return new ApiKey(id, userId, apiKey, apiSecret);
    }

    /**
     * Creates an ApiKey instance without specifying an ID.
     *
     * @param userId    The identifier of the user associated with the API key.
     * @param apiKey    The API key value.
     * @param apiSecret The API secret value.
     * @return An ApiKey instance.
     */
    public static ApiKey from(Long userId, String apiKey, String apiSecret) {
        return new ApiKey(userId, apiKey, apiSecret);
    }
}
