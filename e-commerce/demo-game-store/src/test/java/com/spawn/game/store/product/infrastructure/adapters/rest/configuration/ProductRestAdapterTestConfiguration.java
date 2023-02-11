package com.spawn.game.store.product.infrastructure.adapters.rest.configuration;

import com.spawn.game.store.product.application.ports.output.ProductEventPublisher;
import com.spawn.game.store.product.application.ports.output.ProductOutPort;
import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductRestAdapterTestConfiguration {


    @MockBean
    ProductPersistenceAdapter productPersistenceAdapter;

    @MockBean
    ProductEventPublisherAdapter productEventPublisherAdapter;

    @Bean
    ProductRestMapper productRestMapper(){
        return Mappers.getMapper(ProductRestMapper.class);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter, ProductEventPublisherAdapter productEventPublisherAdapter){
        return new ProductService(productPersistenceAdapter, productEventPublisherAdapter);
    }
}
