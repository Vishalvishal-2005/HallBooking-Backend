package com.example.demo.model;

import java.util.Map;

public class DialogflowRequest {
    private QueryResult queryResult;

    // Getter and Setter
    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    public static class QueryResult {
        private Map<String, Object> parameters;

        // Getter and Setter
        public Map<String, Object> getParameters() {
            return parameters;
        }

        public void setParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
        }

        public String getQueryText() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getQueryText'");
        }
    }
}
