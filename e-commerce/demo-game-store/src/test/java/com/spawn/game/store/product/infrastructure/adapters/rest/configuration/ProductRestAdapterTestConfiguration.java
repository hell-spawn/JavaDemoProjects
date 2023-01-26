package com.spawn.game.store.product.infrastructure.adapters.rest.configuration;

import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapperImpl;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductRestAdapterTestConfiguration {

    @MockBean
    private ProductService productService;

    @Bean
    ProductRestMapper productRestMapper(){
        return Mappers.getMapper(ProductRestMapper.class);
    }

}
