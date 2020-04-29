package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Danhmucpttt;
import com.api.backend.domain.Danhmucnhompttt;
import com.api.backend.repository.DanhmucptttRepository;
import com.api.backend.service.DanhmucptttService;
import com.api.backend.service.dto.DanhmucptttDTO;
import com.api.backend.service.mapper.DanhmucptttMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DanhmucptttCriteria;
import com.api.backend.service.DanhmucptttQueryService;

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
 * Integration tests for the {@link DanhmucptttResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DanhmucptttResourceIT {

    private static final Integer DEFAULT_LOAI = 1;
    private static final Integer UPDATED_LOAI = 2;
    private static final Integer SMALLER_LOAI = 1 - 1;

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_BYT = "BBBBBBBBBB";

    @Autowired
    private DanhmucptttRepository danhmucptttRepository;

    @Autowired
    private DanhmucptttMapper danhmucptttMapper;

    @Autowired
    private DanhmucptttService danhmucptttService;

    @Autowired
    private DanhmucptttQueryService danhmucptttQueryService;

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

    private MockMvc restDanhmucptttMockMvc;

    private Danhmucpttt danhmucpttt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhmucptttResource danhmucptttResource = new DanhmucptttResource(danhmucptttService, danhmucptttQueryService);
        this.restDanhmucptttMockMvc = MockMvcBuilders.standaloneSetup(danhmucptttResource)
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
    public static Danhmucpttt createEntity(EntityManager em) {
        Danhmucpttt danhmucpttt = new Danhmucpttt()
            .loai(DEFAULT_LOAI)
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .maByt(DEFAULT_MA_BYT);
        return danhmucpttt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Danhmucpttt createUpdatedEntity(EntityManager em) {
        Danhmucpttt danhmucpttt = new Danhmucpttt()
            .loai(UPDATED_LOAI)
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .maByt(UPDATED_MA_BYT);
        return danhmucpttt;
    }

    @BeforeEach
    public void initTest() {
        danhmucpttt = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhmucpttt() throws Exception {
        int databaseSizeBeforeCreate = danhmucptttRepository.findAll().size();

        // Create the Danhmucpttt
        DanhmucptttDTO danhmucptttDTO = danhmucptttMapper.toDto(danhmucpttt);
        restDanhmucptttMockMvc.perform(post("/api/danhmucpttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucptttDTO)))
            .andExpect(status().isCreated());

        // Validate the Danhmucpttt in the database
        List<Danhmucpttt> danhmucptttList = danhmucptttRepository.findAll();
        assertThat(danhmucptttList).hasSize(databaseSizeBeforeCreate + 1);
        Danhmucpttt testDanhmucpttt = danhmucptttList.get(danhmucptttList.size() - 1);
        assertThat(testDanhmucpttt.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDanhmucpttt.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDanhmucpttt.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDanhmucpttt.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDanhmucpttt.getMaByt()).isEqualTo(DEFAULT_MA_BYT);
    }

    @Test
    @Transactional
    public void createDanhmucptttWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhmucptttRepository.findAll().size();

        // Create the Danhmucpttt with an existing ID
        danhmucpttt.setId(1L);
        DanhmucptttDTO danhmucptttDTO = danhmucptttMapper.toDto(danhmucpttt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhmucptttMockMvc.perform(post("/api/danhmucpttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucptttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucpttt in the database
        List<Danhmucpttt> danhmucptttList = danhmucptttRepository.findAll();
        assertThat(danhmucptttList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDanhmucpttts() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucpttt.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].maByt").value(hasItem(DEFAULT_MA_BYT)));
    }
    
    @Test
    @Transactional
    public void getDanhmucpttt() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get the danhmucpttt
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts/{id}", danhmucpttt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhmucpttt.getId().intValue()))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.maByt").value(DEFAULT_MA_BYT));
    }


    @Test
    @Transactional
    public void getDanhmucptttsByIdFiltering() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        Long id = danhmucpttt.getId();

        defaultDanhmucptttShouldBeFound("id.equals=" + id);
        defaultDanhmucptttShouldNotBeFound("id.notEquals=" + id);

        defaultDanhmucptttShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhmucptttShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhmucptttShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhmucptttShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai equals to DEFAULT_LOAI
        defaultDanhmucptttShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai equals to UPDATED_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai not equals to DEFAULT_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai not equals to UPDATED_LOAI
        defaultDanhmucptttShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDanhmucptttShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the danhmucptttList where loai equals to UPDATED_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai is not null
        defaultDanhmucptttShouldBeFound("loai.specified=true");

        // Get all the danhmucptttList where loai is null
        defaultDanhmucptttShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai is greater than or equal to DEFAULT_LOAI
        defaultDanhmucptttShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai is greater than or equal to UPDATED_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai is less than or equal to DEFAULT_LOAI
        defaultDanhmucptttShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai is less than or equal to SMALLER_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai is less than DEFAULT_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai is less than UPDATED_LOAI
        defaultDanhmucptttShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where loai is greater than DEFAULT_LOAI
        defaultDanhmucptttShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the danhmucptttList where loai is greater than SMALLER_LOAI
        defaultDanhmucptttShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma equals to DEFAULT_MA
        defaultDanhmucptttShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the danhmucptttList where ma equals to UPDATED_MA
        defaultDanhmucptttShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma not equals to DEFAULT_MA
        defaultDanhmucptttShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the danhmucptttList where ma not equals to UPDATED_MA
        defaultDanhmucptttShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma in DEFAULT_MA or UPDATED_MA
        defaultDanhmucptttShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the danhmucptttList where ma equals to UPDATED_MA
        defaultDanhmucptttShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma is not null
        defaultDanhmucptttShouldBeFound("ma.specified=true");

        // Get all the danhmucptttList where ma is null
        defaultDanhmucptttShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucptttsByMaContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma contains DEFAULT_MA
        defaultDanhmucptttShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the danhmucptttList where ma contains UPDATED_MA
        defaultDanhmucptttShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ma does not contain DEFAULT_MA
        defaultDanhmucptttShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the danhmucptttList where ma does not contain UPDATED_MA
        defaultDanhmucptttShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten equals to DEFAULT_TEN
        defaultDanhmucptttShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the danhmucptttList where ten equals to UPDATED_TEN
        defaultDanhmucptttShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten not equals to DEFAULT_TEN
        defaultDanhmucptttShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the danhmucptttList where ten not equals to UPDATED_TEN
        defaultDanhmucptttShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDanhmucptttShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the danhmucptttList where ten equals to UPDATED_TEN
        defaultDanhmucptttShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten is not null
        defaultDanhmucptttShouldBeFound("ten.specified=true");

        // Get all the danhmucptttList where ten is null
        defaultDanhmucptttShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucptttsByTenContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten contains DEFAULT_TEN
        defaultDanhmucptttShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the danhmucptttList where ten contains UPDATED_TEN
        defaultDanhmucptttShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where ten does not contain DEFAULT_TEN
        defaultDanhmucptttShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the danhmucptttList where ten does not contain UPDATED_TEN
        defaultDanhmucptttShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota equals to DEFAULT_MOTA
        defaultDanhmucptttShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the danhmucptttList where mota equals to UPDATED_MOTA
        defaultDanhmucptttShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota not equals to DEFAULT_MOTA
        defaultDanhmucptttShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the danhmucptttList where mota not equals to UPDATED_MOTA
        defaultDanhmucptttShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDanhmucptttShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the danhmucptttList where mota equals to UPDATED_MOTA
        defaultDanhmucptttShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota is not null
        defaultDanhmucptttShouldBeFound("mota.specified=true");

        // Get all the danhmucptttList where mota is null
        defaultDanhmucptttShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucptttsByMotaContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota contains DEFAULT_MOTA
        defaultDanhmucptttShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the danhmucptttList where mota contains UPDATED_MOTA
        defaultDanhmucptttShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where mota does not contain DEFAULT_MOTA
        defaultDanhmucptttShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the danhmucptttList where mota does not contain UPDATED_MOTA
        defaultDanhmucptttShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt equals to DEFAULT_MA_BYT
        defaultDanhmucptttShouldBeFound("maByt.equals=" + DEFAULT_MA_BYT);

        // Get all the danhmucptttList where maByt equals to UPDATED_MA_BYT
        defaultDanhmucptttShouldNotBeFound("maByt.equals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt not equals to DEFAULT_MA_BYT
        defaultDanhmucptttShouldNotBeFound("maByt.notEquals=" + DEFAULT_MA_BYT);

        // Get all the danhmucptttList where maByt not equals to UPDATED_MA_BYT
        defaultDanhmucptttShouldBeFound("maByt.notEquals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt in DEFAULT_MA_BYT or UPDATED_MA_BYT
        defaultDanhmucptttShouldBeFound("maByt.in=" + DEFAULT_MA_BYT + "," + UPDATED_MA_BYT);

        // Get all the danhmucptttList where maByt equals to UPDATED_MA_BYT
        defaultDanhmucptttShouldNotBeFound("maByt.in=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt is not null
        defaultDanhmucptttShouldBeFound("maByt.specified=true");

        // Get all the danhmucptttList where maByt is null
        defaultDanhmucptttShouldNotBeFound("maByt.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt contains DEFAULT_MA_BYT
        defaultDanhmucptttShouldBeFound("maByt.contains=" + DEFAULT_MA_BYT);

        // Get all the danhmucptttList where maByt contains UPDATED_MA_BYT
        defaultDanhmucptttShouldNotBeFound("maByt.contains=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDanhmucptttsByMaBytNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        // Get all the danhmucptttList where maByt does not contain DEFAULT_MA_BYT
        defaultDanhmucptttShouldNotBeFound("maByt.doesNotContain=" + DEFAULT_MA_BYT);

        // Get all the danhmucptttList where maByt does not contain UPDATED_MA_BYT
        defaultDanhmucptttShouldBeFound("maByt.doesNotContain=" + UPDATED_MA_BYT);
    }


    @Test
    @Transactional
    public void getAllDanhmucptttsByDanhmucnhomptttIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);
        Danhmucnhompttt danhmucnhompttt = DanhmucnhomptttResourceIT.createEntity(em);
        em.persist(danhmucnhompttt);
        em.flush();
        danhmucpttt.setDanhmucnhompttt(danhmucnhompttt);
        danhmucptttRepository.saveAndFlush(danhmucpttt);
        Long danhmucnhomptttId = danhmucnhompttt.getId();

        // Get all the danhmucptttList where danhmucnhompttt equals to danhmucnhomptttId
        defaultDanhmucptttShouldBeFound("danhmucnhomptttId.equals=" + danhmucnhomptttId);

        // Get all the danhmucptttList where danhmucnhompttt equals to danhmucnhomptttId + 1
        defaultDanhmucptttShouldNotBeFound("danhmucnhomptttId.equals=" + (danhmucnhomptttId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhmucptttShouldBeFound(String filter) throws Exception {
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucpttt.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].maByt").value(hasItem(DEFAULT_MA_BYT)));

        // Check, that the count call also returns 1
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhmucptttShouldNotBeFound(String filter) throws Exception {
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhmucpttt() throws Exception {
        // Get the danhmucpttt
        restDanhmucptttMockMvc.perform(get("/api/danhmucpttts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhmucpttt() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        int databaseSizeBeforeUpdate = danhmucptttRepository.findAll().size();

        // Update the danhmucpttt
        Danhmucpttt updatedDanhmucpttt = danhmucptttRepository.findById(danhmucpttt.getId()).get();
        // Disconnect from session so that the updates on updatedDanhmucpttt are not directly saved in db
        em.detach(updatedDanhmucpttt);
        updatedDanhmucpttt
            .loai(UPDATED_LOAI)
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .maByt(UPDATED_MA_BYT);
        DanhmucptttDTO danhmucptttDTO = danhmucptttMapper.toDto(updatedDanhmucpttt);

        restDanhmucptttMockMvc.perform(put("/api/danhmucpttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucptttDTO)))
            .andExpect(status().isOk());

        // Validate the Danhmucpttt in the database
        List<Danhmucpttt> danhmucptttList = danhmucptttRepository.findAll();
        assertThat(danhmucptttList).hasSize(databaseSizeBeforeUpdate);
        Danhmucpttt testDanhmucpttt = danhmucptttList.get(danhmucptttList.size() - 1);
        assertThat(testDanhmucpttt.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDanhmucpttt.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDanhmucpttt.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDanhmucpttt.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDanhmucpttt.getMaByt()).isEqualTo(UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhmucpttt() throws Exception {
        int databaseSizeBeforeUpdate = danhmucptttRepository.findAll().size();

        // Create the Danhmucpttt
        DanhmucptttDTO danhmucptttDTO = danhmucptttMapper.toDto(danhmucpttt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhmucptttMockMvc.perform(put("/api/danhmucpttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucptttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucpttt in the database
        List<Danhmucpttt> danhmucptttList = danhmucptttRepository.findAll();
        assertThat(danhmucptttList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhmucpttt() throws Exception {
        // Initialize the database
        danhmucptttRepository.saveAndFlush(danhmucpttt);

        int databaseSizeBeforeDelete = danhmucptttRepository.findAll().size();

        // Delete the danhmucpttt
        restDanhmucptttMockMvc.perform(delete("/api/danhmucpttts/{id}", danhmucpttt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Danhmucpttt> danhmucptttList = danhmucptttRepository.findAll();
        assertThat(danhmucptttList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
