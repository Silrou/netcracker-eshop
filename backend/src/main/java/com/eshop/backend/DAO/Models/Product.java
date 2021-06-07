package com.eshop.backend.DAO.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;

    private int productCategory;

    private String productName;

    private int productAmount;

    private double productPrice;

    private int productDiscount;

    private Date productDate;

    private String productDescription;

    private boolean productStatus;

}
