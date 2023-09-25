package com.lostsidewalk.buffy;

import java.util.Date;

/**
 * An interface representing an auditable entity, providing information about its creation and last modification timestamps.
 */
public interface Auditable {

    /**
     * Gets the timestamp when this entity was created.
     *
     * @return The creation timestamp.
     */
    Date getCreated();

    /**
     * Gets the timestamp when this entity was last modified.
     *
     * @return The last modification timestamp.
     */
    Date getLastModified();
}
