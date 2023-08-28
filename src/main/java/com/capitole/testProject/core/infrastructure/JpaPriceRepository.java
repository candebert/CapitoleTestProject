package com.capitole.testProject.core.infrastructure;

import com.capitole.testProject.core.infrastructure.resources.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Transactional
     @Query(value =
            "SELECT * " +
            "FROM PRICES " +
            "WHERE " +
                "(:application_date BETWEEN START_DATE AND END_DATE) " +
                "AND PRODUCT_ID=:product_id " +
                "AND BRAND_ID=:brand_id " +
            "ORDER BY PRIORITY DESC", nativeQuery = true)
    List<PriceEntity> getPriceBy(
            @Param("application_date") String applicationDate,
            @Param("product_id") int productId,
            @Param("brand_id") int brandId
    );
}
