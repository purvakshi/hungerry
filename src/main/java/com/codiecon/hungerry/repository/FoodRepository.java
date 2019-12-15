package com.codiecon.hungerry.repository;

import com.codiecon.hungerry.model.dao.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    FoodEntity findByIdAndDonateEntityId(Long id, Long orderId);
}
