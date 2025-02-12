package com.example.demo.model;

public class QueryResult {
    private Intent intent;
    private String queryText;
    private Parameters parameters;
    private boolean allRequiredParamsPresent;
    private String fulfillmentText;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public boolean isAllRequiredParamsPresent() {
        return allRequiredParamsPresent;
    }

    public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
        this.allRequiredParamsPresent = allRequiredParamsPresent;
    }

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }
}
