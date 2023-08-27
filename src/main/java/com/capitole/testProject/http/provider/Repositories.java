package com.capitole.testProject.http.provider;

import com.capitole.testProject.core.infrastructure.PriceRepositoryImpl;
import com.capitole.testProject.core.infrastructure.PriceRepository;

class Repositories {

    private final PriceRepositoryImpl priceRepositoryImpl;


    public Repositories(PriceRepositoryImpl priceRepositoryImpl) {
        this.priceRepositoryImpl = priceRepositoryImpl;
    }

    PriceRepository priceRepository() {
        return priceRepositoryImpl;
    }
}
