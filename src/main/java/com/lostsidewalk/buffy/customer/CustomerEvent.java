package com.lostsidewalk.buffy.customer;

import lombok.Data;

/**
 * Represents an event related to a customer, including an event type and a payload.
 */
@Data
public class CustomerEvent {

    /**
     * The type of the customer event.
     */
    String eventType;

    /**
     * The payload associated with the customer event.
     */
    String payload;

    /**
     * Constructs a CustomerEvent with the specified event type and payload.
     *
     * @param eventType The type of the customer event.
     * @param payload   The payload associated with the customer event.
     */
    private CustomerEvent(String eventType, String payload) {
        this.eventType = eventType;
        this.payload = payload;
    }

    /**
     * Creates a CustomerEvent instance with the given event type and payload.
     *
     * @param eventType The type of the customer event.
     * @param payload   The payload associated with the customer event.
     * @return A CustomerEvent instance.
     */
    @SuppressWarnings("unused")
    public static CustomerEvent from(String eventType, String payload) {
        return new CustomerEvent(eventType, payload);
    }
}
