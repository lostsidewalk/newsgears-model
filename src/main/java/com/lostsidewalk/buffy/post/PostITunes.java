package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.itunes.EntryInformation;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.ITunes;
import com.rometools.modules.itunes.types.Duration;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents iTunes-specific metadata associated with a post.
 */
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostITunes implements Serializable {

    @Serial
    private static final long serialVersionUID = 983403122303L;

    /**
     * The author associated with this post
     */
    String author;

    /**
     * Indicates if the post is blocked
     */
    boolean isBlock;

    /**
     * Indicates if the post is explicit
     */
    boolean isExplicit;

    /**
     * Indicates if the explicit status is nullable
     */
    Boolean isExplicitNullable;

    /**
     * The URL of the image associated with the post
     */
    URL image;

    /**
     * The URI of the image associated with the post
     */
    String imageUri;

    /**
     * Keywords associated with the post
     */
    String[] keywords;

    /**
     * Subtitle of the post
     */
    String subTitle;

    /**
     * Summary of the post
     */
    String summary;

    /**
     * Indicates if the post is close captioned
     */
    boolean isCloseCaptioned;

    /**
     * Duration of the post
     */
    Duration duration;

    /**
     * Episode number of the post
     */
    Integer episode;

    /**
     * Type of the episode
     */
    String episodeType;

    /**
     * Order of the post
     */
    Integer order;

    /**
     * Season number of the post
     */
    Integer season;

    /**
     * Title of the post
     */
    String title;

    /**
     * Private constructor for creating a PostITunes object.
     *
     * @param author             The author associated with this post.
     * @param isBlock            Indicates if the post is blocked.
     * @param isExplicit         Indicates if the post is explicit.
     * @param isExplicitNullable Indicates if the explicit status is nullable.
     * @param image              The URL of the image associated with the post.
     * @param imageUri           The URI of the image associated with the post.
     * @param keywords           Keywords associated with the post.
     * @param subTitle           Subtitle of the post.
     * @param summary            Summary of the post.
     * @param isCloseCaptioned   Indicates if the post is close captioned.
     * @param duration           Duration of the post.
     * @param episode            Episode number of the post.
     * @param episodeType        Type of the episode.
     * @param order              Order of the post.
     * @param season             Season number of the post.
     * @param title              Title of the post.
     */
    PostITunes(String author, boolean isBlock, boolean isExplicit, Boolean isExplicitNullable, URL image, String imageUri, String[] keywords, String subTitle, String summary,
               boolean isCloseCaptioned, Duration duration, Integer episode, String episodeType, Integer order, Integer season, String title) {
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

    /**
     * Static factory method to create a PostITunes object from an ITunes instance.
     *
     * @param iTunes The ITunes instance to convert to a PostITunes object.
     * @return A new PostITunes object created from the given ITunes instance.
     */
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

    /**
     * Converts the PostITunes object to an ITunes entry module.
     *
     * @return An ITunes entry module representing the PostITunes object.
     */
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

    /**
     * Converts the PostITunes object to an ITunes feed module.
     *
     * @return An ITunes feed module representing the PostITunes object.
     */
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
