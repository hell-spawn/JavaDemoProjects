package com.spawn.game.store.product.infrastructure.adapters.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.config.ProductRestMapping;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.ProductRestAdapter;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.game.store.product.infrastructure.adapters.rest.configuration.ProductRestAdapterTestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestAdapter.class)
@ContextConfiguration(classes = ProductRestAdapterTestConfiguration.class)
class ProductRestAdapterTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenPostCreateProduct_whenPostProduct_thenReturnValidProductCreateResponse() throws  Exception {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest(
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99")
        );

        Product unCreatedProduct = new Product(
                null,
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99")
        );


        Product createdProduct = objectMapper.readValue(
                objectMapper.writeValueAsString(unCreatedProduct), Product.class
        );
        createdProduct.setId("asdfg-asdfg-asdfg");

        given(productService.createProduct(Mockito.any())).willReturn(createdProduct);

        mockMvc.perform(post(ProductRestMapping.ROOT + ProductRestMapping.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdProduct.getId()))
                .andExpect(jsonPath("$.name").value(createdProduct.getName()))
                .andExpect(jsonPath("$.reference").value(createdProduct.getReference()))
                .andExpect(jsonPath("$.description").value(createdProduct.getDescription()))
                .andExpect(jsonPath("$.details").value(createdProduct.getDetails()))
                .andExpect(jsonPath("$.price").value(createdProduct.getPrice()));


        Mockito.verify(productService, VerificationModeFactory.times(1)).createProduct(Mockito.any());
        Mockito.reset(productService);
    }

/*
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRestMapper productRestMapper;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenGetRequestProduct_whenHasProducts_thenReturnListProductQueryResponse() throws Exception {
        Product laptop = new Product(0L, "Laptop", "Macbook Pro");
        Product  smartPhone = new Product(1L, "SmartPhone", "IPhone 12");
        ProductQueryResponse laptopResponse = new ProductQueryResponse(0L, "Laptop", "Macbook Pro");
        ProductQueryResponse  smartPhoneResponse = new ProductQueryResponse(1L, "SmartPhone", "IPhone 12");

        given(productService.findAll()).willReturn(Arrays.asList(laptop, smartPhone));
        given(productRestMapper.map(Arrays.asList(laptop,smartPhone))).willReturn(Arrays.asList(laptopResponse, smartPhoneResponse));

        mvc.perform(get(ProductMapping.ROOT + ProductMapping.PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[-1].id").value(1L))
                .andExpect(jsonPath("$.[0].id").value(2L));

        Mockito.verify(productService, VerificationModeFactory.times(0)).findAll();
        Mockito.reset(productService);
        Mockito.reset(productRestMapper);


    }


    @Test
    public void givenProductIdValid_whenFindProductById_thenReturnValidProductQueryResponse() throws Exception {
        Product laptop = new Product(1L, "MacBook Air", "Laptop Apple");
        ProductQueryResponse queryResponse = new ProductQueryResponse(1L, "MacBook Air", "Laptop Apple");
        given(productService.findProductById(Mockito.anyLong())).willReturn(laptop);
        given(productRestMapper.toProductQueryResponse(Mockito.any())).willReturn(queryResponse);

        mvc.perform(get(ProductMapping.ROOT + ProductMapping.PATH+ "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        Mockito.verify(productService, VerificationModeFactory.times(1)).findProductById(Mockito.anyLong());
        Mockito.reset(productService);
        Mockito.reset(productRestMapper);
    }

    @Test
    public void givenProductIdNotValid_whenFindProductById_thenReturnExceptionResponse() throws Exception {
        Long id = 5L;

        Mockito.when(productService.findProductById(id)).thenThrow(new ProductNotFound("Product Not Found"));

        mvc.perform(get(ProductMapping.ROOT + ProductMapping.PATH+ "/{id}", 5L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.message").exists());

        Mockito.reset(productService);
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

        Mockito.verify(productService, VerificationModeFactory.times(1)).createProduct(Mockito.any());
        Mockito.reset(productService);
        Mockito.reset(productRestMapper);
    }
*/
}