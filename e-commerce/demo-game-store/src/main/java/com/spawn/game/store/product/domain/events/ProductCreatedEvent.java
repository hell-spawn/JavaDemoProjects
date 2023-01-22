package com.spawn.game.store.product.domain.events;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductCreatedEvent {

    private String id;
    private LocalDateTime date;

    public ProductCreatedEvent(String id){
        this.id = id;
        this.date = LocalDateTime.now();
    }
}
