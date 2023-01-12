package com.spawn.hexagonalarq.product.domain.services;

import com.spawn.hexagonalarq.product.domain.exceptions.ProductNotFound;
import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapperImpl;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Configuration()
    static class ContextConfiguration {

        @MockBean()
        ProductRepository productRepository;

        @MockBean
        ApplicationEventPublisher applicationEventPublisher;

        @Bean
        public ProductPersistenceMapper productPersistenceMapper(){
            return new ProductPersistenceMapperImpl();
        }

        @Bean
        public ProductPersistenceAdapter productPersistenceAdapter() {
            ProductPersistenceMapper productPersistenceMapper = Mappers.getMapper(ProductPersistenceMapper.class);
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

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPersistenceMapper productPersistenceMapper;

    @Test
    public void whenFindAllProducts_thenReturnListProducts(){
        Product laptop = new Product(1L, "Laptop", "Macbook Pro");
        Product  smartPhone = new Product(2L, "SmartPhone", "IPhone 12");
        ProductEntity laptopEntity = new ProductEntity(1L, "Laptop", "Macbook Pro");
        ProductEntity smartPhoneEntity = new ProductEntity(2L, "SmartPhone", "IPhone 12");

        given(productRepository.findAll()).willReturn(Arrays.asList(laptopEntity, smartPhoneEntity));

        List<Product> products = productService.findAll();

        assertAll( "Find all products test",
                () -> assertThat(products, hasSize(2)),
                () -> assertThat(products, hasItem(laptop)),
                () -> assertThat(products, hasItem(smartPhone))
        );

        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(productRepository);
    }


    @Test
    public void whenValidProductId_thenProductShouldBeFound() {
        Long id = 3L;

        Product laptop = new Product(id, "Laptop", "Macbook Pro");
        ProductEntity laptopEntity = new ProductEntity();
        laptopEntity.setId(3L);
        laptopEntity.setName("Laptop");
        laptopEntity.setDescription("Macbook Pro");

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(laptopEntity));

        Product product = productService.findProductById(3L);
        assertThat(3L, is(product.getId()));

        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findById(3L);
        Mockito.reset(productRepository);
    }


    @Test
    public void whenNotValidProductId_thenProductShouldNotBeFound() {
        Long id = 5L;
        Exception exception = assertThrows(ProductNotFound.class, () -> {
            productService.findProductById(id);
        });
    }


    @Test
    public void givenProduct_whenCreateProduct_thenReturnProductValid(){
        Product newLaptop = new Product(null, "Laptop", "Macbook Pro");
        Product laptop = new Product(2L, "Laptop", "Macbook Pro");
        ProductEntity unsavedLaptopEntity =  new ProductEntity(null, "Laptop", "Macbook Pro");
        ProductEntity savedLaptopEntity =  new ProductEntity(2L, "Laptop", "Macbook Pro");

        Mockito.when(productRepository.save(unsavedLaptopEntity)).thenReturn(savedLaptopEntity);
        Product product = productService.createProduct(newLaptop);

        assertAll(
                "Create product",
                () -> assertThat(laptop.getId(), is(product.getId())),
                () -> assertThat(newLaptop.getName(), is(product.getName())),
                () -> assertThat(newLaptop.getDescription(), is(product.getDescription()))
        );
    }

}