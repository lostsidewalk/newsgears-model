package com.lostsidewalk.buffy.feed;

import lombok.Data;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static javax.xml.bind.DatatypeConverter.printHexBinary;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
public class FeedDefinition {

    private Long id;

    private String ident;

    private String title;

    private String description;

    private String generator;

    private String transportIdent;

    private String username;

    private boolean isActive;

    private Serializable exportConfig;

    private String copyright;

    private String language;

    private String feedImgSrc;

    private String feedImgTransportIdent;

    private Date lastDeployed;

    FeedDefinition(String ident, String title, String description, String generator, String transportIdent,
                   String username, boolean isActive, Serializable exportConfig, String copyright,
                   String language, String feedImgSrc, String feedImgTransportIdent, Date lastDeployed)
    {
        this.ident = ident;
        this.title = title;
        this.description = description;
        this.generator = generator;
        this.transportIdent = transportIdent;
        this.username = username;
        this.isActive = isActive;
        this.exportConfig = exportConfig;
        this.copyright = copyright;
        this.language = language;
        this.feedImgSrc = feedImgSrc;
        this.feedImgTransportIdent = feedImgTransportIdent;
        this.lastDeployed = lastDeployed;
    }

    @SuppressWarnings("unused")
    static FeedDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            boolean isActive,
            Serializable exportConfig,
            String copyright,
            String language,
            String feedImgSrc,
            String feedImgTransportIdent,
            Date lastDeployed)
    {
        return new FeedDefinition(
                ident,
                title,
                description,
                generator,
                transportIdent,
                username,
                isActive,
                exportConfig,
                copyright,
                language,
                feedImgSrc,
                feedImgTransportIdent,
                lastDeployed);
    }

    @SuppressWarnings("unused")
    public static FeedDefinition from(
            String ident,
            String title,
            String description,
            String generator,
            String transportIdent,
            String username,
            boolean isActive,
            Serializable exportConfig,
            String copyright,
            String language,
            String feedImgSrc)
    {
        String feedImgTransportIdent = getFeedImgSrcHash(feedImgSrc);
        return new FeedDefinition(
                ident,
                title,
                description,
                generator,
                transportIdent,
                username,
                isActive,
                exportConfig,
                copyright,
                language,
                feedImgSrc,
                feedImgTransportIdent,
                null);
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
