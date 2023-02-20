package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMediaGroup extends BasePostMediaObject {

    List<PostMediaContent> postMediaContents;

    PostMediaMetadata postMediaMetadata;

    Integer defaultContentIndex;

    PostMediaGroup(List<PostMediaContent> postMediaContents, PostMediaMetadata postMediaMetadata, Integer defaultContentIndex) {
        super();
        this.postMediaContents = postMediaContents;
        this.postMediaMetadata = postMediaMetadata;
        this.defaultContentIndex = defaultContentIndex;
    }

    public static PostMediaGroup from(MediaGroup mediaGroup) {
        List<PostMediaContent> postMediaContents = stream(mediaGroup.getContents()).map(PostMediaContent::from).toList();
        PostMediaMetadata postMediaMetadata = PostMediaMetadata.from(mediaGroup.getMetadata());
        Integer defaultContentIndex = mediaGroup.getDefaultContentIndex();

        return new PostMediaGroup(postMediaContents, postMediaMetadata, defaultContentIndex);
    }

    public MediaGroup toModule() {
        MediaGroup mg = new MediaGroup(convertArray(this::getPostMediaContents, PostMediaContent::toModule, MediaContent.class));
        mg.setMetadata(ofNullable(getPostMediaMetadata()).map(PostMediaMetadata::toModule).orElse(null));
        Integer defaultContentIndex = getDefaultContentIndex();
        if (defaultContentIndex != null) { // Note: due to a bug in ROME, default content index must be primitive
            mg.setDefaultContentIndex(defaultContentIndex);
        }

        return mg;
    }
}