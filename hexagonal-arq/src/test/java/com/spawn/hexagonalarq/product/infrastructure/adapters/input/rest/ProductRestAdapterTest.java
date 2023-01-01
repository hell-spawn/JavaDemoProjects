package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.domain.services.ProductService;
import com.spawn.hexagonalarq.product.infrastructure.adapters.config.ProductMapping;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestAdapter.class)
class ProductRestAdapterTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRestMapper productRestMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenProduct_whenFindProductById_thenReturnValidProductQueryResponse() throws Exception {
        Product laptop = new Product(1L, "MacBook Air", "Laptop Apple");
        ProductQueryResponse queryResponse = new ProductQueryResponse(1L, "MacBook Air", "Laptop Apple");
        given(productService.findProductById(Mockito.anyLong())).willReturn(laptop);
        given(productRestMapper.toProductQueryResponse(Mockito.any())).willReturn(queryResponse);

        mvc.perform(get(ProductMapping.ROOT + ProductMapping.PATH+ "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(productService, VerificationModeFactory.times(1)).findProductById(Mockito.anyLong());
        reset(productService);
        reset(productRestMapper);
    }


    @Test
    public void givenPostCreateProduct_whenPostProduct_thenReturnValidProductCreateResponse() throws  Exception {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest("MacBook Air", "Laptop Apple");
        Product laptop = new Product(null, "MacBook Air", "Laptop Apple");
        Product createdLaptop = new Product(1L, "MacBook Air", "Laptop Apple");
        given(productRestMapper.toProduct(Mockito.any())).willReturn(laptop);
        given(productService.createProduct(laptop)).willReturn(new Product(1L, "MacBook Air", "Laptop Apple"));
        given(productRestMapper.toProductCreateResponse(createdLaptop)).willReturn(new ProductCreateResponse(1L, "MacBook Air", "Laptop Apple"));


        mvc.perform(post(ProductMapping.ROOT + ProductMapping.PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("MacBook Air"))
                .andExpect(jsonPath("$.description").value("Laptop Apple"));

        verify(productService, VerificationModeFactory.times(1)).createProduct(Mockito.any());
        reset(productService);
        reset(productRestMapper);
    }

}