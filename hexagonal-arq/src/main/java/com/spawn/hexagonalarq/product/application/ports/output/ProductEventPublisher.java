package com.spawn.hexagonalarq.product.application.ports.output;

import com.spawn.hexagonalarq.product.domain.events.ProductCreatedEvent;

public interface ProductEventPublisher {

    void publishProductCreateEvent(ProductCreatedEvent productCreatedEvent);
}
