package com.spawn.hexagonalarq.product.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductCreatedEvent {

    private Long id;
    private LocalDateTime date;

    public ProductCreatedEvent(Long id){
        this.id = id;
        this.date = LocalDateTime.now();
    }
}
