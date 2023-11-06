package com.lostsidewalk.buffy.queue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lostsidewalk.buffy.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a credential for a feed queue.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class QueueCredential implements Serializable, Auditable {

    @Serial
    private static final long serialVersionUID = 2946889266426024982L;

    /**
     * The unique identifier of the queue credential.
     */
    Long id;

    /**
     * The identifier of the queue associated with the credential.
     */
    Long queueId;

    /**
     * The username associated with the credential.
     */
    String username;

    /**
     * The basic authentication username.
     */
    String basicUsername;

    /**
     * The basic authentication password.
     */
    String basicPassword;

    /**
     * The timestamp the queue was created.
     */
    Date created;

    /**
     * The timestamp the queue was last modified in any way.
     */
    Date lastModified;

    /**
     * Creates a new QueueCredential with the specified properties.
     *
     * @param id            The unique identifier of the queue credential.
     * @param username      The username associated with the credential.
     * @param queueId       The identifier of the queue associated with the credential.
     * @param basicUsername The basic authentication username.
     * @param basicPassword The basic authentication password.
     * @param created       The timestamp when the queue credential was created.
     * @param lastModified  The timestamp when the queue credential was last modified.
     */
    QueueCredential(Long id, String username, Long queueId, String basicUsername, String basicPassword, Date created, Date lastModified) {
        this.id = id;
        this.queueId = queueId;
        this.username = username;
        this.basicUsername = basicUsername;
        this.basicPassword = basicPassword;
        this.created = created == null ? null : new Date(created.getTime());
        this.lastModified = lastModified == null ? null : new Date(lastModified.getTime());
    }

    /**
     * Static factory method to create a QueueCredential object with the specified properties.
     *
     * @param id            The unique identifier of the queue credential.
     * @param username      The username associated with the credential.
     * @param queueId       The identifier of the queue associated with the credential.
     * @param basicUsername The basic authentication username.
     * @param basicPassword The basic authentication password.
     * @param created       The timestamp when the queue credential was created.
     * @param lastModified  The timestamp when the queue credential was last modified.
     * @return A new QueueCredential instance.
     */
    public static QueueCredential from(
            Long id,
            String username,
            Long queueId,
            String basicUsername,
            String basicPassword,
            Date created,
            Date lastModified) {
        return new QueueCredential(
                id,
                username,
                queueId,
                basicUsername,
                basicPassword,
                created,
                lastModified);
    }

    /**
     * Static factory method to create a QueueCredential object with the specified properties.
     *
     * @param username      The username associated with the credential.
     * @param queueId       The identifier of the queue associated with the credential.
     * @param basicUsername The basic authentication username.
     * @param basicPassword The basic authentication password.
     * @return A new QueueCredential instance.
     */
    public static QueueCredential from(
            String username,
            Long queueId,
            String basicUsername,
            String basicPassword) {
        return new QueueCredential(
                null,
                username,
                queueId,
                basicUsername,
                basicPassword,
                new Date(),
                null);
    }
}
