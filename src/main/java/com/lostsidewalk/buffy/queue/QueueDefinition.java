package com.lostsidewalk.buffy.queue;

import com.fasterxml.jackson.annotation.JsonInclude;
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

@Data
@JsonInclude(NON_EMPTY)
public class QueueDefinition implements Serializable {

    @Serial
    private static final long serialVersionUID = 294234688900249823L;

    @SuppressWarnings("unused")
    public enum QueueStatus {
        ENABLED, DISABLED
    }

    private Long id;

    @NotBlank
    private String ident;

    @NotBlank
    private String title;

    private String description;

    private String generator;

    @NotBlank
    private String transportIdent;

    @NotBlank
    private String username;

    @Setter(PUBLIC)
    @NotNull
    private QueueStatus queueStatus;

    private Serializable exportConfig;

    private String copyright;

    @NotBlank
    private String language;

    private String queueImgSrc;

    private String queueImgTransportIdent;

    private Date lastDeployed;

    private Boolean isAuthenticated;

    QueueDefinition(String ident, String title, String description, String generator, String transportIdent,
                    String username, QueueStatus queueStatus, Serializable exportConfig, String copyright,
                    String language, String queueImgSrc, String queueImgTransportIdent, Date lastDeployed,
                    Boolean isAuthenticated)
    {
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
    }

    @SuppressWarnings("unused")
    static QueueDefinition from(
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
            Boolean isAuthenticated)
    {
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
                isAuthenticated);
    }

    @SuppressWarnings("unused")
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
            Boolean isAuthenticated)
    {
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
                isAuthenticated);
    }

    private static String getImgSrcHash(String imageSrc) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return computeImageHash(md, imageSrc);
        } catch (NoSuchAlgorithmException ignored) {
            // ignored
        }

        return null;
    }

    static String computeImageHash(MessageDigest md, String imageSrc) {
        return isNotEmpty(imageSrc) ? printHexBinary(md.digest(serialize(imageSrc))) : null;
    }
}
