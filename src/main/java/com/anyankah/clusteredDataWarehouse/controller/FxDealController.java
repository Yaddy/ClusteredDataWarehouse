package com.anyankah.clusteredDataWarehouse.controller;

import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.repository.FxDealRepository;
import com.anyankah.clusteredDataWarehouse.service.FxDealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.anyankah.clusteredDataWarehouse.utils.mapper.FxDealDtoMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
public class FxDealController {

    private final FxDealService fxDealService;
    private final FxDealRepository fxDealRepository;

    private final FxDealDtoMapper fxMapper;

    @PostMapping("/fxDeals")
    public ResponseEntity<FxDealOrderDTO> createFxDeal(@Valid @RequestBody FxDealOrderDTO fxDealOrderDTO) throws URISyntaxException {
        log.info("HTTP request to save fxDeal: {}", fxDealOrderDTO);
        log.info("{} is null? {}", fxDealOrderDTO, fxDealOrderDTO == null);
        FxDealOrderDTO fxDealOrder = fxDealService.createFxDeal(fxDealOrderDTO);
        log.info("Successfully saved fxDeal: {}", fxDealOrderDTO);
        return ResponseEntity
                .created(new URI(String.format("%s%s", "/fxDeal/", fxDealOrder.getId() )))
                .body(fxDealOrder);

        }

    @GetMapping("/fxDeals/{id}")
    public ResponseEntity<FxDealOrderDTO> getFxDealById( @PathVariable("id") Long id) {
        FxDealOrderDTO fxDeal = fxDealService.getFxDealById(id);
        log.info("Successfully retrieved fx deal by id: {}", id);
        return ResponseEntity.ok(fxDeal);
    }
    @GetMapping("/fxDeals")
    public ResponseEntity<List<FxDealOrderDTO>> getAllFxDeals() {
        log.debug("REST request to get list of fxDeals");
        return ResponseEntity.ok().body(fxDealService.getAllFxDeals());
    }
}
