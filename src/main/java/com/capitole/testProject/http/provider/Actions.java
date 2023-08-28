package com.capitole.testProject.http.provider;

import com.capitole.testProject.core.action.GetPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Actions {

    private final InstanceRepositories instanceRepositories;

    @Autowired
    public Actions(InstanceRepositories instanceRepositories) {
        this.instanceRepositories = instanceRepositories;
    }

    public GetPrice getPrice() {
        return new GetPrice(instanceRepositories.priceRepository());
    }
}
