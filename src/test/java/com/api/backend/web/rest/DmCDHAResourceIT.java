package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.DmCDHA;
import com.api.backend.domain.DmnhomCDHA;
import com.api.backend.repository.DmCDHARepository;
import com.api.backend.service.DmCDHAService;
import com.api.backend.service.dto.DmCDHADTO;
import com.api.backend.service.mapper.DmCDHAMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmCDHACriteria;
import com.api.backend.service.DmCDHAQueryService;

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
 * Integration tests for the {@link DmCDHAResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmCDHAResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final Integer DEFAULT_GIOITINH = 1;
    private static final Integer UPDATED_GIOITINH = 2;
    private static final Integer SMALLER_GIOITINH = 1 - 1;

    private static final String DEFAULT_MA_BYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_BYT = "BBBBBBBBBB";

    private static final String DEFAULT_MANHOM_BH = "AAAAAAAAAA";
    private static final String UPDATED_MANHOM_BH = "BBBBBBBBBB";

    @Autowired
    private DmCDHARepository dmCDHARepository;

    @Autowired
    private DmCDHAMapper dmCDHAMapper;

    @Autowired
    private DmCDHAService dmCDHAService;

    @Autowired
    private DmCDHAQueryService dmCDHAQueryService;

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

    private MockMvc restDmCDHAMockMvc;

    private DmCDHA dmCDHA;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmCDHAResource dmCDHAResource = new DmCDHAResource(dmCDHAService, dmCDHAQueryService);
        this.restDmCDHAMockMvc = MockMvcBuilders.standaloneSetup(dmCDHAResource)
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
    public static DmCDHA createEntity(EntityManager em) {
        DmCDHA dmCDHA = new DmCDHA()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .gioitinh(DEFAULT_GIOITINH)
            .maBYT(DEFAULT_MA_BYT)
            .manhomBH(DEFAULT_MANHOM_BH);
        return dmCDHA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmCDHA createUpdatedEntity(EntityManager em) {
        DmCDHA dmCDHA = new DmCDHA()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        return dmCDHA;
    }

    @BeforeEach
    public void initTest() {
        dmCDHA = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmCDHA() throws Exception {
        int databaseSizeBeforeCreate = dmCDHARepository.findAll().size();

        // Create the DmCDHA
        DmCDHADTO dmCDHADTO = dmCDHAMapper.toDto(dmCDHA);
        restDmCDHAMockMvc.perform(post("/api/dm-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmCDHADTO)))
            .andExpect(status().isCreated());

        // Validate the DmCDHA in the database
        List<DmCDHA> dmCDHAList = dmCDHARepository.findAll();
        assertThat(dmCDHAList).hasSize(databaseSizeBeforeCreate + 1);
        DmCDHA testDmCDHA = dmCDHAList.get(dmCDHAList.size() - 1);
        assertThat(testDmCDHA.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmCDHA.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmCDHA.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDmCDHA.getGioitinh()).isEqualTo(DEFAULT_GIOITINH);
        assertThat(testDmCDHA.getMaBYT()).isEqualTo(DEFAULT_MA_BYT);
        assertThat(testDmCDHA.getManhomBH()).isEqualTo(DEFAULT_MANHOM_BH);
    }

    @Test
    @Transactional
    public void createDmCDHAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmCDHARepository.findAll().size();

        // Create the DmCDHA with an existing ID
        dmCDHA.setId(1L);
        DmCDHADTO dmCDHADTO = dmCDHAMapper.toDto(dmCDHA);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmCDHAMockMvc.perform(post("/api/dm-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmCDHADTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmCDHA in the database
        List<DmCDHA> dmCDHAList = dmCDHARepository.findAll();
        assertThat(dmCDHAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmCDHAS() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmCDHA.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));
    }
    
    @Test
    @Transactional
    public void getDmCDHA() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get the dmCDHA
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas/{id}", dmCDHA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmCDHA.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.gioitinh").value(DEFAULT_GIOITINH))
            .andExpect(jsonPath("$.maBYT").value(DEFAULT_MA_BYT))
            .andExpect(jsonPath("$.manhomBH").value(DEFAULT_MANHOM_BH));
    }


    @Test
    @Transactional
    public void getDmCDHASByIdFiltering() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        Long id = dmCDHA.getId();

        defaultDmCDHAShouldBeFound("id.equals=" + id);
        defaultDmCDHAShouldNotBeFound("id.notEquals=" + id);

        defaultDmCDHAShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmCDHAShouldNotBeFound("id.greaterThan=" + id);

        defaultDmCDHAShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmCDHAShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma equals to DEFAULT_MA
        defaultDmCDHAShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmCDHAList where ma equals to UPDATED_MA
        defaultDmCDHAShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma not equals to DEFAULT_MA
        defaultDmCDHAShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmCDHAList where ma not equals to UPDATED_MA
        defaultDmCDHAShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmCDHAShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmCDHAList where ma equals to UPDATED_MA
        defaultDmCDHAShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma is not null
        defaultDmCDHAShouldBeFound("ma.specified=true");

        // Get all the dmCDHAList where ma is null
        defaultDmCDHAShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmCDHASByMaContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma contains DEFAULT_MA
        defaultDmCDHAShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmCDHAList where ma contains UPDATED_MA
        defaultDmCDHAShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ma does not contain DEFAULT_MA
        defaultDmCDHAShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmCDHAList where ma does not contain UPDATED_MA
        defaultDmCDHAShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten equals to DEFAULT_TEN
        defaultDmCDHAShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmCDHAList where ten equals to UPDATED_TEN
        defaultDmCDHAShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten not equals to DEFAULT_TEN
        defaultDmCDHAShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmCDHAList where ten not equals to UPDATED_TEN
        defaultDmCDHAShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmCDHAShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmCDHAList where ten equals to UPDATED_TEN
        defaultDmCDHAShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten is not null
        defaultDmCDHAShouldBeFound("ten.specified=true");

        // Get all the dmCDHAList where ten is null
        defaultDmCDHAShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmCDHASByTenContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten contains DEFAULT_TEN
        defaultDmCDHAShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmCDHAList where ten contains UPDATED_TEN
        defaultDmCDHAShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where ten does not contain DEFAULT_TEN
        defaultDmCDHAShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmCDHAList where ten does not contain UPDATED_TEN
        defaultDmCDHAShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota equals to DEFAULT_MOTA
        defaultDmCDHAShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmCDHAList where mota equals to UPDATED_MOTA
        defaultDmCDHAShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota not equals to DEFAULT_MOTA
        defaultDmCDHAShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmCDHAList where mota not equals to UPDATED_MOTA
        defaultDmCDHAShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmCDHAShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmCDHAList where mota equals to UPDATED_MOTA
        defaultDmCDHAShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota is not null
        defaultDmCDHAShouldBeFound("mota.specified=true");

        // Get all the dmCDHAList where mota is null
        defaultDmCDHAShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmCDHASByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota contains DEFAULT_MOTA
        defaultDmCDHAShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmCDHAList where mota contains UPDATED_MOTA
        defaultDmCDHAShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where mota does not contain DEFAULT_MOTA
        defaultDmCDHAShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmCDHAList where mota does not contain UPDATED_MOTA
        defaultDmCDHAShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh equals to DEFAULT_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.equals=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh equals to UPDATED_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.equals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh not equals to DEFAULT_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.notEquals=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh not equals to UPDATED_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.notEquals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh in DEFAULT_GIOITINH or UPDATED_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.in=" + DEFAULT_GIOITINH + "," + UPDATED_GIOITINH);

        // Get all the dmCDHAList where gioitinh equals to UPDATED_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.in=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh is not null
        defaultDmCDHAShouldBeFound("gioitinh.specified=true");

        // Get all the dmCDHAList where gioitinh is null
        defaultDmCDHAShouldNotBeFound("gioitinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh is greater than or equal to DEFAULT_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.greaterThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh is greater than or equal to UPDATED_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.greaterThanOrEqual=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh is less than or equal to DEFAULT_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.lessThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh is less than or equal to SMALLER_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.lessThanOrEqual=" + SMALLER_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsLessThanSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh is less than DEFAULT_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.lessThan=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh is less than UPDATED_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.lessThan=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByGioitinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where gioitinh is greater than DEFAULT_GIOITINH
        defaultDmCDHAShouldNotBeFound("gioitinh.greaterThan=" + DEFAULT_GIOITINH);

        // Get all the dmCDHAList where gioitinh is greater than SMALLER_GIOITINH
        defaultDmCDHAShouldBeFound("gioitinh.greaterThan=" + SMALLER_GIOITINH);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByMaBYTIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT equals to DEFAULT_MA_BYT
        defaultDmCDHAShouldBeFound("maBYT.equals=" + DEFAULT_MA_BYT);

        // Get all the dmCDHAList where maBYT equals to UPDATED_MA_BYT
        defaultDmCDHAShouldNotBeFound("maBYT.equals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaBYTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT not equals to DEFAULT_MA_BYT
        defaultDmCDHAShouldNotBeFound("maBYT.notEquals=" + DEFAULT_MA_BYT);

        // Get all the dmCDHAList where maBYT not equals to UPDATED_MA_BYT
        defaultDmCDHAShouldBeFound("maBYT.notEquals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaBYTIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT in DEFAULT_MA_BYT or UPDATED_MA_BYT
        defaultDmCDHAShouldBeFound("maBYT.in=" + DEFAULT_MA_BYT + "," + UPDATED_MA_BYT);

        // Get all the dmCDHAList where maBYT equals to UPDATED_MA_BYT
        defaultDmCDHAShouldNotBeFound("maBYT.in=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaBYTIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT is not null
        defaultDmCDHAShouldBeFound("maBYT.specified=true");

        // Get all the dmCDHAList where maBYT is null
        defaultDmCDHAShouldNotBeFound("maBYT.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmCDHASByMaBYTContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT contains DEFAULT_MA_BYT
        defaultDmCDHAShouldBeFound("maBYT.contains=" + DEFAULT_MA_BYT);

        // Get all the dmCDHAList where maBYT contains UPDATED_MA_BYT
        defaultDmCDHAShouldNotBeFound("maBYT.contains=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByMaBYTNotContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where maBYT does not contain DEFAULT_MA_BYT
        defaultDmCDHAShouldNotBeFound("maBYT.doesNotContain=" + DEFAULT_MA_BYT);

        // Get all the dmCDHAList where maBYT does not contain UPDATED_MA_BYT
        defaultDmCDHAShouldBeFound("maBYT.doesNotContain=" + UPDATED_MA_BYT);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByManhomBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH equals to DEFAULT_MANHOM_BH
        defaultDmCDHAShouldBeFound("manhomBH.equals=" + DEFAULT_MANHOM_BH);

        // Get all the dmCDHAList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmCDHAShouldNotBeFound("manhomBH.equals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByManhomBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH not equals to DEFAULT_MANHOM_BH
        defaultDmCDHAShouldNotBeFound("manhomBH.notEquals=" + DEFAULT_MANHOM_BH);

        // Get all the dmCDHAList where manhomBH not equals to UPDATED_MANHOM_BH
        defaultDmCDHAShouldBeFound("manhomBH.notEquals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByManhomBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH in DEFAULT_MANHOM_BH or UPDATED_MANHOM_BH
        defaultDmCDHAShouldBeFound("manhomBH.in=" + DEFAULT_MANHOM_BH + "," + UPDATED_MANHOM_BH);

        // Get all the dmCDHAList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmCDHAShouldNotBeFound("manhomBH.in=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByManhomBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH is not null
        defaultDmCDHAShouldBeFound("manhomBH.specified=true");

        // Get all the dmCDHAList where manhomBH is null
        defaultDmCDHAShouldNotBeFound("manhomBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmCDHASByManhomBHContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH contains DEFAULT_MANHOM_BH
        defaultDmCDHAShouldBeFound("manhomBH.contains=" + DEFAULT_MANHOM_BH);

        // Get all the dmCDHAList where manhomBH contains UPDATED_MANHOM_BH
        defaultDmCDHAShouldNotBeFound("manhomBH.contains=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmCDHASByManhomBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        // Get all the dmCDHAList where manhomBH does not contain DEFAULT_MANHOM_BH
        defaultDmCDHAShouldNotBeFound("manhomBH.doesNotContain=" + DEFAULT_MANHOM_BH);

        // Get all the dmCDHAList where manhomBH does not contain UPDATED_MANHOM_BH
        defaultDmCDHAShouldBeFound("manhomBH.doesNotContain=" + UPDATED_MANHOM_BH);
    }


    @Test
    @Transactional
    public void getAllDmCDHASByDmnhomCDHAIsEqualToSomething() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);
        DmnhomCDHA dmnhomCDHA = DmnhomCDHAResourceIT.createEntity(em);
        em.persist(dmnhomCDHA);
        em.flush();
        dmCDHA.setDmnhomCDHA(dmnhomCDHA);
        dmCDHARepository.saveAndFlush(dmCDHA);
        Long dmnhomCDHAId = dmnhomCDHA.getId();

        // Get all the dmCDHAList where dmnhomCDHA equals to dmnhomCDHAId
        defaultDmCDHAShouldBeFound("dmnhomCDHAId.equals=" + dmnhomCDHAId);

        // Get all the dmCDHAList where dmnhomCDHA equals to dmnhomCDHAId + 1
        defaultDmCDHAShouldNotBeFound("dmnhomCDHAId.equals=" + (dmnhomCDHAId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmCDHAShouldBeFound(String filter) throws Exception {
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmCDHA.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));

        // Check, that the count call also returns 1
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmCDHAShouldNotBeFound(String filter) throws Exception {
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmCDHA() throws Exception {
        // Get the dmCDHA
        restDmCDHAMockMvc.perform(get("/api/dm-cdhas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmCDHA() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        int databaseSizeBeforeUpdate = dmCDHARepository.findAll().size();

        // Update the dmCDHA
        DmCDHA updatedDmCDHA = dmCDHARepository.findById(dmCDHA.getId()).get();
        // Disconnect from session so that the updates on updatedDmCDHA are not directly saved in db
        em.detach(updatedDmCDHA);
        updatedDmCDHA
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        DmCDHADTO dmCDHADTO = dmCDHAMapper.toDto(updatedDmCDHA);

        restDmCDHAMockMvc.perform(put("/api/dm-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmCDHADTO)))
            .andExpect(status().isOk());

        // Validate the DmCDHA in the database
        List<DmCDHA> dmCDHAList = dmCDHARepository.findAll();
        assertThat(dmCDHAList).hasSize(databaseSizeBeforeUpdate);
        DmCDHA testDmCDHA = dmCDHAList.get(dmCDHAList.size() - 1);
        assertThat(testDmCDHA.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmCDHA.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmCDHA.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDmCDHA.getGioitinh()).isEqualTo(UPDATED_GIOITINH);
        assertThat(testDmCDHA.getMaBYT()).isEqualTo(UPDATED_MA_BYT);
        assertThat(testDmCDHA.getManhomBH()).isEqualTo(UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmCDHA() throws Exception {
        int databaseSizeBeforeUpdate = dmCDHARepository.findAll().size();

        // Create the DmCDHA
        DmCDHADTO dmCDHADTO = dmCDHAMapper.toDto(dmCDHA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmCDHAMockMvc.perform(put("/api/dm-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmCDHADTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmCDHA in the database
        List<DmCDHA> dmCDHAList = dmCDHARepository.findAll();
        assertThat(dmCDHAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmCDHA() throws Exception {
        // Initialize the database
        dmCDHARepository.saveAndFlush(dmCDHA);

        int databaseSizeBeforeDelete = dmCDHARepository.findAll().size();

        // Delete the dmCDHA
        restDmCDHAMockMvc.perform(delete("/api/dm-cdhas/{id}", dmCDHA.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DmCDHA> dmCDHAList = dmCDHARepository.findAll();
        assertThat(dmCDHAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
