package com.lostsidewalk.buffy.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.lostsidewalk.buffy.auth.AuthProvider.LOCAL;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * The User class represents a user entity with authentication and subscription information.
 */
@Data
public class User {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String emailAddress;

    private String authClaim;

    private String pwResetClaim;

    private String pwResetAuthClaim;

    private String verificationClaim;

    private boolean isVerified;

    private String subscriptionStatus;

    private Date subscriptionExpDate;

    private String customerId;

    @NotNull
    private AuthProvider authProvider;

    private String authProviderId;

    private String authProviderProfileImgUrl;

    private String authProviderUsername;

    /**
     * Creates a User object with the provided username, password, and email address.
     *
     * @param username     The username of the user.
     * @param password     The password of the user.
     * @param emailAddress The email address of the user.
     */
    @SuppressWarnings("unused")
    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.authProvider = LOCAL;
        this.authProviderId = null;
        this.authProviderProfileImgUrl = null;
        this.authProviderUsername = null;
    }

    /**
     * Creates a User object with the provided ID, username, password, and email address.
     *
     * @param id           The ID of the user.
     * @param username     The username of the user.
     * @param password     The password of the user.
     * @param emailAddress The email address of the user.
     */
    @SuppressWarnings("unused")
    public User(Long id, String username, String password, String emailAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.authProvider = LOCAL;
        this.authProviderId = null;
        this.authProviderProfileImgUrl = null;
        this.authProviderUsername = null;
    }

    /**
     * Creates a User object with the provided username, email address, authentication provider details, and profile information.
     *
     * @param username                  The username of the user.
     * @param emailAddress              The email address of the user.
     * @param authProvider              The authentication provider used by the user.
     * @param authProviderId            The ID associated with the user on the authentication provider's platform.
     * @param authProviderProfileImgUrl The URL of the user's profile image on the authentication provider's platform.
     * @param authProviderUsername      The username on the authentication provider's platform.
     */
    @SuppressWarnings("unused")
    public User(String username, String emailAddress, AuthProvider authProvider, String authProviderId, String authProviderProfileImgUrl, String authProviderUsername) {
        this.username = username;
        this.password = randomAlphanumeric(32);
        this.emailAddress = emailAddress;
        this.authProvider = authProvider;
        this.authProviderId = authProviderId;
        this.authProviderProfileImgUrl = authProviderProfileImgUrl;
        this.authProviderUsername = authProviderUsername;
    }
}
