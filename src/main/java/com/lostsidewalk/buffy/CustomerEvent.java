package com.lostsidewalk.buffy;

import lombok.Data;

@Data
public class CustomerEvent {

    String eventType;
    String payload;

    private CustomerEvent(String eventType, String payload) {
        this.eventType = eventType;
        this.payload = payload;
    }

    @SuppressWarnings("unused")
    public static CustomerEvent from(String eventType, String payload) {
        return new CustomerEvent(eventType, payload);
    }
}
