package com.spawn.game.store.product.infrastructure.adapters.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.domain.models.ProductType;
import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.config.ProductRestMapping;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.ProductRestAdapter;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductTypeRequest;
import com.spawn.game.store.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import com.spawn.game.store.product.infrastructure.adapters.rest.configuration.ProductRestAdapterTestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestAdapter.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = ProductRestAdapterTestConfiguration.class)
class ProductRestAdapterTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Autowired
    ProductPersistenceAdapter productPersistenceAdapter;

    @Autowired
    ProductEventPublisherAdapter productEventPublisherAdapter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenPostCreateProduct_whenPostProduct_thenReturnValidProductCreateResponse() throws  Exception {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest(
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99"),
                new ProductTypeRequest(1L, "Game")

        );

        Product unCreatedProduct = new Product(
                null,
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99"),
                new ProductType(1L, "Game")
        );


        Product createdProduct = objectMapper.readValue(
                objectMapper.writeValueAsString(unCreatedProduct), Product.class
        );
        createdProduct.setId(UUID.randomUUID().toString());

        given(productPersistenceAdapter.saveProduct(Mockito.any(Product.class))).willReturn(createdProduct);

        mockMvc.perform(post(ProductRestMapping.ROOT + ProductRestMapping.PATH + ProductRestMapping.PATH_CREATE_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdProduct.getId()))
                .andExpect(jsonPath("$.name").value(createdProduct.getName()))
                .andExpect(jsonPath("$.reference").value(createdProduct.getReference()))
                .andExpect(jsonPath("$.description").value(createdProduct.getDescription()))
                .andExpect(jsonPath("$.details").value(createdProduct.getDetails()))
                .andExpect(jsonPath("$.price").value(createdProduct.getPrice()))
                .andExpect(jsonPath("$.productType.id").value(createdProduct.getProductType().getId()))
                .andExpect(jsonPath("$.productType.description").value(createdProduct.getProductType().getDescription()));


        Mockito.verify(productPersistenceAdapter, VerificationModeFactory.times(1)).saveProduct(Mockito.any());
        Mockito.reset(productPersistenceAdapter);
    }

    @Test
    public void givenPostCreateProductInvalid_whenPostProduct_thenReturnExceptionResponse() throws  Exception {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();

        Product unCreatedProduct = new Product();

        Product createdProduct = objectMapper.readValue(
                objectMapper.writeValueAsString(unCreatedProduct), Product.class
        );

        given(productPersistenceAdapter.saveProduct(unCreatedProduct)).willReturn(createdProduct);

        LocalDateTime currentDate = LocalDateTime.now();

        mockMvc.perform(post(ProductRestMapping.ROOT + ProductRestMapping.PATH + ProductRestMapping.PATH_CREATE_PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productCreateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath( "$.date", allOf(
                        containsString(Integer.toString(currentDate.getYear())),
                        containsString(Integer.toString(currentDate.getMonthValue())),
                        containsString(Integer.toString(currentDate.getDayOfMonth()))
                )))
                .andExpect(jsonPath("$.message", is("Validation Failed") ))
                .andExpect(jsonPath("$.details", containsInAnyOrder(
                        "Name cannot be empty",
                        "Description cannot be empty",
                        "Reference cannot may be empty",
                        "Details cannot be empty",
                        "Price cannot be empty",
                        "ProductType is required"
                )));



        Mockito.verify(productPersistenceAdapter, VerificationModeFactory.times(0))
                .saveProduct(Mockito.any());
        Mockito.reset(productPersistenceAdapter);

    }

    @Test
    public void givenProductId_whenProductExist_thenReturnProductValid() throws Exception {
       String productId = UUID.randomUUID().toString();

        Product product = new Product(
                productId,
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor st amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99"),
                new ProductType(1L, "Game")
        );

       given(productPersistenceAdapter.searchProductById(productId)).willReturn(Optional.of(product));
       mockMvc.perform(get(ProductRestMapping.ROOT + ProductRestMapping.PATH + "/{productId}",  productId)
               .accept(MediaType.APPLICATION_JSON)
       ).andExpect(status().isOk())
               .andExpect( jsonPath("$.id", is(product.getId())))
               .andExpect( jsonPath("$.name", is(product.getName())))
               .andExpect( jsonPath("$.reference", is(product.getReference())))
               .andExpect( jsonPath("$.description", is(product.getDescription())))
               .andExpect( jsonPath("$.details", is(product.getDetails())))
               .andExpect( jsonPath("$.price", is(product.getPrice().doubleValue())));

    }
}