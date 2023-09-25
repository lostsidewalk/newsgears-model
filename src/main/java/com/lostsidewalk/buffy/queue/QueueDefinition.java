package com.lostsidewalk.buffy.queue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lostsidewalk.buffy.Auditable;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.lostsidewalk.buffy.queue.QueueDefinition.QueueStatus.ENABLED;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static lombok.AccessLevel.PUBLIC;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Represents a definition for a feed queue.
 */
@Data
@JsonInclude(NON_EMPTY)
public class QueueDefinition implements Serializable, Auditable {

    @Serial
    private static final long serialVersionUID = 294234688900249823L;
    /**
     * The unique identifier of the queue.
     */
    Long id;
    /**
     * The identifier of the queue.
     */
    @NotBlank
    String ident;
    /**
     * The title of the queue.
     */
    String title;
    /**
     * The description of the queue.
     */
    String description;
    /**
     * The generator information of the queue.
     */
    String generator;
    /**
     * The transport identifier of the queue.
     */
    @NotBlank
    String transportIdent;
    /**
     * The username associated with the queue.
     */
    @NotBlank
    String username;
    /**
     * The status of the queue (enabled or disabled).
     */
    @Setter(PUBLIC)
    @NotNull
    QueueStatus queueStatus;
    /**
     * The configuration for exporting the queue.
     */
    Serializable exportConfig;
    /**
     * The copyright information of the queue.
     */
    String copyright;
    /**
     * The language of the queue.
     */
    @NotBlank
    String language;
    /**
     * The image source of the queue.
     */
    String queueImgSrc;
    /**
     * The hash of the image source for the queue's image.
     */
    String queueImgTransportIdent;
    /**
     * The timestamp when the queue was last deployed.
     */
    Date lastDeployed;
    /**
     * Whether authentication is required for the queue.
     */
    Boolean isAuthenticated;
    /**
     * The timestamp the queue was created.
     */
    Date created;
    /**
     * The timestamp the queue was last modified in any way.
     */
    Date lastModified;

    /**
     * Creates a new QueueDefinition with the specified properties.
     *
     * @param ident                  The unique identifier of the queue definition.
     * @param title                  The title of the queue.
     * @param description            The description of the queue.
     * @param generator              The generator associated with the queue.
     * @param transportIdent         The transport identifier of the queue.
     * @param username               The username associated with the queue.
     * @param queueStatus            The status of the queue.
     * @param exportConfig           The configuration for exporting data from the queue.
     * @param copyright              The copyright information for the queue.
     * @param language               The language of the queue.
     * @param queueImgSrc            The source of the queue's image.
     * @param queueImgTransportIdent The transport identifier of the queue's image.
     * @param lastDeployed           The timestamp when the queue was last deployed.
     * @param isAuthenticated        Indicates whether the queue requires authentication.
     * @param created                The timestamp when the queue was created.
     * @param lastModified           The timestamp when the queue was last modified.
     */
    QueueDefinition(String ident, String title, String description, String generator, String transportIdent,
                    String username, QueueStatus queueStatus, Serializable exportConfig, String copyright,
                    String language, String queueImgSrc, String queueImgTransportIdent, Date lastDeployed,
                    Boolean isAuthenticated, Date created, Date lastModified) {
        this.ident = ident;
        this.title = title;
        this.description = description;
        this.generator = generator;
        this.transportIdent = transportIdent;
        this.username = username;
        this.queueStatus = queueStatus;
        this.exportConfig = exportConfig;
        this.copyright = copyright;
        this.language = language;
        this.queueImgSrc = queueImgSrc;
        this.queueImgTransportIdent = queueImgTransportIdent;
        this.lastDeployed = lastDeployed;
        this.isAuthenticated = isAuthenticated;
        this.created = created;
        this.lastModified = lastModified;
    }

    /**
     * Static factory method to create a QueueDefinition object with explicit status.
     *
     * @param ident                  The unique identifier of the queue definition.
     * @param title                  The title of the queue.
     * @param description            The description of the queue.
     * @param generator              The generator associated with the queue.
     * @param transportIdent         The transport identifier of the queue.
     * @param username               The username associated with the queue.
     * @param queueStatus            The status of the queue.
     * @param exportConfig           The configuration for exporting data from the queue.
     * @param copyright              The copyright information for the queue.
     * @param language               The language of the queue.
     * @param queueImgSrc            The source of the queue's image.
     * @param queueImgTransportIdent The transport identifier of the queue's image.
     * @param lastDeployed           The timestamp when the queue was last deployed.
     * @param isAuthenticated        Indicates whether the queue requires authentication.
     * @param created                The timestamp when the queue was created.
     * @param lastModified           The timestamp when the queue was last modified.
     * @return A new QueueDefinition instance.
     */
    public static QueueDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            QueueStatus queueStatus,
            Serializable exportConfig,
            String copyright,
            String language,
            String queueImgSrc,
            String queueImgTransportIdent,
            Date lastDeployed,
            Boolean isAuthenticated,
            Date created,
            Date lastModified) {
        return new QueueDefinition(
                ident,
                title,
                description,
                generator,
                transportIdent,
                username,
                queueStatus,
                exportConfig,
                copyright,
                language,
                queueImgSrc,
                queueImgTransportIdent,
                lastDeployed,
                isAuthenticated,
                created,
                lastModified);
    }

    /**
     * Static factory method to create a QueueDefinition object with default status.
     *
     * @param ident           The unique identifier of the queue definition.
     * @param title           The title of the queue.
     * @param description     The description of the queue.
     * @param generator       The generator associated with the queue.
     * @param transportIdent  The transport identifier of the queue.
     * @param username        The username associated with the queue.
     * @param exportConfig    The configuration for exporting data from the queue.
     * @param copyright       The copyright information for the queue.
     * @param language        The language of the queue.
     * @param queueImgSrc     The source of the queue's image.
     * @param isAuthenticated Indicates whether the queue requires authentication.
     * @return A new QueueDefinition instance with default status.
     */
    public static QueueDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            Serializable exportConfig,
            String copyright,
            String language,
            String queueImgSrc,
            Boolean isAuthenticated) {
        String queueImgTransportIdent = getImgSrcHash(queueImgSrc);
        return new QueueDefinition(
                ident,
                title,
                description,
                generator,
                transportIdent,
                username,
                ENABLED,
                exportConfig,
                copyright,
                language,
                queueImgSrc,
                queueImgTransportIdent,
                null,
                isAuthenticated,
                new Date(),
                null);
    }

    /**
     * Computes the MD5 hash of an image source.
     *
     * @param imageSrc The image source to compute the hash for.
     * @return The MD5 hash of the image source.
     */
    private static String getImgSrcHash(String imageSrc) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return computeImageHash(md, imageSrc);
        } catch (NoSuchAlgorithmException ignored) {
            // ignored
        }

        return null;
    }

    /**
     * Computes the hash of an image source using the provided MessageDigest.
     *
     * @param md       The MessageDigest instance to use.
     * @param imageSrc The image source to compute the hash for.
     * @return The computed hash of the image source.
     */
    static String computeImageHash(MessageDigest md, String imageSrc) {
        return isNotEmpty(imageSrc) ? printHexBinary(md.digest(serialize(imageSrc))) : null;
    }

    /**
     * Possible statuses of the feed queue.
     */
    public enum QueueStatus {
        /**
         * The queue is enabled.
         */
        ENABLED,

        /**
         * The queue is disabled.
         */
        @SuppressWarnings("unused")
        DISABLED
    }
}
