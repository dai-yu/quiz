package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderPO")
public class OrderPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    private ProductPO productPO;
    @NotNull
    @Min(0)
    private int price;
    @NotNull
    @Min(0)
    private int number;
    @NotNull
    private String quantifier;

    @ManyToOne
    private OrderListPo orderList;
}
