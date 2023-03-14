package com.lostsidewalk.buffy;

import com.lostsidewalk.buffy.discovery.FeedDiscoveryInfo;
import com.lostsidewalk.buffy.post.StagingPost;
import com.lostsidewalk.buffy.query.QueryDefinition;
import com.lostsidewalk.buffy.query.QueryMetrics;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public interface Importer {

    @Data
    class ImportResult {

        Set<StagingPost> importSet;

        List<QueryMetrics> queryMetrics;

        private ImportResult(Set<StagingPost> importSet, List<QueryMetrics> queryMetrics) {
            this.importSet = importSet;
            this.queryMetrics = queryMetrics;
        }

        public static ImportResult from(Set<StagingPost> importSet, List<QueryMetrics> queryMetrics) {
            return new ImportResult(importSet, queryMetrics);
        }
    }

    ImportResult doImport(List<QueryDefinition> queryDefinitions, Map<String, FeedDiscoveryInfo> discoveryCache);

    interface ImportResponseCallback {
        ImportResult onSuccess(Set<StagingPost> stagingPosts);

        ImportResult onFailure(Throwable throwable);
    }

    String getImporterId();
}
