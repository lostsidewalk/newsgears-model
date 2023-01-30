package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.MediaContent;
import lombok.Data;

import static java.util.Optional.ofNullable;

@Data
public class PostMediaContent {

    Integer audioChannels;
    Float bitRate;
    Long duration;
    PostMediaExpression expression;
    Long fileSize;
    Float frameRate;
    Integer height;
    String language;
    String medium;
    PostMediaMetadata metadata;
    PostMediaReference reference;
    Float samplingRate;
    String type;
    Integer width;

    PostMediaContent(
            Integer audioChannels,
            Float bitRate,
            Long duration,
            PostMediaExpression expression,
            Long fileSize,
            Float frameRate,
            Integer height,
            String language,
            String medium,
            PostMediaMetadata metadata,
            PostMediaReference reference,
            Float samplingRate,
            String type,
            Integer width
    ) {
        super();
        this.audioChannels = audioChannels;
        this.bitRate = bitRate;
        this.duration = duration;
        this.expression = expression;
        this.fileSize = fileSize;
        this.frameRate = frameRate;
        this.height = height;
        this.language = language;
        this.medium = medium;
        this.metadata = metadata;
        this.reference = reference;
        this.samplingRate = samplingRate;
        this.type = type;
        this.width = width;
    }

    public static PostMediaContent from(MediaContent mediaContent) {
        Integer audioChannels = mediaContent.getAudioChannels();
        Float bitRate = mediaContent.getBitrate();
        Long duration = mediaContent.getDuration();
        PostMediaExpression expression = ofNullable(mediaContent.getExpression()).map(PostMediaExpression::from).orElse(null);
        Long fileSize = mediaContent.getFileSize();
        Float frameRate = mediaContent.getFramerate();
        Integer height = mediaContent.getHeight();
        String language = mediaContent.getLanguage();
        String medium = mediaContent.getMedium();
        PostMediaMetadata  metadata = ofNullable(mediaContent.getMetadata()).map(PostMediaMetadata::from).orElse(null);
//        mediaContent.getPlayer(); // ignored
        PostMediaReference reference = ofNullable(mediaContent.getReference()).map(PostMediaReference::from).orElse(null); //  technically reference isn't nullable in MC
        Float samplingRate = mediaContent.getSamplingrate();
        String type = mediaContent.getType();
        Integer width = mediaContent.getWidth();

        return new PostMediaContent(
                audioChannels,
                bitRate,
                duration,
                expression,
                fileSize,
                frameRate,
                height,
                language,
                medium,
                metadata,
                reference,
                samplingRate,
                type,
                width
        );
    }

    public MediaContent toModule() {
        MediaContent mediaContent = new MediaContent(getReference().toUrlModule());
        mediaContent.setAudioChannels(getAudioChannels());
        mediaContent.setBitrate(getBitRate());
        mediaContent.setDuration(getDuration());
        mediaContent.setExpression(ofNullable(getExpression()).map(PostMediaExpression::toModule).orElse(null));
        mediaContent.setFileSize(getFileSize());
        mediaContent.setFramerate(getFrameRate());
        mediaContent.setHeight(getHeight());
        mediaContent.setLanguage(getLanguage());
        mediaContent.setMedium(getMedium());
        mediaContent.setMetadata(ofNullable(getMetadata()).map(PostMediaMetadata::toModule).orElse(null));
        mediaContent.setSamplingrate(getSamplingRate());
        mediaContent.setType(getType());
        mediaContent.setWidth(getWidth());

        return mediaContent;
    }
}
