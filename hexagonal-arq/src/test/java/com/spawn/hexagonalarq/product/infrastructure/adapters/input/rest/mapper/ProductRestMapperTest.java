package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductRestMapperImpl.class})
class ProductRestMapperTest {


    @Autowired
    ProductRestMapper productRestMapper;


    @Test
    public void givenProductCreateRequest_thenReturnProduct(){
        ProductCreateRequest laptopCreateRequest = new ProductCreateRequest("MacBook Air", "Laptop Apple");
        Product laptop = productRestMapper.toProduct(laptopCreateRequest);

        assertAll("Test mapper ProductRest",
                () -> assertThat(laptop.getName(), is(laptopCreateRequest.getName())),
                () -> assertThat(laptop.getDescription(), is(laptopCreateRequest.getDescription())));

    }

    @Test
    public void givenProduct_thenReturnProducCreateResponse(){
        Product laptop = new Product(1L, "MacBook Air", "Laptop Apple");
        ProductCreateResponse productCreateResponse = productRestMapper.toProductCreateResponse(laptop);

        assertAll("Test mapper ProductRest",
                () -> assertThat(productCreateResponse.getId(), is(laptop.getId())),
                () -> assertThat(productCreateResponse.getName(), is(laptop.getName())),
                () -> assertThat(productCreateResponse.getDescription(), is(laptop.getDescription())));
    }

    @Test
    public void givenProduct_theReturnProductQueryResponse(){
        Product laptop = new Product(1L, "MacBook Air", "Laptop Apple");
        ProductQueryResponse productQueryResponse = productRestMapper.toProductQueryResponse(laptop);

        assertAll("Test mapper ProductRest",
                () -> assertThat(productQueryResponse.getId(), is(laptop.getId())),
                () -> assertThat(productQueryResponse.getName(), is(laptop.getName())),
                () -> assertThat(productQueryResponse.getDescription(), is(laptop.getDescription())));
    }

    @Test
    public void givenListProducts_thenReturnListProductsQueryResponse(){
        Product laptop = new Product(1L, "Laptop", "Macbook Pro");
        Product  smartPhone = new Product(2L, "SmartPhone", "IPhone 12");

        List<ProductQueryResponse> productsQueryResponse = productRestMapper.map(Arrays.asList(laptop, smartPhone));

        assertAll("Test mapper ProductRest",
                () -> assertThat(productsQueryResponse.size(), is(2)),
                () -> assertThat(productsQueryResponse.get(0), hasProperty("id", equalTo(laptop.getId()))),
                () -> assertThat(productsQueryResponse.get(0), hasProperty("name", equalTo(laptop.getName()))),
                () -> assertThat(productsQueryResponse.get(0), hasProperty("description", equalTo(laptop.getDescription()))));

    }

}