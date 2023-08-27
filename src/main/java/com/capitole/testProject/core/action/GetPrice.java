package com.capitole.testProject.core.action;

import com.capitole.testProject.core.domain.PriceResult;
import com.capitole.testProject.core.infrastructure.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GetPrice {

    private final PriceRepository priceRepository;

    public GetPrice(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceResult invoke(Date applicationDate, int productId, int brandId) {
        return priceRepository.getPriceBy(applicationDate, brandId, productId).getResource(applicationDate);
    }
}
