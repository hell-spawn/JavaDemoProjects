package com.spawn.game.store.product.infrastructure.adapters.mapper;

import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapperImpl;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductPersistenceMapperImpl.class})
class ProductPersistenceMapperTest {


    @Autowired
    ProductPersistenceMapper productPersistenceMapper;



    @Test
    public void givenProductEntity_whenMapperToProduct_thenReturnProduct(){
        ProductEntity gameEntity = new ProductEntity();
        gameEntity.setId(null);
        gameEntity.setName("Mario Kart 8 Deluxe");
        gameEntity.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        gameEntity.setReference("REF-0001");
        gameEntity.setDetails("{'Publishers':'Nintendo'}");
        gameEntity.setPrice(new BigDecimal("49.99"));

        Product game = productPersistenceMapper.toProduct(gameEntity);

        assertAll("Test mapper product",
                () -> assertNull(game.getId()),
                () -> assertThat(game.getName(), is(gameEntity.getName())),
                () -> assertThat(game.getDescription(), is(gameEntity.getDescription())),
                () -> assertThat(game.getReference(), is(gameEntity.getReference())),
                () -> assertThat(game.getDetails(), is(gameEntity.getDetails())),
                () -> assertThat(game.getPrice(), is(gameEntity.getPrice()))
        );

    }

    @Test
    public void givenProduct_whenMapperToProductEntity_thenReturnProductEntity(){

        Product game = new Product(
                "asdfg-asdfg-asdfg-asdfg",
                "Mario Kart 8 Deluxe",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "REF-0001",
                "{'Publishers':'Nintendo'}",
                new BigDecimal("49.99")
        );

        ProductEntity gameEntity = productPersistenceMapper.toProductEntity(game);

        assertAll("Test mapper to product entity",
                () -> assertThat(gameEntity.getId(), is(game.getId())),
                () -> assertThat(gameEntity.getName(), is(game.getName())),
                () -> assertThat(gameEntity.getDescription(), is(game.getDescription())),
                () -> assertThat(gameEntity.getReference(), is(game.getReference())),
                () -> assertThat(gameEntity.getDetails(), is(game.getDetails())),
                () -> assertThat(gameEntity.getPrice(), is(game.getPrice()))
        );
    }
}