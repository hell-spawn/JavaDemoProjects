package com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

}
