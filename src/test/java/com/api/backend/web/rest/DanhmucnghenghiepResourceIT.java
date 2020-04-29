package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Danhmucnghenghiep;
import com.api.backend.repository.DanhmucnghenghiepRepository;
import com.api.backend.service.DanhmucnghenghiepService;
import com.api.backend.service.dto.DanhmucnghenghiepDTO;
import com.api.backend.service.mapper.DanhmucnghenghiepMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DanhmucnghenghiepCriteria;
import com.api.backend.service.DanhmucnghenghiepQueryService;

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
 * Integration tests for the {@link DanhmucnghenghiepResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DanhmucnghenghiepResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final String DEFAULT_B_YT = "AAAAAAAAAA";
    private static final String UPDATED_B_YT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private DanhmucnghenghiepRepository danhmucnghenghiepRepository;

    @Autowired
    private DanhmucnghenghiepMapper danhmucnghenghiepMapper;

    @Autowired
    private DanhmucnghenghiepService danhmucnghenghiepService;

    @Autowired
    private DanhmucnghenghiepQueryService danhmucnghenghiepQueryService;

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

    private MockMvc restDanhmucnghenghiepMockMvc;

    private Danhmucnghenghiep danhmucnghenghiep;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhmucnghenghiepResource danhmucnghenghiepResource = new DanhmucnghenghiepResource(danhmucnghenghiepService, danhmucnghenghiepQueryService);
        this.restDanhmucnghenghiepMockMvc = MockMvcBuilders.standaloneSetup(danhmucnghenghiepResource)
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
    public static Danhmucnghenghiep createEntity(EntityManager em) {
        Danhmucnghenghiep danhmucnghenghiep = new Danhmucnghenghiep()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .bYT(DEFAULT_B_YT)
            .sudung(DEFAULT_SUDUNG);
        return danhmucnghenghiep;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Danhmucnghenghiep createUpdatedEntity(EntityManager em) {
        Danhmucnghenghiep danhmucnghenghiep = new Danhmucnghenghiep()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .bYT(UPDATED_B_YT)
            .sudung(UPDATED_SUDUNG);
        return danhmucnghenghiep;
    }

    @BeforeEach
    public void initTest() {
        danhmucnghenghiep = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhmucnghenghiep() throws Exception {
        int databaseSizeBeforeCreate = danhmucnghenghiepRepository.findAll().size();

        // Create the Danhmucnghenghiep
        DanhmucnghenghiepDTO danhmucnghenghiepDTO = danhmucnghenghiepMapper.toDto(danhmucnghenghiep);
        restDanhmucnghenghiepMockMvc.perform(post("/api/danhmucnghenghieps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnghenghiepDTO)))
            .andExpect(status().isCreated());

        // Validate the Danhmucnghenghiep in the database
        List<Danhmucnghenghiep> danhmucnghenghiepList = danhmucnghenghiepRepository.findAll();
        assertThat(danhmucnghenghiepList).hasSize(databaseSizeBeforeCreate + 1);
        Danhmucnghenghiep testDanhmucnghenghiep = danhmucnghenghiepList.get(danhmucnghenghiepList.size() - 1);
        assertThat(testDanhmucnghenghiep.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDanhmucnghenghiep.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDanhmucnghenghiep.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDanhmucnghenghiep.getbYT()).isEqualTo(DEFAULT_B_YT);
        assertThat(testDanhmucnghenghiep.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createDanhmucnghenghiepWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhmucnghenghiepRepository.findAll().size();

        // Create the Danhmucnghenghiep with an existing ID
        danhmucnghenghiep.setId(1L);
        DanhmucnghenghiepDTO danhmucnghenghiepDTO = danhmucnghenghiepMapper.toDto(danhmucnghenghiep);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhmucnghenghiepMockMvc.perform(post("/api/danhmucnghenghieps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnghenghiepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucnghenghiep in the database
        List<Danhmucnghenghiep> danhmucnghenghiepList = danhmucnghenghiepRepository.findAll();
        assertThat(danhmucnghenghiepList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghieps() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucnghenghiep.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].bYT").value(hasItem(DEFAULT_B_YT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getDanhmucnghenghiep() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get the danhmucnghenghiep
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps/{id}", danhmucnghenghiep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhmucnghenghiep.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.bYT").value(DEFAULT_B_YT))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getDanhmucnghenghiepsByIdFiltering() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        Long id = danhmucnghenghiep.getId();

        defaultDanhmucnghenghiepShouldBeFound("id.equals=" + id);
        defaultDanhmucnghenghiepShouldNotBeFound("id.notEquals=" + id);

        defaultDanhmucnghenghiepShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhmucnghenghiepShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhmucnghenghiepShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhmucnghenghiepShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma equals to DEFAULT_MA
        defaultDanhmucnghenghiepShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the danhmucnghenghiepList where ma equals to UPDATED_MA
        defaultDanhmucnghenghiepShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma not equals to DEFAULT_MA
        defaultDanhmucnghenghiepShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the danhmucnghenghiepList where ma not equals to UPDATED_MA
        defaultDanhmucnghenghiepShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma in DEFAULT_MA or UPDATED_MA
        defaultDanhmucnghenghiepShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the danhmucnghenghiepList where ma equals to UPDATED_MA
        defaultDanhmucnghenghiepShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma is not null
        defaultDanhmucnghenghiepShouldBeFound("ma.specified=true");

        // Get all the danhmucnghenghiepList where ma is null
        defaultDanhmucnghenghiepShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma contains DEFAULT_MA
        defaultDanhmucnghenghiepShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the danhmucnghenghiepList where ma contains UPDATED_MA
        defaultDanhmucnghenghiepShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ma does not contain DEFAULT_MA
        defaultDanhmucnghenghiepShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the danhmucnghenghiepList where ma does not contain UPDATED_MA
        defaultDanhmucnghenghiepShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten equals to DEFAULT_TEN
        defaultDanhmucnghenghiepShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the danhmucnghenghiepList where ten equals to UPDATED_TEN
        defaultDanhmucnghenghiepShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten not equals to DEFAULT_TEN
        defaultDanhmucnghenghiepShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the danhmucnghenghiepList where ten not equals to UPDATED_TEN
        defaultDanhmucnghenghiepShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDanhmucnghenghiepShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the danhmucnghenghiepList where ten equals to UPDATED_TEN
        defaultDanhmucnghenghiepShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten is not null
        defaultDanhmucnghenghiepShouldBeFound("ten.specified=true");

        // Get all the danhmucnghenghiepList where ten is null
        defaultDanhmucnghenghiepShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten contains DEFAULT_TEN
        defaultDanhmucnghenghiepShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the danhmucnghenghiepList where ten contains UPDATED_TEN
        defaultDanhmucnghenghiepShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where ten does not contain DEFAULT_TEN
        defaultDanhmucnghenghiepShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the danhmucnghenghiepList where ten does not contain UPDATED_TEN
        defaultDanhmucnghenghiepShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota equals to DEFAULT_MOTA
        defaultDanhmucnghenghiepShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the danhmucnghenghiepList where mota equals to UPDATED_MOTA
        defaultDanhmucnghenghiepShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota not equals to DEFAULT_MOTA
        defaultDanhmucnghenghiepShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the danhmucnghenghiepList where mota not equals to UPDATED_MOTA
        defaultDanhmucnghenghiepShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDanhmucnghenghiepShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the danhmucnghenghiepList where mota equals to UPDATED_MOTA
        defaultDanhmucnghenghiepShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota is not null
        defaultDanhmucnghenghiepShouldBeFound("mota.specified=true");

        // Get all the danhmucnghenghiepList where mota is null
        defaultDanhmucnghenghiepShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota contains DEFAULT_MOTA
        defaultDanhmucnghenghiepShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the danhmucnghenghiepList where mota contains UPDATED_MOTA
        defaultDanhmucnghenghiepShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where mota does not contain DEFAULT_MOTA
        defaultDanhmucnghenghiepShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the danhmucnghenghiepList where mota does not contain UPDATED_MOTA
        defaultDanhmucnghenghiepShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT equals to DEFAULT_B_YT
        defaultDanhmucnghenghiepShouldBeFound("bYT.equals=" + DEFAULT_B_YT);

        // Get all the danhmucnghenghiepList where bYT equals to UPDATED_B_YT
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.equals=" + UPDATED_B_YT);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT not equals to DEFAULT_B_YT
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.notEquals=" + DEFAULT_B_YT);

        // Get all the danhmucnghenghiepList where bYT not equals to UPDATED_B_YT
        defaultDanhmucnghenghiepShouldBeFound("bYT.notEquals=" + UPDATED_B_YT);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT in DEFAULT_B_YT or UPDATED_B_YT
        defaultDanhmucnghenghiepShouldBeFound("bYT.in=" + DEFAULT_B_YT + "," + UPDATED_B_YT);

        // Get all the danhmucnghenghiepList where bYT equals to UPDATED_B_YT
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.in=" + UPDATED_B_YT);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT is not null
        defaultDanhmucnghenghiepShouldBeFound("bYT.specified=true");

        // Get all the danhmucnghenghiepList where bYT is null
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT contains DEFAULT_B_YT
        defaultDanhmucnghenghiepShouldBeFound("bYT.contains=" + DEFAULT_B_YT);

        // Get all the danhmucnghenghiepList where bYT contains UPDATED_B_YT
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.contains=" + UPDATED_B_YT);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBybYTNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where bYT does not contain DEFAULT_B_YT
        defaultDanhmucnghenghiepShouldNotBeFound("bYT.doesNotContain=" + DEFAULT_B_YT);

        // Get all the danhmucnghenghiepList where bYT does not contain UPDATED_B_YT
        defaultDanhmucnghenghiepShouldBeFound("bYT.doesNotContain=" + UPDATED_B_YT);
    }


    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung equals to DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung equals to UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung not equals to DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung not equals to UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung equals to UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung is not null
        defaultDanhmucnghenghiepShouldBeFound("sudung.specified=true");

        // Get all the danhmucnghenghiepList where sudung is null
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung is less than or equal to SMALLER_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung is less than DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung is less than UPDATED_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhmucnghenghiepsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        // Get all the danhmucnghenghiepList where sudung is greater than DEFAULT_SUDUNG
        defaultDanhmucnghenghiepShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the danhmucnghenghiepList where sudung is greater than SMALLER_SUDUNG
        defaultDanhmucnghenghiepShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhmucnghenghiepShouldBeFound(String filter) throws Exception {
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucnghenghiep.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].bYT").value(hasItem(DEFAULT_B_YT)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhmucnghenghiepShouldNotBeFound(String filter) throws Exception {
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhmucnghenghiep() throws Exception {
        // Get the danhmucnghenghiep
        restDanhmucnghenghiepMockMvc.perform(get("/api/danhmucnghenghieps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhmucnghenghiep() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        int databaseSizeBeforeUpdate = danhmucnghenghiepRepository.findAll().size();

        // Update the danhmucnghenghiep
        Danhmucnghenghiep updatedDanhmucnghenghiep = danhmucnghenghiepRepository.findById(danhmucnghenghiep.getId()).get();
        // Disconnect from session so that the updates on updatedDanhmucnghenghiep are not directly saved in db
        em.detach(updatedDanhmucnghenghiep);
        updatedDanhmucnghenghiep
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .bYT(UPDATED_B_YT)
            .sudung(UPDATED_SUDUNG);
        DanhmucnghenghiepDTO danhmucnghenghiepDTO = danhmucnghenghiepMapper.toDto(updatedDanhmucnghenghiep);

        restDanhmucnghenghiepMockMvc.perform(put("/api/danhmucnghenghieps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnghenghiepDTO)))
            .andExpect(status().isOk());

        // Validate the Danhmucnghenghiep in the database
        List<Danhmucnghenghiep> danhmucnghenghiepList = danhmucnghenghiepRepository.findAll();
        assertThat(danhmucnghenghiepList).hasSize(databaseSizeBeforeUpdate);
        Danhmucnghenghiep testDanhmucnghenghiep = danhmucnghenghiepList.get(danhmucnghenghiepList.size() - 1);
        assertThat(testDanhmucnghenghiep.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDanhmucnghenghiep.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDanhmucnghenghiep.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDanhmucnghenghiep.getbYT()).isEqualTo(UPDATED_B_YT);
        assertThat(testDanhmucnghenghiep.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhmucnghenghiep() throws Exception {
        int databaseSizeBeforeUpdate = danhmucnghenghiepRepository.findAll().size();

        // Create the Danhmucnghenghiep
        DanhmucnghenghiepDTO danhmucnghenghiepDTO = danhmucnghenghiepMapper.toDto(danhmucnghenghiep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhmucnghenghiepMockMvc.perform(put("/api/danhmucnghenghieps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnghenghiepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucnghenghiep in the database
        List<Danhmucnghenghiep> danhmucnghenghiepList = danhmucnghenghiepRepository.findAll();
        assertThat(danhmucnghenghiepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhmucnghenghiep() throws Exception {
        // Initialize the database
        danhmucnghenghiepRepository.saveAndFlush(danhmucnghenghiep);

        int databaseSizeBeforeDelete = danhmucnghenghiepRepository.findAll().size();

        // Delete the danhmucnghenghiep
        restDanhmucnghenghiepMockMvc.perform(delete("/api/danhmucnghenghieps/{id}", danhmucnghenghiep.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Danhmucnghenghiep> danhmucnghenghiepList = danhmucnghenghiepRepository.findAll();
        assertThat(danhmucnghenghiepList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
