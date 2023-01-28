package com.anyankah.clusteredDataWarehouse.service;


import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.repository.FxDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FxDealServiceImpl {

    private final FxDealRepository fxDealRepository;

    public void createProduct(FxDealOrderDTO orderRequest) {

    }
}
