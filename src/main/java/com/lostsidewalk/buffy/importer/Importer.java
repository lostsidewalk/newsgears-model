package com.lostsidewalk.buffy.importer;

import com.lostsidewalk.buffy.discovery.FeedDiscoveryInfo;
import com.lostsidewalk.buffy.post.StagingPost;
import com.lostsidewalk.buffy.subscription.SubscriptionDefinition;
import com.lostsidewalk.buffy.subscription.SubscriptionMetrics;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Importer interface defines the contract for classes responsible for importing feed data.
 */
@SuppressWarnings({"unused", "InterfaceNeverImplemented"})
public interface Importer {

    /**
     * Perform the import operation.
     *
     * @param subscriptionDefinitions The list of subscription definitions to import.
     * @param discoveryCache          A map containing feed discovery information.
     * @return An ImportResult object representing the result of the import operation.
     */
    ImportResult doImport(List<SubscriptionDefinition> subscriptionDefinitions, Map<String, FeedDiscoveryInfo> discoveryCache);

    /**
     * Get the unique identifier of the importer.
     *
     * @return The importer's unique identifier.
     */
    String getImporterId();

    /**
     * The ImportResponseCallback interface defines methods for handling import responses.
     */
    @SuppressWarnings({"InnerClassOfInterface", "InterfaceNeverImplemented"})
    interface ImportResponseCallback {

        /**
         * Callback method invoked when the import operation is successful.
         *
         * @param stagingPosts A set of staging posts created during the import.
         * @return An ImportResult object representing the result of the import operation.
         */
        ImportResult onSuccess(Set<StagingPost> stagingPosts);

        /**
         * Callback method invoked when the import operation encounters an error.
         *
         * @param throwable The throwable representing the error.
         * @return An ImportResult object representing the result of the import operation.
         */
        ImportResult onFailure(Throwable throwable);
    }

    /**
     * The ImportResult class represents the result of an import operation.
     */
    @SuppressWarnings("InnerClassOfInterface")
    @Data
    class ImportResult {

        /**
         * A set of staging posts created during the import.
         */
        Set<StagingPost> importSet;

        /**
         * A list of subscription metrics generated during the import.
         */
        List<SubscriptionMetrics> subscriptionMetrics;

        /**
         * Private constructor for creating an ImportResult instance.
         *
         * @param importSet           A set of staging posts created during the import.
         * @param subscriptionMetrics A list of subscription metrics generated during the import.
         */
        private ImportResult(Set<StagingPost> importSet, List<SubscriptionMetrics> subscriptionMetrics) {
            this.importSet = importSet;
            this.subscriptionMetrics = subscriptionMetrics;
        }

        /**
         * Static factory method to create an ImportResult object.
         *
         * @param importSet           A set of staging posts created during the import.
         * @param subscriptionMetrics A list of subscription metrics generated during the import.
         * @return A new ImportResult instance.
         */
        public static ImportResult from(Set<StagingPost> importSet, List<SubscriptionMetrics> subscriptionMetrics) {
            return new ImportResult(importSet, subscriptionMetrics);
        }
    }
}
