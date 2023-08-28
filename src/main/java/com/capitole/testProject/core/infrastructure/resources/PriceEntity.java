package com.capitole.testProject.core.infrastructure.resources;

import com.capitole.testProject.core.domain.PriceResult;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Timestamp START_DATE;
    private Timestamp END_DATE;
    private int BRAND_ID;
    private String CURR;
    private Double PRICE;
    private int PRICE_LIST;
    private int PRIORITY;
    private int PRODUCT_ID;

    public PriceEntity() {
    }

    public PriceEntity(
            Integer ID,
            Timestamp START_DATE,
            Timestamp END_DATE,
            int BRAND_ID,
            String CURR,
            Double PRICE,
            int PRICE_LIST,
            int PRIORITY,
            int PRODUCT_ID
    ) {
        this.ID = ID;
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

    public Date getSTART_DATE() {
        return START_DATE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public Double getPRICE() {
        return PRICE;
    }

    public int getPRIORITY() {
        return PRIORITY;
    }
}
