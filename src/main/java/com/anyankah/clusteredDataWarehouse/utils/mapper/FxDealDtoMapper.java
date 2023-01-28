package com.anyankah.clusteredDataWarehouse.utils.mapper;

import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.model.FxDeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
@Slf4j
public class FxDealDtoMapper {

    public FxDeal map(FxDealOrderDTO fxDealOrderDTO) {
        FxDeal fxDeal = new FxDeal();

        try{
            fxDeal.setAmount(fxDealOrderDTO.getAmount() != null? fxDealOrderDTO.getAmount() : null);
            fxDeal.setOrderingCurrency(fxDealOrderDTO.getOrderingCurrency() != null? Currency.getInstance(fxDealOrderDTO.getOrderingCurrency()) : null);
            fxDeal.setConvertingCurrency(fxDealOrderDTO.getConvertingCurrency() != null? Currency.getInstance(fxDealOrderDTO.getConvertingCurrency()) : null);
            fxDeal.setOrderTimeStamp(fxDealOrderDTO.getOrderTimeStamp() != null? fxDealOrderDTO.getOrderTimeStamp() : null);
            return fxDeal;
        }
        catch(Exception e) {
            log.info("{}", e);
            log.info("Exception in mapping fx deal DTO");
            return null;
        }
    }

    public FxDealOrderDTO map(FxDeal fxDeal) {
        FxDealOrderDTO fxDealOrderDTO = new FxDealOrderDTO();

        try{
            fxDealOrderDTO.setAmount(fxDeal.getAmount() != null? fxDeal.getAmount() : null);
            fxDealOrderDTO.setId(fxDeal.getId() != null? fxDeal.getId() : null);
            fxDealOrderDTO.setOrderingCurrency(fxDeal.getOrderingCurrency() != null? fxDeal.getOrderingCurrency().getCurrencyCode() : null);
            fxDealOrderDTO.setConvertingCurrency(fxDeal.getConvertingCurrency() != null? fxDeal.getConvertingCurrency().getCurrencyCode() : null);
            fxDealOrderDTO.setOrderTimeStamp(fxDeal.getOrderTimeStamp() != null? fxDeal.getOrderTimeStamp() : null);
            return fxDealOrderDTO;
        }
        catch(Exception e) {
            log.info("Exception in mapping fx deal DTO");
            return null;
        }
    }
}
