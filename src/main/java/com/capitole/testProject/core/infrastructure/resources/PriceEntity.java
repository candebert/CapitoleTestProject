package com.capitole.testProject.core.infrastructure.resources;

import com.capitole.testProject.core.domain.PriceResult;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Date START_DATE;
    private Date END_DATE;
    private  int BRAND_ID;
    private String CURR;
    private Double PRICE;
    private int PRICE_LIST;
    private int PRIORITY;
    private int PRODUCT_ID;

    public PriceEntity(
            Date START_DATE,
            Date END_DATE,
            int BRAND_ID,
            String CURR,
            Double PRICE,
            int PRICE_LIST,
            int PRIORITY,
            int PRODUCT_ID
    ) {
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.BRAND_ID = BRAND_ID;
        this.CURR = CURR;
        this.PRICE = PRICE;
        this.PRICE_LIST = PRICE_LIST;
        this.PRIORITY = PRIORITY;
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public PriceResult getResource(Date applicationDate) {
        return new PriceResult(
                applicationDate,
                this.BRAND_ID,
                this.PRICE,
                this.PRICE_LIST,
                this.PRODUCT_ID
        );
    }

    public int getPRIORITY() {
        return PRIORITY;
    }
}
