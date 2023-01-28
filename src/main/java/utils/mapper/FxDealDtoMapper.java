package utils.mapper;

import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.model.FxDeal;

public class FxDealDtoMapper {

    public static FxDeal mapFxDealOrderDto(FxDeal fxDeal, FxDealOrderDTO fxDealOrderDTO) {

        try{
            fxDeal.setAmount(fxDealOrderDTO.getAmount() != null? fxDealOrderDTO.getAmount() : null);
            fxDeal.setId(fxDealOrderDTO.getId() != null? fxDealOrderDTO.getId() : null);
            fxDeal.setFromCurrency(fxDealOrderDTO.getOrderingCurrency() != null? fxDealOrderDTO.getOrderingCurrency() : null);
            fxDeal.setToCurrency(fxDealOrderDTO.getConvertingCurrency() != null? fxDealOrderDTO.getConvertingCurrency() : null);
            fxDeal.setOrderTimeStamp(fxDealOrderDTO.getOrderTimeStamp() != null? fxDealOrderDTO.getOrderTimeStamp() : null);
            return fxDeal;
        }
        catch(Exception e) {
            System.out.println("Exception in mapping fx deal DTO");
            return null;
        }
    }
}
