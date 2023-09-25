package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.MediaContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Optional.ofNullable;

/**
 * Represents media content associated with a post's media.
 */
@Data
@EqualsAndHashCode
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaContent implements Serializable {

    @Serial
    private static final long serialVersionUID = 2982334234249823L;

    /**
     * The number of audio channels in the media content.
     */
    Integer audioChannels;

    /**
     * The bit rate of the media content.
     */
    Float bitRate;

    /**
     * The duration of the media content.
     */
    Long duration;

    /**
     * The expression associated with the media content.
     */
    PostMediaExpression expression;

    /**
     * The file size of the media content.
     */
    Long fileSize;

    /**
     * The frame rate of the media content.
     */
    Float frameRate;

    /**
     * The height of the media content.
     */
    Integer height;

    /**
     * The language of the media content.
     */
    String language;

    /**
     * The medium of the media content.
     */
    String medium;

    /**
     * The metadata associated with the media content.
     */
    PostMediaMetadata metadata;

    /**
     * The reference of the media content.
     */
    PostMediaReference reference;

    /**
     * The sampling rate of the media content.
     */
    Float samplingRate;

    /**
     * The type of the media content.
     */
    String type;

    /**
     * The width of the media content.
     */
    Integer width;

    /**
     * Constructor to create a PostMediaContent object.
     *
     * @param audioChannels The number of audio channels in the media content.
     * @param bitRate       The bit rate of the media content.
     * @param duration      The duration of the media content.
     * @param expression    The expression associated with the media content.
     * @param fileSize      The file size of the media content.
     * @param frameRate     The frame rate of the media content.
     * @param height        The height of the media content.
     * @param language      The language of the media content.
     * @param medium        The medium of the media content.
     * @param metadata      The metadata associated with the media content.
     * @param reference     The reference of the media content.
     * @param samplingRate  The sampling rate of the media content.
     * @param type          The type of the media content.
     * @param width         The width of the media content.
     */
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

    /**
     * Static factory method to create a PostMediaContent object from a MediaContent instance.
     *
     * @param mediaContent The MediaContent instance to convert to a PostMediaContent object.
     * @return A new PostMediaContent object created from the given MediaContent instance.
     */
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
        PostMediaMetadata metadata = ofNullable(mediaContent.getMetadata()).map(PostMediaMetadata::from).orElse(null);
        PostMediaReference reference = ofNullable(mediaContent.getReference()).map(PostMediaReference::from).orElse(null);
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

    /**
     * Converts the PostMediaContent object to a MediaContent instance.
     *
     * @return A MediaContent instance representing the PostMediaContent object.
     */
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
