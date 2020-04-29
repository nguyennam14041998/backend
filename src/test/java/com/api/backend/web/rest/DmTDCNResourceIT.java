package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.DmTDCN;
import com.api.backend.domain.DmnhomTDCN;
import com.api.backend.repository.DmTDCNRepository;
import com.api.backend.service.DmTDCNQueryService;
import com.api.backend.service.DmTDCNService;
import com.api.backend.service.dto.DmTDCNDTO;
import com.api.backend.service.mapper.MyDanhMucTDCNMapper;
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
 * Integration tests for the {@link DmTDCNResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmTDCNResourceIT {

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
    private DmTDCNRepository dmTDCNRepository;

    @Autowired
    private MyDanhMucTDCNMapper dmTDCNMapper;

    @Autowired
    private DmTDCNService dmTDCNService;

    @Autowired
    private DmTDCNQueryService dmTDCNQueryService;

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

    private MockMvc restDmTDCNMockMvc;

    private DmTDCN dmTDCN;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmTDCNResource dmTDCNResource = new DmTDCNResource(dmTDCNService, dmTDCNQueryService);
        this.restDmTDCNMockMvc = MockMvcBuilders.standaloneSetup(dmTDCNResource)
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
    public static DmTDCN createEntity(EntityManager em) {
        DmTDCN dmTDCN = new DmTDCN()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .gioitinh(DEFAULT_GIOITINH)
            .maBYT(DEFAULT_MA_BYT)
            .manhomBH(DEFAULT_MANHOM_BH);
        return dmTDCN;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmTDCN createUpdatedEntity(EntityManager em) {
        DmTDCN dmTDCN = new DmTDCN()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        return dmTDCN;
    }

    @BeforeEach
    public void initTest() {
        dmTDCN = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmTDCN() throws Exception {
        int databaseSizeBeforeCreate = dmTDCNRepository.findAll().size();

        // Create the DmTDCN
        DmTDCNDTO dmTDCNDTO = dmTDCNMapper.toDto(dmTDCN);
        restDmTDCNMockMvc.perform(post("/api/dm-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmTDCNDTO)))
            .andExpect(status().isCreated());

        // Validate the DmTDCN in the database
        List<DmTDCN> dmTDCNList = dmTDCNRepository.findAll();
        assertThat(dmTDCNList).hasSize(databaseSizeBeforeCreate + 1);
        DmTDCN testDmTDCN = dmTDCNList.get(dmTDCNList.size() - 1);
        assertThat(testDmTDCN.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmTDCN.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmTDCN.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDmTDCN.getGioitinh()).isEqualTo(DEFAULT_GIOITINH);
        assertThat(testDmTDCN.getMaBYT()).isEqualTo(DEFAULT_MA_BYT);
        assertThat(testDmTDCN.getManhomBH()).isEqualTo(DEFAULT_MANHOM_BH);
    }

    @Test
    @Transactional
    public void createDmTDCNWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmTDCNRepository.findAll().size();

        // Create the DmTDCN with an existing ID
        dmTDCN.setId(1L);
        DmTDCNDTO dmTDCNDTO = dmTDCNMapper.toDto(dmTDCN);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmTDCNMockMvc.perform(post("/api/dm-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmTDCNDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmTDCN in the database
        List<DmTDCN> dmTDCNList = dmTDCNRepository.findAll();
        assertThat(dmTDCNList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmTDCNS() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmTDCN.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));
    }

    @Test
    @Transactional
    public void getDmTDCN() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get the dmTDCN
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns/{id}", dmTDCN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmTDCN.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.gioitinh").value(DEFAULT_GIOITINH))
            .andExpect(jsonPath("$.maBYT").value(DEFAULT_MA_BYT))
            .andExpect(jsonPath("$.manhomBH").value(DEFAULT_MANHOM_BH));
    }


    @Test
    @Transactional
    public void getDmTDCNSByIdFiltering() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        Long id = dmTDCN.getId();

        defaultDmTDCNShouldBeFound("id.equals=" + id);
        defaultDmTDCNShouldNotBeFound("id.notEquals=" + id);

        defaultDmTDCNShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmTDCNShouldNotBeFound("id.greaterThan=" + id);

        defaultDmTDCNShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmTDCNShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma equals to DEFAULT_MA
        defaultDmTDCNShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmTDCNList where ma equals to UPDATED_MA
        defaultDmTDCNShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma not equals to DEFAULT_MA
        defaultDmTDCNShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmTDCNList where ma not equals to UPDATED_MA
        defaultDmTDCNShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmTDCNShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmTDCNList where ma equals to UPDATED_MA
        defaultDmTDCNShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma is not null
        defaultDmTDCNShouldBeFound("ma.specified=true");

        // Get all the dmTDCNList where ma is null
        defaultDmTDCNShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmTDCNSByMaContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma contains DEFAULT_MA
        defaultDmTDCNShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmTDCNList where ma contains UPDATED_MA
        defaultDmTDCNShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ma does not contain DEFAULT_MA
        defaultDmTDCNShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmTDCNList where ma does not contain UPDATED_MA
        defaultDmTDCNShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten equals to DEFAULT_TEN
        defaultDmTDCNShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmTDCNList where ten equals to UPDATED_TEN
        defaultDmTDCNShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten not equals to DEFAULT_TEN
        defaultDmTDCNShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmTDCNList where ten not equals to UPDATED_TEN
        defaultDmTDCNShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmTDCNShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmTDCNList where ten equals to UPDATED_TEN
        defaultDmTDCNShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten is not null
        defaultDmTDCNShouldBeFound("ten.specified=true");

        // Get all the dmTDCNList where ten is null
        defaultDmTDCNShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmTDCNSByTenContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten contains DEFAULT_TEN
        defaultDmTDCNShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmTDCNList where ten contains UPDATED_TEN
        defaultDmTDCNShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where ten does not contain DEFAULT_TEN
        defaultDmTDCNShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmTDCNList where ten does not contain UPDATED_TEN
        defaultDmTDCNShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota equals to DEFAULT_MOTA
        defaultDmTDCNShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmTDCNList where mota equals to UPDATED_MOTA
        defaultDmTDCNShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota not equals to DEFAULT_MOTA
        defaultDmTDCNShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmTDCNList where mota not equals to UPDATED_MOTA
        defaultDmTDCNShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmTDCNShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmTDCNList where mota equals to UPDATED_MOTA
        defaultDmTDCNShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota is not null
        defaultDmTDCNShouldBeFound("mota.specified=true");

        // Get all the dmTDCNList where mota is null
        defaultDmTDCNShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmTDCNSByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota contains DEFAULT_MOTA
        defaultDmTDCNShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmTDCNList where mota contains UPDATED_MOTA
        defaultDmTDCNShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where mota does not contain DEFAULT_MOTA
        defaultDmTDCNShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmTDCNList where mota does not contain UPDATED_MOTA
        defaultDmTDCNShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh equals to DEFAULT_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.equals=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh equals to UPDATED_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.equals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh not equals to DEFAULT_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.notEquals=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh not equals to UPDATED_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.notEquals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh in DEFAULT_GIOITINH or UPDATED_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.in=" + DEFAULT_GIOITINH + "," + UPDATED_GIOITINH);

        // Get all the dmTDCNList where gioitinh equals to UPDATED_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.in=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh is not null
        defaultDmTDCNShouldBeFound("gioitinh.specified=true");

        // Get all the dmTDCNList where gioitinh is null
        defaultDmTDCNShouldNotBeFound("gioitinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh is greater than or equal to DEFAULT_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.greaterThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh is greater than or equal to UPDATED_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.greaterThanOrEqual=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh is less than or equal to DEFAULT_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.lessThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh is less than or equal to SMALLER_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.lessThanOrEqual=" + SMALLER_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsLessThanSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh is less than DEFAULT_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.lessThan=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh is less than UPDATED_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.lessThan=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByGioitinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where gioitinh is greater than DEFAULT_GIOITINH
        defaultDmTDCNShouldNotBeFound("gioitinh.greaterThan=" + DEFAULT_GIOITINH);

        // Get all the dmTDCNList where gioitinh is greater than SMALLER_GIOITINH
        defaultDmTDCNShouldBeFound("gioitinh.greaterThan=" + SMALLER_GIOITINH);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT equals to DEFAULT_MA_BYT
        defaultDmTDCNShouldBeFound("maBYT.equals=" + DEFAULT_MA_BYT);

        // Get all the dmTDCNList where maBYT equals to UPDATED_MA_BYT
        defaultDmTDCNShouldNotBeFound("maBYT.equals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT not equals to DEFAULT_MA_BYT
        defaultDmTDCNShouldNotBeFound("maBYT.notEquals=" + DEFAULT_MA_BYT);

        // Get all the dmTDCNList where maBYT not equals to UPDATED_MA_BYT
        defaultDmTDCNShouldBeFound("maBYT.notEquals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT in DEFAULT_MA_BYT or UPDATED_MA_BYT
        defaultDmTDCNShouldBeFound("maBYT.in=" + DEFAULT_MA_BYT + "," + UPDATED_MA_BYT);

        // Get all the dmTDCNList where maBYT equals to UPDATED_MA_BYT
        defaultDmTDCNShouldNotBeFound("maBYT.in=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT is not null
        defaultDmTDCNShouldBeFound("maBYT.specified=true");

        // Get all the dmTDCNList where maBYT is null
        defaultDmTDCNShouldNotBeFound("maBYT.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT contains DEFAULT_MA_BYT
        defaultDmTDCNShouldBeFound("maBYT.contains=" + DEFAULT_MA_BYT);

        // Get all the dmTDCNList where maBYT contains UPDATED_MA_BYT
        defaultDmTDCNShouldNotBeFound("maBYT.contains=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByMaBYTNotContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where maBYT does not contain DEFAULT_MA_BYT
        defaultDmTDCNShouldNotBeFound("maBYT.doesNotContain=" + DEFAULT_MA_BYT);

        // Get all the dmTDCNList where maBYT does not contain UPDATED_MA_BYT
        defaultDmTDCNShouldBeFound("maBYT.doesNotContain=" + UPDATED_MA_BYT);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH equals to DEFAULT_MANHOM_BH
        defaultDmTDCNShouldBeFound("manhomBH.equals=" + DEFAULT_MANHOM_BH);

        // Get all the dmTDCNList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmTDCNShouldNotBeFound("manhomBH.equals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH not equals to DEFAULT_MANHOM_BH
        defaultDmTDCNShouldNotBeFound("manhomBH.notEquals=" + DEFAULT_MANHOM_BH);

        // Get all the dmTDCNList where manhomBH not equals to UPDATED_MANHOM_BH
        defaultDmTDCNShouldBeFound("manhomBH.notEquals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH in DEFAULT_MANHOM_BH or UPDATED_MANHOM_BH
        defaultDmTDCNShouldBeFound("manhomBH.in=" + DEFAULT_MANHOM_BH + "," + UPDATED_MANHOM_BH);

        // Get all the dmTDCNList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmTDCNShouldNotBeFound("manhomBH.in=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH is not null
        defaultDmTDCNShouldBeFound("manhomBH.specified=true");

        // Get all the dmTDCNList where manhomBH is null
        defaultDmTDCNShouldNotBeFound("manhomBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH contains DEFAULT_MANHOM_BH
        defaultDmTDCNShouldBeFound("manhomBH.contains=" + DEFAULT_MANHOM_BH);

        // Get all the dmTDCNList where manhomBH contains UPDATED_MANHOM_BH
        defaultDmTDCNShouldNotBeFound("manhomBH.contains=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmTDCNSByManhomBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        // Get all the dmTDCNList where manhomBH does not contain DEFAULT_MANHOM_BH
        defaultDmTDCNShouldNotBeFound("manhomBH.doesNotContain=" + DEFAULT_MANHOM_BH);

        // Get all the dmTDCNList where manhomBH does not contain UPDATED_MANHOM_BH
        defaultDmTDCNShouldBeFound("manhomBH.doesNotContain=" + UPDATED_MANHOM_BH);
    }


    @Test
    @Transactional
    public void getAllDmTDCNSByDmnhomTDCNIsEqualToSomething() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);
        DmnhomTDCN dmnhomTDCN = DmnhomTDCNResourceIT.createEntity(em);
        em.persist(dmnhomTDCN);
        em.flush();
        dmTDCN.setDmnhomTDCN(dmnhomTDCN);
        dmTDCNRepository.saveAndFlush(dmTDCN);
        Long dmnhomTDCNId = dmnhomTDCN.getId();

        // Get all the dmTDCNList where dmnhomTDCN equals to dmnhomTDCNId
        defaultDmTDCNShouldBeFound("dmnhomTDCNId.equals=" + dmnhomTDCNId);

        // Get all the dmTDCNList where dmnhomTDCN equals to dmnhomTDCNId + 1
        defaultDmTDCNShouldNotBeFound("dmnhomTDCNId.equals=" + (dmnhomTDCNId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmTDCNShouldBeFound(String filter) throws Exception {
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmTDCN.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].maBYT").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));

        // Check, that the count call also returns 1
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmTDCNShouldNotBeFound(String filter) throws Exception {
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmTDCN() throws Exception {
        // Get the dmTDCN
        restDmTDCNMockMvc.perform(get("/api/dm-tdcns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmTDCN() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        int databaseSizeBeforeUpdate = dmTDCNRepository.findAll().size();

        // Update the dmTDCN
        DmTDCN updatedDmTDCN = dmTDCNRepository.findById(dmTDCN.getId()).get();
        // Disconnect from session so that the updates on updatedDmTDCN are not directly saved in db
        em.detach(updatedDmTDCN);
        updatedDmTDCN
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .maBYT(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        DmTDCNDTO dmTDCNDTO = dmTDCNMapper.toDto(updatedDmTDCN);

        restDmTDCNMockMvc.perform(put("/api/dm-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmTDCNDTO)))
            .andExpect(status().isOk());

        // Validate the DmTDCN in the database
        List<DmTDCN> dmTDCNList = dmTDCNRepository.findAll();
        assertThat(dmTDCNList).hasSize(databaseSizeBeforeUpdate);
        DmTDCN testDmTDCN = dmTDCNList.get(dmTDCNList.size() - 1);
        assertThat(testDmTDCN.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmTDCN.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmTDCN.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDmTDCN.getGioitinh()).isEqualTo(UPDATED_GIOITINH);
        assertThat(testDmTDCN.getMaBYT()).isEqualTo(UPDATED_MA_BYT);
        assertThat(testDmTDCN.getManhomBH()).isEqualTo(UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmTDCN() throws Exception {
        int databaseSizeBeforeUpdate = dmTDCNRepository.findAll().size();

        // Create the DmTDCN
        DmTDCNDTO dmTDCNDTO = dmTDCNMapper.toDto(dmTDCN);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmTDCNMockMvc.perform(put("/api/dm-tdcns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmTDCNDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmTDCN in the database
        List<DmTDCN> dmTDCNList = dmTDCNRepository.findAll();
        assertThat(dmTDCNList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmTDCN() throws Exception {
        // Initialize the database
        dmTDCNRepository.saveAndFlush(dmTDCN);

        int databaseSizeBeforeDelete = dmTDCNRepository.findAll().size();

        // Delete the dmTDCN
        restDmTDCNMockMvc.perform(delete("/api/dm-tdcns/{id}", dmTDCN.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DmTDCN> dmTDCNList = dmTDCNRepository.findAll();
        assertThat(dmTDCNList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
