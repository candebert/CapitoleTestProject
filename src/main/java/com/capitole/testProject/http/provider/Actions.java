package com.capitole.testProject.http.provider;

import com.capitole.testProject.core.action.GetPrice;
import org.springframework.beans.factory.annotation.Autowired;

class Actions {

    private final Repositories repositories;

    @Autowired
    public Actions(Repositories repositories) {
        this.repositories = repositories;
    }

    GetPrice getPrice() {
        return new GetPrice(repositories.priceRepository());
    }
}
