package com.spawn.game.store.product.application.ports.output;

import com.spawn.game.store.product.domain.events.ProductCreatedEvent;

public interface ProductEventPublisher {

    void publishProductCreateEvent(ProductCreatedEvent productCreatedEvent);
}
