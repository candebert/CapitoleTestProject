package com.capitole.testProject.core.domain;

import java.util.Date;
import java.util.Objects;

public class PriceResult {
    private Date applicationDate;
    private int brandId;
    private Double price;
    private int productId;
    private int productList;

    public PriceResult(Date applicationDate, int brandId, Double price, int productId, int productList) {
        this.applicationDate = applicationDate;
        this.brandId = brandId;
        this.price = price;
        this.productId = productId;
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceResult)) return false;
        PriceResult that = (PriceResult) o;
        return brandId == that.brandId &&
                price == that.price &&
                productId == that.productId &&
                productList == that.productList &&
                applicationDate.equals(that.applicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationDate, brandId, price, productId, productList);
    }
}

