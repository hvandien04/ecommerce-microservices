package com.example.productService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_attributes")
public class ProductAttribute {
    @Id
    @Column(name = "product_attributes_id", nullable = false, length = 50)
    private String productAttributesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Product products;

    @Column(name = "`key`", length = 100)
    private String key;

    @Column(name = "value")
    private String value;

}