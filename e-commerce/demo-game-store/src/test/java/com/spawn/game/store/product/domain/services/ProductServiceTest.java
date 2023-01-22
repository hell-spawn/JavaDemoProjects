package com.spawn.game.store.product.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.domain.services.configuration.ProductServiceConfiguration;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(ProductServiceConfiguration.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void givenProductValid_whenCreateProduct_thenReturnProductValid() throws JsonProcessingException {

        String test_UUID = "xxxxxx-xxxxxx-xxxxx";
        ObjectMapper objectMapper = new ObjectMapper();

        Product game = new Product();
        game.setId(null);
        game.setName("Mario Kart 8 Deluxe");
        game.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        game.setReference("REF-0001");
        game.setDetails("{'Publishers':'Nintendo'}");
        game.setPrice(new BigDecimal("49.99"));

        ProductEntity unSavedProductEntity = new ProductEntity();
        unSavedProductEntity.setId(null);
        unSavedProductEntity.setName("Mario Kart 8 Deluxe");
        unSavedProductEntity.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        unSavedProductEntity.setReference("REF-0001");
        unSavedProductEntity.setDetails("{'Publishers':'Nintendo'}");
        unSavedProductEntity.setPrice(new BigDecimal("49.99"));

        ProductEntity savedProductEntity = objectMapper.readValue(
                objectMapper.writeValueAsString(unSavedProductEntity), ProductEntity.class
        );
        savedProductEntity.setId(test_UUID);

        when(productRepository.save(unSavedProductEntity)).thenReturn(savedProductEntity);
        Product savedProduct = productService.createProduct(game);

        assertAll("Create Product Case",
                () -> assertThat(savedProduct.getId(), is(test_UUID)),
                () -> assertThat(savedProduct.getDescription(), is(game.getDescription())),
                () -> assertThat(savedProduct.getDetails(), is(game.getDetails())),
                () -> assertThat(savedProduct.getName(), is(game.getName())),
                () -> assertThat(savedProduct.getPrice(), is(game.getPrice())),
                () -> assertThat(savedProduct.getReference(), is(game.getReference()))
                //() -> assertThat(savedProduct.getProductType().getCode(), is(game.getProductType().getCode()))
        );

    }
}