package com.realtimeticketing;

import java.time.LocalDateTime;


public class Ticket {
    private final int id;
    private final LocalDateTime createAt;

    public Ticket(int id){
        this.id = id;
        this.createAt = LocalDateTime.now();
    }

    public int getId() {return id;}
    public LocalDateTime getCreateAt() {return createAt;}
}
