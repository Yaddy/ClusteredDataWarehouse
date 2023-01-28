package com.anyankah.clusteredDataWarehouse.service;

import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;

import java.util.List;

public interface FxDealService {

    FxDealOrderDTO createFxDeal(FxDealOrderDTO fxDealOrderDTO);

    List<FxDealOrderDTO> getAllFxDeals();

}
