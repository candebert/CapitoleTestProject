package com.capitole.testProject.core.action;

import com.capitole.testProject.core.domain.PriceResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class GetPriceTest {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
    private int productId = 35455, brandId = 1;

    private GetPrice getPrice;

    @BeforeEach
    void setUp() {
        getPrice = new GetPrice();
    }

    @Test
    void whenCallGetPriceThenReturnCorrectPrice() throws ParseException {
        Date applicationDate = simpleDateFormat.parse("2020-06-14 00.00.00");
        PriceResult expectedPriceResult = new
                PriceResult(applicationDate, brandId, 35.5, productId, 1);

        PriceResult result = getPrice.invoke(applicationDate, productId, brandId);

        assert(expectedPriceResult).equals(result);
    }
}