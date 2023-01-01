package com.spawn.hexagonalarq.product.infrastructure.adapters.config;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.domain.services.ProductService;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfiguration {

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository, ProductPersistenceMapper productPersistenceMapper) {
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public ProductEventPublisherAdapter productEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new ProductEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter, ProductEventPublisherAdapter productEventPublisherAdapter) {
        return new ProductService(productPersistenceAdapter, productEventPublisherAdapter);
    }

}
