package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmbenhly;
import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.repository.DmbenhlyRepository;
import com.api.backend.service.DmbenhlyService;
import com.api.backend.service.dto.DmbenhlyDTO;
import com.api.backend.service.mapper.DmbenhlyMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmbenhlyCriteria;
import com.api.backend.service.DmbenhlyQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.api.backend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DmbenhlyResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmbenhlyResourceIT {

    private static final String DEFAULT_I_CD = "AAAAAAAAAA";
    private static final String UPDATED_I_CD = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_ICD_10 = "AAAAAAAAAA";
    private static final String UPDATED_TEN_ICD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_TENTIENGANH = "AAAAAAAAAA";
    private static final String UPDATED_TENTIENGANH = "BBBBBBBBBB";

    private static final String DEFAULT_TENTHUONGGOI = "AAAAAAAAAA";
    private static final String UPDATED_TENTHUONGGOI = "BBBBBBBBBB";

    private static final String DEFAULT_I_C_DCHA = "AAAAAAAAAA";
    private static final String UPDATED_I_C_DCHA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_AD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_AD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_AD = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_TRANGTHAI = 1;
    private static final Integer UPDATED_TRANGTHAI = 2;
    private static final Integer SMALLER_TRANGTHAI = 1 - 1;

    @Autowired
    private DmbenhlyRepository dmbenhlyRepository;

    @Autowired
    private DmbenhlyMapper dmbenhlyMapper;

    @Autowired
    private DmbenhlyService dmbenhlyService;

    @Autowired
    private DmbenhlyQueryService dmbenhlyQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDmbenhlyMockMvc;

    private Dmbenhly dmbenhly;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmbenhlyResource dmbenhlyResource = new DmbenhlyResource(dmbenhlyService, dmbenhlyQueryService);
        this.restDmbenhlyMockMvc = MockMvcBuilders.standaloneSetup(dmbenhlyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmbenhly createEntity(EntityManager em) {
        Dmbenhly dmbenhly = new Dmbenhly()
            .iCD(DEFAULT_I_CD)
            .tenICD10(DEFAULT_TEN_ICD_10)
            .tentienganh(DEFAULT_TENTIENGANH)
            .tenthuonggoi(DEFAULT_TENTHUONGGOI)
            .iCDcha(DEFAULT_I_C_DCHA)
            .ngayAD(DEFAULT_NGAY_AD)
            .trangthai(DEFAULT_TRANGTHAI);
        return dmbenhly;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmbenhly createUpdatedEntity(EntityManager em) {
        Dmbenhly dmbenhly = new Dmbenhly()
            .iCD(UPDATED_I_CD)
            .tenICD10(UPDATED_TEN_ICD_10)
            .tentienganh(UPDATED_TENTIENGANH)
            .tenthuonggoi(UPDATED_TENTHUONGGOI)
            .iCDcha(UPDATED_I_C_DCHA)
            .ngayAD(UPDATED_NGAY_AD)
            .trangthai(UPDATED_TRANGTHAI);
        return dmbenhly;
    }

    @BeforeEach
    public void initTest() {
        dmbenhly = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmbenhly() throws Exception {
        int databaseSizeBeforeCreate = dmbenhlyRepository.findAll().size();

        // Create the Dmbenhly
        DmbenhlyDTO dmbenhlyDTO = dmbenhlyMapper.toDto(dmbenhly);
        restDmbenhlyMockMvc.perform(post("/api/dmbenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmbenhlyDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmbenhly in the database
        List<Dmbenhly> dmbenhlyList = dmbenhlyRepository.findAll();
        assertThat(dmbenhlyList).hasSize(databaseSizeBeforeCreate + 1);
        Dmbenhly testDmbenhly = dmbenhlyList.get(dmbenhlyList.size() - 1);
        assertThat(testDmbenhly.getiCD()).isEqualTo(DEFAULT_I_CD);
        assertThat(testDmbenhly.getTenICD10()).isEqualTo(DEFAULT_TEN_ICD_10);
        assertThat(testDmbenhly.getTentienganh()).isEqualTo(DEFAULT_TENTIENGANH);
        assertThat(testDmbenhly.getTenthuonggoi()).isEqualTo(DEFAULT_TENTHUONGGOI);
        assertThat(testDmbenhly.getiCDcha()).isEqualTo(DEFAULT_I_C_DCHA);
        assertThat(testDmbenhly.getNgayAD()).isEqualTo(DEFAULT_NGAY_AD);
        assertThat(testDmbenhly.getTrangthai()).isEqualTo(DEFAULT_TRANGTHAI);
    }

    @Test
    @Transactional
    public void createDmbenhlyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmbenhlyRepository.findAll().size();

        // Create the Dmbenhly with an existing ID
        dmbenhly.setId(1L);
        DmbenhlyDTO dmbenhlyDTO = dmbenhlyMapper.toDto(dmbenhly);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmbenhlyMockMvc.perform(post("/api/dmbenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmbenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmbenhly in the database
        List<Dmbenhly> dmbenhlyList = dmbenhlyRepository.findAll();
        assertThat(dmbenhlyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmbenhlies() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmbenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].iCD").value(hasItem(DEFAULT_I_CD)))
            .andExpect(jsonPath("$.[*].tenICD10").value(hasItem(DEFAULT_TEN_ICD_10)))
            .andExpect(jsonPath("$.[*].tentienganh").value(hasItem(DEFAULT_TENTIENGANH)))
            .andExpect(jsonPath("$.[*].tenthuonggoi").value(hasItem(DEFAULT_TENTHUONGGOI)))
            .andExpect(jsonPath("$.[*].iCDcha").value(hasItem(DEFAULT_I_C_DCHA)))
            .andExpect(jsonPath("$.[*].ngayAD").value(hasItem(DEFAULT_NGAY_AD.toString())))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)));
    }
    
    @Test
    @Transactional
    public void getDmbenhly() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get the dmbenhly
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies/{id}", dmbenhly.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmbenhly.getId().intValue()))
            .andExpect(jsonPath("$.iCD").value(DEFAULT_I_CD))
            .andExpect(jsonPath("$.tenICD10").value(DEFAULT_TEN_ICD_10))
            .andExpect(jsonPath("$.tentienganh").value(DEFAULT_TENTIENGANH))
            .andExpect(jsonPath("$.tenthuonggoi").value(DEFAULT_TENTHUONGGOI))
            .andExpect(jsonPath("$.iCDcha").value(DEFAULT_I_C_DCHA))
            .andExpect(jsonPath("$.ngayAD").value(DEFAULT_NGAY_AD.toString()))
            .andExpect(jsonPath("$.trangthai").value(DEFAULT_TRANGTHAI));
    }


    @Test
    @Transactional
    public void getDmbenhliesByIdFiltering() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        Long id = dmbenhly.getId();

        defaultDmbenhlyShouldBeFound("id.equals=" + id);
        defaultDmbenhlyShouldNotBeFound("id.notEquals=" + id);

        defaultDmbenhlyShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmbenhlyShouldNotBeFound("id.greaterThan=" + id);

        defaultDmbenhlyShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmbenhlyShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByiCDIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD equals to DEFAULT_I_CD
        defaultDmbenhlyShouldBeFound("iCD.equals=" + DEFAULT_I_CD);

        // Get all the dmbenhlyList where iCD equals to UPDATED_I_CD
        defaultDmbenhlyShouldNotBeFound("iCD.equals=" + UPDATED_I_CD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD not equals to DEFAULT_I_CD
        defaultDmbenhlyShouldNotBeFound("iCD.notEquals=" + DEFAULT_I_CD);

        // Get all the dmbenhlyList where iCD not equals to UPDATED_I_CD
        defaultDmbenhlyShouldBeFound("iCD.notEquals=" + UPDATED_I_CD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD in DEFAULT_I_CD or UPDATED_I_CD
        defaultDmbenhlyShouldBeFound("iCD.in=" + DEFAULT_I_CD + "," + UPDATED_I_CD);

        // Get all the dmbenhlyList where iCD equals to UPDATED_I_CD
        defaultDmbenhlyShouldNotBeFound("iCD.in=" + UPDATED_I_CD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD is not null
        defaultDmbenhlyShouldBeFound("iCD.specified=true");

        // Get all the dmbenhlyList where iCD is null
        defaultDmbenhlyShouldNotBeFound("iCD.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmbenhliesByiCDContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD contains DEFAULT_I_CD
        defaultDmbenhlyShouldBeFound("iCD.contains=" + DEFAULT_I_CD);

        // Get all the dmbenhlyList where iCD contains UPDATED_I_CD
        defaultDmbenhlyShouldNotBeFound("iCD.contains=" + UPDATED_I_CD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDNotContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCD does not contain DEFAULT_I_CD
        defaultDmbenhlyShouldNotBeFound("iCD.doesNotContain=" + DEFAULT_I_CD);

        // Get all the dmbenhlyList where iCD does not contain UPDATED_I_CD
        defaultDmbenhlyShouldBeFound("iCD.doesNotContain=" + UPDATED_I_CD);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10IsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 equals to DEFAULT_TEN_ICD_10
        defaultDmbenhlyShouldBeFound("tenICD10.equals=" + DEFAULT_TEN_ICD_10);

        // Get all the dmbenhlyList where tenICD10 equals to UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldNotBeFound("tenICD10.equals=" + UPDATED_TEN_ICD_10);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 not equals to DEFAULT_TEN_ICD_10
        defaultDmbenhlyShouldNotBeFound("tenICD10.notEquals=" + DEFAULT_TEN_ICD_10);

        // Get all the dmbenhlyList where tenICD10 not equals to UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldBeFound("tenICD10.notEquals=" + UPDATED_TEN_ICD_10);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10IsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 in DEFAULT_TEN_ICD_10 or UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldBeFound("tenICD10.in=" + DEFAULT_TEN_ICD_10 + "," + UPDATED_TEN_ICD_10);

        // Get all the dmbenhlyList where tenICD10 equals to UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldNotBeFound("tenICD10.in=" + UPDATED_TEN_ICD_10);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10IsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 is not null
        defaultDmbenhlyShouldBeFound("tenICD10.specified=true");

        // Get all the dmbenhlyList where tenICD10 is null
        defaultDmbenhlyShouldNotBeFound("tenICD10.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10ContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 contains DEFAULT_TEN_ICD_10
        defaultDmbenhlyShouldBeFound("tenICD10.contains=" + DEFAULT_TEN_ICD_10);

        // Get all the dmbenhlyList where tenICD10 contains UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldNotBeFound("tenICD10.contains=" + UPDATED_TEN_ICD_10);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenICD10NotContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenICD10 does not contain DEFAULT_TEN_ICD_10
        defaultDmbenhlyShouldNotBeFound("tenICD10.doesNotContain=" + DEFAULT_TEN_ICD_10);

        // Get all the dmbenhlyList where tenICD10 does not contain UPDATED_TEN_ICD_10
        defaultDmbenhlyShouldBeFound("tenICD10.doesNotContain=" + UPDATED_TEN_ICD_10);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh equals to DEFAULT_TENTIENGANH
        defaultDmbenhlyShouldBeFound("tentienganh.equals=" + DEFAULT_TENTIENGANH);

        // Get all the dmbenhlyList where tentienganh equals to UPDATED_TENTIENGANH
        defaultDmbenhlyShouldNotBeFound("tentienganh.equals=" + UPDATED_TENTIENGANH);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh not equals to DEFAULT_TENTIENGANH
        defaultDmbenhlyShouldNotBeFound("tentienganh.notEquals=" + DEFAULT_TENTIENGANH);

        // Get all the dmbenhlyList where tentienganh not equals to UPDATED_TENTIENGANH
        defaultDmbenhlyShouldBeFound("tentienganh.notEquals=" + UPDATED_TENTIENGANH);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh in DEFAULT_TENTIENGANH or UPDATED_TENTIENGANH
        defaultDmbenhlyShouldBeFound("tentienganh.in=" + DEFAULT_TENTIENGANH + "," + UPDATED_TENTIENGANH);

        // Get all the dmbenhlyList where tentienganh equals to UPDATED_TENTIENGANH
        defaultDmbenhlyShouldNotBeFound("tentienganh.in=" + UPDATED_TENTIENGANH);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh is not null
        defaultDmbenhlyShouldBeFound("tentienganh.specified=true");

        // Get all the dmbenhlyList where tentienganh is null
        defaultDmbenhlyShouldNotBeFound("tentienganh.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh contains DEFAULT_TENTIENGANH
        defaultDmbenhlyShouldBeFound("tentienganh.contains=" + DEFAULT_TENTIENGANH);

        // Get all the dmbenhlyList where tentienganh contains UPDATED_TENTIENGANH
        defaultDmbenhlyShouldNotBeFound("tentienganh.contains=" + UPDATED_TENTIENGANH);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTentienganhNotContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tentienganh does not contain DEFAULT_TENTIENGANH
        defaultDmbenhlyShouldNotBeFound("tentienganh.doesNotContain=" + DEFAULT_TENTIENGANH);

        // Get all the dmbenhlyList where tentienganh does not contain UPDATED_TENTIENGANH
        defaultDmbenhlyShouldBeFound("tentienganh.doesNotContain=" + UPDATED_TENTIENGANH);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi equals to DEFAULT_TENTHUONGGOI
        defaultDmbenhlyShouldBeFound("tenthuonggoi.equals=" + DEFAULT_TENTHUONGGOI);

        // Get all the dmbenhlyList where tenthuonggoi equals to UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.equals=" + UPDATED_TENTHUONGGOI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi not equals to DEFAULT_TENTHUONGGOI
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.notEquals=" + DEFAULT_TENTHUONGGOI);

        // Get all the dmbenhlyList where tenthuonggoi not equals to UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldBeFound("tenthuonggoi.notEquals=" + UPDATED_TENTHUONGGOI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi in DEFAULT_TENTHUONGGOI or UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldBeFound("tenthuonggoi.in=" + DEFAULT_TENTHUONGGOI + "," + UPDATED_TENTHUONGGOI);

        // Get all the dmbenhlyList where tenthuonggoi equals to UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.in=" + UPDATED_TENTHUONGGOI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi is not null
        defaultDmbenhlyShouldBeFound("tenthuonggoi.specified=true");

        // Get all the dmbenhlyList where tenthuonggoi is null
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi contains DEFAULT_TENTHUONGGOI
        defaultDmbenhlyShouldBeFound("tenthuonggoi.contains=" + DEFAULT_TENTHUONGGOI);

        // Get all the dmbenhlyList where tenthuonggoi contains UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.contains=" + UPDATED_TENTHUONGGOI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTenthuonggoiNotContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where tenthuonggoi does not contain DEFAULT_TENTHUONGGOI
        defaultDmbenhlyShouldNotBeFound("tenthuonggoi.doesNotContain=" + DEFAULT_TENTHUONGGOI);

        // Get all the dmbenhlyList where tenthuonggoi does not contain UPDATED_TENTHUONGGOI
        defaultDmbenhlyShouldBeFound("tenthuonggoi.doesNotContain=" + UPDATED_TENTHUONGGOI);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha equals to DEFAULT_I_C_DCHA
        defaultDmbenhlyShouldBeFound("iCDcha.equals=" + DEFAULT_I_C_DCHA);

        // Get all the dmbenhlyList where iCDcha equals to UPDATED_I_C_DCHA
        defaultDmbenhlyShouldNotBeFound("iCDcha.equals=" + UPDATED_I_C_DCHA);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha not equals to DEFAULT_I_C_DCHA
        defaultDmbenhlyShouldNotBeFound("iCDcha.notEquals=" + DEFAULT_I_C_DCHA);

        // Get all the dmbenhlyList where iCDcha not equals to UPDATED_I_C_DCHA
        defaultDmbenhlyShouldBeFound("iCDcha.notEquals=" + UPDATED_I_C_DCHA);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha in DEFAULT_I_C_DCHA or UPDATED_I_C_DCHA
        defaultDmbenhlyShouldBeFound("iCDcha.in=" + DEFAULT_I_C_DCHA + "," + UPDATED_I_C_DCHA);

        // Get all the dmbenhlyList where iCDcha equals to UPDATED_I_C_DCHA
        defaultDmbenhlyShouldNotBeFound("iCDcha.in=" + UPDATED_I_C_DCHA);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha is not null
        defaultDmbenhlyShouldBeFound("iCDcha.specified=true");

        // Get all the dmbenhlyList where iCDcha is null
        defaultDmbenhlyShouldNotBeFound("iCDcha.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha contains DEFAULT_I_C_DCHA
        defaultDmbenhlyShouldBeFound("iCDcha.contains=" + DEFAULT_I_C_DCHA);

        // Get all the dmbenhlyList where iCDcha contains UPDATED_I_C_DCHA
        defaultDmbenhlyShouldNotBeFound("iCDcha.contains=" + UPDATED_I_C_DCHA);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByiCDchaNotContainsSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where iCDcha does not contain DEFAULT_I_C_DCHA
        defaultDmbenhlyShouldNotBeFound("iCDcha.doesNotContain=" + DEFAULT_I_C_DCHA);

        // Get all the dmbenhlyList where iCDcha does not contain UPDATED_I_C_DCHA
        defaultDmbenhlyShouldBeFound("iCDcha.doesNotContain=" + UPDATED_I_C_DCHA);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD equals to DEFAULT_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.equals=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD equals to UPDATED_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.equals=" + UPDATED_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD not equals to DEFAULT_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.notEquals=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD not equals to UPDATED_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.notEquals=" + UPDATED_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD in DEFAULT_NGAY_AD or UPDATED_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.in=" + DEFAULT_NGAY_AD + "," + UPDATED_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD equals to UPDATED_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.in=" + UPDATED_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD is not null
        defaultDmbenhlyShouldBeFound("ngayAD.specified=true");

        // Get all the dmbenhlyList where ngayAD is null
        defaultDmbenhlyShouldNotBeFound("ngayAD.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD is greater than or equal to DEFAULT_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.greaterThanOrEqual=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD is greater than or equal to UPDATED_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.greaterThanOrEqual=" + UPDATED_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD is less than or equal to DEFAULT_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.lessThanOrEqual=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD is less than or equal to SMALLER_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.lessThanOrEqual=" + SMALLER_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsLessThanSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD is less than DEFAULT_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.lessThan=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD is less than UPDATED_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.lessThan=" + UPDATED_NGAY_AD);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByNgayADIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where ngayAD is greater than DEFAULT_NGAY_AD
        defaultDmbenhlyShouldNotBeFound("ngayAD.greaterThan=" + DEFAULT_NGAY_AD);

        // Get all the dmbenhlyList where ngayAD is greater than SMALLER_NGAY_AD
        defaultDmbenhlyShouldBeFound("ngayAD.greaterThan=" + SMALLER_NGAY_AD);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai equals to DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.equals=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai equals to UPDATED_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.equals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai not equals to DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.notEquals=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai not equals to UPDATED_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.notEquals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsInShouldWork() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai in DEFAULT_TRANGTHAI or UPDATED_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.in=" + DEFAULT_TRANGTHAI + "," + UPDATED_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai equals to UPDATED_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.in=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai is not null
        defaultDmbenhlyShouldBeFound("trangthai.specified=true");

        // Get all the dmbenhlyList where trangthai is null
        defaultDmbenhlyShouldNotBeFound("trangthai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai is greater than or equal to DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.greaterThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai is greater than or equal to UPDATED_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.greaterThanOrEqual=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai is less than or equal to DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.lessThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai is less than or equal to SMALLER_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.lessThanOrEqual=" + SMALLER_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsLessThanSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai is less than DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.lessThan=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai is less than UPDATED_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.lessThan=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllDmbenhliesByTrangthaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        // Get all the dmbenhlyList where trangthai is greater than DEFAULT_TRANGTHAI
        defaultDmbenhlyShouldNotBeFound("trangthai.greaterThan=" + DEFAULT_TRANGTHAI);

        // Get all the dmbenhlyList where trangthai is greater than SMALLER_TRANGTHAI
        defaultDmbenhlyShouldBeFound("trangthai.greaterThan=" + SMALLER_TRANGTHAI);
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByDmloaibenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);
        Dmloaibenhly dmloaibenhly = DmloaibenhlyResourceIT.createEntity(em);
        em.persist(dmloaibenhly);
        em.flush();
        dmbenhly.setDmloaibenhly(dmloaibenhly);
        dmbenhlyRepository.saveAndFlush(dmbenhly);
        Long dmloaibenhlyId = dmloaibenhly.getId();

        // Get all the dmbenhlyList where dmloaibenhly equals to dmloaibenhlyId
        defaultDmbenhlyShouldBeFound("dmloaibenhlyId.equals=" + dmloaibenhlyId);

        // Get all the dmbenhlyList where dmloaibenhly equals to dmloaibenhlyId + 1
        defaultDmbenhlyShouldNotBeFound("dmloaibenhlyId.equals=" + (dmloaibenhlyId + 1));
    }


    @Test
    @Transactional
    public void getAllDmbenhliesByDmnhombenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);
        Dmnhombenhly dmnhombenhly = DmnhombenhlyResourceIT.createEntity(em);
        em.persist(dmnhombenhly);
        em.flush();
        dmbenhly.setDmnhombenhly(dmnhombenhly);
        dmbenhlyRepository.saveAndFlush(dmbenhly);
        Long dmnhombenhlyId = dmnhombenhly.getId();

        // Get all the dmbenhlyList where dmnhombenhly equals to dmnhombenhlyId
        defaultDmbenhlyShouldBeFound("dmnhombenhlyId.equals=" + dmnhombenhlyId);

        // Get all the dmbenhlyList where dmnhombenhly equals to dmnhombenhlyId + 1
        defaultDmbenhlyShouldNotBeFound("dmnhombenhlyId.equals=" + (dmnhombenhlyId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmbenhlyShouldBeFound(String filter) throws Exception {
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmbenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].iCD").value(hasItem(DEFAULT_I_CD)))
            .andExpect(jsonPath("$.[*].tenICD10").value(hasItem(DEFAULT_TEN_ICD_10)))
            .andExpect(jsonPath("$.[*].tentienganh").value(hasItem(DEFAULT_TENTIENGANH)))
            .andExpect(jsonPath("$.[*].tenthuonggoi").value(hasItem(DEFAULT_TENTHUONGGOI)))
            .andExpect(jsonPath("$.[*].iCDcha").value(hasItem(DEFAULT_I_C_DCHA)))
            .andExpect(jsonPath("$.[*].ngayAD").value(hasItem(DEFAULT_NGAY_AD.toString())))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)));

        // Check, that the count call also returns 1
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmbenhlyShouldNotBeFound(String filter) throws Exception {
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmbenhly() throws Exception {
        // Get the dmbenhly
        restDmbenhlyMockMvc.perform(get("/api/dmbenhlies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmbenhly() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        int databaseSizeBeforeUpdate = dmbenhlyRepository.findAll().size();

        // Update the dmbenhly
        Dmbenhly updatedDmbenhly = dmbenhlyRepository.findById(dmbenhly.getId()).get();
        // Disconnect from session so that the updates on updatedDmbenhly are not directly saved in db
        em.detach(updatedDmbenhly);
        updatedDmbenhly
            .iCD(UPDATED_I_CD)
            .tenICD10(UPDATED_TEN_ICD_10)
            .tentienganh(UPDATED_TENTIENGANH)
            .tenthuonggoi(UPDATED_TENTHUONGGOI)
            .iCDcha(UPDATED_I_C_DCHA)
            .ngayAD(UPDATED_NGAY_AD)
            .trangthai(UPDATED_TRANGTHAI);
        DmbenhlyDTO dmbenhlyDTO = dmbenhlyMapper.toDto(updatedDmbenhly);

        restDmbenhlyMockMvc.perform(put("/api/dmbenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmbenhlyDTO)))
            .andExpect(status().isOk());

        // Validate the Dmbenhly in the database
        List<Dmbenhly> dmbenhlyList = dmbenhlyRepository.findAll();
        assertThat(dmbenhlyList).hasSize(databaseSizeBeforeUpdate);
        Dmbenhly testDmbenhly = dmbenhlyList.get(dmbenhlyList.size() - 1);
        assertThat(testDmbenhly.getiCD()).isEqualTo(UPDATED_I_CD);
        assertThat(testDmbenhly.getTenICD10()).isEqualTo(UPDATED_TEN_ICD_10);
        assertThat(testDmbenhly.getTentienganh()).isEqualTo(UPDATED_TENTIENGANH);
        assertThat(testDmbenhly.getTenthuonggoi()).isEqualTo(UPDATED_TENTHUONGGOI);
        assertThat(testDmbenhly.getiCDcha()).isEqualTo(UPDATED_I_C_DCHA);
        assertThat(testDmbenhly.getNgayAD()).isEqualTo(UPDATED_NGAY_AD);
        assertThat(testDmbenhly.getTrangthai()).isEqualTo(UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void updateNonExistingDmbenhly() throws Exception {
        int databaseSizeBeforeUpdate = dmbenhlyRepository.findAll().size();

        // Create the Dmbenhly
        DmbenhlyDTO dmbenhlyDTO = dmbenhlyMapper.toDto(dmbenhly);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmbenhlyMockMvc.perform(put("/api/dmbenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmbenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmbenhly in the database
        List<Dmbenhly> dmbenhlyList = dmbenhlyRepository.findAll();
        assertThat(dmbenhlyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmbenhly() throws Exception {
        // Initialize the database
        dmbenhlyRepository.saveAndFlush(dmbenhly);

        int databaseSizeBeforeDelete = dmbenhlyRepository.findAll().size();

        // Delete the dmbenhly
        restDmbenhlyMockMvc.perform(delete("/api/dmbenhlies/{id}", dmbenhly.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmbenhly> dmbenhlyList = dmbenhlyRepository.findAll();
        assertThat(dmbenhlyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
