package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull
    private String productName;
    @NotNull
    @Min(0)
    private int price;
    @NotNull
    private String image;
    @NotNull
    private String quantifier;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "productPO")
    private List<OrderPO> orderPOs;
}
