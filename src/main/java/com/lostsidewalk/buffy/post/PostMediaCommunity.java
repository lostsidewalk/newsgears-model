package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Community;
import com.rometools.modules.mediarss.types.Tag;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaCommunity implements Serializable {

    @Serial
    private static final long serialVersionUID = 29823049823L;

    PostMediaStarRating postMediaStarRating;

    PostMediaStatistics postMediaStatistics;

    Set<PostMediaTag> postMediaTags;

    public PostMediaCommunity(PostMediaStarRating postMediaStarRating, PostMediaStatistics postMediaStatistics, Set<PostMediaTag> postMediaTags) {
        this.postMediaStarRating = postMediaStarRating;
        this.postMediaStatistics = postMediaStatistics;
        this.postMediaTags = postMediaTags;
    }

    public static PostMediaCommunity from(Community c) {
        Set<Tag> tags = c.getTags();
        Set<PostMediaTag> postMediaTags = isEmpty(tags) ? null : tags.stream().map(PostMediaTag::from).collect(toSet());

        return new PostMediaCommunity(
                PostMediaStarRating.from(c.getStarRating()),
                PostMediaStatistics.from(c.getStatistics()),
                postMediaTags
        );
    }

    public Community toModule() {
        Community community = new Community();
        community.setStarRating(ofNullable(getPostMediaStarRating()).map(PostMediaStarRating::toModule).orElse(null));
        community.setStatistics(ofNullable(getPostMediaStatistics()).map(PostMediaStatistics::toModule).orElse(null));

        return community;
    }
}
