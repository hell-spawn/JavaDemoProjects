package com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {


    @Id
    @GeneratedValue()
    private String id;
    //private ProductType productType;
    private String name;
    private BigDecimal price;
    private String reference;
    private String description;
    private String details;
}
