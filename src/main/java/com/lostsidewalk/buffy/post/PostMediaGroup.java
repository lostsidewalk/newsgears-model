package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

/**
 * Represents a media group associated with a post's media.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaGroup extends BasePostMediaObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 298231231249823L;

    /**
     * List of media content items within the media group.
     */
    List<PostMediaContent> postMediaContents;

    /**
     * Metadata associated with the media group.
     */
    PostMediaMetadata postMediaMetadata;

    /**
     * Index of the default content within the media group.
     */
    Integer defaultContentIndex;

    /**
     * Constructor to create a PostMediaGroup object.
     *
     * @param postMediaContents   List of media content items within the media group.
     * @param postMediaMetadata   Metadata associated with the media group.
     * @param defaultContentIndex Index of the default content within the media group.
     */
    PostMediaGroup(List<PostMediaContent> postMediaContents, PostMediaMetadata postMediaMetadata, Integer defaultContentIndex) {
        super();
        this.postMediaContents = postMediaContents;
        this.postMediaMetadata = postMediaMetadata;
        this.defaultContentIndex = defaultContentIndex;
    }

    /**
     * Creates a PostMediaGroup object from a MediaGroup instance.
     *
     * @param mediaGroup The MediaGroup instance to convert from.
     * @return A PostMediaGroup object with data from the MediaGroup instance.
     */
    public static PostMediaGroup from(MediaGroup mediaGroup) {
        List<PostMediaContent> postMediaContents = stream(mediaGroup.getContents())
                .map(PostMediaContent::from)
                .toList();
        PostMediaMetadata postMediaMetadata = PostMediaMetadata.from(mediaGroup.getMetadata());
        Integer defaultContentIndex = mediaGroup.getDefaultContentIndex();

        return new PostMediaGroup(postMediaContents, postMediaMetadata, defaultContentIndex);
    }

    /**
     * Converts the PostMediaGroup object to a MediaGroup instance.
     *
     * @return A MediaGroup instance representing the PostMediaGroup data.
     */
    public MediaGroup toModule() {
        MediaGroup mg = new MediaGroup(convertArray(
                this::getPostMediaContents,
                PostMediaContent::toModule,
                MediaContent.class
        ));
        mg.setMetadata(ofNullable(getPostMediaMetadata()).map(PostMediaMetadata::toModule).orElse(null));
        Integer defaultContentIndex = getDefaultContentIndex();
        if (defaultContentIndex != null) { // Note: due to a bug in ROME, default content index must be primitive
            mg.setDefaultContentIndex(defaultContentIndex);
        }

        return mg;
    }
}
