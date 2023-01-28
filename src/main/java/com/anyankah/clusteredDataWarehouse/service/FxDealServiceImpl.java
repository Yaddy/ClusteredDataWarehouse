package com.anyankah.clusteredDataWarehouse.service;


import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.model.FxDeal;
import com.anyankah.clusteredDataWarehouse.repository.FxDealRepository;
import com.anyankah.clusteredDataWarehouse.exceptions.NotFoundException;
import com.anyankah.clusteredDataWarehouse.utils.validator.FxDealValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.anyankah.clusteredDataWarehouse.utils.mapper.FxDealDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FxDealServiceImpl implements FxDealService{

    private final FxDealRepository fxDealRepository;

    private final FxDealDtoMapper fxMapper;

    private final FxDealValidator validator;

    @Override
    public FxDealOrderDTO createFxDeal(FxDealOrderDTO fxDealDTO) {
        if (validator.isISOCurrencyCodeNotValid(fxDealDTO.getConvertingCurrency())
                || validator.isISOCurrencyCodeNotValid(fxDealDTO.getOrderingCurrency())) {
            log.info("wrong iso code");
                    throw new ValidationException("Invalid currency code");
        }

        FxDeal fxDeal = fxMapper.map(fxDealDTO);
        fxDeal = fxDealRepository.save(fxDeal);
        return fxMapper.map(fxDeal);
    }

    @Override
    public FxDealOrderDTO getFxDealById(Long id) {
        log.info("get fx deal by id");
        Optional<FxDealOrderDTO> optionalFxDeal = fxDealRepository.findById(id).map(fxMapper::map);
        return optionalFxDeal.orElseThrow(() -> new NotFoundException(""));
    }

    @Override
    public List<FxDealOrderDTO> getAllFxDeals() {
        log.info("get all deals");
        List<FxDeal> fxDeals = fxDealRepository.findAll();
        return fxDeals.stream().map(fxMapper::map).collect(Collectors.toList());
    }
}
