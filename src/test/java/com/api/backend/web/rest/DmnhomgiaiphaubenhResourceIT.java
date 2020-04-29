package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmnhomgiaiphaubenh;
import com.api.backend.domain.Dmgiaiphaubenh;
import com.api.backend.repository.DmnhomgiaiphaubenhRepository;
import com.api.backend.service.DmnhomgiaiphaubenhService;
import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;
import com.api.backend.service.mapper.DmnhomgiaiphaubenhMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmnhomgiaiphaubenhCriteria;
import com.api.backend.service.DmnhomgiaiphaubenhQueryService;

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
 * Integration tests for the {@link DmnhomgiaiphaubenhResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmnhomgiaiphaubenhResourceIT {

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
    private DmnhomgiaiphaubenhRepository dmnhomgiaiphaubenhRepository;

    @Autowired
    private DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper;

    @Autowired
    private DmnhomgiaiphaubenhService dmnhomgiaiphaubenhService;

    @Autowired
    private DmnhomgiaiphaubenhQueryService dmnhomgiaiphaubenhQueryService;

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

    private MockMvc restDmnhomgiaiphaubenhMockMvc;

    private Dmnhomgiaiphaubenh dmnhomgiaiphaubenh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmnhomgiaiphaubenhResource dmnhomgiaiphaubenhResource = new DmnhomgiaiphaubenhResource(dmnhomgiaiphaubenhService, dmnhomgiaiphaubenhQueryService);
        this.restDmnhomgiaiphaubenhMockMvc = MockMvcBuilders.standaloneSetup(dmnhomgiaiphaubenhResource)
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
    public static Dmnhomgiaiphaubenh createEntity(EntityManager em) {
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh = new Dmnhomgiaiphaubenh()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .loai(DEFAULT_LOAI)
            .maBH(DEFAULT_MA_BH);
        return dmnhomgiaiphaubenh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmnhomgiaiphaubenh createUpdatedEntity(EntityManager em) {
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh = new Dmnhomgiaiphaubenh()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        return dmnhomgiaiphaubenh;
    }

    @BeforeEach
    public void initTest() {
        dmnhomgiaiphaubenh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmnhomgiaiphaubenh() throws Exception {
        int databaseSizeBeforeCreate = dmnhomgiaiphaubenhRepository.findAll().size();

        // Create the Dmnhomgiaiphaubenh
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO = dmnhomgiaiphaubenhMapper.toDto(dmnhomgiaiphaubenh);
        restDmnhomgiaiphaubenhMockMvc.perform(post("/api/dmnhomgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomgiaiphaubenhDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmnhomgiaiphaubenh in the database
        List<Dmnhomgiaiphaubenh> dmnhomgiaiphaubenhList = dmnhomgiaiphaubenhRepository.findAll();
        assertThat(dmnhomgiaiphaubenhList).hasSize(databaseSizeBeforeCreate + 1);
        Dmnhomgiaiphaubenh testDmnhomgiaiphaubenh = dmnhomgiaiphaubenhList.get(dmnhomgiaiphaubenhList.size() - 1);
        assertThat(testDmnhomgiaiphaubenh.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmnhomgiaiphaubenh.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmnhomgiaiphaubenh.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDmnhomgiaiphaubenh.getMaBH()).isEqualTo(DEFAULT_MA_BH);
    }

    @Test
    @Transactional
    public void createDmnhomgiaiphaubenhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmnhomgiaiphaubenhRepository.findAll().size();

        // Create the Dmnhomgiaiphaubenh with an existing ID
        dmnhomgiaiphaubenh.setId(1L);
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO = dmnhomgiaiphaubenhMapper.toDto(dmnhomgiaiphaubenh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmnhomgiaiphaubenhMockMvc.perform(post("/api/dmnhomgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomgiaiphaubenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhomgiaiphaubenh in the database
        List<Dmnhomgiaiphaubenh> dmnhomgiaiphaubenhList = dmnhomgiaiphaubenhRepository.findAll();
        assertThat(dmnhomgiaiphaubenhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhs() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomgiaiphaubenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));
    }
    
    @Test
    @Transactional
    public void getDmnhomgiaiphaubenh() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get the dmnhomgiaiphaubenh
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs/{id}", dmnhomgiaiphaubenh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmnhomgiaiphaubenh.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.maBH").value(DEFAULT_MA_BH));
    }


    @Test
    @Transactional
    public void getDmnhomgiaiphaubenhsByIdFiltering() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        Long id = dmnhomgiaiphaubenh.getId();

        defaultDmnhomgiaiphaubenhShouldBeFound("id.equals=" + id);
        defaultDmnhomgiaiphaubenhShouldNotBeFound("id.notEquals=" + id);

        defaultDmnhomgiaiphaubenhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmnhomgiaiphaubenhShouldNotBeFound("id.greaterThan=" + id);

        defaultDmnhomgiaiphaubenhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmnhomgiaiphaubenhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma equals to DEFAULT_MA
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmnhomgiaiphaubenhList where ma equals to UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma not equals to DEFAULT_MA
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmnhomgiaiphaubenhList where ma not equals to UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmnhomgiaiphaubenhList where ma equals to UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma is not null
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.specified=true");

        // Get all the dmnhomgiaiphaubenhList where ma is null
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma contains DEFAULT_MA
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmnhomgiaiphaubenhList where ma contains UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ma does not contain DEFAULT_MA
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmnhomgiaiphaubenhList where ma does not contain UPDATED_MA
        defaultDmnhomgiaiphaubenhShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten equals to DEFAULT_TEN
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmnhomgiaiphaubenhList where ten equals to UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten not equals to DEFAULT_TEN
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmnhomgiaiphaubenhList where ten not equals to UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmnhomgiaiphaubenhList where ten equals to UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten is not null
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.specified=true");

        // Get all the dmnhomgiaiphaubenhList where ten is null
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten contains DEFAULT_TEN
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmnhomgiaiphaubenhList where ten contains UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where ten does not contain DEFAULT_TEN
        defaultDmnhomgiaiphaubenhShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmnhomgiaiphaubenhList where ten does not contain UPDATED_TEN
        defaultDmnhomgiaiphaubenhShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai equals to DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai equals to UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai not equals to DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai not equals to UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai equals to UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai is not null
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.specified=true");

        // Get all the dmnhomgiaiphaubenhList where loai is null
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai is greater than or equal to DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai is greater than or equal to UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai is less than or equal to DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai is less than or equal to SMALLER_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai is less than DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai is less than UPDATED_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where loai is greater than DEFAULT_LOAI
        defaultDmnhomgiaiphaubenhShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the dmnhomgiaiphaubenhList where loai is greater than SMALLER_LOAI
        defaultDmnhomgiaiphaubenhShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH equals to DEFAULT_MA_BH
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.equals=" + DEFAULT_MA_BH);

        // Get all the dmnhomgiaiphaubenhList where maBH equals to UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.equals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH not equals to DEFAULT_MA_BH
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.notEquals=" + DEFAULT_MA_BH);

        // Get all the dmnhomgiaiphaubenhList where maBH not equals to UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.notEquals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH in DEFAULT_MA_BH or UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.in=" + DEFAULT_MA_BH + "," + UPDATED_MA_BH);

        // Get all the dmnhomgiaiphaubenhList where maBH equals to UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.in=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH is not null
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.specified=true");

        // Get all the dmnhomgiaiphaubenhList where maBH is null
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH contains DEFAULT_MA_BH
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.contains=" + DEFAULT_MA_BH);

        // Get all the dmnhomgiaiphaubenhList where maBH contains UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.contains=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByMaBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        // Get all the dmnhomgiaiphaubenhList where maBH does not contain DEFAULT_MA_BH
        defaultDmnhomgiaiphaubenhShouldNotBeFound("maBH.doesNotContain=" + DEFAULT_MA_BH);

        // Get all the dmnhomgiaiphaubenhList where maBH does not contain UPDATED_MA_BH
        defaultDmnhomgiaiphaubenhShouldBeFound("maBH.doesNotContain=" + UPDATED_MA_BH);
    }


    @Test
    @Transactional
    public void getAllDmnhomgiaiphaubenhsByDmgiaiphaubenhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);
        Dmgiaiphaubenh dmgiaiphaubenh = DmgiaiphaubenhResourceIT.createEntity(em);
        em.persist(dmgiaiphaubenh);
        em.flush();
        dmnhomgiaiphaubenh.addDmgiaiphaubenh(dmgiaiphaubenh);
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);
        Long dmgiaiphaubenhId = dmgiaiphaubenh.getId();

        // Get all the dmnhomgiaiphaubenhList where dmgiaiphaubenh equals to dmgiaiphaubenhId
        defaultDmnhomgiaiphaubenhShouldBeFound("dmgiaiphaubenhId.equals=" + dmgiaiphaubenhId);

        // Get all the dmnhomgiaiphaubenhList where dmgiaiphaubenh equals to dmgiaiphaubenhId + 1
        defaultDmnhomgiaiphaubenhShouldNotBeFound("dmgiaiphaubenhId.equals=" + (dmgiaiphaubenhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmnhomgiaiphaubenhShouldBeFound(String filter) throws Exception {
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomgiaiphaubenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));

        // Check, that the count call also returns 1
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmnhomgiaiphaubenhShouldNotBeFound(String filter) throws Exception {
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmnhomgiaiphaubenh() throws Exception {
        // Get the dmnhomgiaiphaubenh
        restDmnhomgiaiphaubenhMockMvc.perform(get("/api/dmnhomgiaiphaubenhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmnhomgiaiphaubenh() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        int databaseSizeBeforeUpdate = dmnhomgiaiphaubenhRepository.findAll().size();

        // Update the dmnhomgiaiphaubenh
        Dmnhomgiaiphaubenh updatedDmnhomgiaiphaubenh = dmnhomgiaiphaubenhRepository.findById(dmnhomgiaiphaubenh.getId()).get();
        // Disconnect from session so that the updates on updatedDmnhomgiaiphaubenh are not directly saved in db
        em.detach(updatedDmnhomgiaiphaubenh);
        updatedDmnhomgiaiphaubenh
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO = dmnhomgiaiphaubenhMapper.toDto(updatedDmnhomgiaiphaubenh);

        restDmnhomgiaiphaubenhMockMvc.perform(put("/api/dmnhomgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomgiaiphaubenhDTO)))
            .andExpect(status().isOk());

        // Validate the Dmnhomgiaiphaubenh in the database
        List<Dmnhomgiaiphaubenh> dmnhomgiaiphaubenhList = dmnhomgiaiphaubenhRepository.findAll();
        assertThat(dmnhomgiaiphaubenhList).hasSize(databaseSizeBeforeUpdate);
        Dmnhomgiaiphaubenh testDmnhomgiaiphaubenh = dmnhomgiaiphaubenhList.get(dmnhomgiaiphaubenhList.size() - 1);
        assertThat(testDmnhomgiaiphaubenh.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmnhomgiaiphaubenh.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmnhomgiaiphaubenh.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDmnhomgiaiphaubenh.getMaBH()).isEqualTo(UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmnhomgiaiphaubenh() throws Exception {
        int databaseSizeBeforeUpdate = dmnhomgiaiphaubenhRepository.findAll().size();

        // Create the Dmnhomgiaiphaubenh
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO = dmnhomgiaiphaubenhMapper.toDto(dmnhomgiaiphaubenh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmnhomgiaiphaubenhMockMvc.perform(put("/api/dmnhomgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomgiaiphaubenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhomgiaiphaubenh in the database
        List<Dmnhomgiaiphaubenh> dmnhomgiaiphaubenhList = dmnhomgiaiphaubenhRepository.findAll();
        assertThat(dmnhomgiaiphaubenhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmnhomgiaiphaubenh() throws Exception {
        // Initialize the database
        dmnhomgiaiphaubenhRepository.saveAndFlush(dmnhomgiaiphaubenh);

        int databaseSizeBeforeDelete = dmnhomgiaiphaubenhRepository.findAll().size();

        // Delete the dmnhomgiaiphaubenh
        restDmnhomgiaiphaubenhMockMvc.perform(delete("/api/dmnhomgiaiphaubenhs/{id}", dmnhomgiaiphaubenh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmnhomgiaiphaubenh> dmnhomgiaiphaubenhList = dmnhomgiaiphaubenhRepository.findAll();
        assertThat(dmnhomgiaiphaubenhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
