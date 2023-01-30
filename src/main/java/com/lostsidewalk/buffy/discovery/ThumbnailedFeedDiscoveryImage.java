package com.lostsidewalk.buffy.discovery;

import lombok.Data;

import java.io.Serializable;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

@Data
public class ThumbnailedFeedDiscoveryImage implements Serializable {

    public static final long serialVersionUID = 120349823509L;

    String title;
    String description;
    Integer height;
    Integer width;
    String link;
    String imgSrc;
    String url;

    ThumbnailedFeedDiscoveryImage(
            String title,
            String description,
            Integer height,
            Integer width,
            String link,
            String url
    ) {
        this.title = title;
        this.description = description;
        this.height = height;
        this.width = width;
        this.link = link;
        this.url = url;
    }

    public static ThumbnailedFeedDiscoveryImage from(FeedDiscoveryImageInfo imageInfo, byte[] image) {
        ThumbnailedFeedDiscoveryImage r = new ThumbnailedFeedDiscoveryImage(
                imageInfo.getTitle(),
                imageInfo.getDescription(),
                imageInfo.getHeight(),
                imageInfo.getWidth(),
                imageInfo.getLink(),
                imageInfo.getUrl()
        );
        r.imgSrc = encodeBase64String(image);
        return r;
    }
}
