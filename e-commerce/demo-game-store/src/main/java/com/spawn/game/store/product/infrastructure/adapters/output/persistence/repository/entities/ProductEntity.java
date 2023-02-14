package com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String reference;
    private String description;
    private String details;
    private BigDecimal price;
    @OneToOne(cascade=CascadeType.ALL)//one-to-one
    @JoinColumn(name="PRODUCT_TYPE_ID")
    private ProductTypeEntity productType;

}
