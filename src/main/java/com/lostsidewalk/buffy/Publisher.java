package com.lostsidewalk.buffy;

import java.util.List;

public interface Publisher {

    void doPublish(List<StagingPost> posts);

    String getPublisherId();
}
