package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaEntryModuleImpl;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMedia extends BasePostMediaObject implements Serializable {

    public static long serialVersionUID = 983403122303L;

    private final PostMediaMetadata postMediaMetadata;

    private final List<PostMediaGroup> postMediaGroups;

    private final List<PostMediaContent> postMediaContents;

    PostMedia(PostMediaMetadata postMediaMetadata, List<PostMediaGroup> postMediaGroups, List<PostMediaContent> postMediaContents) {
        super();
        this.postMediaMetadata = postMediaMetadata;
        this.postMediaGroups = postMediaGroups;
        this.postMediaContents = postMediaContents;
    }

    public static PostMedia from(MediaEntryModule mm) {
        // media module top-level metadata
        PostMediaMetadata postMediaMetadata = PostMediaMetadata.from(mm.getMetadata());
        // top-level media groups
        List<PostMediaGroup> postMediaGroups = Arrays.stream(mm.getMediaGroups()).map(PostMediaGroup::from).collect(toList());
        // top-level media contents
        List<PostMediaContent> postMediaContents = Arrays.stream(mm.getMediaContents()).map(PostMediaContent::from).collect(toList());

        return new PostMedia(postMediaMetadata, postMediaGroups, postMediaContents);
    }

    @SuppressWarnings({"unused"})
    public MediaEntryModule toModule() {
        MediaEntryModuleImpl mm = new MediaEntryModuleImpl();
        mm.setMetadata(ofNullable(getPostMediaMetadata()).map(PostMediaMetadata::toModule).orElse(null));
        mm.setMediaContents(convertArray(this::getPostMediaContents, PostMediaContent::toModule, MediaContent.class));
        mm.setMediaGroups(convertArray(this::getPostMediaGroups, PostMediaGroup::toModule, MediaGroup.class));

        return mm;
    }
}
