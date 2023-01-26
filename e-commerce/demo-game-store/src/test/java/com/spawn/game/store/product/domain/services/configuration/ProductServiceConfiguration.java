package com.spawn.game.store.product.domain.services.configuration;

import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductServiceConfiguration {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ApplicationEventPublisher applicationEventPublisher;
    @Bean
    public ProductEventPublisherAdapter productEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new ProductEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter() {
        ProductPersistenceMapper productPersistenceMapper = Mappers.getMapper(ProductPersistenceMapper.class);
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter, ProductEventPublisherAdapter productEventPublisherAdapter){
        return new ProductService(productPersistenceAdapter, productEventPublisherAdapter);
    }

}
