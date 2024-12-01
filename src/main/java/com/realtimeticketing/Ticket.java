package com.realtimeticketing;

import java.time.LocalDateTime;

public class Ticket {
    private final int id;
    private final LocalDateTime createdDateTime;

    public Ticket(int id) {
        this.id = id;
        this.createdDateTime = LocalDateTime.now();
    }

    public int getId() {
            return id;
    }
    public LocalDateTime getCreatedDate() {
        return createdDateTime;
    }
}
