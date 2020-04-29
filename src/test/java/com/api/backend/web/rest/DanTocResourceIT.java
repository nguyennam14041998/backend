package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.DanToc;
import com.api.backend.repository.DanTocRepository;
import com.api.backend.service.DanTocService;
import com.api.backend.service.dto.DanTocDTO;
import com.api.backend.service.mapper.DanTocMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DanTocCriteria;
import com.api.backend.service.DanTocQueryService;

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
 * Integration tests for the {@link DanTocResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DanTocResourceIT {

    private static final String DEFAULT_MA_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_MA_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_BHYT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BHXH = "AAAAAAAAAA";
    private static final String UPDATED_MA_BHXH = "BBBBBBBBBB";

    @Autowired
    private DanTocRepository danTocRepository;

    @Autowired
    private DanTocMapper danTocMapper;

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private DanTocQueryService danTocQueryService;

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

    private MockMvc restDanTocMockMvc;

    private DanToc danToc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanTocResource danTocResource = new DanTocResource(danTocService, danTocQueryService);
        this.restDanTocMockMvc = MockMvcBuilders.standaloneSetup(danTocResource)
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
    public static DanToc createEntity(EntityManager em) {
        DanToc danToc = new DanToc()
            .maDanToc(DEFAULT_MA_DAN_TOC)
            .tenDanToc(DEFAULT_TEN_DAN_TOC)
            .moTa(DEFAULT_MO_TA)
            .maBHYT(DEFAULT_MA_BHYT)
            .maBHXH(DEFAULT_MA_BHXH);
        return danToc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanToc createUpdatedEntity(EntityManager em) {
        DanToc danToc = new DanToc()
            .maDanToc(UPDATED_MA_DAN_TOC)
            .tenDanToc(UPDATED_TEN_DAN_TOC)
            .moTa(UPDATED_MO_TA)
            .maBHYT(UPDATED_MA_BHYT)
            .maBHXH(UPDATED_MA_BHXH);
        return danToc;
    }

    @BeforeEach
    public void initTest() {
        danToc = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanToc() throws Exception {
        int databaseSizeBeforeCreate = danTocRepository.findAll().size();

        // Create the DanToc
        DanTocDTO danTocDTO = danTocMapper.toDto(danToc);
        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isCreated());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeCreate + 1);
        DanToc testDanToc = danTocList.get(danTocList.size() - 1);
        assertThat(testDanToc.getMaDanToc()).isEqualTo(DEFAULT_MA_DAN_TOC);
        assertThat(testDanToc.getTenDanToc()).isEqualTo(DEFAULT_TEN_DAN_TOC);
        assertThat(testDanToc.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testDanToc.getMaBHYT()).isEqualTo(DEFAULT_MA_BHYT);
        assertThat(testDanToc.getMaBHXH()).isEqualTo(DEFAULT_MA_BHXH);
    }

    @Test
    @Transactional
    public void createDanTocWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danTocRepository.findAll().size();

        // Create the DanToc with an existing ID
        danToc.setId(1L);
        DanTocDTO danTocDTO = danTocMapper.toDto(danToc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaDanTocIsRequired() throws Exception {
        int databaseSizeBeforeTest = danTocRepository.findAll().size();
        // set the field null
        danToc.setMaDanToc(null);

        // Create the DanToc, which fails.
        DanTocDTO danTocDTO = danTocMapper.toDto(danToc);

        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isBadRequest());

        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenDanTocIsRequired() throws Exception {
        int databaseSizeBeforeTest = danTocRepository.findAll().size();
        // set the field null
        danToc.setTenDanToc(null);

        // Create the DanToc, which fails.
        DanTocDTO danTocDTO = danTocMapper.toDto(danToc);

        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isBadRequest());

        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDanTocs() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danToc.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDanToc").value(hasItem(DEFAULT_MA_DAN_TOC)))
            .andExpect(jsonPath("$.[*].tenDanToc").value(hasItem(DEFAULT_TEN_DAN_TOC)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].maBHYT").value(hasItem(DEFAULT_MA_BHYT)))
            .andExpect(jsonPath("$.[*].maBHXH").value(hasItem(DEFAULT_MA_BHXH)));
    }
    
    @Test
    @Transactional
    public void getDanToc() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get the danToc
        restDanTocMockMvc.perform(get("/api/dan-tocs/{id}", danToc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danToc.getId().intValue()))
            .andExpect(jsonPath("$.maDanToc").value(DEFAULT_MA_DAN_TOC))
            .andExpect(jsonPath("$.tenDanToc").value(DEFAULT_TEN_DAN_TOC))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.maBHYT").value(DEFAULT_MA_BHYT))
            .andExpect(jsonPath("$.maBHXH").value(DEFAULT_MA_BHXH));
    }


    @Test
    @Transactional
    public void getDanTocsByIdFiltering() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        Long id = danToc.getId();

        defaultDanTocShouldBeFound("id.equals=" + id);
        defaultDanTocShouldNotBeFound("id.notEquals=" + id);

        defaultDanTocShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanTocShouldNotBeFound("id.greaterThan=" + id);

        defaultDanTocShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanTocShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc equals to DEFAULT_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.equals=" + DEFAULT_MA_DAN_TOC);

        // Get all the danTocList where maDanToc equals to UPDATED_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.equals=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc not equals to DEFAULT_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.notEquals=" + DEFAULT_MA_DAN_TOC);

        // Get all the danTocList where maDanToc not equals to UPDATED_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.notEquals=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc in DEFAULT_MA_DAN_TOC or UPDATED_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.in=" + DEFAULT_MA_DAN_TOC + "," + UPDATED_MA_DAN_TOC);

        // Get all the danTocList where maDanToc equals to UPDATED_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.in=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc is not null
        defaultDanTocShouldBeFound("maDanToc.specified=true");

        // Get all the danTocList where maDanToc is null
        defaultDanTocShouldNotBeFound("maDanToc.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanTocsByMaDanTocContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc contains DEFAULT_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.contains=" + DEFAULT_MA_DAN_TOC);

        // Get all the danTocList where maDanToc contains UPDATED_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.contains=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocNotContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc does not contain DEFAULT_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.doesNotContain=" + DEFAULT_MA_DAN_TOC);

        // Get all the danTocList where maDanToc does not contain UPDATED_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.doesNotContain=" + UPDATED_MA_DAN_TOC);
    }


    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc equals to DEFAULT_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.equals=" + DEFAULT_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc equals to UPDATED_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.equals=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc not equals to DEFAULT_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.notEquals=" + DEFAULT_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc not equals to UPDATED_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.notEquals=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc in DEFAULT_TEN_DAN_TOC or UPDATED_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.in=" + DEFAULT_TEN_DAN_TOC + "," + UPDATED_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc equals to UPDATED_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.in=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc is not null
        defaultDanTocShouldBeFound("tenDanToc.specified=true");

        // Get all the danTocList where tenDanToc is null
        defaultDanTocShouldNotBeFound("tenDanToc.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanTocsByTenDanTocContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc contains DEFAULT_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.contains=" + DEFAULT_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc contains UPDATED_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.contains=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocNotContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc does not contain DEFAULT_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.doesNotContain=" + DEFAULT_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc does not contain UPDATED_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.doesNotContain=" + UPDATED_TEN_DAN_TOC);
    }


    @Test
    @Transactional
    public void getAllDanTocsByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa equals to DEFAULT_MO_TA
        defaultDanTocShouldBeFound("moTa.equals=" + DEFAULT_MO_TA);

        // Get all the danTocList where moTa equals to UPDATED_MO_TA
        defaultDanTocShouldNotBeFound("moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMoTaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa not equals to DEFAULT_MO_TA
        defaultDanTocShouldNotBeFound("moTa.notEquals=" + DEFAULT_MO_TA);

        // Get all the danTocList where moTa not equals to UPDATED_MO_TA
        defaultDanTocShouldBeFound("moTa.notEquals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa in DEFAULT_MO_TA or UPDATED_MO_TA
        defaultDanTocShouldBeFound("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA);

        // Get all the danTocList where moTa equals to UPDATED_MO_TA
        defaultDanTocShouldNotBeFound("moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa is not null
        defaultDanTocShouldBeFound("moTa.specified=true");

        // Get all the danTocList where moTa is null
        defaultDanTocShouldNotBeFound("moTa.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanTocsByMoTaContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa contains DEFAULT_MO_TA
        defaultDanTocShouldBeFound("moTa.contains=" + DEFAULT_MO_TA);

        // Get all the danTocList where moTa contains UPDATED_MO_TA
        defaultDanTocShouldNotBeFound("moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where moTa does not contain DEFAULT_MO_TA
        defaultDanTocShouldNotBeFound("moTa.doesNotContain=" + DEFAULT_MO_TA);

        // Get all the danTocList where moTa does not contain UPDATED_MO_TA
        defaultDanTocShouldBeFound("moTa.doesNotContain=" + UPDATED_MO_TA);
    }


    @Test
    @Transactional
    public void getAllDanTocsByMaBHYTIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT equals to DEFAULT_MA_BHYT
        defaultDanTocShouldBeFound("maBHYT.equals=" + DEFAULT_MA_BHYT);

        // Get all the danTocList where maBHYT equals to UPDATED_MA_BHYT
        defaultDanTocShouldNotBeFound("maBHYT.equals=" + UPDATED_MA_BHYT);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHYTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT not equals to DEFAULT_MA_BHYT
        defaultDanTocShouldNotBeFound("maBHYT.notEquals=" + DEFAULT_MA_BHYT);

        // Get all the danTocList where maBHYT not equals to UPDATED_MA_BHYT
        defaultDanTocShouldBeFound("maBHYT.notEquals=" + UPDATED_MA_BHYT);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHYTIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT in DEFAULT_MA_BHYT or UPDATED_MA_BHYT
        defaultDanTocShouldBeFound("maBHYT.in=" + DEFAULT_MA_BHYT + "," + UPDATED_MA_BHYT);

        // Get all the danTocList where maBHYT equals to UPDATED_MA_BHYT
        defaultDanTocShouldNotBeFound("maBHYT.in=" + UPDATED_MA_BHYT);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHYTIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT is not null
        defaultDanTocShouldBeFound("maBHYT.specified=true");

        // Get all the danTocList where maBHYT is null
        defaultDanTocShouldNotBeFound("maBHYT.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanTocsByMaBHYTContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT contains DEFAULT_MA_BHYT
        defaultDanTocShouldBeFound("maBHYT.contains=" + DEFAULT_MA_BHYT);

        // Get all the danTocList where maBHYT contains UPDATED_MA_BHYT
        defaultDanTocShouldNotBeFound("maBHYT.contains=" + UPDATED_MA_BHYT);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHYTNotContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHYT does not contain DEFAULT_MA_BHYT
        defaultDanTocShouldNotBeFound("maBHYT.doesNotContain=" + DEFAULT_MA_BHYT);

        // Get all the danTocList where maBHYT does not contain UPDATED_MA_BHYT
        defaultDanTocShouldBeFound("maBHYT.doesNotContain=" + UPDATED_MA_BHYT);
    }


    @Test
    @Transactional
    public void getAllDanTocsByMaBHXHIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH equals to DEFAULT_MA_BHXH
        defaultDanTocShouldBeFound("maBHXH.equals=" + DEFAULT_MA_BHXH);

        // Get all the danTocList where maBHXH equals to UPDATED_MA_BHXH
        defaultDanTocShouldNotBeFound("maBHXH.equals=" + UPDATED_MA_BHXH);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHXHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH not equals to DEFAULT_MA_BHXH
        defaultDanTocShouldNotBeFound("maBHXH.notEquals=" + DEFAULT_MA_BHXH);

        // Get all the danTocList where maBHXH not equals to UPDATED_MA_BHXH
        defaultDanTocShouldBeFound("maBHXH.notEquals=" + UPDATED_MA_BHXH);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHXHIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH in DEFAULT_MA_BHXH or UPDATED_MA_BHXH
        defaultDanTocShouldBeFound("maBHXH.in=" + DEFAULT_MA_BHXH + "," + UPDATED_MA_BHXH);

        // Get all the danTocList where maBHXH equals to UPDATED_MA_BHXH
        defaultDanTocShouldNotBeFound("maBHXH.in=" + UPDATED_MA_BHXH);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHXHIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH is not null
        defaultDanTocShouldBeFound("maBHXH.specified=true");

        // Get all the danTocList where maBHXH is null
        defaultDanTocShouldNotBeFound("maBHXH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanTocsByMaBHXHContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH contains DEFAULT_MA_BHXH
        defaultDanTocShouldBeFound("maBHXH.contains=" + DEFAULT_MA_BHXH);

        // Get all the danTocList where maBHXH contains UPDATED_MA_BHXH
        defaultDanTocShouldNotBeFound("maBHXH.contains=" + UPDATED_MA_BHXH);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaBHXHNotContainsSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maBHXH does not contain DEFAULT_MA_BHXH
        defaultDanTocShouldNotBeFound("maBHXH.doesNotContain=" + DEFAULT_MA_BHXH);

        // Get all the danTocList where maBHXH does not contain UPDATED_MA_BHXH
        defaultDanTocShouldBeFound("maBHXH.doesNotContain=" + UPDATED_MA_BHXH);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanTocShouldBeFound(String filter) throws Exception {
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danToc.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDanToc").value(hasItem(DEFAULT_MA_DAN_TOC)))
            .andExpect(jsonPath("$.[*].tenDanToc").value(hasItem(DEFAULT_TEN_DAN_TOC)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].maBHYT").value(hasItem(DEFAULT_MA_BHYT)))
            .andExpect(jsonPath("$.[*].maBHXH").value(hasItem(DEFAULT_MA_BHXH)));

        // Check, that the count call also returns 1
        restDanTocMockMvc.perform(get("/api/dan-tocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanTocShouldNotBeFound(String filter) throws Exception {
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanTocMockMvc.perform(get("/api/dan-tocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanToc() throws Exception {
        // Get the danToc
        restDanTocMockMvc.perform(get("/api/dan-tocs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanToc() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        int databaseSizeBeforeUpdate = danTocRepository.findAll().size();

        // Update the danToc
        DanToc updatedDanToc = danTocRepository.findById(danToc.getId()).get();
        // Disconnect from session so that the updates on updatedDanToc are not directly saved in db
        em.detach(updatedDanToc);
        updatedDanToc
            .maDanToc(UPDATED_MA_DAN_TOC)
            .tenDanToc(UPDATED_TEN_DAN_TOC)
            .moTa(UPDATED_MO_TA)
            .maBHYT(UPDATED_MA_BHYT)
            .maBHXH(UPDATED_MA_BHXH);
        DanTocDTO danTocDTO = danTocMapper.toDto(updatedDanToc);

        restDanTocMockMvc.perform(put("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isOk());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeUpdate);
        DanToc testDanToc = danTocList.get(danTocList.size() - 1);
        assertThat(testDanToc.getMaDanToc()).isEqualTo(UPDATED_MA_DAN_TOC);
        assertThat(testDanToc.getTenDanToc()).isEqualTo(UPDATED_TEN_DAN_TOC);
        assertThat(testDanToc.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testDanToc.getMaBHYT()).isEqualTo(UPDATED_MA_BHYT);
        assertThat(testDanToc.getMaBHXH()).isEqualTo(UPDATED_MA_BHXH);
    }

    @Test
    @Transactional
    public void updateNonExistingDanToc() throws Exception {
        int databaseSizeBeforeUpdate = danTocRepository.findAll().size();

        // Create the DanToc
        DanTocDTO danTocDTO = danTocMapper.toDto(danToc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanTocMockMvc.perform(put("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danTocDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanToc() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        int databaseSizeBeforeDelete = danTocRepository.findAll().size();

        // Delete the danToc
        restDanTocMockMvc.perform(delete("/api/dan-tocs/{id}", danToc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
