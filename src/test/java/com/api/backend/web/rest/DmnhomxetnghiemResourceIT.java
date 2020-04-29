package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmnhomxetnghiem;
import com.api.backend.domain.Dmxetnghiem;
import com.api.backend.repository.DmnhomxetnghiemRepository;
import com.api.backend.service.DmnhomxetnghiemService;
import com.api.backend.service.dto.DmnhomxetnghiemDTO;
import com.api.backend.service.mapper.DmnhomxetnghiemMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmnhomxetnghiemCriteria;
import com.api.backend.service.DmnhomxetnghiemQueryService;

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
 * Integration tests for the {@link DmnhomxetnghiemResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmnhomxetnghiemResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAI = 1;
    private static final Integer UPDATED_LOAI = 2;
    private static final Integer SMALLER_LOAI = 1 - 1;

    private static final String DEFAULT_MABH = "AAAAAAAAAA";
    private static final String UPDATED_MABH = "BBBBBBBBBB";

    @Autowired
    private DmnhomxetnghiemRepository dmnhomxetnghiemRepository;

    @Autowired
    private DmnhomxetnghiemMapper dmnhomxetnghiemMapper;

    @Autowired
    private DmnhomxetnghiemService dmnhomxetnghiemService;

    @Autowired
    private DmnhomxetnghiemQueryService dmnhomxetnghiemQueryService;

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

    private MockMvc restDmnhomxetnghiemMockMvc;

    private Dmnhomxetnghiem dmnhomxetnghiem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmnhomxetnghiemResource dmnhomxetnghiemResource = new DmnhomxetnghiemResource(dmnhomxetnghiemService, dmnhomxetnghiemQueryService);
        this.restDmnhomxetnghiemMockMvc = MockMvcBuilders.standaloneSetup(dmnhomxetnghiemResource)
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
    public static Dmnhomxetnghiem createEntity(EntityManager em) {
        Dmnhomxetnghiem dmnhomxetnghiem = new Dmnhomxetnghiem()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .loai(DEFAULT_LOAI)
            .mabh(DEFAULT_MABH);
        return dmnhomxetnghiem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmnhomxetnghiem createUpdatedEntity(EntityManager em) {
        Dmnhomxetnghiem dmnhomxetnghiem = new Dmnhomxetnghiem()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .mabh(UPDATED_MABH);
        return dmnhomxetnghiem;
    }

    @BeforeEach
    public void initTest() {
        dmnhomxetnghiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmnhomxetnghiem() throws Exception {
        int databaseSizeBeforeCreate = dmnhomxetnghiemRepository.findAll().size();

        // Create the Dmnhomxetnghiem
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO = dmnhomxetnghiemMapper.toDto(dmnhomxetnghiem);
        restDmnhomxetnghiemMockMvc.perform(post("/api/dmnhomxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomxetnghiemDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmnhomxetnghiem in the database
        List<Dmnhomxetnghiem> dmnhomxetnghiemList = dmnhomxetnghiemRepository.findAll();
        assertThat(dmnhomxetnghiemList).hasSize(databaseSizeBeforeCreate + 1);
        Dmnhomxetnghiem testDmnhomxetnghiem = dmnhomxetnghiemList.get(dmnhomxetnghiemList.size() - 1);
        assertThat(testDmnhomxetnghiem.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmnhomxetnghiem.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmnhomxetnghiem.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDmnhomxetnghiem.getMabh()).isEqualTo(DEFAULT_MABH);
    }

    @Test
    @Transactional
    public void createDmnhomxetnghiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmnhomxetnghiemRepository.findAll().size();

        // Create the Dmnhomxetnghiem with an existing ID
        dmnhomxetnghiem.setId(1L);
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO = dmnhomxetnghiemMapper.toDto(dmnhomxetnghiem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmnhomxetnghiemMockMvc.perform(post("/api/dmnhomxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomxetnghiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhomxetnghiem in the database
        List<Dmnhomxetnghiem> dmnhomxetnghiemList = dmnhomxetnghiemRepository.findAll();
        assertThat(dmnhomxetnghiemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiems() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomxetnghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].mabh").value(hasItem(DEFAULT_MABH)));
    }
    
    @Test
    @Transactional
    public void getDmnhomxetnghiem() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get the dmnhomxetnghiem
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems/{id}", dmnhomxetnghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmnhomxetnghiem.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.mabh").value(DEFAULT_MABH));
    }


    @Test
    @Transactional
    public void getDmnhomxetnghiemsByIdFiltering() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        Long id = dmnhomxetnghiem.getId();

        defaultDmnhomxetnghiemShouldBeFound("id.equals=" + id);
        defaultDmnhomxetnghiemShouldNotBeFound("id.notEquals=" + id);

        defaultDmnhomxetnghiemShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmnhomxetnghiemShouldNotBeFound("id.greaterThan=" + id);

        defaultDmnhomxetnghiemShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmnhomxetnghiemShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma equals to DEFAULT_MA
        defaultDmnhomxetnghiemShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmnhomxetnghiemList where ma equals to UPDATED_MA
        defaultDmnhomxetnghiemShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma not equals to DEFAULT_MA
        defaultDmnhomxetnghiemShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmnhomxetnghiemList where ma not equals to UPDATED_MA
        defaultDmnhomxetnghiemShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmnhomxetnghiemShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmnhomxetnghiemList where ma equals to UPDATED_MA
        defaultDmnhomxetnghiemShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma is not null
        defaultDmnhomxetnghiemShouldBeFound("ma.specified=true");

        // Get all the dmnhomxetnghiemList where ma is null
        defaultDmnhomxetnghiemShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma contains DEFAULT_MA
        defaultDmnhomxetnghiemShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmnhomxetnghiemList where ma contains UPDATED_MA
        defaultDmnhomxetnghiemShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ma does not contain DEFAULT_MA
        defaultDmnhomxetnghiemShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmnhomxetnghiemList where ma does not contain UPDATED_MA
        defaultDmnhomxetnghiemShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten equals to DEFAULT_TEN
        defaultDmnhomxetnghiemShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmnhomxetnghiemList where ten equals to UPDATED_TEN
        defaultDmnhomxetnghiemShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten not equals to DEFAULT_TEN
        defaultDmnhomxetnghiemShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmnhomxetnghiemList where ten not equals to UPDATED_TEN
        defaultDmnhomxetnghiemShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmnhomxetnghiemShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmnhomxetnghiemList where ten equals to UPDATED_TEN
        defaultDmnhomxetnghiemShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten is not null
        defaultDmnhomxetnghiemShouldBeFound("ten.specified=true");

        // Get all the dmnhomxetnghiemList where ten is null
        defaultDmnhomxetnghiemShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten contains DEFAULT_TEN
        defaultDmnhomxetnghiemShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmnhomxetnghiemList where ten contains UPDATED_TEN
        defaultDmnhomxetnghiemShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where ten does not contain DEFAULT_TEN
        defaultDmnhomxetnghiemShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmnhomxetnghiemList where ten does not contain UPDATED_TEN
        defaultDmnhomxetnghiemShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai equals to DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai equals to UPDATED_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai not equals to DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai not equals to UPDATED_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the dmnhomxetnghiemList where loai equals to UPDATED_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai is not null
        defaultDmnhomxetnghiemShouldBeFound("loai.specified=true");

        // Get all the dmnhomxetnghiemList where loai is null
        defaultDmnhomxetnghiemShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai is greater than or equal to DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai is greater than or equal to UPDATED_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai is less than or equal to DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai is less than or equal to SMALLER_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai is less than DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai is less than UPDATED_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where loai is greater than DEFAULT_LOAI
        defaultDmnhomxetnghiemShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the dmnhomxetnghiemList where loai is greater than SMALLER_LOAI
        defaultDmnhomxetnghiemShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh equals to DEFAULT_MABH
        defaultDmnhomxetnghiemShouldBeFound("mabh.equals=" + DEFAULT_MABH);

        // Get all the dmnhomxetnghiemList where mabh equals to UPDATED_MABH
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.equals=" + UPDATED_MABH);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh not equals to DEFAULT_MABH
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.notEquals=" + DEFAULT_MABH);

        // Get all the dmnhomxetnghiemList where mabh not equals to UPDATED_MABH
        defaultDmnhomxetnghiemShouldBeFound("mabh.notEquals=" + UPDATED_MABH);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh in DEFAULT_MABH or UPDATED_MABH
        defaultDmnhomxetnghiemShouldBeFound("mabh.in=" + DEFAULT_MABH + "," + UPDATED_MABH);

        // Get all the dmnhomxetnghiemList where mabh equals to UPDATED_MABH
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.in=" + UPDATED_MABH);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh is not null
        defaultDmnhomxetnghiemShouldBeFound("mabh.specified=true");

        // Get all the dmnhomxetnghiemList where mabh is null
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh contains DEFAULT_MABH
        defaultDmnhomxetnghiemShouldBeFound("mabh.contains=" + DEFAULT_MABH);

        // Get all the dmnhomxetnghiemList where mabh contains UPDATED_MABH
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.contains=" + UPDATED_MABH);
    }

    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByMabhNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        // Get all the dmnhomxetnghiemList where mabh does not contain DEFAULT_MABH
        defaultDmnhomxetnghiemShouldNotBeFound("mabh.doesNotContain=" + DEFAULT_MABH);

        // Get all the dmnhomxetnghiemList where mabh does not contain UPDATED_MABH
        defaultDmnhomxetnghiemShouldBeFound("mabh.doesNotContain=" + UPDATED_MABH);
    }


    @Test
    @Transactional
    public void getAllDmnhomxetnghiemsByDmxetnghiemIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);
        Dmxetnghiem dmxetnghiem = DmxetnghiemResourceIT.createEntity(em);
        em.persist(dmxetnghiem);
        em.flush();
        dmnhomxetnghiem.addDmxetnghiem(dmxetnghiem);
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);
        Long dmxetnghiemId = dmxetnghiem.getId();

        // Get all the dmnhomxetnghiemList where dmxetnghiem equals to dmxetnghiemId
        defaultDmnhomxetnghiemShouldBeFound("dmxetnghiemId.equals=" + dmxetnghiemId);

        // Get all the dmnhomxetnghiemList where dmxetnghiem equals to dmxetnghiemId + 1
        defaultDmnhomxetnghiemShouldNotBeFound("dmxetnghiemId.equals=" + (dmxetnghiemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmnhomxetnghiemShouldBeFound(String filter) throws Exception {
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomxetnghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].mabh").value(hasItem(DEFAULT_MABH)));

        // Check, that the count call also returns 1
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmnhomxetnghiemShouldNotBeFound(String filter) throws Exception {
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmnhomxetnghiem() throws Exception {
        // Get the dmnhomxetnghiem
        restDmnhomxetnghiemMockMvc.perform(get("/api/dmnhomxetnghiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmnhomxetnghiem() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        int databaseSizeBeforeUpdate = dmnhomxetnghiemRepository.findAll().size();

        // Update the dmnhomxetnghiem
        Dmnhomxetnghiem updatedDmnhomxetnghiem = dmnhomxetnghiemRepository.findById(dmnhomxetnghiem.getId()).get();
        // Disconnect from session so that the updates on updatedDmnhomxetnghiem are not directly saved in db
        em.detach(updatedDmnhomxetnghiem);
        updatedDmnhomxetnghiem
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .mabh(UPDATED_MABH);
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO = dmnhomxetnghiemMapper.toDto(updatedDmnhomxetnghiem);

        restDmnhomxetnghiemMockMvc.perform(put("/api/dmnhomxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomxetnghiemDTO)))
            .andExpect(status().isOk());

        // Validate the Dmnhomxetnghiem in the database
        List<Dmnhomxetnghiem> dmnhomxetnghiemList = dmnhomxetnghiemRepository.findAll();
        assertThat(dmnhomxetnghiemList).hasSize(databaseSizeBeforeUpdate);
        Dmnhomxetnghiem testDmnhomxetnghiem = dmnhomxetnghiemList.get(dmnhomxetnghiemList.size() - 1);
        assertThat(testDmnhomxetnghiem.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmnhomxetnghiem.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmnhomxetnghiem.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDmnhomxetnghiem.getMabh()).isEqualTo(UPDATED_MABH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmnhomxetnghiem() throws Exception {
        int databaseSizeBeforeUpdate = dmnhomxetnghiemRepository.findAll().size();

        // Create the Dmnhomxetnghiem
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO = dmnhomxetnghiemMapper.toDto(dmnhomxetnghiem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmnhomxetnghiemMockMvc.perform(put("/api/dmnhomxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomxetnghiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhomxetnghiem in the database
        List<Dmnhomxetnghiem> dmnhomxetnghiemList = dmnhomxetnghiemRepository.findAll();
        assertThat(dmnhomxetnghiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmnhomxetnghiem() throws Exception {
        // Initialize the database
        dmnhomxetnghiemRepository.saveAndFlush(dmnhomxetnghiem);

        int databaseSizeBeforeDelete = dmnhomxetnghiemRepository.findAll().size();

        // Delete the dmnhomxetnghiem
        restDmnhomxetnghiemMockMvc.perform(delete("/api/dmnhomxetnghiems/{id}", dmnhomxetnghiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmnhomxetnghiem> dmnhomxetnghiemList = dmnhomxetnghiemRepository.findAll();
        assertThat(dmnhomxetnghiemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
