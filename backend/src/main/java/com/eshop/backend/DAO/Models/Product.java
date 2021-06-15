package com.eshop.backend.dao.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private Long productAmount;
    private int productPrice;
    private int productDiscount;
    private Date productDate;
    private String productPict;
    private String productDescription;
    private String productStatus;

    private Long genre;
    private Long coverType;
    private Long author;
    private Long language;
    private Long publisher;

}
