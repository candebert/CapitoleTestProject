package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.resources.PriceEntity;

import java.util.Date;

public interface PriceRepository {

    PriceEntity getPriceBy(Date applicationDate, int brandId, int productId);
}
