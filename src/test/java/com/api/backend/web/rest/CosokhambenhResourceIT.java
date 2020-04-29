package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Cosokhambenh;
import com.api.backend.domain.Tinh;
import com.api.backend.domain.Huyen;
import com.api.backend.domain.Xa;
import com.api.backend.repository.CosokhambenhRepository;
import com.api.backend.service.CosokhambenhService;
import com.api.backend.service.dto.CosokhambenhDTO;
import com.api.backend.service.mapper.CosokhambenhMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.CosokhambenhCriteria;
import com.api.backend.service.CosokhambenhQueryService;

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
 * Integration tests for the {@link CosokhambenhResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class CosokhambenhResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_KCBBD = "AAAAAAAAAA";
    private static final String UPDATED_MA_KCBBD = "BBBBBBBBBB";

    private static final String DEFAULT_HANG = "AAAAAAAAAA";
    private static final String UPDATED_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_TUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_LOAI = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI = "BBBBBBBBBB";

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    @Autowired
    private CosokhambenhRepository cosokhambenhRepository;

    @Autowired
    private CosokhambenhMapper cosokhambenhMapper;

    @Autowired
    private CosokhambenhService cosokhambenhService;

    @Autowired
    private CosokhambenhQueryService cosokhambenhQueryService;

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

    private MockMvc restCosokhambenhMockMvc;

    private Cosokhambenh cosokhambenh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CosokhambenhResource cosokhambenhResource = new CosokhambenhResource(cosokhambenhService, cosokhambenhQueryService);
        this.restCosokhambenhMockMvc = MockMvcBuilders.standaloneSetup(cosokhambenhResource)
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
    public static Cosokhambenh createEntity(EntityManager em) {
        Cosokhambenh cosokhambenh = new Cosokhambenh()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .maKCBBD(DEFAULT_MA_KCBBD)
            .hang(DEFAULT_HANG)
            .tuyen(DEFAULT_TUYEN)
            .loai(DEFAULT_LOAI)
            .diachi(DEFAULT_DIACHI)
            .ghichu(DEFAULT_GHICHU);
        return cosokhambenh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cosokhambenh createUpdatedEntity(EntityManager em) {
        Cosokhambenh cosokhambenh = new Cosokhambenh()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .maKCBBD(UPDATED_MA_KCBBD)
            .hang(UPDATED_HANG)
            .tuyen(UPDATED_TUYEN)
            .loai(UPDATED_LOAI)
            .diachi(UPDATED_DIACHI)
            .ghichu(UPDATED_GHICHU);
        return cosokhambenh;
    }

    @BeforeEach
    public void initTest() {
        cosokhambenh = createEntity(em);
    }

    @Test
    @Transactional
    public void createCosokhambenh() throws Exception {
        int databaseSizeBeforeCreate = cosokhambenhRepository.findAll().size();

        // Create the Cosokhambenh
        CosokhambenhDTO cosokhambenhDTO = cosokhambenhMapper.toDto(cosokhambenh);
        restCosokhambenhMockMvc.perform(post("/api/cosokhambenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cosokhambenhDTO)))
            .andExpect(status().isCreated());

        // Validate the Cosokhambenh in the database
        List<Cosokhambenh> cosokhambenhList = cosokhambenhRepository.findAll();
        assertThat(cosokhambenhList).hasSize(databaseSizeBeforeCreate + 1);
        Cosokhambenh testCosokhambenh = cosokhambenhList.get(cosokhambenhList.size() - 1);
        assertThat(testCosokhambenh.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testCosokhambenh.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testCosokhambenh.getMaKCBBD()).isEqualTo(DEFAULT_MA_KCBBD);
        assertThat(testCosokhambenh.getHang()).isEqualTo(DEFAULT_HANG);
        assertThat(testCosokhambenh.getTuyen()).isEqualTo(DEFAULT_TUYEN);
        assertThat(testCosokhambenh.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testCosokhambenh.getDiachi()).isEqualTo(DEFAULT_DIACHI);
        assertThat(testCosokhambenh.getGhichu()).isEqualTo(DEFAULT_GHICHU);
    }

    @Test
    @Transactional
    public void createCosokhambenhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cosokhambenhRepository.findAll().size();

        // Create the Cosokhambenh with an existing ID
        cosokhambenh.setId(1L);
        CosokhambenhDTO cosokhambenhDTO = cosokhambenhMapper.toDto(cosokhambenh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCosokhambenhMockMvc.perform(post("/api/cosokhambenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cosokhambenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cosokhambenh in the database
        List<Cosokhambenh> cosokhambenhList = cosokhambenhRepository.findAll();
        assertThat(cosokhambenhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhs() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cosokhambenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].maKCBBD").value(hasItem(DEFAULT_MA_KCBBD)))
            .andExpect(jsonPath("$.[*].hang").value(hasItem(DEFAULT_HANG)))
            .andExpect(jsonPath("$.[*].tuyen").value(hasItem(DEFAULT_TUYEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)));
    }
    
    @Test
    @Transactional
    public void getCosokhambenh() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get the cosokhambenh
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs/{id}", cosokhambenh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cosokhambenh.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.maKCBBD").value(DEFAULT_MA_KCBBD))
            .andExpect(jsonPath("$.hang").value(DEFAULT_HANG))
            .andExpect(jsonPath("$.tuyen").value(DEFAULT_TUYEN))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.diachi").value(DEFAULT_DIACHI))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU));
    }


    @Test
    @Transactional
    public void getCosokhambenhsByIdFiltering() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        Long id = cosokhambenh.getId();

        defaultCosokhambenhShouldBeFound("id.equals=" + id);
        defaultCosokhambenhShouldNotBeFound("id.notEquals=" + id);

        defaultCosokhambenhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCosokhambenhShouldNotBeFound("id.greaterThan=" + id);

        defaultCosokhambenhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCosokhambenhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma equals to DEFAULT_MA
        defaultCosokhambenhShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the cosokhambenhList where ma equals to UPDATED_MA
        defaultCosokhambenhShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma not equals to DEFAULT_MA
        defaultCosokhambenhShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the cosokhambenhList where ma not equals to UPDATED_MA
        defaultCosokhambenhShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma in DEFAULT_MA or UPDATED_MA
        defaultCosokhambenhShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the cosokhambenhList where ma equals to UPDATED_MA
        defaultCosokhambenhShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma is not null
        defaultCosokhambenhShouldBeFound("ma.specified=true");

        // Get all the cosokhambenhList where ma is null
        defaultCosokhambenhShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByMaContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma contains DEFAULT_MA
        defaultCosokhambenhShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the cosokhambenhList where ma contains UPDATED_MA
        defaultCosokhambenhShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ma does not contain DEFAULT_MA
        defaultCosokhambenhShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the cosokhambenhList where ma does not contain UPDATED_MA
        defaultCosokhambenhShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten equals to DEFAULT_TEN
        defaultCosokhambenhShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the cosokhambenhList where ten equals to UPDATED_TEN
        defaultCosokhambenhShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten not equals to DEFAULT_TEN
        defaultCosokhambenhShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the cosokhambenhList where ten not equals to UPDATED_TEN
        defaultCosokhambenhShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultCosokhambenhShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the cosokhambenhList where ten equals to UPDATED_TEN
        defaultCosokhambenhShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten is not null
        defaultCosokhambenhShouldBeFound("ten.specified=true");

        // Get all the cosokhambenhList where ten is null
        defaultCosokhambenhShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByTenContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten contains DEFAULT_TEN
        defaultCosokhambenhShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the cosokhambenhList where ten contains UPDATED_TEN
        defaultCosokhambenhShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ten does not contain DEFAULT_TEN
        defaultCosokhambenhShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the cosokhambenhList where ten does not contain UPDATED_TEN
        defaultCosokhambenhShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD equals to DEFAULT_MA_KCBBD
        defaultCosokhambenhShouldBeFound("maKCBBD.equals=" + DEFAULT_MA_KCBBD);

        // Get all the cosokhambenhList where maKCBBD equals to UPDATED_MA_KCBBD
        defaultCosokhambenhShouldNotBeFound("maKCBBD.equals=" + UPDATED_MA_KCBBD);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD not equals to DEFAULT_MA_KCBBD
        defaultCosokhambenhShouldNotBeFound("maKCBBD.notEquals=" + DEFAULT_MA_KCBBD);

        // Get all the cosokhambenhList where maKCBBD not equals to UPDATED_MA_KCBBD
        defaultCosokhambenhShouldBeFound("maKCBBD.notEquals=" + UPDATED_MA_KCBBD);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD in DEFAULT_MA_KCBBD or UPDATED_MA_KCBBD
        defaultCosokhambenhShouldBeFound("maKCBBD.in=" + DEFAULT_MA_KCBBD + "," + UPDATED_MA_KCBBD);

        // Get all the cosokhambenhList where maKCBBD equals to UPDATED_MA_KCBBD
        defaultCosokhambenhShouldNotBeFound("maKCBBD.in=" + UPDATED_MA_KCBBD);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD is not null
        defaultCosokhambenhShouldBeFound("maKCBBD.specified=true");

        // Get all the cosokhambenhList where maKCBBD is null
        defaultCosokhambenhShouldNotBeFound("maKCBBD.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD contains DEFAULT_MA_KCBBD
        defaultCosokhambenhShouldBeFound("maKCBBD.contains=" + DEFAULT_MA_KCBBD);

        // Get all the cosokhambenhList where maKCBBD contains UPDATED_MA_KCBBD
        defaultCosokhambenhShouldNotBeFound("maKCBBD.contains=" + UPDATED_MA_KCBBD);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByMaKCBBDNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where maKCBBD does not contain DEFAULT_MA_KCBBD
        defaultCosokhambenhShouldNotBeFound("maKCBBD.doesNotContain=" + DEFAULT_MA_KCBBD);

        // Get all the cosokhambenhList where maKCBBD does not contain UPDATED_MA_KCBBD
        defaultCosokhambenhShouldBeFound("maKCBBD.doesNotContain=" + UPDATED_MA_KCBBD);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByHangIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang equals to DEFAULT_HANG
        defaultCosokhambenhShouldBeFound("hang.equals=" + DEFAULT_HANG);

        // Get all the cosokhambenhList where hang equals to UPDATED_HANG
        defaultCosokhambenhShouldNotBeFound("hang.equals=" + UPDATED_HANG);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByHangIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang not equals to DEFAULT_HANG
        defaultCosokhambenhShouldNotBeFound("hang.notEquals=" + DEFAULT_HANG);

        // Get all the cosokhambenhList where hang not equals to UPDATED_HANG
        defaultCosokhambenhShouldBeFound("hang.notEquals=" + UPDATED_HANG);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByHangIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang in DEFAULT_HANG or UPDATED_HANG
        defaultCosokhambenhShouldBeFound("hang.in=" + DEFAULT_HANG + "," + UPDATED_HANG);

        // Get all the cosokhambenhList where hang equals to UPDATED_HANG
        defaultCosokhambenhShouldNotBeFound("hang.in=" + UPDATED_HANG);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByHangIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang is not null
        defaultCosokhambenhShouldBeFound("hang.specified=true");

        // Get all the cosokhambenhList where hang is null
        defaultCosokhambenhShouldNotBeFound("hang.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByHangContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang contains DEFAULT_HANG
        defaultCosokhambenhShouldBeFound("hang.contains=" + DEFAULT_HANG);

        // Get all the cosokhambenhList where hang contains UPDATED_HANG
        defaultCosokhambenhShouldNotBeFound("hang.contains=" + UPDATED_HANG);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByHangNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where hang does not contain DEFAULT_HANG
        defaultCosokhambenhShouldNotBeFound("hang.doesNotContain=" + DEFAULT_HANG);

        // Get all the cosokhambenhList where hang does not contain UPDATED_HANG
        defaultCosokhambenhShouldBeFound("hang.doesNotContain=" + UPDATED_HANG);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen equals to DEFAULT_TUYEN
        defaultCosokhambenhShouldBeFound("tuyen.equals=" + DEFAULT_TUYEN);

        // Get all the cosokhambenhList where tuyen equals to UPDATED_TUYEN
        defaultCosokhambenhShouldNotBeFound("tuyen.equals=" + UPDATED_TUYEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen not equals to DEFAULT_TUYEN
        defaultCosokhambenhShouldNotBeFound("tuyen.notEquals=" + DEFAULT_TUYEN);

        // Get all the cosokhambenhList where tuyen not equals to UPDATED_TUYEN
        defaultCosokhambenhShouldBeFound("tuyen.notEquals=" + UPDATED_TUYEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen in DEFAULT_TUYEN or UPDATED_TUYEN
        defaultCosokhambenhShouldBeFound("tuyen.in=" + DEFAULT_TUYEN + "," + UPDATED_TUYEN);

        // Get all the cosokhambenhList where tuyen equals to UPDATED_TUYEN
        defaultCosokhambenhShouldNotBeFound("tuyen.in=" + UPDATED_TUYEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen is not null
        defaultCosokhambenhShouldBeFound("tuyen.specified=true");

        // Get all the cosokhambenhList where tuyen is null
        defaultCosokhambenhShouldNotBeFound("tuyen.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen contains DEFAULT_TUYEN
        defaultCosokhambenhShouldBeFound("tuyen.contains=" + DEFAULT_TUYEN);

        // Get all the cosokhambenhList where tuyen contains UPDATED_TUYEN
        defaultCosokhambenhShouldNotBeFound("tuyen.contains=" + UPDATED_TUYEN);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByTuyenNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where tuyen does not contain DEFAULT_TUYEN
        defaultCosokhambenhShouldNotBeFound("tuyen.doesNotContain=" + DEFAULT_TUYEN);

        // Get all the cosokhambenhList where tuyen does not contain UPDATED_TUYEN
        defaultCosokhambenhShouldBeFound("tuyen.doesNotContain=" + UPDATED_TUYEN);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai equals to DEFAULT_LOAI
        defaultCosokhambenhShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the cosokhambenhList where loai equals to UPDATED_LOAI
        defaultCosokhambenhShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai not equals to DEFAULT_LOAI
        defaultCosokhambenhShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the cosokhambenhList where loai not equals to UPDATED_LOAI
        defaultCosokhambenhShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultCosokhambenhShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the cosokhambenhList where loai equals to UPDATED_LOAI
        defaultCosokhambenhShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai is not null
        defaultCosokhambenhShouldBeFound("loai.specified=true");

        // Get all the cosokhambenhList where loai is null
        defaultCosokhambenhShouldNotBeFound("loai.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai contains DEFAULT_LOAI
        defaultCosokhambenhShouldBeFound("loai.contains=" + DEFAULT_LOAI);

        // Get all the cosokhambenhList where loai contains UPDATED_LOAI
        defaultCosokhambenhShouldNotBeFound("loai.contains=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByLoaiNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where loai does not contain DEFAULT_LOAI
        defaultCosokhambenhShouldNotBeFound("loai.doesNotContain=" + DEFAULT_LOAI);

        // Get all the cosokhambenhList where loai does not contain UPDATED_LOAI
        defaultCosokhambenhShouldBeFound("loai.doesNotContain=" + UPDATED_LOAI);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi equals to DEFAULT_DIACHI
        defaultCosokhambenhShouldBeFound("diachi.equals=" + DEFAULT_DIACHI);

        // Get all the cosokhambenhList where diachi equals to UPDATED_DIACHI
        defaultCosokhambenhShouldNotBeFound("diachi.equals=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi not equals to DEFAULT_DIACHI
        defaultCosokhambenhShouldNotBeFound("diachi.notEquals=" + DEFAULT_DIACHI);

        // Get all the cosokhambenhList where diachi not equals to UPDATED_DIACHI
        defaultCosokhambenhShouldBeFound("diachi.notEquals=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi in DEFAULT_DIACHI or UPDATED_DIACHI
        defaultCosokhambenhShouldBeFound("diachi.in=" + DEFAULT_DIACHI + "," + UPDATED_DIACHI);

        // Get all the cosokhambenhList where diachi equals to UPDATED_DIACHI
        defaultCosokhambenhShouldNotBeFound("diachi.in=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi is not null
        defaultCosokhambenhShouldBeFound("diachi.specified=true");

        // Get all the cosokhambenhList where diachi is null
        defaultCosokhambenhShouldNotBeFound("diachi.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi contains DEFAULT_DIACHI
        defaultCosokhambenhShouldBeFound("diachi.contains=" + DEFAULT_DIACHI);

        // Get all the cosokhambenhList where diachi contains UPDATED_DIACHI
        defaultCosokhambenhShouldNotBeFound("diachi.contains=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByDiachiNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where diachi does not contain DEFAULT_DIACHI
        defaultCosokhambenhShouldNotBeFound("diachi.doesNotContain=" + DEFAULT_DIACHI);

        // Get all the cosokhambenhList where diachi does not contain UPDATED_DIACHI
        defaultCosokhambenhShouldBeFound("diachi.doesNotContain=" + UPDATED_DIACHI);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu equals to DEFAULT_GHICHU
        defaultCosokhambenhShouldBeFound("ghichu.equals=" + DEFAULT_GHICHU);

        // Get all the cosokhambenhList where ghichu equals to UPDATED_GHICHU
        defaultCosokhambenhShouldNotBeFound("ghichu.equals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu not equals to DEFAULT_GHICHU
        defaultCosokhambenhShouldNotBeFound("ghichu.notEquals=" + DEFAULT_GHICHU);

        // Get all the cosokhambenhList where ghichu not equals to UPDATED_GHICHU
        defaultCosokhambenhShouldBeFound("ghichu.notEquals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuIsInShouldWork() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu in DEFAULT_GHICHU or UPDATED_GHICHU
        defaultCosokhambenhShouldBeFound("ghichu.in=" + DEFAULT_GHICHU + "," + UPDATED_GHICHU);

        // Get all the cosokhambenhList where ghichu equals to UPDATED_GHICHU
        defaultCosokhambenhShouldNotBeFound("ghichu.in=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuIsNullOrNotNull() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu is not null
        defaultCosokhambenhShouldBeFound("ghichu.specified=true");

        // Get all the cosokhambenhList where ghichu is null
        defaultCosokhambenhShouldNotBeFound("ghichu.specified=false");
    }
                @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu contains DEFAULT_GHICHU
        defaultCosokhambenhShouldBeFound("ghichu.contains=" + DEFAULT_GHICHU);

        // Get all the cosokhambenhList where ghichu contains UPDATED_GHICHU
        defaultCosokhambenhShouldNotBeFound("ghichu.contains=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllCosokhambenhsByGhichuNotContainsSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        // Get all the cosokhambenhList where ghichu does not contain DEFAULT_GHICHU
        defaultCosokhambenhShouldNotBeFound("ghichu.doesNotContain=" + DEFAULT_GHICHU);

        // Get all the cosokhambenhList where ghichu does not contain UPDATED_GHICHU
        defaultCosokhambenhShouldBeFound("ghichu.doesNotContain=" + UPDATED_GHICHU);
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Tinh tinh = TinhResourceIT.createEntity(em);
        em.persist(tinh);
        em.flush();
        cosokhambenh.setTinh(tinh);
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Long tinhId = tinh.getId();

        // Get all the cosokhambenhList where tinh equals to tinhId
        defaultCosokhambenhShouldBeFound("tinhId.equals=" + tinhId);

        // Get all the cosokhambenhList where tinh equals to tinhId + 1
        defaultCosokhambenhShouldNotBeFound("tinhId.equals=" + (tinhId + 1));
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Huyen huyen = HuyenResourceIT.createEntity(em);
        em.persist(huyen);
        em.flush();
        cosokhambenh.setHuyen(huyen);
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Long huyenId = huyen.getId();

        // Get all the cosokhambenhList where huyen equals to huyenId
        defaultCosokhambenhShouldBeFound("huyenId.equals=" + huyenId);

        // Get all the cosokhambenhList where huyen equals to huyenId + 1
        defaultCosokhambenhShouldNotBeFound("huyenId.equals=" + (huyenId + 1));
    }


    @Test
    @Transactional
    public void getAllCosokhambenhsByXaIsEqualToSomething() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Xa xa = XaResourceIT.createEntity(em);
        em.persist(xa);
        em.flush();
        cosokhambenh.setXa(xa);
        cosokhambenhRepository.saveAndFlush(cosokhambenh);
        Long xaId = xa.getId();

        // Get all the cosokhambenhList where xa equals to xaId
        defaultCosokhambenhShouldBeFound("xaId.equals=" + xaId);

        // Get all the cosokhambenhList where xa equals to xaId + 1
        defaultCosokhambenhShouldNotBeFound("xaId.equals=" + (xaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCosokhambenhShouldBeFound(String filter) throws Exception {
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cosokhambenh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].maKCBBD").value(hasItem(DEFAULT_MA_KCBBD)))
            .andExpect(jsonPath("$.[*].hang").value(hasItem(DEFAULT_HANG)))
            .andExpect(jsonPath("$.[*].tuyen").value(hasItem(DEFAULT_TUYEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)));

        // Check, that the count call also returns 1
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCosokhambenhShouldNotBeFound(String filter) throws Exception {
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCosokhambenh() throws Exception {
        // Get the cosokhambenh
        restCosokhambenhMockMvc.perform(get("/api/cosokhambenhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCosokhambenh() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        int databaseSizeBeforeUpdate = cosokhambenhRepository.findAll().size();

        // Update the cosokhambenh
        Cosokhambenh updatedCosokhambenh = cosokhambenhRepository.findById(cosokhambenh.getId()).get();
        // Disconnect from session so that the updates on updatedCosokhambenh are not directly saved in db
        em.detach(updatedCosokhambenh);
        updatedCosokhambenh
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .maKCBBD(UPDATED_MA_KCBBD)
            .hang(UPDATED_HANG)
            .tuyen(UPDATED_TUYEN)
            .loai(UPDATED_LOAI)
            .diachi(UPDATED_DIACHI)
            .ghichu(UPDATED_GHICHU);
        CosokhambenhDTO cosokhambenhDTO = cosokhambenhMapper.toDto(updatedCosokhambenh);

        restCosokhambenhMockMvc.perform(put("/api/cosokhambenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cosokhambenhDTO)))
            .andExpect(status().isOk());

        // Validate the Cosokhambenh in the database
        List<Cosokhambenh> cosokhambenhList = cosokhambenhRepository.findAll();
        assertThat(cosokhambenhList).hasSize(databaseSizeBeforeUpdate);
        Cosokhambenh testCosokhambenh = cosokhambenhList.get(cosokhambenhList.size() - 1);
        assertThat(testCosokhambenh.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testCosokhambenh.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testCosokhambenh.getMaKCBBD()).isEqualTo(UPDATED_MA_KCBBD);
        assertThat(testCosokhambenh.getHang()).isEqualTo(UPDATED_HANG);
        assertThat(testCosokhambenh.getTuyen()).isEqualTo(UPDATED_TUYEN);
        assertThat(testCosokhambenh.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testCosokhambenh.getDiachi()).isEqualTo(UPDATED_DIACHI);
        assertThat(testCosokhambenh.getGhichu()).isEqualTo(UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void updateNonExistingCosokhambenh() throws Exception {
        int databaseSizeBeforeUpdate = cosokhambenhRepository.findAll().size();

        // Create the Cosokhambenh
        CosokhambenhDTO cosokhambenhDTO = cosokhambenhMapper.toDto(cosokhambenh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCosokhambenhMockMvc.perform(put("/api/cosokhambenhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cosokhambenhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cosokhambenh in the database
        List<Cosokhambenh> cosokhambenhList = cosokhambenhRepository.findAll();
        assertThat(cosokhambenhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCosokhambenh() throws Exception {
        // Initialize the database
        cosokhambenhRepository.saveAndFlush(cosokhambenh);

        int databaseSizeBeforeDelete = cosokhambenhRepository.findAll().size();

        // Delete the cosokhambenh
        restCosokhambenhMockMvc.perform(delete("/api/cosokhambenhs/{id}", cosokhambenh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cosokhambenh> cosokhambenhList = cosokhambenhRepository.findAll();
        assertThat(cosokhambenhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
