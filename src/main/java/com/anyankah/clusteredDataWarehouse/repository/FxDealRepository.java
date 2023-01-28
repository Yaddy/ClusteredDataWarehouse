package com.anyankah.clusteredDataWarehouse.repository;

import com.anyankah.clusteredDataWarehouse.model.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {

}
