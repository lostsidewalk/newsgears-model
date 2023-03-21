package com.lostsidewalk.buffy.feed;

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
import static com.lostsidewalk.buffy.feed.FeedDefinition.FeedStatus.ENABLED;
import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static lombok.AccessLevel.PUBLIC;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
@JsonInclude(NON_EMPTY)
public class FeedDefinition implements Serializable {

    @Serial
    private static final long serialVersionUID = 294234688900249823L;

    @SuppressWarnings("unused")
    public enum FeedStatus {
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
    private FeedStatus feedStatus;

    private Serializable exportConfig;

    private String copyright;

    @NotBlank
    private String language;

    private String feedImgSrc;

    private String feedImgTransportIdent;

    private Date lastDeployed;

    private Boolean isAuthenticated;

    FeedDefinition(String ident, String title, String description, String generator, String transportIdent,
                   String username, FeedStatus feedStatus, Serializable exportConfig, String copyright,
                   String language, String feedImgSrc, String feedImgTransportIdent, Date lastDeployed,
                   Boolean isAuthenticated)
    {
        this.ident = ident;
        this.title = title;
        this.description = description;
        this.generator = generator;
        this.transportIdent = transportIdent;
        this.username = username;
        this.feedStatus = feedStatus;
        this.exportConfig = exportConfig;
        this.copyright = copyright;
        this.language = language;
        this.feedImgSrc = feedImgSrc;
        this.feedImgTransportIdent = feedImgTransportIdent;
        this.lastDeployed = lastDeployed;
        this.isAuthenticated = isAuthenticated;
    }

    @SuppressWarnings("unused")
    static FeedDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            FeedStatus feedStatus,
            Serializable exportConfig,
            String copyright,
            String language,
            String feedImgSrc,
            String feedImgTransportIdent,
            Date lastDeployed,
            Boolean isAuthenticated)
    {
        return new FeedDefinition(
                ident,
                title,
                description,
                generator,
                transportIdent,
                username,
                feedStatus,
                exportConfig,
                copyright,
                language,
                feedImgSrc,
                feedImgTransportIdent,
                lastDeployed,
                isAuthenticated);
    }

    @SuppressWarnings("unused")
    public static FeedDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            Serializable exportConfig,
            String copyright,
            String language,
            String feedImgSrc,
            Boolean isAuthenticated)
    {
        String feedImgTransportIdent = getFeedImgSrcHash(feedImgSrc);
        return new FeedDefinition(
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
                feedImgSrc,
                feedImgTransportIdent,
                null,
                isAuthenticated);
    }

    private static String getFeedImgSrcHash(String imageSrc) {
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
