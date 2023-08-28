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

import java.sql.Timestamp;
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
        //Arrange
        Date applicationDate = simpleDateFormat.parse("2020-06-14 00.00.00");
        int productId = 35455, brandId = 1;
        PriceResult expectedPriceResult = new PriceResult(applicationDate, brandId, 35.5, 1, productId);
        when(priceRepository.getPriceBy(applicationDate, productId, brandId))
                .thenReturn(getPriceEntity(brandId, productId));

        //Act
        PriceResult result = getPrice.invoke(applicationDate, productId, brandId);

        //Assert
        assert(expectedPriceResult).equals(result);
    }

    private PriceEntity getPriceEntity(int brandId, int productId) throws ParseException {
        return new PriceEntity(
                1,
                new Timestamp(simpleDateFormat.parse("2020-06-14 00.00.00").getTime()),
                new Timestamp(simpleDateFormat.parse("2020-12-31 23.59.59").getTime()),
                brandId,
                "EUR",
                35.5,
                1,
                1,
                productId
        );
    }
}