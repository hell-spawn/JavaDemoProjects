package com.spawn.hexagonalarq.product.domain.services;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
class ProductServiceTest {
    @TestConfiguration
    static class ProductServiceTestConfig {
        @Bean
        public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository, ProductPersistenceMapper productPersistenceMapper) {
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

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Before
    public void setUp() {
        Product laptop = new Product(3L, "Laptop", "Macbook Pro");
        ProductEntity laptopEntity = new ProductEntity();
        laptopEntity.setId(3L);
        laptopEntity.setName("Laptop");
        laptopEntity.setDescription("Macbook Pro");

        ProductEntity mobilePhone = new ProductEntity();
        laptopEntity.setId(1L);
        laptopEntity.setName("Laptop");
        laptopEntity.setDescription("Macbook Pro");

        Mockito.when(productRepository.findById(3L)).thenReturn(Optional.of(laptopEntity));
        Mockito.when(productRepository.save(mobilePhone)).thenReturn(mobilePhone);
    }


    @Test
    public void whenValidProductId_thenProductShouldBeFound() {
        Long id = 3L;
        Product product = productService.findProductById(3L);

        assertThat(3L, is(product.getId()));
        //verifyGetByProductIdIsCalledOnce();
    }

    /*

    @Test
    public void whenInValidProductId_thenProductShouldNotBeFound() {
        Product product = productService.getProductById(5);

        assertThat(product).isNull();
        verifyGetByProductIdIsCalledOnce();
    }

    @Test
    public void givenThreeProducts_whenGetAllProducts_thenThreeProductsReturned() {
        Product mobilePhone = new Product(1, "Mobile Phone", "Samsung Galaxy S9");
        Product book = new Product(2, "Book", "Kite Runner");
        Product laptop = new Product(3, "Laptop", "Macbook Pro");

        List<Product> allProducts = productService.getProducts();

        assertThat(allProducts).hasSize(3).extracting(Product::getType).contains(mobilePhone.getType(), book.getType(), laptop.getType());
        verifyGetProductsIsCalledOnce();
    }

    @Test
    public void whenAddProduct_thenProductTypeIsMatched() {
        Product electronics = new Product(4, "Electronics", "Bluetooth Speaker");

        assertThat(productService.addProduct(electronics)).extracting(Product::getType).as(electronics.getType());
    }

    @Test
    public void whenRemoveProductById_thenTwoProductReturned() {
        Product laptop = new Product(3, "Laptop", "Macbook Pro");

        assertThat(productService.removeProduct(3)).extracting(Product::getType).as(laptop.getType());
    }

    private void verifyGetByProductIdIsCalledOnce() {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).getProductById(Mockito.anyInt());
        Mockito.reset(productRepository);
    }

    private void verifyGetProductsIsCalledOnce() {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).getProducts();
        Mockito.reset(productRepository);
    }

     */
}