package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Danhmuchanhchinh;
import com.api.backend.repository.DanhmuchanhchinhRepository;
import com.api.backend.service.DanhmuchanhchinhService;
import com.api.backend.service.dto.DanhmuchanhchinhDTO;
import com.api.backend.service.mapper.DanhmuchanhchinhMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DanhmuchanhchinhCriteria;
import com.api.backend.service.DanhmuchanhchinhQueryService;

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
 * Integration tests for the {@link DanhmuchanhchinhResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DanhmuchanhchinhResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_TENDAYDU = "AAAAAAAAAA";
    private static final String UPDATED_TENDAYDU = "BBBBBBBBBB";

    private static final String DEFAULT_TENVIETTAT = "AAAAAAAAAA";
    private static final String UPDATED_TENVIETTAT = "BBBBBBBBBB";

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIADANHCHA = 1;
    private static final Integer UPDATED_DIADANHCHA = 2;
    private static final Integer SMALLER_DIADANHCHA = 1 - 1;

    private static final Integer DEFAULT_CAP = 1;
    private static final Integer UPDATED_CAP = 2;
    private static final Integer SMALLER_CAP = 1 - 1;

    private static final Integer DEFAULT_THANHTHI = 1;
    private static final Integer UPDATED_THANHTHI = 2;
    private static final Integer SMALLER_THANHTHI = 1 - 1;

    private static final Integer DEFAULT_HOATDONG = 1;
    private static final Integer UPDATED_HOATDONG = 2;
    private static final Integer SMALLER_HOATDONG = 1 - 1;

    @Autowired
    private DanhmuchanhchinhRepository danhmuchanhchinhRepository;

    @Autowired
    private DanhmuchanhchinhMapper danhmuchanhchinhMapper;

    @Autowired
    private DanhmuchanhchinhService danhmuchanhchinhService;

    @Autowired
    private DanhmuchanhchinhQueryService danhmuchanhchinhQueryService;

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

    private MockMvc restDanhmuchanhchinhMockMvc;

    private Danhmuchanhchinh danhmuchanhchinh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhmuchanhchinhResource danhmuchanhchinhResource = new DanhmuchanhchinhResource(danhmuchanhchinhService, danhmuchanhchinhQueryService);
        this.restDanhmuchanhchinhMockMvc = MockMvcBuilders.standaloneSetup(danhmuchanhchinhResource)
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
    public static Danhmuchanhchinh createEntity(EntityManager em) {
        Danhmuchanhchinh danhmuchanhchinh = new Danhmuchanhchinh()
            .ten(DEFAULT_TEN)
            .tendaydu(DEFAULT_TENDAYDU)
            .tenviettat(DEFAULT_TENVIETTAT)
            .tags(DEFAULT_TAGS)
            .diadanhcha(DEFAULT_DIADANHCHA)
            .cap(DEFAULT_CAP)
            .thanhthi(DEFAULT_THANHTHI)
            .hoatdong(DEFAULT_HOATDONG);
        return danhmuchanhchinh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Danhmuchanhchinh createUpdatedEntity(EntityManager em) {
        Danhmuchanhchinh danhmuchanhchinh = new Danhmuchanhchinh()
            .ten(UPDATED_TEN)
            .tendaydu(UPDATED_TENDAYDU)
            .tenviettat(UPDATED_TENVIETTAT)
            .tags(UPDATED_TAGS)
            .diadanhcha(UPDATED_DIADANHCHA)
            .cap(UPDATED_CAP)
            .thanhthi(UPDATED_THANHTHI)
            .hoatdong(UPDATED_HOATDONG);
        return danhmuchanhchinh;
    }

    @BeforeEach
    public void initTest() {
        danhmuchanhchinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhmuchanhchinh() throws Exception {
        int databaseSizeBeforeCreate = danhmuchanhchinhRepository.findAll().size();

        // Create the Danhmuchanhchinh
        DanhmuchanhchinhDTO danhmuchanhchinhDTO = danhmuchanhchinhMapper.toDto(danhmuchanhchinh);
        restDanhmuchanhchinhMockMvc.perform(post("/api/danhmuchanhchinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmuchanhchinhDTO)))
            .andExpect(status().isCreated());

        // Validate the Danhmuchanhchinh in the database
        List<Danhmuchanhchinh> danhmuchanhchinhList = danhmuchanhchinhRepository.findAll();
        assertThat(danhmuchanhchinhList).hasSize(databaseSizeBeforeCreate + 1);
        Danhmuchanhchinh testDanhmuchanhchinh = danhmuchanhchinhList.get(danhmuchanhchinhList.size() - 1);
        assertThat(testDanhmuchanhchinh.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDanhmuchanhchinh.getTendaydu()).isEqualTo(DEFAULT_TENDAYDU);
        assertThat(testDanhmuchanhchinh.getTenviettat()).isEqualTo(DEFAULT_TENVIETTAT);
        assertThat(testDanhmuchanhchinh.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testDanhmuchanhchinh.getDiadanhcha()).isEqualTo(DEFAULT_DIADANHCHA);
        assertThat(testDanhmuchanhchinh.getCap()).isEqualTo(DEFAULT_CAP);
        assertThat(testDanhmuchanhchinh.getThanhthi()).isEqualTo(DEFAULT_THANHTHI);
        assertThat(testDanhmuchanhchinh.getHoatdong()).isEqualTo(DEFAULT_HOATDONG);
    }

    @Test
    @Transactional
    public void createDanhmuchanhchinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhmuchanhchinhRepository.findAll().size();

        // Create the Danhmuchanhchinh with an existing ID
        danhmuchanhchinh.setId(1L);
        DanhmuchanhchinhDTO danhmuchanhchinhDTO = danhmuchanhchinhMapper.toDto(danhmuchanhchinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhmuchanhchinhMockMvc.perform(post("/api/danhmuchanhchinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmuchanhchinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmuchanhchinh in the database
        List<Danhmuchanhchinh> danhmuchanhchinhList = danhmuchanhchinhRepository.findAll();
        assertThat(danhmuchanhchinhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhs() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmuchanhchinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].tendaydu").value(hasItem(DEFAULT_TENDAYDU)))
            .andExpect(jsonPath("$.[*].tenviettat").value(hasItem(DEFAULT_TENVIETTAT)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].diadanhcha").value(hasItem(DEFAULT_DIADANHCHA)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP)))
            .andExpect(jsonPath("$.[*].thanhthi").value(hasItem(DEFAULT_THANHTHI)))
            .andExpect(jsonPath("$.[*].hoatdong").value(hasItem(DEFAULT_HOATDONG)));
    }
    
    @Test
    @Transactional
    public void getDanhmuchanhchinh() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get the danhmuchanhchinh
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs/{id}", danhmuchanhchinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhmuchanhchinh.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.tendaydu").value(DEFAULT_TENDAYDU))
            .andExpect(jsonPath("$.tenviettat").value(DEFAULT_TENVIETTAT))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS))
            .andExpect(jsonPath("$.diadanhcha").value(DEFAULT_DIADANHCHA))
            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP))
            .andExpect(jsonPath("$.thanhthi").value(DEFAULT_THANHTHI))
            .andExpect(jsonPath("$.hoatdong").value(DEFAULT_HOATDONG));
    }


    @Test
    @Transactional
    public void getDanhmuchanhchinhsByIdFiltering() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        Long id = danhmuchanhchinh.getId();

        defaultDanhmuchanhchinhShouldBeFound("id.equals=" + id);
        defaultDanhmuchanhchinhShouldNotBeFound("id.notEquals=" + id);

        defaultDanhmuchanhchinhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhmuchanhchinhShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhmuchanhchinhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhmuchanhchinhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten equals to DEFAULT_TEN
        defaultDanhmuchanhchinhShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the danhmuchanhchinhList where ten equals to UPDATED_TEN
        defaultDanhmuchanhchinhShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten not equals to DEFAULT_TEN
        defaultDanhmuchanhchinhShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the danhmuchanhchinhList where ten not equals to UPDATED_TEN
        defaultDanhmuchanhchinhShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDanhmuchanhchinhShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the danhmuchanhchinhList where ten equals to UPDATED_TEN
        defaultDanhmuchanhchinhShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten is not null
        defaultDanhmuchanhchinhShouldBeFound("ten.specified=true");

        // Get all the danhmuchanhchinhList where ten is null
        defaultDanhmuchanhchinhShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten contains DEFAULT_TEN
        defaultDanhmuchanhchinhShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the danhmuchanhchinhList where ten contains UPDATED_TEN
        defaultDanhmuchanhchinhShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where ten does not contain DEFAULT_TEN
        defaultDanhmuchanhchinhShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the danhmuchanhchinhList where ten does not contain UPDATED_TEN
        defaultDanhmuchanhchinhShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu equals to DEFAULT_TENDAYDU
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.equals=" + DEFAULT_TENDAYDU);

        // Get all the danhmuchanhchinhList where tendaydu equals to UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.equals=" + UPDATED_TENDAYDU);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu not equals to DEFAULT_TENDAYDU
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.notEquals=" + DEFAULT_TENDAYDU);

        // Get all the danhmuchanhchinhList where tendaydu not equals to UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.notEquals=" + UPDATED_TENDAYDU);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu in DEFAULT_TENDAYDU or UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.in=" + DEFAULT_TENDAYDU + "," + UPDATED_TENDAYDU);

        // Get all the danhmuchanhchinhList where tendaydu equals to UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.in=" + UPDATED_TENDAYDU);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu is not null
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.specified=true");

        // Get all the danhmuchanhchinhList where tendaydu is null
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu contains DEFAULT_TENDAYDU
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.contains=" + DEFAULT_TENDAYDU);

        // Get all the danhmuchanhchinhList where tendaydu contains UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.contains=" + UPDATED_TENDAYDU);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTendayduNotContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tendaydu does not contain DEFAULT_TENDAYDU
        defaultDanhmuchanhchinhShouldNotBeFound("tendaydu.doesNotContain=" + DEFAULT_TENDAYDU);

        // Get all the danhmuchanhchinhList where tendaydu does not contain UPDATED_TENDAYDU
        defaultDanhmuchanhchinhShouldBeFound("tendaydu.doesNotContain=" + UPDATED_TENDAYDU);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat equals to DEFAULT_TENVIETTAT
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.equals=" + DEFAULT_TENVIETTAT);

        // Get all the danhmuchanhchinhList where tenviettat equals to UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.equals=" + UPDATED_TENVIETTAT);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat not equals to DEFAULT_TENVIETTAT
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.notEquals=" + DEFAULT_TENVIETTAT);

        // Get all the danhmuchanhchinhList where tenviettat not equals to UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.notEquals=" + UPDATED_TENVIETTAT);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat in DEFAULT_TENVIETTAT or UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.in=" + DEFAULT_TENVIETTAT + "," + UPDATED_TENVIETTAT);

        // Get all the danhmuchanhchinhList where tenviettat equals to UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.in=" + UPDATED_TENVIETTAT);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat is not null
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.specified=true");

        // Get all the danhmuchanhchinhList where tenviettat is null
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat contains DEFAULT_TENVIETTAT
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.contains=" + DEFAULT_TENVIETTAT);

        // Get all the danhmuchanhchinhList where tenviettat contains UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.contains=" + UPDATED_TENVIETTAT);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTenviettatNotContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tenviettat does not contain DEFAULT_TENVIETTAT
        defaultDanhmuchanhchinhShouldNotBeFound("tenviettat.doesNotContain=" + DEFAULT_TENVIETTAT);

        // Get all the danhmuchanhchinhList where tenviettat does not contain UPDATED_TENVIETTAT
        defaultDanhmuchanhchinhShouldBeFound("tenviettat.doesNotContain=" + UPDATED_TENVIETTAT);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags equals to DEFAULT_TAGS
        defaultDanhmuchanhchinhShouldBeFound("tags.equals=" + DEFAULT_TAGS);

        // Get all the danhmuchanhchinhList where tags equals to UPDATED_TAGS
        defaultDanhmuchanhchinhShouldNotBeFound("tags.equals=" + UPDATED_TAGS);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags not equals to DEFAULT_TAGS
        defaultDanhmuchanhchinhShouldNotBeFound("tags.notEquals=" + DEFAULT_TAGS);

        // Get all the danhmuchanhchinhList where tags not equals to UPDATED_TAGS
        defaultDanhmuchanhchinhShouldBeFound("tags.notEquals=" + UPDATED_TAGS);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags in DEFAULT_TAGS or UPDATED_TAGS
        defaultDanhmuchanhchinhShouldBeFound("tags.in=" + DEFAULT_TAGS + "," + UPDATED_TAGS);

        // Get all the danhmuchanhchinhList where tags equals to UPDATED_TAGS
        defaultDanhmuchanhchinhShouldNotBeFound("tags.in=" + UPDATED_TAGS);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags is not null
        defaultDanhmuchanhchinhShouldBeFound("tags.specified=true");

        // Get all the danhmuchanhchinhList where tags is null
        defaultDanhmuchanhchinhShouldNotBeFound("tags.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags contains DEFAULT_TAGS
        defaultDanhmuchanhchinhShouldBeFound("tags.contains=" + DEFAULT_TAGS);

        // Get all the danhmuchanhchinhList where tags contains UPDATED_TAGS
        defaultDanhmuchanhchinhShouldNotBeFound("tags.contains=" + UPDATED_TAGS);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByTagsNotContainsSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where tags does not contain DEFAULT_TAGS
        defaultDanhmuchanhchinhShouldNotBeFound("tags.doesNotContain=" + DEFAULT_TAGS);

        // Get all the danhmuchanhchinhList where tags does not contain UPDATED_TAGS
        defaultDanhmuchanhchinhShouldBeFound("tags.doesNotContain=" + UPDATED_TAGS);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha equals to DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.equals=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha equals to UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.equals=" + UPDATED_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha not equals to DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.notEquals=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha not equals to UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.notEquals=" + UPDATED_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha in DEFAULT_DIADANHCHA or UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.in=" + DEFAULT_DIADANHCHA + "," + UPDATED_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha equals to UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.in=" + UPDATED_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha is not null
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.specified=true");

        // Get all the danhmuchanhchinhList where diadanhcha is null
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha is greater than or equal to DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.greaterThanOrEqual=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha is greater than or equal to UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.greaterThanOrEqual=" + UPDATED_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha is less than or equal to DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.lessThanOrEqual=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha is less than or equal to SMALLER_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.lessThanOrEqual=" + SMALLER_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha is less than DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.lessThan=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha is less than UPDATED_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.lessThan=" + UPDATED_DIADANHCHA);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByDiadanhchaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where diadanhcha is greater than DEFAULT_DIADANHCHA
        defaultDanhmuchanhchinhShouldNotBeFound("diadanhcha.greaterThan=" + DEFAULT_DIADANHCHA);

        // Get all the danhmuchanhchinhList where diadanhcha is greater than SMALLER_DIADANHCHA
        defaultDanhmuchanhchinhShouldBeFound("diadanhcha.greaterThan=" + SMALLER_DIADANHCHA);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap equals to DEFAULT_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.equals=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap equals to UPDATED_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.equals=" + UPDATED_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap not equals to DEFAULT_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.notEquals=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap not equals to UPDATED_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.notEquals=" + UPDATED_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap in DEFAULT_CAP or UPDATED_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.in=" + DEFAULT_CAP + "," + UPDATED_CAP);

        // Get all the danhmuchanhchinhList where cap equals to UPDATED_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.in=" + UPDATED_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap is not null
        defaultDanhmuchanhchinhShouldBeFound("cap.specified=true");

        // Get all the danhmuchanhchinhList where cap is null
        defaultDanhmuchanhchinhShouldNotBeFound("cap.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap is greater than or equal to DEFAULT_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.greaterThanOrEqual=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap is greater than or equal to UPDATED_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.greaterThanOrEqual=" + UPDATED_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap is less than or equal to DEFAULT_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.lessThanOrEqual=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap is less than or equal to SMALLER_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.lessThanOrEqual=" + SMALLER_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap is less than DEFAULT_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.lessThan=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap is less than UPDATED_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.lessThan=" + UPDATED_CAP);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByCapIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where cap is greater than DEFAULT_CAP
        defaultDanhmuchanhchinhShouldNotBeFound("cap.greaterThan=" + DEFAULT_CAP);

        // Get all the danhmuchanhchinhList where cap is greater than SMALLER_CAP
        defaultDanhmuchanhchinhShouldBeFound("cap.greaterThan=" + SMALLER_CAP);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi equals to DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.equals=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi equals to UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.equals=" + UPDATED_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi not equals to DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.notEquals=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi not equals to UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.notEquals=" + UPDATED_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi in DEFAULT_THANHTHI or UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.in=" + DEFAULT_THANHTHI + "," + UPDATED_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi equals to UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.in=" + UPDATED_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi is not null
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.specified=true");

        // Get all the danhmuchanhchinhList where thanhthi is null
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi is greater than or equal to DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.greaterThanOrEqual=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi is greater than or equal to UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.greaterThanOrEqual=" + UPDATED_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi is less than or equal to DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.lessThanOrEqual=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi is less than or equal to SMALLER_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.lessThanOrEqual=" + SMALLER_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi is less than DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.lessThan=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi is less than UPDATED_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.lessThan=" + UPDATED_THANHTHI);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByThanhthiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where thanhthi is greater than DEFAULT_THANHTHI
        defaultDanhmuchanhchinhShouldNotBeFound("thanhthi.greaterThan=" + DEFAULT_THANHTHI);

        // Get all the danhmuchanhchinhList where thanhthi is greater than SMALLER_THANHTHI
        defaultDanhmuchanhchinhShouldBeFound("thanhthi.greaterThan=" + SMALLER_THANHTHI);
    }


    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong equals to DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.equals=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong equals to UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.equals=" + UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong not equals to DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.notEquals=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong not equals to UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.notEquals=" + UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsInShouldWork() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong in DEFAULT_HOATDONG or UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.in=" + DEFAULT_HOATDONG + "," + UPDATED_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong equals to UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.in=" + UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong is not null
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.specified=true");

        // Get all the danhmuchanhchinhList where hoatdong is null
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong is greater than or equal to DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.greaterThanOrEqual=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong is greater than or equal to UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.greaterThanOrEqual=" + UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong is less than or equal to DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.lessThanOrEqual=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong is less than or equal to SMALLER_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.lessThanOrEqual=" + SMALLER_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong is less than DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.lessThan=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong is less than UPDATED_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.lessThan=" + UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void getAllDanhmuchanhchinhsByHoatdongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        // Get all the danhmuchanhchinhList where hoatdong is greater than DEFAULT_HOATDONG
        defaultDanhmuchanhchinhShouldNotBeFound("hoatdong.greaterThan=" + DEFAULT_HOATDONG);

        // Get all the danhmuchanhchinhList where hoatdong is greater than SMALLER_HOATDONG
        defaultDanhmuchanhchinhShouldBeFound("hoatdong.greaterThan=" + SMALLER_HOATDONG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhmuchanhchinhShouldBeFound(String filter) throws Exception {
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmuchanhchinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].tendaydu").value(hasItem(DEFAULT_TENDAYDU)))
            .andExpect(jsonPath("$.[*].tenviettat").value(hasItem(DEFAULT_TENVIETTAT)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].diadanhcha").value(hasItem(DEFAULT_DIADANHCHA)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP)))
            .andExpect(jsonPath("$.[*].thanhthi").value(hasItem(DEFAULT_THANHTHI)))
            .andExpect(jsonPath("$.[*].hoatdong").value(hasItem(DEFAULT_HOATDONG)));

        // Check, that the count call also returns 1
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhmuchanhchinhShouldNotBeFound(String filter) throws Exception {
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhmuchanhchinh() throws Exception {
        // Get the danhmuchanhchinh
        restDanhmuchanhchinhMockMvc.perform(get("/api/danhmuchanhchinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhmuchanhchinh() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        int databaseSizeBeforeUpdate = danhmuchanhchinhRepository.findAll().size();

        // Update the danhmuchanhchinh
        Danhmuchanhchinh updatedDanhmuchanhchinh = danhmuchanhchinhRepository.findById(danhmuchanhchinh.getId()).get();
        // Disconnect from session so that the updates on updatedDanhmuchanhchinh are not directly saved in db
        em.detach(updatedDanhmuchanhchinh);
        updatedDanhmuchanhchinh
            .ten(UPDATED_TEN)
            .tendaydu(UPDATED_TENDAYDU)
            .tenviettat(UPDATED_TENVIETTAT)
            .tags(UPDATED_TAGS)
            .diadanhcha(UPDATED_DIADANHCHA)
            .cap(UPDATED_CAP)
            .thanhthi(UPDATED_THANHTHI)
            .hoatdong(UPDATED_HOATDONG);
        DanhmuchanhchinhDTO danhmuchanhchinhDTO = danhmuchanhchinhMapper.toDto(updatedDanhmuchanhchinh);

        restDanhmuchanhchinhMockMvc.perform(put("/api/danhmuchanhchinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmuchanhchinhDTO)))
            .andExpect(status().isOk());

        // Validate the Danhmuchanhchinh in the database
        List<Danhmuchanhchinh> danhmuchanhchinhList = danhmuchanhchinhRepository.findAll();
        assertThat(danhmuchanhchinhList).hasSize(databaseSizeBeforeUpdate);
        Danhmuchanhchinh testDanhmuchanhchinh = danhmuchanhchinhList.get(danhmuchanhchinhList.size() - 1);
        assertThat(testDanhmuchanhchinh.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDanhmuchanhchinh.getTendaydu()).isEqualTo(UPDATED_TENDAYDU);
        assertThat(testDanhmuchanhchinh.getTenviettat()).isEqualTo(UPDATED_TENVIETTAT);
        assertThat(testDanhmuchanhchinh.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testDanhmuchanhchinh.getDiadanhcha()).isEqualTo(UPDATED_DIADANHCHA);
        assertThat(testDanhmuchanhchinh.getCap()).isEqualTo(UPDATED_CAP);
        assertThat(testDanhmuchanhchinh.getThanhthi()).isEqualTo(UPDATED_THANHTHI);
        assertThat(testDanhmuchanhchinh.getHoatdong()).isEqualTo(UPDATED_HOATDONG);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhmuchanhchinh() throws Exception {
        int databaseSizeBeforeUpdate = danhmuchanhchinhRepository.findAll().size();

        // Create the Danhmuchanhchinh
        DanhmuchanhchinhDTO danhmuchanhchinhDTO = danhmuchanhchinhMapper.toDto(danhmuchanhchinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhmuchanhchinhMockMvc.perform(put("/api/danhmuchanhchinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmuchanhchinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmuchanhchinh in the database
        List<Danhmuchanhchinh> danhmuchanhchinhList = danhmuchanhchinhRepository.findAll();
        assertThat(danhmuchanhchinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhmuchanhchinh() throws Exception {
        // Initialize the database
        danhmuchanhchinhRepository.saveAndFlush(danhmuchanhchinh);

        int databaseSizeBeforeDelete = danhmuchanhchinhRepository.findAll().size();

        // Delete the danhmuchanhchinh
        restDanhmuchanhchinhMockMvc.perform(delete("/api/danhmuchanhchinhs/{id}", danhmuchanhchinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Danhmuchanhchinh> danhmuchanhchinhList = danhmuchanhchinhRepository.findAll();
        assertThat(danhmuchanhchinhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
