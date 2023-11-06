package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rometools.modules.itunes.EntryInformation;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.ITunes;
import com.rometools.modules.itunes.types.Duration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Represents iTunes-specific metadata associated with a post.
 */
@Slf4j
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
    @JsonProperty("isBlock")
    boolean isBlock;

    /**
     * Indicates if the post is explicit
     */
    @JsonProperty("isExplicit")
    boolean isExplicit;

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
    @JsonProperty("isCloseCaptioned")
    boolean isCloseCaptioned;

    /**
     * Duration of the post
     */
    Long duration;

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
     * @param imageUri           The URI of the image associated with the post.
     * @param keywords           Keywords associated with the post.
     * @param subTitle           Subtitle of the post.
     * @param summary            Summary of the post.
     * @param isCloseCaptioned   Indicates if the post is close captioned.
     * @param duration           Duration of the post (ms).
     * @param episode            Episode number of the post.
     * @param episodeType        Type of the episode.
     * @param order              Order of the post.
     * @param season             Season number of the post.
     * @param title              Title of the post.
     */
    PostITunes(String author, boolean isBlock, boolean isExplicit, String imageUri, String[] keywords, String subTitle, String summary,
               boolean isCloseCaptioned, Long duration, Integer episode, String episodeType, Integer order, Integer season, String title) {
        this.author = author;
        this.isBlock = isBlock;
        this.isExplicit = isExplicit;
        this.imageUri = imageUri;
        this.keywords = keywords == null ? null : Arrays.copyOf(keywords, keywords.length);
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
        String imageUri = iTunes.getImageUri();
        String[] keywords = iTunes.getKeywords();
        String subTitle = iTunes.getSubtitle();
        String summary = iTunes.getSummary();

        boolean isCloseCaptioned = false;
        Long duration = null;
        Integer episode = null;
        String episodeType = null;
        Integer order = null;
        Integer season = null;
        String title = null;
        if (iTunes instanceof EntryInformation entryInformation) {
            isCloseCaptioned = entryInformation.getClosedCaptioned();
            duration = Optional.ofNullable(entryInformation.getDuration())
                    .map(Duration::getMilliseconds)
                    .orElse(null);
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
    public final ITunes toEntryModule() {
        EntryInformation e = new EntryInformationImpl();
        e.setAuthor(author);
        e.setBlock(isBlock);
        e.setExplicit(isExplicit);
        e.setImageUri(imageUri);
        if (imageUri != null) {
            try {
                e.setImage(new URL(imageUri));
            } catch (MalformedURLException ex) {
                // ignored
            }
        }
        e.setKeywords(keywords);
        e.setSubtitle(subTitle);
        e.setSummary(summary);
        e.setTitle(title);
        e.setSeason(season);
        e.setOrder(order);
        e.setEpisodeType(episodeType);
        e.setEpisode(episode);
        e.setClosedCaptioned(isCloseCaptioned);
        if (duration != null) {
            e.setDuration(new Duration(duration));
        }

        return e;
    }

    /**
     * Converts the PostITunes object to an ITunes feed module.
     *
     * @return An ITunes feed module representing the PostITunes object.
     */
    @SuppressWarnings("unused")
    public final ITunes toFeedModule() {
        ITunes iTunes = new FeedInformationImpl();
        iTunes.setAuthor(author);
        iTunes.setBlock(isBlock);
        iTunes.setExplicit(isExplicit);
        iTunes.setImageUri(imageUri);
        if (isNotBlank(imageUri)) {
            try {
                iTunes.setImage(new URL(imageUri));
            } catch (MalformedURLException e) {
                // ignored
            }
        }
        iTunes.setKeywords(keywords);
        iTunes.setSubtitle(subTitle);
        iTunes.setSummary(summary);

        return iTunes;
    }
}
