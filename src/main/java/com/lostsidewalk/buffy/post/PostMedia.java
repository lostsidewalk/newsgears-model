package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaEntryModuleImpl;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Represents media information associated with a post.
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMedia extends BasePostMediaObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 983403122303L;

    /**
     * Metadata associated with the media content of the post.
     */
    PostMediaMetadata postMediaMetadata;

    /**
     * List of media groups associated with the post.
     */
    List<PostMediaGroup> postMediaGroups;

    /**
     * List of media contents associated with the post.
     */
    List<PostMediaContent> postMediaContents;

    /**
     * Private constructor for creating a PostMedia object.
     *
     * @param postMediaMetadata Metadata associated with the media content.
     * @param postMediaGroups   List of media groups associated with the post.
     * @param postMediaContents List of media contents associated with the post.
     */
    PostMedia(PostMediaMetadata postMediaMetadata, Collection<? extends PostMediaGroup> postMediaGroups, Collection<? extends PostMediaContent> postMediaContents) {
        this.postMediaMetadata = postMediaMetadata;
        this.postMediaGroups = postMediaGroups == null ? null : List.copyOf(postMediaGroups);
        this.postMediaContents = postMediaContents == null ? null : List.copyOf(postMediaContents);
    }

    /**
     * Static factory method to create a PostMedia object from a MediaEntryModule instance.
     *
     * @param mm The MediaEntryModule instance to convert to a PostMedia object.
     * @return A new PostMedia object created from the given MediaEntryModule instance.
     */
    public static PostMedia from(MediaEntryModule mm) {
        // media module top-level metadata
        PostMediaMetadata postMediaMetadata = PostMediaMetadata.from(mm.getMetadata());
        // top-level media groups
        List<PostMediaGroup> postMediaGroups = Arrays.stream(mm.getMediaGroups()).map(PostMediaGroup::from).collect(toList());
        // top-level media contents
        List<PostMediaContent> postMediaContents = Arrays.stream(mm.getMediaContents()).map(PostMediaContent::from).collect(toList());

        return new PostMedia(postMediaMetadata, postMediaGroups, postMediaContents);
    }

    /**
     * Converts the PostMedia object to a MediaEntryModule instance.
     *
     * @return A MediaEntryModule instance representing the PostMedia object.
     */
    @SuppressWarnings("unused")
    public final MediaEntryModule toModule() {
        MediaEntryModuleImpl mm = new MediaEntryModuleImpl();
        mm.setMetadata(ofNullable(postMediaMetadata).map(PostMediaMetadata::toModule).orElse(null));
        mm.setMediaContents(convertArray(this::getPostMediaContents, PostMediaContent::toModule, MediaContent.class));
        mm.setMediaGroups(convertArray(this::getPostMediaGroups, PostMediaGroup::toModule, MediaGroup.class));

        return mm;
    }
}
