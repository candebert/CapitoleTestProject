package com.capitole.testProject.core.domain;

import java.util.Date;
import java.util.Objects;

public class PriceResult {
    private Date applicationDate;
    private int brandId;
    private Double price;
    private int priceList;
    private int productId;

    public PriceResult(Date applicationDate, int brandId, Double price, int priceList, int productId) {
        this.applicationDate = applicationDate;
        this.brandId = brandId;
        this.price = price;
        this.priceList = priceList;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceResult)) return false;
        PriceResult that = (PriceResult) o;
        return brandId == that.brandId &&
                price.equals(that.price) &&
                priceList == that.priceList &&
                productId == that.productId &&
                applicationDate.equals(that.applicationDate);
    }

    @Override
    public int hashCode() { return Objects.hash(applicationDate, brandId, price, priceList, productId); }

}

