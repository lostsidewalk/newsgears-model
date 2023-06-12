package com.lostsidewalk.buffy.importer;

import com.lostsidewalk.buffy.discovery.FeedDiscoveryInfo;
import com.lostsidewalk.buffy.post.StagingPost;
import com.lostsidewalk.buffy.subscription.SubscriptionDefinition;
import com.lostsidewalk.buffy.subscription.SubscriptionMetrics;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public interface Importer {

    @Data
    class ImportResult {

        Set<StagingPost> importSet;

        List<SubscriptionMetrics> subscriptionMetrics;

        private ImportResult(Set<StagingPost> importSet, List<SubscriptionMetrics> subscriptionMetrics) {
            this.importSet = importSet;
            this.subscriptionMetrics = subscriptionMetrics;
        }

        public static ImportResult from(Set<StagingPost> importSet, List<SubscriptionMetrics> subscriptionMetrics) {
            return new ImportResult(importSet, subscriptionMetrics);
        }
    }

    ImportResult doImport(List<SubscriptionDefinition> subscriptionDefinitions, Map<String, FeedDiscoveryInfo> discoveryCache);

    interface ImportResponseCallback {
        ImportResult onSuccess(Set<StagingPost> stagingPosts);

        ImportResult onFailure(Throwable throwable);
    }

    String getImporterId();
}
