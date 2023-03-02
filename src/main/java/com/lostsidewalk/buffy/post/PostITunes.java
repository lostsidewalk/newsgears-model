package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.itunes.EntryInformation;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.ITunes;
import com.rometools.modules.itunes.types.Duration;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostITunes implements Serializable {

    @Serial
    private static final long serialVersionUID = 983403122303L;

    String author;
    boolean isBlock; // wtf does this mean
    boolean isExplicit;
    Boolean isExplicitNullable; // again, sounds stupid
    URL image;
    String imageUri;
    String[] keywords;
    String subTitle;
    String summary;
    boolean isCloseCaptioned;
    Duration duration;
    Integer episode;
    String episodeType;
    Integer order;
    Integer season;
    String title;

    PostITunes(String author, boolean isBlock, boolean isExplicit, Boolean isExplicitNullable, URL image, String imageUri, String[] keywords, String subTitle, String summary,
               boolean isCloseCaptioned, Duration duration, Integer episode, String episodeType, Integer order, Integer season, String title)
    {
        this.author = author;
        this.isBlock = isBlock;
        this.isExplicit = isExplicit;
        this.isExplicitNullable = isExplicitNullable;
        this.image = image;
        this.imageUri = imageUri;
        this.keywords = keywords;
        this.subTitle = subTitle;
        this.summary = summary;
        this.isCloseCaptioned = isCloseCaptioned;
        this.duration = duration;
        this.episode = episode;
        this.episodeType = episodeType;
        this.order = order;
        this.season = season;
        this.title = title;
    }

    public static PostITunes from(ITunes iTunes) {
        String author = iTunes.getAuthor();
        boolean isBlock = iTunes.getBlock(); // wtf does this mean
        boolean isExplicit = iTunes.getExplicit();
        Boolean isExplicitNullable = iTunes.getExplicitNullable(); // again, sounds stupid
        URL image = iTunes.getImage();
        String imageUri = iTunes.getImageUri();
        String[] keywords = iTunes.getKeywords();
        String subTitle = iTunes.getSubtitle();
        String summary = iTunes.getSummary();

        boolean isCloseCaptioned = false;
        com.rometools.modules.itunes.types.Duration duration = null;
        Integer episode = null;
        String episodeType = null;
        Integer order = null;
        Integer season = null;
        String title = null;
        if (iTunes instanceof EntryInformation entryInformation) {
            isCloseCaptioned = entryInformation.getClosedCaptioned();
            duration = entryInformation.getDuration();
            episode = entryInformation.getEpisode();
            episodeType = entryInformation.getEpisodeType();
            order = entryInformation.getOrder();
            season = entryInformation.getSeason();
            title = entryInformation.getTitle();
        }

        return new PostITunes(
                author,
                isBlock,
                isExplicit,
                isExplicitNullable,
                image,
                imageUri,
                keywords,
                subTitle,
                summary,
                isCloseCaptioned,
                duration,
                episode,
                episodeType,
                order,
                season,
                title
        );
    }

    @SuppressWarnings("unused")
    public ITunes toEntryModule() {
        EntryInformationImpl e = new EntryInformationImpl();
        e.setAuthor(author);
        e.setBlock(isBlock);
        e.setExplicit(isExplicit);
        e.setExplicitNullable(isExplicitNullable);
        e.setImage(image);
        e.setImageUri(imageUri);
        e.setKeywords(keywords);
        e.setSubtitle(subTitle);
        e.setSummary(summary);

        return e;
    }

    @SuppressWarnings("unused")
    public ITunes toFeedModule() {
        FeedInformationImpl f = new FeedInformationImpl();
        f.setAuthor(author);
        f.setBlock(isBlock);
        f.setExplicit(isExplicit);
        f.setExplicitNullable(isExplicitNullable);
        f.setImage(image);
        f.setImageUri(imageUri);
        f.setKeywords(keywords);
        f.setSubtitle(subTitle);
        f.setSummary(summary);

        return f;
    }
}
