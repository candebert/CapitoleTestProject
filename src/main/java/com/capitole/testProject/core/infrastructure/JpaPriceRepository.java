package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Integer> {

     @Query(value =
            "SELECT " +
                "START_DATE, END_DATE, BRAND_ID, CURR, PRICE, PRICE_LIST,PRIORITY,PRODUCT_ID FROM PriceEntity " +
            "WHERE " +
                "(:application_date BETWEEN START_DATE AND END_DATE) " +
                "AND BRAND_ID=:brand_id " +
                "AND PRODUCT_ID=:product_id " +
            "ORDER BY PRIORITY DESC")
    List<PriceEntity> getPriceBy(
            @Param("application_date") Date applicationDate,
            @Param("brand_id") int brandId,
            @Param("product_id") int productId
    );
}
