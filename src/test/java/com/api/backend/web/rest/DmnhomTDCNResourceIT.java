package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.DmnhomTDCN;
import com.api.backend.domain.DmTDCN;
import com.api.backend.repository.DmnhomTDCNRepository;
import com.api.backend.service.DmnhomTDCNService;
import com.api.backend.service.dto.DmnhomTDCNDTO;
import com.api.backend.service.mapper.DmnhomTDCNMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmnhomTDCNCriteria;
import com.api.backend.service.DmnhomTDCNQueryService;

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
import java.util.List;

import static com.api.backend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DmnhomTDCNResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmnhomTDCNResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAI = 1;
    private static final Integer UPDATED_LOAI = 2;
    private static final Integer SMALLER_LOAI = 1 - 1;

    private static final String DEFAULT_MA_BH = "AAAAAAAAAA";
    private static final String UPDATED_MA_BH = "BBBBBBBBBB";

    @Autowired
    private DmnhomTDCNRepository dmnhomTDCNRepository;

    @Autowired
    private DmnhomTDCNMapper dmnhomTDCNMapper;

    @Autowired
    private DmnhomTDCNService dmnhomTDCNService;

    @Autowired
    private DmnhomTDCNQueryService dmnhomTDCNQueryService;

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

    private MockMvc restDmnhomTDCNMockMvc;

    private DmnhomTDCN dmnhomTDCN;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmnhomTDCNResource dmnhomTDCNResource = new DmnhomTDCNResource(dmnhomTDCNService, dmnhomTDCNQueryService);
        this.restDmnhomTDCNMockMvc = MockMvcBuilders.standaloneSetup(dmnhomTDCNResource)
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
    public static DmnhomTDCN createEntity(EntityManager em) {
        DmnhomTDCN dmnhomTDCN = new DmnhomTDCN()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .loai(DEFAULT_LOAI)
            .maBH(DEFAULT_MA_BH);
        return dmnhomTDCN;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmnhomTDCN createUpdatedEntity(EntityManager em) {
        DmnhomTDCN dmnhomTDCN = new DmnhomTDCN()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        return dmnhomTDCN;
    }

    @BeforeEach
    public void initTest() {
        dmnhomTDCN = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmnhomTDCN() throws Exception {
        int databaseSizeBeforeCreate = dmnhomTDCNRepository.findAll().size();

        // Create the DmnhomTDCN
        DmnhomTDCNDTO dmnhomTDCNDTO = dmnhomTDCNMapper.toDto(dmnhomTDCN);
        restDmnhomTDCNMockMvc.perform(post("/api/dmnhom-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomTDCNDTO)))
            .andExpect(status().isCreated());

        // Validate the DmnhomTDCN in the database
        List<DmnhomTDCN> dmnhomTDCNList = dmnhomTDCNRepository.findAll();
        assertThat(dmnhomTDCNList).hasSize(databaseSizeBeforeCreate + 1);
        DmnhomTDCN testDmnhomTDCN = dmnhomTDCNList.get(dmnhomTDCNList.size() - 1);
        assertThat(testDmnhomTDCN.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmnhomTDCN.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmnhomTDCN.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDmnhomTDCN.getMaBH()).isEqualTo(DEFAULT_MA_BH);
    }

    @Test
    @Transactional
    public void createDmnhomTDCNWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmnhomTDCNRepository.findAll().size();

        // Create the DmnhomTDCN with an existing ID
        dmnhomTDCN.setId(1L);
        DmnhomTDCNDTO dmnhomTDCNDTO = dmnhomTDCNMapper.toDto(dmnhomTDCN);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmnhomTDCNMockMvc.perform(post("/api/dmnhom-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomTDCNDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmnhomTDCN in the database
        List<DmnhomTDCN> dmnhomTDCNList = dmnhomTDCNRepository.findAll();
        assertThat(dmnhomTDCNList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNS() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomTDCN.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));
    }
    
    @Test
    @Transactional
    public void getDmnhomTDCN() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get the dmnhomTDCN
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns/{id}", dmnhomTDCN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmnhomTDCN.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.maBH").value(DEFAULT_MA_BH));
    }


    @Test
    @Transactional
    public void getDmnhomTDCNSByIdFiltering() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        Long id = dmnhomTDCN.getId();

        defaultDmnhomTDCNShouldBeFound("id.equals=" + id);
        defaultDmnhomTDCNShouldNotBeFound("id.notEquals=" + id);

        defaultDmnhomTDCNShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmnhomTDCNShouldNotBeFound("id.greaterThan=" + id);

        defaultDmnhomTDCNShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmnhomTDCNShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma equals to DEFAULT_MA
        defaultDmnhomTDCNShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmnhomTDCNList where ma equals to UPDATED_MA
        defaultDmnhomTDCNShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma not equals to DEFAULT_MA
        defaultDmnhomTDCNShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmnhomTDCNList where ma not equals to UPDATED_MA
        defaultDmnhomTDCNShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmnhomTDCNShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmnhomTDCNList where ma equals to UPDATED_MA
        defaultDmnhomTDCNShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma is not null
        defaultDmnhomTDCNShouldBeFound("ma.specified=true");

        // Get all the dmnhomTDCNList where ma is null
        defaultDmnhomTDCNShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma contains DEFAULT_MA
        defaultDmnhomTDCNShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmnhomTDCNList where ma contains UPDATED_MA
        defaultDmnhomTDCNShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ma does not contain DEFAULT_MA
        defaultDmnhomTDCNShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmnhomTDCNList where ma does not contain UPDATED_MA
        defaultDmnhomTDCNShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten equals to DEFAULT_TEN
        defaultDmnhomTDCNShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmnhomTDCNList where ten equals to UPDATED_TEN
        defaultDmnhomTDCNShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten not equals to DEFAULT_TEN
        defaultDmnhomTDCNShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmnhomTDCNList where ten not equals to UPDATED_TEN
        defaultDmnhomTDCNShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmnhomTDCNShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmnhomTDCNList where ten equals to UPDATED_TEN
        defaultDmnhomTDCNShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten is not null
        defaultDmnhomTDCNShouldBeFound("ten.specified=true");

        // Get all the dmnhomTDCNList where ten is null
        defaultDmnhomTDCNShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten contains DEFAULT_TEN
        defaultDmnhomTDCNShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmnhomTDCNList where ten contains UPDATED_TEN
        defaultDmnhomTDCNShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where ten does not contain DEFAULT_TEN
        defaultDmnhomTDCNShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmnhomTDCNList where ten does not contain UPDATED_TEN
        defaultDmnhomTDCNShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai equals to DEFAULT_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai equals to UPDATED_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai not equals to DEFAULT_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai not equals to UPDATED_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the dmnhomTDCNList where loai equals to UPDATED_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai is not null
        defaultDmnhomTDCNShouldBeFound("loai.specified=true");

        // Get all the dmnhomTDCNList where loai is null
        defaultDmnhomTDCNShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai is greater than or equal to DEFAULT_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai is greater than or equal to UPDATED_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai is less than or equal to DEFAULT_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai is less than or equal to SMALLER_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai is less than DEFAULT_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai is less than UPDATED_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where loai is greater than DEFAULT_LOAI
        defaultDmnhomTDCNShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the dmnhomTDCNList where loai is greater than SMALLER_LOAI
        defaultDmnhomTDCNShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH equals to DEFAULT_MA_BH
        defaultDmnhomTDCNShouldBeFound("maBH.equals=" + DEFAULT_MA_BH);

        // Get all the dmnhomTDCNList where maBH equals to UPDATED_MA_BH
        defaultDmnhomTDCNShouldNotBeFound("maBH.equals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH not equals to DEFAULT_MA_BH
        defaultDmnhomTDCNShouldNotBeFound("maBH.notEquals=" + DEFAULT_MA_BH);

        // Get all the dmnhomTDCNList where maBH not equals to UPDATED_MA_BH
        defaultDmnhomTDCNShouldBeFound("maBH.notEquals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH in DEFAULT_MA_BH or UPDATED_MA_BH
        defaultDmnhomTDCNShouldBeFound("maBH.in=" + DEFAULT_MA_BH + "," + UPDATED_MA_BH);

        // Get all the dmnhomTDCNList where maBH equals to UPDATED_MA_BH
        defaultDmnhomTDCNShouldNotBeFound("maBH.in=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH is not null
        defaultDmnhomTDCNShouldBeFound("maBH.specified=true");

        // Get all the dmnhomTDCNList where maBH is null
        defaultDmnhomTDCNShouldNotBeFound("maBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH contains DEFAULT_MA_BH
        defaultDmnhomTDCNShouldBeFound("maBH.contains=" + DEFAULT_MA_BH);

        // Get all the dmnhomTDCNList where maBH contains UPDATED_MA_BH
        defaultDmnhomTDCNShouldNotBeFound("maBH.contains=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomTDCNSByMaBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        // Get all the dmnhomTDCNList where maBH does not contain DEFAULT_MA_BH
        defaultDmnhomTDCNShouldNotBeFound("maBH.doesNotContain=" + DEFAULT_MA_BH);

        // Get all the dmnhomTDCNList where maBH does not contain UPDATED_MA_BH
        defaultDmnhomTDCNShouldBeFound("maBH.doesNotContain=" + UPDATED_MA_BH);
    }


    @Test
    @Transactional
    public void getAllDmnhomTDCNSByDmTDCNIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);
        DmTDCN dmTDCN = DmTDCNResourceIT.createEntity(em);
        em.persist(dmTDCN);
        em.flush();
        dmnhomTDCN.addDmTDCN(dmTDCN);
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);
        Long dmTDCNId = dmTDCN.getId();

        // Get all the dmnhomTDCNList where dmTDCN equals to dmTDCNId
        defaultDmnhomTDCNShouldBeFound("dmTDCNId.equals=" + dmTDCNId);

        // Get all the dmnhomTDCNList where dmTDCN equals to dmTDCNId + 1
        defaultDmnhomTDCNShouldNotBeFound("dmTDCNId.equals=" + (dmTDCNId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmnhomTDCNShouldBeFound(String filter) throws Exception {
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomTDCN.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));

        // Check, that the count call also returns 1
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmnhomTDCNShouldNotBeFound(String filter) throws Exception {
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmnhomTDCN() throws Exception {
        // Get the dmnhomTDCN
        restDmnhomTDCNMockMvc.perform(get("/api/dmnhom-tdcns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmnhomTDCN() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        int databaseSizeBeforeUpdate = dmnhomTDCNRepository.findAll().size();

        // Update the dmnhomTDCN
        DmnhomTDCN updatedDmnhomTDCN = dmnhomTDCNRepository.findById(dmnhomTDCN.getId()).get();
        // Disconnect from session so that the updates on updatedDmnhomTDCN are not directly saved in db
        em.detach(updatedDmnhomTDCN);
        updatedDmnhomTDCN
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        DmnhomTDCNDTO dmnhomTDCNDTO = dmnhomTDCNMapper.toDto(updatedDmnhomTDCN);

        restDmnhomTDCNMockMvc.perform(put("/api/dmnhom-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomTDCNDTO)))
            .andExpect(status().isOk());

        // Validate the DmnhomTDCN in the database
        List<DmnhomTDCN> dmnhomTDCNList = dmnhomTDCNRepository.findAll();
        assertThat(dmnhomTDCNList).hasSize(databaseSizeBeforeUpdate);
        DmnhomTDCN testDmnhomTDCN = dmnhomTDCNList.get(dmnhomTDCNList.size() - 1);
        assertThat(testDmnhomTDCN.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmnhomTDCN.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmnhomTDCN.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDmnhomTDCN.getMaBH()).isEqualTo(UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmnhomTDCN() throws Exception {
        int databaseSizeBeforeUpdate = dmnhomTDCNRepository.findAll().size();

        // Create the DmnhomTDCN
        DmnhomTDCNDTO dmnhomTDCNDTO = dmnhomTDCNMapper.toDto(dmnhomTDCN);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmnhomTDCNMockMvc.perform(put("/api/dmnhom-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomTDCNDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmnhomTDCN in the database
        List<DmnhomTDCN> dmnhomTDCNList = dmnhomTDCNRepository.findAll();
        assertThat(dmnhomTDCNList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmnhomTDCN() throws Exception {
        // Initialize the database
        dmnhomTDCNRepository.saveAndFlush(dmnhomTDCN);

        int databaseSizeBeforeDelete = dmnhomTDCNRepository.findAll().size();

        // Delete the dmnhomTDCN
        restDmnhomTDCNMockMvc.perform(delete("/api/dmnhom-tdcns/{id}", dmnhomTDCN.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DmnhomTDCN> dmnhomTDCNList = dmnhomTDCNRepository.findAll();
        assertThat(dmnhomTDCNList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
