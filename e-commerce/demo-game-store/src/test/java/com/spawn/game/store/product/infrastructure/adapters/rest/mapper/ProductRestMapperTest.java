package com.spawn.game.store.product.infrastructure.adapters.rest.mapper;

import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductTypeRequest;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductRestMapperImpl.class})
class ProductRestMapperTest {


    @Autowired
    ProductRestMapper productRestMapper;

    @Test
    public void givenProductCreateRequest_whenMapperToProduct_thenReturnValidProduct(){
        ProductCreateRequest productCreateRequest = new ProductCreateRequest(
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99"),
                new ProductTypeRequest(1L, "Game")
        );

        Product product = productRestMapper.toProduct(productCreateRequest);

        assertAll("Test Mapper Product Create Request to Product",
                () -> assertNull( product.getId()),
                () -> assertThat(product.getName(), is (productCreateRequest.getName())),
                () -> assertThat(product.getDescription(), is (productCreateRequest.getDescription())),
                () -> assertThat(product.getReference(), is (productCreateRequest.getReference())),
                () -> assertThat(product.getDetails(), is (productCreateRequest.getDetails())),
                () -> assertThat(product.getPrice(), is (productCreateRequest.getPrice())));

    }

}