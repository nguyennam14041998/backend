package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Danhmucnhompttt;
import com.api.backend.domain.Danhmucpttt;
import com.api.backend.repository.DanhmucnhomptttRepository;
import com.api.backend.service.DanhmucnhomptttService;
import com.api.backend.service.dto.DanhmucnhomptttDTO;
import com.api.backend.service.mapper.DanhmucnhomptttMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DanhmucnhomptttCriteria;
import com.api.backend.service.DanhmucnhomptttQueryService;

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
 * Integration tests for the {@link DanhmucnhomptttResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DanhmucnhomptttResourceIT {

    private static final Integer DEFAULT_LOAI = 1;
    private static final Integer UPDATED_LOAI = 2;
    private static final Integer SMALLER_LOAI = 1 - 1;

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private DanhmucnhomptttRepository danhmucnhomptttRepository;

    @Autowired
    private DanhmucnhomptttMapper danhmucnhomptttMapper;

    @Autowired
    private DanhmucnhomptttService danhmucnhomptttService;

    @Autowired
    private DanhmucnhomptttQueryService danhmucnhomptttQueryService;

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

    private MockMvc restDanhmucnhomptttMockMvc;

    private Danhmucnhompttt danhmucnhompttt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhmucnhomptttResource danhmucnhomptttResource = new DanhmucnhomptttResource(danhmucnhomptttService, danhmucnhomptttQueryService);
        this.restDanhmucnhomptttMockMvc = MockMvcBuilders.standaloneSetup(danhmucnhomptttResource)
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
    public static Danhmucnhompttt createEntity(EntityManager em) {
        Danhmucnhompttt danhmucnhompttt = new Danhmucnhompttt()
            .loai(DEFAULT_LOAI)
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN);
        return danhmucnhompttt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Danhmucnhompttt createUpdatedEntity(EntityManager em) {
        Danhmucnhompttt danhmucnhompttt = new Danhmucnhompttt()
            .loai(UPDATED_LOAI)
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN);
        return danhmucnhompttt;
    }

    @BeforeEach
    public void initTest() {
        danhmucnhompttt = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhmucnhompttt() throws Exception {
        int databaseSizeBeforeCreate = danhmucnhomptttRepository.findAll().size();

        // Create the Danhmucnhompttt
        DanhmucnhomptttDTO danhmucnhomptttDTO = danhmucnhomptttMapper.toDto(danhmucnhompttt);
        restDanhmucnhomptttMockMvc.perform(post("/api/danhmucnhompttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnhomptttDTO)))
            .andExpect(status().isCreated());

        // Validate the Danhmucnhompttt in the database
        List<Danhmucnhompttt> danhmucnhomptttList = danhmucnhomptttRepository.findAll();
        assertThat(danhmucnhomptttList).hasSize(databaseSizeBeforeCreate + 1);
        Danhmucnhompttt testDanhmucnhompttt = danhmucnhomptttList.get(danhmucnhomptttList.size() - 1);
        assertThat(testDanhmucnhompttt.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDanhmucnhompttt.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDanhmucnhompttt.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createDanhmucnhomptttWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhmucnhomptttRepository.findAll().size();

        // Create the Danhmucnhompttt with an existing ID
        danhmucnhompttt.setId(1L);
        DanhmucnhomptttDTO danhmucnhomptttDTO = danhmucnhomptttMapper.toDto(danhmucnhompttt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhmucnhomptttMockMvc.perform(post("/api/danhmucnhompttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnhomptttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucnhompttt in the database
        List<Danhmucnhompttt> danhmucnhomptttList = danhmucnhomptttRepository.findAll();
        assertThat(danhmucnhomptttList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDanhmucnhompttts() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucnhompttt.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getDanhmucnhompttt() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get the danhmucnhompttt
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts/{id}", danhmucnhompttt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhmucnhompttt.getId().intValue()))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }


    @Test
    @Transactional
    public void getDanhmucnhomptttsByIdFiltering() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        Long id = danhmucnhompttt.getId();

        defaultDanhmucnhomptttShouldBeFound("id.equals=" + id);
        defaultDanhmucnhomptttShouldNotBeFound("id.notEquals=" + id);

        defaultDanhmucnhomptttShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhmucnhomptttShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhmucnhomptttShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhmucnhomptttShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai equals to DEFAULT_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai equals to UPDATED_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai not equals to DEFAULT_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai not equals to UPDATED_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the danhmucnhomptttList where loai equals to UPDATED_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai is not null
        defaultDanhmucnhomptttShouldBeFound("loai.specified=true");

        // Get all the danhmucnhomptttList where loai is null
        defaultDanhmucnhomptttShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai is greater than or equal to DEFAULT_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai is greater than or equal to UPDATED_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai is less than or equal to DEFAULT_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai is less than or equal to SMALLER_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai is less than DEFAULT_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai is less than UPDATED_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where loai is greater than DEFAULT_LOAI
        defaultDanhmucnhomptttShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the danhmucnhomptttList where loai is greater than SMALLER_LOAI
        defaultDanhmucnhomptttShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma equals to DEFAULT_MA
        defaultDanhmucnhomptttShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the danhmucnhomptttList where ma equals to UPDATED_MA
        defaultDanhmucnhomptttShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma not equals to DEFAULT_MA
        defaultDanhmucnhomptttShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the danhmucnhomptttList where ma not equals to UPDATED_MA
        defaultDanhmucnhomptttShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma in DEFAULT_MA or UPDATED_MA
        defaultDanhmucnhomptttShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the danhmucnhomptttList where ma equals to UPDATED_MA
        defaultDanhmucnhomptttShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma is not null
        defaultDanhmucnhomptttShouldBeFound("ma.specified=true");

        // Get all the danhmucnhomptttList where ma is null
        defaultDanhmucnhomptttShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaContainsSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma contains DEFAULT_MA
        defaultDanhmucnhomptttShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the danhmucnhomptttList where ma contains UPDATED_MA
        defaultDanhmucnhomptttShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ma does not contain DEFAULT_MA
        defaultDanhmucnhomptttShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the danhmucnhomptttList where ma does not contain UPDATED_MA
        defaultDanhmucnhomptttShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten equals to DEFAULT_TEN
        defaultDanhmucnhomptttShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the danhmucnhomptttList where ten equals to UPDATED_TEN
        defaultDanhmucnhomptttShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten not equals to DEFAULT_TEN
        defaultDanhmucnhomptttShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the danhmucnhomptttList where ten not equals to UPDATED_TEN
        defaultDanhmucnhomptttShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDanhmucnhomptttShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the danhmucnhomptttList where ten equals to UPDATED_TEN
        defaultDanhmucnhomptttShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten is not null
        defaultDanhmucnhomptttShouldBeFound("ten.specified=true");

        // Get all the danhmucnhomptttList where ten is null
        defaultDanhmucnhomptttShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenContainsSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten contains DEFAULT_TEN
        defaultDanhmucnhomptttShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the danhmucnhomptttList where ten contains UPDATED_TEN
        defaultDanhmucnhomptttShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        // Get all the danhmucnhomptttList where ten does not contain DEFAULT_TEN
        defaultDanhmucnhomptttShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the danhmucnhomptttList where ten does not contain UPDATED_TEN
        defaultDanhmucnhomptttShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDanhmucnhomptttsByDanhmucptttIsEqualToSomething() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);
        Danhmucpttt danhmucpttt = DanhmucptttResourceIT.createEntity(em);
        em.persist(danhmucpttt);
        em.flush();
        danhmucnhompttt.addDanhmucpttt(danhmucpttt);
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);
        Long danhmucptttId = danhmucpttt.getId();

        // Get all the danhmucnhomptttList where danhmucpttt equals to danhmucptttId
        defaultDanhmucnhomptttShouldBeFound("danhmucptttId.equals=" + danhmucptttId);

        // Get all the danhmucnhomptttList where danhmucpttt equals to danhmucptttId + 1
        defaultDanhmucnhomptttShouldNotBeFound("danhmucptttId.equals=" + (danhmucptttId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhmucnhomptttShouldBeFound(String filter) throws Exception {
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhmucnhompttt.getId().intValue())))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));

        // Check, that the count call also returns 1
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhmucnhomptttShouldNotBeFound(String filter) throws Exception {
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhmucnhompttt() throws Exception {
        // Get the danhmucnhompttt
        restDanhmucnhomptttMockMvc.perform(get("/api/danhmucnhompttts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhmucnhompttt() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        int databaseSizeBeforeUpdate = danhmucnhomptttRepository.findAll().size();

        // Update the danhmucnhompttt
        Danhmucnhompttt updatedDanhmucnhompttt = danhmucnhomptttRepository.findById(danhmucnhompttt.getId()).get();
        // Disconnect from session so that the updates on updatedDanhmucnhompttt are not directly saved in db
        em.detach(updatedDanhmucnhompttt);
        updatedDanhmucnhompttt
            .loai(UPDATED_LOAI)
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN);
        DanhmucnhomptttDTO danhmucnhomptttDTO = danhmucnhomptttMapper.toDto(updatedDanhmucnhompttt);

        restDanhmucnhomptttMockMvc.perform(put("/api/danhmucnhompttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnhomptttDTO)))
            .andExpect(status().isOk());

        // Validate the Danhmucnhompttt in the database
        List<Danhmucnhompttt> danhmucnhomptttList = danhmucnhomptttRepository.findAll();
        assertThat(danhmucnhomptttList).hasSize(databaseSizeBeforeUpdate);
        Danhmucnhompttt testDanhmucnhompttt = danhmucnhomptttList.get(danhmucnhomptttList.size() - 1);
        assertThat(testDanhmucnhompttt.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDanhmucnhompttt.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDanhmucnhompttt.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhmucnhompttt() throws Exception {
        int databaseSizeBeforeUpdate = danhmucnhomptttRepository.findAll().size();

        // Create the Danhmucnhompttt
        DanhmucnhomptttDTO danhmucnhomptttDTO = danhmucnhomptttMapper.toDto(danhmucnhompttt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhmucnhomptttMockMvc.perform(put("/api/danhmucnhompttts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhmucnhomptttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Danhmucnhompttt in the database
        List<Danhmucnhompttt> danhmucnhomptttList = danhmucnhomptttRepository.findAll();
        assertThat(danhmucnhomptttList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhmucnhompttt() throws Exception {
        // Initialize the database
        danhmucnhomptttRepository.saveAndFlush(danhmucnhompttt);

        int databaseSizeBeforeDelete = danhmucnhomptttRepository.findAll().size();

        // Delete the danhmucnhompttt
        restDanhmucnhomptttMockMvc.perform(delete("/api/danhmucnhompttts/{id}", danhmucnhompttt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Danhmucnhompttt> danhmucnhomptttList = danhmucnhomptttRepository.findAll();
        assertThat(danhmucnhomptttList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
