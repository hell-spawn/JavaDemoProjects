package com.spawn.hexagonalarq.product.infrastructure.adapters.input.listeners;

import com.spawn.hexagonalarq.product.domain.events.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventListenerAdapter {

    @EventListener
    public void handle(ProductCreatedEvent event){
        log.info("Product created with id " + event.getId() + " at " + event.getDate());
    }

}
