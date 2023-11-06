package com.lostsidewalk.buffy.auth;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections4.CollectionUtils.size;

/**
 * Enumeration representing different authentication providers used within the application.
 */
@SuppressWarnings("unused")
public enum AuthProvider {
    /**
     * The local authentication provider.
     */
    LOCAL,

    /**
     * The Google authentication provider.
     */
    GOOGLE("google"),

    /**
     * The GitHub authentication provider.
     */
    GITHUB("github");

    /**
     * A map to look up AuthProvider enum values by their registration identifiers.
     */
    static final Map<String, AuthProvider> AUTH_PROVIDERS_BY_REGISTRATION_ID = new HashMap<>(size(values()));
    static {
        for (AuthProvider a : values()) {
            AUTH_PROVIDERS_BY_REGISTRATION_ID.put(a.registrationId, a);
        }
    }

    /**
     * The registration identifier associated with the authentication provider.
     */
    final String registrationId;

    /**
     * Constructs an AuthProvider with the specified registration identifier.
     *
     * @param registrationId The registration identifier for the authentication provider.
     */
    AuthProvider(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * Constructs an AuthProvider without a registration identifier.
     */
    AuthProvider() {
        registrationId = null;
    }

    /**
     * Get the AuthProvider enum value associated with the given registration identifier.
     *
     * @param registrationId The registration identifier for the authentication provider.
     * @return The corresponding AuthProvider enum value, or null if not found.
     */
    public static AuthProvider byRegistrationId(String registrationId) {
        return AUTH_PROVIDERS_BY_REGISTRATION_ID.get(registrationId);
    }

    @Override
    public String toString() {
        return "AuthProvider{" +
                "registrationId='" + registrationId + '\'' +
                '}';
    }
}
