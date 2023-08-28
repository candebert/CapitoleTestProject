package com.capitole.testProject.http.provider;

import com.capitole.testProject.core.infrastructure.PriceRepositoryImpl;
import com.capitole.testProject.core.infrastructure.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class InstanceRepositories {

    private final PriceRepositoryImpl priceRepositoryImpl;

    @Autowired
    public InstanceRepositories(PriceRepositoryImpl priceRepositoryImpl) {
        this.priceRepositoryImpl = priceRepositoryImpl;
    }

    PriceRepository priceRepository() {
        return priceRepositoryImpl;
    }
}
