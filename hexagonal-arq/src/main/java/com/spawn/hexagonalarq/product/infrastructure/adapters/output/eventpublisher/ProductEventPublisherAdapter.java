package com.spawn.hexagonalarq.product.infrastructure.adapters.output.eventpublisher;

import com.spawn.hexagonalarq.product.application.ports.output.ProductEventPublisher;
import com.spawn.hexagonalarq.product.domain.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ProductEventPublisherAdapter implements ProductEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProductCreateEvent(ProductCreatedEvent productCreatedEvent) {
        applicationEventPublisher.publishEvent(productCreatedEvent);
    }
}
