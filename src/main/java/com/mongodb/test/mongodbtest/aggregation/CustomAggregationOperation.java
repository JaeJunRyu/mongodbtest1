package com.mongodb.test.mongodbtest.aggregation;

import com.mongodb.DBObject;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomAggregationOperation implements AggregationOperation {

    private List<Document> document;

    public CustomAggregationOperation(Document document) {
        this.document = (List<Document>) document;
    }

    public CustomAggregationOperation(List<Document> document) {
        this.document = document;
    }

    @Override
    public List<Document> toPipelineStages(AggregationOperationContext context) {
        List<Document> documentList = new LinkedList<>();
        documentList.addAll(document);
        return documentList;
    }

    @Override
    @Deprecated
    public Document toDocument(AggregationOperationContext aggregationOperationContext) {
        return null;
    }
}
