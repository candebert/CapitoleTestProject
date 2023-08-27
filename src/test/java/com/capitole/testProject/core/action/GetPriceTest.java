package com.capitole.testProject.core.action;

import com.capitole.testProject.core.domain.PriceResult;
import com.capitole.testProject.core.infrastructure.PriceRepositoryImpl;
import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class GetPriceTest {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    private GetPrice getPrice;
    @Mock
    private PriceRepositoryImpl priceRepository;

    @BeforeEach
    void setUp() {
        getPrice = new GetPrice(priceRepository);
    }

    @Test
    void whenCallGetPriceThenReturnCorrectPrice() throws ParseException {
        Date applicationDate = simpleDateFormat.parse("2020-06-14 00.00.00");
        int productId = 35455, brandId = 1;
        PriceResult expectedPriceResult = new
                PriceResult(applicationDate, brandId, 35.5, 1, productId);
        when(priceRepository.getPriceBy(applicationDate, brandId, productId)).thenReturn(
                new PriceEntity(
                        simpleDateFormat.parse("2020-06-14 00.00.00"),
                        simpleDateFormat.parse("2020-12-31 23.59.59"),
                        brandId,
                        "EUR",
                        35.5,
                        1,
                        1,
                        productId
                ));

        PriceResult result = getPrice.invoke(applicationDate, productId, brandId);

        assert(expectedPriceResult).equals(result);
    }
}