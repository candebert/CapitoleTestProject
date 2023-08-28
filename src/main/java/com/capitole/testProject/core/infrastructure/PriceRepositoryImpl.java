package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.exception.PriceNotFoundException;
import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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

    @Transactional
    public PriceEntity getPriceBy(Date applicationDate, int productId, int brandId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(applicationDate);

        List<PriceEntity> priceEntities = jpaPriceRepository.getPriceBy(formattedDate, productId, brandId);
        if (priceEntities == null || priceEntities.isEmpty()) {
            throw new PriceNotFoundException(formattedDate, brandId, productId);
        }

        return priceEntities.stream()
                .max(Comparator.comparingInt(PriceEntity::getPRIORITY)).get();
    }

}
