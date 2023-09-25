package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Scene;
import com.rometools.modules.mediarss.types.Time;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a scene associated with a media content in a post. A scene describes a specific
 * portion of media content, including its description, title, start time, and end time.
 */
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaScene implements Serializable {

    @Serial
    private static final long serialVersionUID = 88882213335442343L;

    /**
     * The description of the media scene.
     */
    String description;

    /**
     * The title of the media scene.
     */
    String title;

    /**
     * The start time of the media scene.
     */
    Time startTime;

    /**
     * The end time of the media scene.
     */
    Time endTime;

    /**
     * Constructs a new PostMediaScene instance with the provided parameters.
     *
     * @param description The description of the media scene.
     * @param title       The title of the media scene.
     * @param startTime   The start time of the media scene.
     * @param endTime     The end time of the media scene.
     */
    PostMediaScene(String description, String title, Time startTime, Time endTime) {
        this.description = description;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a PostMediaScene instance from a given Scene object.
     *
     * @param t The Scene object to create a PostMediaScene from.
     * @return A new PostMediaScene instance created from the provided Scene.
     */
    public static PostMediaScene from(Scene t) {
        return new PostMediaScene(t.getDescription(), t.getTitle(), t.getStartTime(), t.getEndTime());
    }

    /**
     * Converts the PostMediaScene to a corresponding Scene from the media RSS module.
     *
     * @return A Scene instance corresponding to the PostMediaScene.
     */
    public Scene toModule() {
        Scene s = new Scene();
        s.setDescription(description);
        s.setTitle(title);
        s.setStartTime(startTime);
        s.setEndTime(endTime);

        return s;
    }
}
