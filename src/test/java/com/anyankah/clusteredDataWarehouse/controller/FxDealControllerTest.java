package com.anyankah.clusteredDataWarehouse.controller;

import com.anyankah.clusteredDataWarehouse.ClusteredDataWarehouseApplication;
import com.anyankah.clusteredDataWarehouse.dto.FxDealOrderDTO;
import com.anyankah.clusteredDataWarehouse.model.FxDeal;
import com.anyankah.clusteredDataWarehouse.repository.FxDealRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.anyankah.clusteredDataWarehouse.utils.mapper.FxDealDtoMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(classes = ClusteredDataWarehouseApplication.class)
public class FxDealControllerTest {


    @Autowired
    FxDealRepository fxDealRepository;

    @Autowired
    FxDealDtoMapper fxDealDtoMapper;

    @Autowired
    private MockMvc restFxDealMockMvc;

    private FxDeal fxDeal;

    private static final ObjectMapper mapper = createObjectMapper();


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }


    private final static Long DEFAULT_UNIQUE_ID = 4L;
    private final static BigDecimal DEFAULT_DEAL_AMOUNT = BigDecimal.ONE;
    private final static Currency DEFAULT_FROM = Currency.getInstance("NGN");
    private final static Currency DEFAULT_TO = Currency.getInstance("USD");
    private final static LocalDateTime DEFAULT_DEAL_TIMESTAMP = LocalDateTime.now();

    @BeforeEach
    public void initTest() {
        fxDeal = createDeal();
    }

    @AfterEach
    public void destroyTest() {
        fxDealRepository.deleteAll();
    }

    public static FxDeal createDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setId(DEFAULT_UNIQUE_ID);
        fxDeal.setAmount(DEFAULT_DEAL_AMOUNT);
        fxDeal.setConvertingCurrency(DEFAULT_FROM);
        fxDeal.setOrderingCurrency(DEFAULT_TO);
        fxDeal.setOrderTimeStamp(DEFAULT_DEAL_TIMESTAMP);
        return fxDeal;
    }

    @Test
    @Transactional
    void createFxDeal() throws Exception {
        int databaseSizeBeforeCreate = fxDealRepository.findAll().size();
        // Create the FxDeal
        FxDealOrderDTO fxDealOrderDTO = fxDealDtoMapper.map(fxDeal);
        restFxDealMockMvc
                .perform(post("/api/v1/fxDeals").contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(fxDealOrderDTO)))
                .andExpect(status().isCreated());

        // Validate the FxDeal in the database
        List<FxDeal> fxDealList = fxDealRepository.findAll();
        assertThat(fxDealList).hasSize(databaseSizeBeforeCreate + 1);
        FxDeal testFxDeal = fxDealList.get(fxDealList.size() - 1);
        assertThat(testFxDeal.getAmount()).isEqualTo(DEFAULT_DEAL_AMOUNT);
        assertThat(testFxDeal.getOrderTimeStamp()).isEqualTo(DEFAULT_DEAL_TIMESTAMP);
        assertThat(testFxDeal.getOrderingCurrency()).isEqualTo(DEFAULT_FROM);
        assertThat(testFxDeal.getConvertingCurrency()).isEqualTo(DEFAULT_TO);
    }

    @Test
    @Transactional
    void getAllFxDeals() throws Exception {
        // Initialize the database
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
                .perform(get("/api/v1/fxDeals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.[*].amount").value(hasItem(DEFAULT_DEAL_AMOUNT.intValue())))
                .andExpect(jsonPath("$.data.[*].orderingCurrency").value(hasItem(DEFAULT_FROM.getCurrencyCode())))
                .andExpect(jsonPath("$.data.[*].convertingCurrency").value(hasItem(DEFAULT_TO.getCurrencyCode())));
    }


    @Test
    @Transactional
    void getFxDealById() throws Exception {
        // Initialize the database
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
                .perform(get("/api/v1/fxDeals/"+fxDeal .getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.amount").value(DEFAULT_DEAL_AMOUNT.intValue()))
                .andExpect(jsonPath("$.data.orderingCurrency").value(DEFAULT_FROM.getCurrencyCode()))
                .andExpect(jsonPath("$.data.convertingCurrency").value(DEFAULT_TO.getCurrencyCode()));
    }

}
