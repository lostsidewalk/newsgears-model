package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Community;
import com.rometools.modules.mediarss.types.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Represents community information associated with a post's media.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaCommunity implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823049823L;

    /**
     * The star rating associated with the media content.
     */
    PostMediaStarRating postMediaStarRating;

    /**
     * The statistics related to the media content.
     */
    PostMediaStatistics postMediaStatistics;

    /**
     * The set of tags associated with the media content.
     */
    Set<PostMediaTag> postMediaTags;

    /**
     * Constructor to create a PostMediaCommunity object.
     *
     * @param postMediaStarRating The star rating associated with the media content.
     * @param postMediaStatistics The statistics related to the media content.
     * @param postMediaTags       The set of tags associated with the media content.
     */
    PostMediaCommunity(PostMediaStarRating postMediaStarRating, PostMediaStatistics postMediaStatistics, Collection<? extends PostMediaTag> postMediaTags) {
        this.postMediaStarRating = postMediaStarRating;
        this.postMediaStatistics = postMediaStatistics;
        this.postMediaTags = Set.copyOf(postMediaTags);
    }

    /**
     * Static factory method to create a PostMediaCommunity object from a Community instance.
     *
     * @param community The Community instance to convert to a PostMediaCommunity object.
     * @return A new PostMediaCommunity object created from the given Community instance.
     */
    public static PostMediaCommunity from(Community community) {
        Set<Tag> tags = community.getTags();
        Set<PostMediaTag> postMediaTags = isEmpty(tags) ? null : tags.stream().map(PostMediaTag::from).collect(toSet());

        return new PostMediaCommunity(
                PostMediaStarRating.from(community.getStarRating()),
                PostMediaStatistics.from(community.getStatistics()),
                postMediaTags
        );
    }

    /**
     * Converts the PostMediaCommunity object to a Community instance.
     *
     * @return A Community instance representing the PostMediaCommunity object.
     */
    public final Community toModule() {
        Community community = new Community();
        community.setStarRating(ofNullable(postMediaStarRating).map(PostMediaStarRating::toModule).orElse(null));
        community.setStatistics(ofNullable(postMediaStatistics).map(PostMediaStatistics::toModule).orElse(null));

        return community;
    }
}
