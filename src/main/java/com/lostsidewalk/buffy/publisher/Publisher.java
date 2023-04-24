package com.lostsidewalk.buffy.publisher;

import com.lostsidewalk.buffy.feed.FeedDefinition;
import com.lostsidewalk.buffy.post.StagingPost;
import lombok.Data;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public interface Publisher {

    enum PubFormat {
        RSS, ATOM, MD, JSON, CSV;

        public static PubFormat byName(String name) {
            for (PubFormat p : values()) {
                if (p.name().equalsIgnoreCase(name)) {
                    return p;
                }
            }

            return null;
        }
    }

    @Data
    class PubResult {

        String publisherIdent;
        List<Throwable> errors;
        Date pubDate;

        private PubResult(String publisherIdent, List<Throwable> errors, Date pubDate) {
            this.publisherIdent = publisherIdent;
            this.errors = errors;
            this.pubDate = pubDate;
        }

        public static PubResult from(String publisherIdent, List<Throwable> errors, Date pubDate) {
            return new PubResult(publisherIdent, errors, pubDate);
        }
    }

    PubResult publishFeed(FeedDefinition feedDefinition, List<StagingPost> posts, Date pubDate);

    String getPublisherId();

    boolean supportsFormat(PubFormat format);

    List<FeedPreview> doPreview(String username, List<StagingPost> posts, PubFormat format) throws Exception;
}
