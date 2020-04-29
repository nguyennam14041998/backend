package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmgiaiphaubenh;
import com.api.backend.domain.Dmnhomgiaiphaubenh;
import com.api.backend.repository.DmgiaiphaubenhRepository;
import com.api.backend.service.DmgiaiphaubenhQueryService;
import com.api.backend.service.DmgiaiphaubenhService;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;
import com.api.backend.service.mapper.MyDanhMucGiaiPhauBenhMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
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
 * Integration tests for the {@link DmgiaiphaubenhResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmgiaiphaubenhResourceIT {

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
    private DmgiaiphaubenhRepository dmgiaiphaubenhRepository;

    @Autowired
    private MyDanhMucGiaiPhauBenhMapper dmgiaiphaubenhMapper;

    @Autowired
    private DmgiaiphaubenhService dmgiaiphaubenhService;

    @Autowired
    private DmgiaiphaubenhQueryService dmgiaiphaubenhQueryService;

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

    private MockMvc restDmgiaiphaubenhMockMvc;

    private Dmgiaiphaubenh dmgiaiphaubenh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmgiaiphaubenhResource dmgiaiphaubenhResource = new DmgiaiphaubenhResource(dmgiaiphaubenhService, dmgiaiphaubenhQueryService);
        this.restDmgiaiphaubenhMockMvc = MockMvcBuilders.standaloneSetup(dmgiaiphaubenhResource)
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
    public static Dmgiaiphaubenh createEntity(EntityManager em) {
        Dmgiaiphaubenh dmgiaiphaubenh = new Dmgiaiphaubenh()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .gioitinh(DEFAULT_GIOITINH)
            .maBYT(DEFAULT_MA_BYT)
            .manhomBH(DEFAULT_MANHOM_BH);
        return dmgiaiphaubenh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmgiaiphaubenh createUpdatedEntity(EntityManager em) {
        Dmgiaiphaubenh dmgiaiphaubenh = new Dmgiaiphaubenh()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        return dmgiaiphaubenh;
    }

    @BeforeEach
    public void initTest() {
        dmgiaiphaubenh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmgiaiphaubenh() throws Exception {
        int databaseSizeBeforeCreate = dmgiaiphaubenhRepository.findAll().size();

        // Create the Dmgiaiphaubenh
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = dmgiaiphaubenhMapper.toDto(dmgiaiphaubenh);
        restDmgiaiphaubenhMockMvc.perform(post("/api/dmgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmgiaiphaubenhDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmgiaiphaubenh in the database
        List<Dmgiaiphaubenh> dmgiaiphaubenhList = dmgiaiphaubenhRepository.findAll();
        assertThat(dmgiaiphaubenhList).hasSize(databaseSizeBeforeCreate + 1);
        Dmgiaiphaubenh testDmgiaiphaubenh = dmgiaiphaubenhList.get(dmgiaiphaubenhList.size() - 1);
        assertThat(testDmgiaiphaubenh.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmgiaiphaubenh.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmgiaiphaubenh.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDmgiaiphaubenh.getGioitinh()).isEqualTo(DEFAULT_GIOITINH);
        assertThat(testDmgiaiphaubenh.getMaBYT()).isEqualTo(DEFAULT_MA_BYT);
        assertThat(testDmgiaiphaubenh.getManhomBH()).isEqualTo(DEFAULT_MANHOM_BH);
    }

    @Test
    @Transactional
    public void createDmgiaiphaubenhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmgiaiphaubenhRepository.findAll().size();

        // Create the Dmgiaiphaubenh with an existing ID
        dmgiaiphaubenh.setId(1L);
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = dmgiaiphaubenhMapper.toDto(dmgiaiphaubenh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmgiaiphaubenhMockMvc.perform(post("/api/dmgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmgiaiphaubenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmgiaiphaubenh in the database
        List<Dmgiaiphaubenh> dmgiaiphaubenhList = dmgiaiphaubenhRepository.findAll();
        assertThat(dmgiaiphaubenhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhs() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmgiaiphaubenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));
    }

    @Test
    @Transactional
    public void getDmgiaiphaubenh() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get the dmgiaiphaubenh
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs/{id}", dmgiaiphaubenh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmgiaiphaubenh.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.gioitinh").value(DEFAULT_GIOITINH))
            .andExpect(jsonPath("$.maBYT").value(DEFAULT_MA_BYT))
            .andExpect(jsonPath("$.manhomBH").value(DEFAULT_MANHOM_BH));
    }


    @Test
    @Transactional
    public void getDmgiaiphaubenhsByIdFiltering() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        Long id = dmgiaiphaubenh.getId();

        defaultDmgiaiphaubenhShouldBeFound("id.equals=" + id);
        defaultDmgiaiphaubenhShouldNotBeFound("id.notEquals=" + id);

        defaultDmgiaiphaubenhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmgiaiphaubenhShouldNotBeFound("id.greaterThan=" + id);

        defaultDmgiaiphaubenhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmgiaiphaubenhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma equals to DEFAULT_MA
        defaultDmgiaiphaubenhShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmgiaiphaubenhList where ma equals to UPDATED_MA
        defaultDmgiaiphaubenhShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma not equals to DEFAULT_MA
        defaultDmgiaiphaubenhShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmgiaiphaubenhList where ma not equals to UPDATED_MA
        defaultDmgiaiphaubenhShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmgiaiphaubenhShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmgiaiphaubenhList where ma equals to UPDATED_MA
        defaultDmgiaiphaubenhShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma is not null
        defaultDmgiaiphaubenhShouldBeFound("ma.specified=true");

        // Get all the dmgiaiphaubenhList where ma is null
        defaultDmgiaiphaubenhShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma contains DEFAULT_MA
        defaultDmgiaiphaubenhShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmgiaiphaubenhList where ma contains UPDATED_MA
        defaultDmgiaiphaubenhShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ma does not contain DEFAULT_MA
        defaultDmgiaiphaubenhShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmgiaiphaubenhList where ma does not contain UPDATED_MA
        defaultDmgiaiphaubenhShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten equals to DEFAULT_TEN
        defaultDmgiaiphaubenhShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmgiaiphaubenhList where ten equals to UPDATED_TEN
        defaultDmgiaiphaubenhShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten not equals to DEFAULT_TEN
        defaultDmgiaiphaubenhShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmgiaiphaubenhList where ten not equals to UPDATED_TEN
        defaultDmgiaiphaubenhShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmgiaiphaubenhShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmgiaiphaubenhList where ten equals to UPDATED_TEN
        defaultDmgiaiphaubenhShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten is not null
        defaultDmgiaiphaubenhShouldBeFound("ten.specified=true");

        // Get all the dmgiaiphaubenhList where ten is null
        defaultDmgiaiphaubenhShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten contains DEFAULT_TEN
        defaultDmgiaiphaubenhShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmgiaiphaubenhList where ten contains UPDATED_TEN
        defaultDmgiaiphaubenhShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where ten does not contain DEFAULT_TEN
        defaultDmgiaiphaubenhShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmgiaiphaubenhList where ten does not contain UPDATED_TEN
        defaultDmgiaiphaubenhShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota equals to DEFAULT_MOTA
        defaultDmgiaiphaubenhShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmgiaiphaubenhList where mota equals to UPDATED_MOTA
        defaultDmgiaiphaubenhShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota not equals to DEFAULT_MOTA
        defaultDmgiaiphaubenhShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmgiaiphaubenhList where mota not equals to UPDATED_MOTA
        defaultDmgiaiphaubenhShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmgiaiphaubenhShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmgiaiphaubenhList where mota equals to UPDATED_MOTA
        defaultDmgiaiphaubenhShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota is not null
        defaultDmgiaiphaubenhShouldBeFound("mota.specified=true");

        // Get all the dmgiaiphaubenhList where mota is null
        defaultDmgiaiphaubenhShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota contains DEFAULT_MOTA
        defaultDmgiaiphaubenhShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmgiaiphaubenhList where mota contains UPDATED_MOTA
        defaultDmgiaiphaubenhShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where mota does not contain DEFAULT_MOTA
        defaultDmgiaiphaubenhShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmgiaiphaubenhList where mota does not contain UPDATED_MOTA
        defaultDmgiaiphaubenhShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh equals to DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.equals=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh equals to UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.equals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh not equals to DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.notEquals=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh not equals to UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.notEquals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh in DEFAULT_GIOITINH or UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.in=" + DEFAULT_GIOITINH + "," + UPDATED_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh equals to UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.in=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh is not null
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.specified=true");

        // Get all the dmgiaiphaubenhList where gioitinh is null
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh is greater than or equal to DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.greaterThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh is greater than or equal to UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.greaterThanOrEqual=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh is less than or equal to DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.lessThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh is less than or equal to SMALLER_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.lessThanOrEqual=" + SMALLER_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsLessThanSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh is less than DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.lessThan=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh is less than UPDATED_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.lessThan=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByGioitinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where gioitinh is greater than DEFAULT_GIOITINH
        defaultDmgiaiphaubenhShouldNotBeFound("gioitinh.greaterThan=" + DEFAULT_GIOITINH);

        // Get all the dmgiaiphaubenhList where gioitinh is greater than SMALLER_GIOITINH
        defaultDmgiaiphaubenhShouldBeFound("gioitinh.greaterThan=" + SMALLER_GIOITINH);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT equals to DEFAULT_MA_BYT
        defaultDmgiaiphaubenhShouldBeFound("maBYT.equals=" + DEFAULT_MA_BYT);

        // Get all the dmgiaiphaubenhList where maBYT equals to UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.equals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT not equals to DEFAULT_MA_BYT
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.notEquals=" + DEFAULT_MA_BYT);

        // Get all the dmgiaiphaubenhList where maBYT not equals to UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldBeFound("maBYT.notEquals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT in DEFAULT_MA_BYT or UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldBeFound("maBYT.in=" + DEFAULT_MA_BYT + "," + UPDATED_MA_BYT);

        // Get all the dmgiaiphaubenhList where maBYT equals to UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.in=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT is not null
        defaultDmgiaiphaubenhShouldBeFound("maBYT.specified=true");

        // Get all the dmgiaiphaubenhList where maBYT is null
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT contains DEFAULT_MA_BYT
        defaultDmgiaiphaubenhShouldBeFound("maBYT.contains=" + DEFAULT_MA_BYT);

        // Get all the dmgiaiphaubenhList where maBYT contains UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.contains=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByMaBYTNotContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where maBYT does not contain DEFAULT_MA_BYT
        defaultDmgiaiphaubenhShouldNotBeFound("maBYT.doesNotContain=" + DEFAULT_MA_BYT);

        // Get all the dmgiaiphaubenhList where maBYT does not contain UPDATED_MA_BYT
        defaultDmgiaiphaubenhShouldBeFound("maBYT.doesNotContain=" + UPDATED_MA_BYT);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH equals to DEFAULT_MANHOM_BH
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.equals=" + DEFAULT_MANHOM_BH);

        // Get all the dmgiaiphaubenhList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.equals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH not equals to DEFAULT_MANHOM_BH
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.notEquals=" + DEFAULT_MANHOM_BH);

        // Get all the dmgiaiphaubenhList where manhomBH not equals to UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.notEquals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH in DEFAULT_MANHOM_BH or UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.in=" + DEFAULT_MANHOM_BH + "," + UPDATED_MANHOM_BH);

        // Get all the dmgiaiphaubenhList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.in=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH is not null
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.specified=true");

        // Get all the dmgiaiphaubenhList where manhomBH is null
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH contains DEFAULT_MANHOM_BH
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.contains=" + DEFAULT_MANHOM_BH);

        // Get all the dmgiaiphaubenhList where manhomBH contains UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.contains=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByManhomBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        // Get all the dmgiaiphaubenhList where manhomBH does not contain DEFAULT_MANHOM_BH
        defaultDmgiaiphaubenhShouldNotBeFound("manhomBH.doesNotContain=" + DEFAULT_MANHOM_BH);

        // Get all the dmgiaiphaubenhList where manhomBH does not contain UPDATED_MANHOM_BH
        defaultDmgiaiphaubenhShouldBeFound("manhomBH.doesNotContain=" + UPDATED_MANHOM_BH);
    }


    @Test
    @Transactional
    public void getAllDmgiaiphaubenhsByDmnhomgiaiphaubenhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh = DmnhomgiaiphaubenhResourceIT.createEntity(em);
        em.persist(dmnhomgiaiphaubenh);
        em.flush();
        dmgiaiphaubenh.setDmnhomgiaiphaubenh(dmnhomgiaiphaubenh);
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);
        Long dmnhomgiaiphaubenhId = dmnhomgiaiphaubenh.getId();

        // Get all the dmgiaiphaubenhList where dmnhomgiaiphaubenh equals to dmnhomgiaiphaubenhId
        defaultDmgiaiphaubenhShouldBeFound("dmnhomgiaiphaubenhId.equals=" + dmnhomgiaiphaubenhId);

        // Get all the dmgiaiphaubenhList where dmnhomgiaiphaubenh equals to dmnhomgiaiphaubenhId + 1
        defaultDmgiaiphaubenhShouldNotBeFound("dmnhomgiaiphaubenhId.equals=" + (dmnhomgiaiphaubenhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmgiaiphaubenhShouldBeFound(String filter) throws Exception {
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmgiaiphaubenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));

        // Check, that the count call also returns 1
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmgiaiphaubenhShouldNotBeFound(String filter) throws Exception {
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmgiaiphaubenh() throws Exception {
        // Get the dmgiaiphaubenh
        restDmgiaiphaubenhMockMvc.perform(get("/api/dmgiaiphaubenhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmgiaiphaubenh() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        int databaseSizeBeforeUpdate = dmgiaiphaubenhRepository.findAll().size();

        // Update the dmgiaiphaubenh
        Dmgiaiphaubenh updatedDmgiaiphaubenh = dmgiaiphaubenhRepository.findById(dmgiaiphaubenh.getId()).get();
        // Disconnect from session so that the updates on updatedDmgiaiphaubenh are not directly saved in db
        em.detach(updatedDmgiaiphaubenh);
        updatedDmgiaiphaubenh
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = dmgiaiphaubenhMapper.toDto(updatedDmgiaiphaubenh);

        restDmgiaiphaubenhMockMvc.perform(put("/api/dmgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmgiaiphaubenhDTO)))
            .andExpect(status().isOk());

        // Validate the Dmgiaiphaubenh in the database
        List<Dmgiaiphaubenh> dmgiaiphaubenhList = dmgiaiphaubenhRepository.findAll();
        assertThat(dmgiaiphaubenhList).hasSize(databaseSizeBeforeUpdate);
        Dmgiaiphaubenh testDmgiaiphaubenh = dmgiaiphaubenhList.get(dmgiaiphaubenhList.size() - 1);
        assertThat(testDmgiaiphaubenh.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmgiaiphaubenh.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmgiaiphaubenh.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDmgiaiphaubenh.getGioitinh()).isEqualTo(UPDATED_GIOITINH);
        assertThat(testDmgiaiphaubenh.getMaBYT()).isEqualTo(UPDATED_MA_BYT);
        assertThat(testDmgiaiphaubenh.getManhomBH()).isEqualTo(UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmgiaiphaubenh() throws Exception {
        int databaseSizeBeforeUpdate = dmgiaiphaubenhRepository.findAll().size();

        // Create the Dmgiaiphaubenh
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = dmgiaiphaubenhMapper.toDto(dmgiaiphaubenh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmgiaiphaubenhMockMvc.perform(put("/api/dmgiaiphaubenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmgiaiphaubenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmgiaiphaubenh in the database
        List<Dmgiaiphaubenh> dmgiaiphaubenhList = dmgiaiphaubenhRepository.findAll();
        assertThat(dmgiaiphaubenhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmgiaiphaubenh() throws Exception {
        // Initialize the database
        dmgiaiphaubenhRepository.saveAndFlush(dmgiaiphaubenh);

        int databaseSizeBeforeDelete = dmgiaiphaubenhRepository.findAll().size();

        // Delete the dmgiaiphaubenh
        restDmgiaiphaubenhMockMvc.perform(delete("/api/dmgiaiphaubenhs/{id}", dmgiaiphaubenh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmgiaiphaubenh> dmgiaiphaubenhList = dmgiaiphaubenhRepository.findAll();
        assertThat(dmgiaiphaubenhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
