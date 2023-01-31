package com.example.tinywiny.model;

import com.example.tinywiny.dto.TypeProduct;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private int price;

    @Column(name = "count_in_stock")
    private Long countInStock;

    private String description;

    @ManyToOne
    @JoinColumn(name = "typeProduct", referencedColumnName = "id_type")
    private TypeProduct typeProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "image_id")
    private Image image;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ProductInBucket> productInBuckets = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ProductInOrder> productInOrders = new ArrayList<>();
   }
