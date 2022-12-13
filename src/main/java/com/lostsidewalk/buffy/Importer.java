package com.lostsidewalk.buffy;

import com.lostsidewalk.buffy.post.StagingPost;
import com.lostsidewalk.buffy.query.QueryDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public interface Importer {

    @Data
    @AllArgsConstructor
    class ImporterMetrics {
        final int successCt;
        final int errorCt;
    }

    void doImport(List<QueryDefinition> queryDefinitions);

    interface ImportResponseCallback {
        void onSuccess(Set<StagingPost> stagingPosts);

        void onFailure(Throwable throwable);
    }

    ImporterMetrics performImport(QueryDefinition queryDefinition, ImportResponseCallback importResponseCallback);

    String getImporterId();
}
