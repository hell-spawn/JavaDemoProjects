package com.spawn.game.store.product.domain.services.configuration;

import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductServiceConfiguration {

    @MockBean
    ProductRepository productRepository;

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter() {
        ProductPersistenceMapper productPersistenceMapper = Mappers.getMapper(ProductPersistenceMapper.class);
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter){
        return new ProductService(productPersistenceAdapter);
    }

}
