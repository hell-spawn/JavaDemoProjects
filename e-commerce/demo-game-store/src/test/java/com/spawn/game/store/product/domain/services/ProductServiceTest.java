package com.spawn.game.store.product.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.domain.models.ProductType;
import com.spawn.game.store.product.domain.services.configuration.ProductServiceConfiguration;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@Import(ProductServiceConfiguration.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void givenProductValid_whenCreateProduct_thenReturnProductValid() throws JsonProcessingException {

        String productId = UUID.randomUUID().toString();
        ObjectMapper objectMapper = new ObjectMapper();

        Product game = new Product(
            null,
            "Mario Kart 8 Deluxe",
            "REF-0001",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "{'Publishers':'Nintendo'}",
            new BigDecimal("49.99"),
            new ProductType(1L, "Games")
        );

        ProductEntity savedProductEntity = new ProductEntity();
        savedProductEntity.setId(productId);
        savedProductEntity.setName("Mario Kart 8 Deluxe");
        savedProductEntity.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        savedProductEntity.setReference("REF-0001");
        savedProductEntity.setDetails("{'Publishers':'Nintendo'}");
        savedProductEntity.setPrice(new BigDecimal("49.99"));
        savedProductEntity.setProductType(new ProductTypeEntity(1L, "Games"));


        given(productRepository.save(Mockito.any(ProductEntity.class))).willReturn(savedProductEntity);
        Product savedProduct = productService.createProduct(game);

        assertAll("Create Product Case",
                () -> assertThat(savedProduct.getId(), is(productId)),
                () -> assertThat(savedProduct.getDescription(), is(game.getDescription())),
                () -> assertThat(savedProduct.getDetails(), is(game.getDetails())),
                () -> assertThat(savedProduct.getName(), is(game.getName())),
                () -> assertThat(savedProduct.getPrice(), is(game.getPrice())),
                () -> assertThat(savedProduct.getReference(), is(game.getReference())),
                () -> assertThat(savedProduct.getProductType().getId(), is(game.getProductType().getId())),
                () -> assertThat(savedProduct.getProductType().getDescription(), is(game.getProductType().getDescription()))
        );

        Mockito.verify(productRepository, VerificationModeFactory.times(1)).save(Mockito.any(ProductEntity.class));
        Mockito.reset(productRepository);
    }

    @Test
    public void givenProductId_whenExist_thenReturnProduct(){

        String productId = UUID.randomUUID().toString();
        ProductEntity productEntity = new ProductEntity(
                productId,
                "Mario Kart 8 Deluxe",
                "REF-0001",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99"),
                new ProductTypeEntity(1L, "Game")
        );

        given(productRepository.findById(productId)).willReturn(Optional.of(productEntity));

        Product currentProduct = productService.searchProductById(productId);

        assertAll("Search product by Id",
                () -> assertThat(currentProduct.getId(), is(productId)),
                () -> assertThat(currentProduct.getName(), is(productEntity.getName())),
                () -> assertThat(currentProduct.getReference(), is(productEntity.getReference())),
                () -> assertThat(currentProduct.getDescription(), is(productEntity.getDescription())),
                () -> assertThat(currentProduct.getDetails(), is(productEntity.getDetails())),
                () -> assertThat(currentProduct.getPrice(), is(productEntity.getPrice())),
                () -> assertThat(currentProduct.getProductType().getId(), is(productEntity.getProductType().getId())),
                () -> assertThat(currentProduct.getProductType().getDescription(), is(productEntity.getProductType().getDescription()))
        );

        Mockito.verify(productRepository, VerificationModeFactory.times(1))
                .findById(productId);
        Mockito.reset(productRepository);
    }
}