package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.exception.PriceNotFoundException;
import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class PriceRepositoryImplTest {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
    private final int productId = 35455, brandId = 1;
    private PriceRepositoryImpl priceRepository;
    private final List<PriceEntity> priceEntities = getPriceEntitiesList();

    @Mock
    private JpaPriceRepository jpaPriceRepository;

	@BeforeEach
    public void setUp() {
        priceRepository = new PriceRepositoryImpl(jpaPriceRepository);
    }

    @ParameterizedTest(name = "Test {index} = Para la fecha: {0} retorna el precio: {1}")
    @CsvSource(value = {
        "2020-06-14 10.00.00,35.5",
        "2020-06-14 16.00.00,25.45",
        "2020-06-14 21.00.00,35.5",
        "2020-06-15 10.00.00,30.5",
        "2020-06-16 21.00.00,38.95",
    }, delimiter = ',')
    void whenCallToGetPriceThenReturnCorrectExpectedPrice(String stringApplicationDate, Double expectedPrice) throws ParseException {
        //Arrange
        Date applicationDate = simpleDateFormat.parse(stringApplicationDate);
        when(jpaPriceRepository.getPriceBy(applicationDate, brandId, productId))
                .thenReturn(getJpaRepositoryResponse(applicationDate));

        //Act
        PriceEntity priceEntityResponse = priceRepository.getPriceBy(applicationDate, brandId, productId);

        //Assert
		assertEquals(priceEntityResponse.getPRICE(), expectedPrice);
    }

    @Test
    void whenCallToGetPriceWithDateOutOfValuesThenReturnPriceNotFoundException() throws ParseException {
        //Arrange
        Date applicationDate = simpleDateFormat.parse("2023-06-14 10.00.00");
        List<PriceEntity> emptyListPriceEntities = new ArrayList<>();
        when(jpaPriceRepository.getPriceBy(applicationDate, brandId, productId))
                .thenReturn(emptyListPriceEntities);

        //Act & Assert
        assertThrows(PriceNotFoundException.class,() -> priceRepository.getPriceBy(applicationDate, brandId, productId));
    }

    private List<PriceEntity> getJpaRepositoryResponse(Date applicationDate) {
        return priceEntities.stream()
                .filter(price -> price.getSTART_DATE().compareTo(applicationDate) <= 0
                        && price.getEND_DATE().compareTo(applicationDate) >= 0)
                .collect(Collectors.toList());
    }

    private List<PriceEntity> getPriceEntitiesList() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(
                getPriceEntity("2020-06-14 00.00.00", "2020-12-31 23.59.59", 1, 35.5, 0)
        );
        priceEntities.add(
                getPriceEntity("2020-06-14 15.00.00", "2020-06-14 18.30.00", 2, 25.45, 1)
        );
        priceEntities.add(
                getPriceEntity("2020-06-15 00.00.00", "2020-06-15 11.00.00", 3, 30.5, 1)
        );
        priceEntities.add(
                getPriceEntity("2020-06-15 16.00.00", "2020-12-31 23.59.59", 4, 38.95, 1)
        );
        return priceEntities;
    }

    private PriceEntity getPriceEntity(
            String startDate,
            String endDate,
            int priceList,
            Double price,
            int priority
    ) {
        try {
            return new PriceEntity(
                    simpleDateFormat.parse(startDate),
                    simpleDateFormat.parse(endDate),
                    brandId,
                    "EUR",
                    price,
                    priceList,
                    priority,
                    productId
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}