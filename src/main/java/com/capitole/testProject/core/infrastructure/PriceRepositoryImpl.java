package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.exception.PriceNotFoundException;
import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryImpl(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    public PriceEntity getPriceBy(Date applicationDate, int brandId, int productId) {
        List<PriceEntity> priceEntity = jpaPriceRepository.getPriceBy(applicationDate, brandId, productId);
        if (priceEntity == null || priceEntity.isEmpty()) {
            throw new PriceNotFoundException(applicationDate, brandId, productId);
        }
        return priceEntity.stream()
                .max(Comparator.comparingInt(PriceEntity::getPRIORITY)).get();
    }
}
