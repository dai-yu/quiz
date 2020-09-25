package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @NotNull
    private String productName;
    @NotNull
    @Min(0)
    private int price;
    @NotNull
    private String image;
    @NotNull
    private String quantifier;
}
