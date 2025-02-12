package com.example.demo.model;

import java.util.List;

public class DialogflowResponse {
    private List<FulfillmentMessage> fulfillmentMessages;

    // Constructor
    public DialogflowResponse(List<FulfillmentMessage> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    // Getter and Setter
    public List<FulfillmentMessage> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<FulfillmentMessage> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }
}
