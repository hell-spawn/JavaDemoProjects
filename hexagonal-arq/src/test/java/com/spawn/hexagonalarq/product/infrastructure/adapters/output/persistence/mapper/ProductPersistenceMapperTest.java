package com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper.ProductRestMapperImpl;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductPersistenceMapperImpl.class})
class ProductPersistenceMapperTest {


    @Autowired
    ProductPersistenceMapper productPersistenceMapper;


    @Test
    public void whenProduct_thenReturnProductEntity(){
        Product laptop = new Product(1L, "MacBook Air", "Laptop Apple");
        ProductEntity laptopEntity = productPersistenceMapper.toProductEntity(laptop);

        assertAll("Test mapper ProductEntity",
                () -> assertThat( laptopEntity.getId(), is(laptop.getId())),
                () -> assertThat(laptopEntity.getName(), is(laptop.getName())),
                () -> assertThat(laptopEntity.getDescription(), is(laptop.getDescription()))
                );

    }

    @Test
    public void whenProductEntity_thenReturnProduct(){
        ProductEntity laptopEntity = new ProductEntity(1L, "MacBook Air", "Laptop Apple");
        Product laptop = productPersistenceMapper.toProduct(laptopEntity);

        assertAll("Test mapper product",
                () -> assertThat(laptop.getId(), is(laptopEntity.getId())),
                () -> assertThat(laptop.getName(), is(laptopEntity.getName())),
                () -> assertThat(laptop.getDescription(), is(laptopEntity.getDescription()))
        );
    }

    @Test
    public void whenListProductEntities_thenResturnListProdudcts(){
        ProductEntity laptopEntity = new ProductEntity(1L, "MacBook Air", "Laptop Apple");
        ProductEntity smartPhone = new ProductEntity(2L, "IPhone X", "Smart phone by Apple");

        List<Product> products = productPersistenceMapper.map(Arrays.asList(laptopEntity, smartPhone));

        assertAll("Test Mapper Products",
                () -> assertThat(products.size(), is(2)),
                () -> assertThat(products.get(0), hasProperty("id")),
                () -> assertThat(products.get(0), hasProperty("name")),
                () -> assertThat(products.get(0), hasProperty("description"))
        );
    }

}