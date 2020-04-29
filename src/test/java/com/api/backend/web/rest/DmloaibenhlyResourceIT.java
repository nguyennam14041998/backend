package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.domain.Dmbenhly;
import com.api.backend.repository.DmloaibenhlyRepository;
import com.api.backend.service.DmloaibenhlyService;
import com.api.backend.service.dto.DmloaibenhlyDTO;
import com.api.backend.service.mapper.DmloaibenhlyMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmloaibenhlyCriteria;
import com.api.backend.service.DmloaibenhlyQueryService;

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
 * Integration tests for the {@link DmloaibenhlyResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmloaibenhlyResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final String DEFAULT_CHUONG = "AAAAAAAAAA";
    private static final String UPDATED_CHUONG = "BBBBBBBBBB";

    @Autowired
    private DmloaibenhlyRepository dmloaibenhlyRepository;

    @Autowired
    private DmloaibenhlyMapper dmloaibenhlyMapper;

    @Autowired
    private DmloaibenhlyService dmloaibenhlyService;

    @Autowired
    private DmloaibenhlyQueryService dmloaibenhlyQueryService;

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

    private MockMvc restDmloaibenhlyMockMvc;

    private Dmloaibenhly dmloaibenhly;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmloaibenhlyResource dmloaibenhlyResource = new DmloaibenhlyResource(dmloaibenhlyService, dmloaibenhlyQueryService);
        this.restDmloaibenhlyMockMvc = MockMvcBuilders.standaloneSetup(dmloaibenhlyResource)
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
    public static Dmloaibenhly createEntity(EntityManager em) {
        Dmloaibenhly dmloaibenhly = new Dmloaibenhly()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA)
            .chuong(DEFAULT_CHUONG);
        return dmloaibenhly;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmloaibenhly createUpdatedEntity(EntityManager em) {
        Dmloaibenhly dmloaibenhly = new Dmloaibenhly()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .chuong(UPDATED_CHUONG);
        return dmloaibenhly;
    }

    @BeforeEach
    public void initTest() {
        dmloaibenhly = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmloaibenhly() throws Exception {
        int databaseSizeBeforeCreate = dmloaibenhlyRepository.findAll().size();

        // Create the Dmloaibenhly
        DmloaibenhlyDTO dmloaibenhlyDTO = dmloaibenhlyMapper.toDto(dmloaibenhly);
        restDmloaibenhlyMockMvc.perform(post("/api/dmloaibenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmloaibenhlyDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmloaibenhly in the database
        List<Dmloaibenhly> dmloaibenhlyList = dmloaibenhlyRepository.findAll();
        assertThat(dmloaibenhlyList).hasSize(databaseSizeBeforeCreate + 1);
        Dmloaibenhly testDmloaibenhly = dmloaibenhlyList.get(dmloaibenhlyList.size() - 1);
        assertThat(testDmloaibenhly.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmloaibenhly.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmloaibenhly.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDmloaibenhly.getChuong()).isEqualTo(DEFAULT_CHUONG);
    }

    @Test
    @Transactional
    public void createDmloaibenhlyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmloaibenhlyRepository.findAll().size();

        // Create the Dmloaibenhly with an existing ID
        dmloaibenhly.setId(1L);
        DmloaibenhlyDTO dmloaibenhlyDTO = dmloaibenhlyMapper.toDto(dmloaibenhly);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmloaibenhlyMockMvc.perform(post("/api/dmloaibenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmloaibenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmloaibenhly in the database
        List<Dmloaibenhly> dmloaibenhlyList = dmloaibenhlyRepository.findAll();
        assertThat(dmloaibenhlyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhlies() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmloaibenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].chuong").value(hasItem(DEFAULT_CHUONG)));
    }
    
    @Test
    @Transactional
    public void getDmloaibenhly() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get the dmloaibenhly
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies/{id}", dmloaibenhly.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmloaibenhly.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.chuong").value(DEFAULT_CHUONG));
    }


    @Test
    @Transactional
    public void getDmloaibenhliesByIdFiltering() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        Long id = dmloaibenhly.getId();

        defaultDmloaibenhlyShouldBeFound("id.equals=" + id);
        defaultDmloaibenhlyShouldNotBeFound("id.notEquals=" + id);

        defaultDmloaibenhlyShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmloaibenhlyShouldNotBeFound("id.greaterThan=" + id);

        defaultDmloaibenhlyShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmloaibenhlyShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma equals to DEFAULT_MA
        defaultDmloaibenhlyShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmloaibenhlyList where ma equals to UPDATED_MA
        defaultDmloaibenhlyShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma not equals to DEFAULT_MA
        defaultDmloaibenhlyShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmloaibenhlyList where ma not equals to UPDATED_MA
        defaultDmloaibenhlyShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmloaibenhlyShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmloaibenhlyList where ma equals to UPDATED_MA
        defaultDmloaibenhlyShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma is not null
        defaultDmloaibenhlyShouldBeFound("ma.specified=true");

        // Get all the dmloaibenhlyList where ma is null
        defaultDmloaibenhlyShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmloaibenhliesByMaContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma contains DEFAULT_MA
        defaultDmloaibenhlyShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmloaibenhlyList where ma contains UPDATED_MA
        defaultDmloaibenhlyShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ma does not contain DEFAULT_MA
        defaultDmloaibenhlyShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmloaibenhlyList where ma does not contain UPDATED_MA
        defaultDmloaibenhlyShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten equals to DEFAULT_TEN
        defaultDmloaibenhlyShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmloaibenhlyList where ten equals to UPDATED_TEN
        defaultDmloaibenhlyShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten not equals to DEFAULT_TEN
        defaultDmloaibenhlyShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmloaibenhlyList where ten not equals to UPDATED_TEN
        defaultDmloaibenhlyShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmloaibenhlyShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmloaibenhlyList where ten equals to UPDATED_TEN
        defaultDmloaibenhlyShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten is not null
        defaultDmloaibenhlyShouldBeFound("ten.specified=true");

        // Get all the dmloaibenhlyList where ten is null
        defaultDmloaibenhlyShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmloaibenhliesByTenContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten contains DEFAULT_TEN
        defaultDmloaibenhlyShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmloaibenhlyList where ten contains UPDATED_TEN
        defaultDmloaibenhlyShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where ten does not contain DEFAULT_TEN
        defaultDmloaibenhlyShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmloaibenhlyList where ten does not contain UPDATED_TEN
        defaultDmloaibenhlyShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota equals to DEFAULT_MOTA
        defaultDmloaibenhlyShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmloaibenhlyList where mota equals to UPDATED_MOTA
        defaultDmloaibenhlyShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota not equals to DEFAULT_MOTA
        defaultDmloaibenhlyShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmloaibenhlyList where mota not equals to UPDATED_MOTA
        defaultDmloaibenhlyShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmloaibenhlyShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmloaibenhlyList where mota equals to UPDATED_MOTA
        defaultDmloaibenhlyShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota is not null
        defaultDmloaibenhlyShouldBeFound("mota.specified=true");

        // Get all the dmloaibenhlyList where mota is null
        defaultDmloaibenhlyShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota contains DEFAULT_MOTA
        defaultDmloaibenhlyShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmloaibenhlyList where mota contains UPDATED_MOTA
        defaultDmloaibenhlyShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where mota does not contain DEFAULT_MOTA
        defaultDmloaibenhlyShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmloaibenhlyList where mota does not contain UPDATED_MOTA
        defaultDmloaibenhlyShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong equals to DEFAULT_CHUONG
        defaultDmloaibenhlyShouldBeFound("chuong.equals=" + DEFAULT_CHUONG);

        // Get all the dmloaibenhlyList where chuong equals to UPDATED_CHUONG
        defaultDmloaibenhlyShouldNotBeFound("chuong.equals=" + UPDATED_CHUONG);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong not equals to DEFAULT_CHUONG
        defaultDmloaibenhlyShouldNotBeFound("chuong.notEquals=" + DEFAULT_CHUONG);

        // Get all the dmloaibenhlyList where chuong not equals to UPDATED_CHUONG
        defaultDmloaibenhlyShouldBeFound("chuong.notEquals=" + UPDATED_CHUONG);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongIsInShouldWork() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong in DEFAULT_CHUONG or UPDATED_CHUONG
        defaultDmloaibenhlyShouldBeFound("chuong.in=" + DEFAULT_CHUONG + "," + UPDATED_CHUONG);

        // Get all the dmloaibenhlyList where chuong equals to UPDATED_CHUONG
        defaultDmloaibenhlyShouldNotBeFound("chuong.in=" + UPDATED_CHUONG);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong is not null
        defaultDmloaibenhlyShouldBeFound("chuong.specified=true");

        // Get all the dmloaibenhlyList where chuong is null
        defaultDmloaibenhlyShouldNotBeFound("chuong.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong contains DEFAULT_CHUONG
        defaultDmloaibenhlyShouldBeFound("chuong.contains=" + DEFAULT_CHUONG);

        // Get all the dmloaibenhlyList where chuong contains UPDATED_CHUONG
        defaultDmloaibenhlyShouldNotBeFound("chuong.contains=" + UPDATED_CHUONG);
    }

    @Test
    @Transactional
    public void getAllDmloaibenhliesByChuongNotContainsSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        // Get all the dmloaibenhlyList where chuong does not contain DEFAULT_CHUONG
        defaultDmloaibenhlyShouldNotBeFound("chuong.doesNotContain=" + DEFAULT_CHUONG);

        // Get all the dmloaibenhlyList where chuong does not contain UPDATED_CHUONG
        defaultDmloaibenhlyShouldBeFound("chuong.doesNotContain=" + UPDATED_CHUONG);
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByDmnhombenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);
        Dmnhombenhly dmnhombenhly = DmnhombenhlyResourceIT.createEntity(em);
        em.persist(dmnhombenhly);
        em.flush();
        dmloaibenhly.addDmnhombenhly(dmnhombenhly);
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);
        Long dmnhombenhlyId = dmnhombenhly.getId();

        // Get all the dmloaibenhlyList where dmnhombenhly equals to dmnhombenhlyId
        defaultDmloaibenhlyShouldBeFound("dmnhombenhlyId.equals=" + dmnhombenhlyId);

        // Get all the dmloaibenhlyList where dmnhombenhly equals to dmnhombenhlyId + 1
        defaultDmloaibenhlyShouldNotBeFound("dmnhombenhlyId.equals=" + (dmnhombenhlyId + 1));
    }


    @Test
    @Transactional
    public void getAllDmloaibenhliesByDmbenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);
        Dmbenhly dmbenhly = DmbenhlyResourceIT.createEntity(em);
        em.persist(dmbenhly);
        em.flush();
        dmloaibenhly.addDmbenhly(dmbenhly);
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);
        Long dmbenhlyId = dmbenhly.getId();

        // Get all the dmloaibenhlyList where dmbenhly equals to dmbenhlyId
        defaultDmloaibenhlyShouldBeFound("dmbenhlyId.equals=" + dmbenhlyId);

        // Get all the dmloaibenhlyList where dmbenhly equals to dmbenhlyId + 1
        defaultDmloaibenhlyShouldNotBeFound("dmbenhlyId.equals=" + (dmbenhlyId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmloaibenhlyShouldBeFound(String filter) throws Exception {
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmloaibenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].chuong").value(hasItem(DEFAULT_CHUONG)));

        // Check, that the count call also returns 1
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmloaibenhlyShouldNotBeFound(String filter) throws Exception {
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmloaibenhly() throws Exception {
        // Get the dmloaibenhly
        restDmloaibenhlyMockMvc.perform(get("/api/dmloaibenhlies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmloaibenhly() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        int databaseSizeBeforeUpdate = dmloaibenhlyRepository.findAll().size();

        // Update the dmloaibenhly
        Dmloaibenhly updatedDmloaibenhly = dmloaibenhlyRepository.findById(dmloaibenhly.getId()).get();
        // Disconnect from session so that the updates on updatedDmloaibenhly are not directly saved in db
        em.detach(updatedDmloaibenhly);
        updatedDmloaibenhly
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA)
            .chuong(UPDATED_CHUONG);
        DmloaibenhlyDTO dmloaibenhlyDTO = dmloaibenhlyMapper.toDto(updatedDmloaibenhly);

        restDmloaibenhlyMockMvc.perform(put("/api/dmloaibenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmloaibenhlyDTO)))
            .andExpect(status().isOk());

        // Validate the Dmloaibenhly in the database
        List<Dmloaibenhly> dmloaibenhlyList = dmloaibenhlyRepository.findAll();
        assertThat(dmloaibenhlyList).hasSize(databaseSizeBeforeUpdate);
        Dmloaibenhly testDmloaibenhly = dmloaibenhlyList.get(dmloaibenhlyList.size() - 1);
        assertThat(testDmloaibenhly.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmloaibenhly.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmloaibenhly.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDmloaibenhly.getChuong()).isEqualTo(UPDATED_CHUONG);
    }

    @Test
    @Transactional
    public void updateNonExistingDmloaibenhly() throws Exception {
        int databaseSizeBeforeUpdate = dmloaibenhlyRepository.findAll().size();

        // Create the Dmloaibenhly
        DmloaibenhlyDTO dmloaibenhlyDTO = dmloaibenhlyMapper.toDto(dmloaibenhly);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmloaibenhlyMockMvc.perform(put("/api/dmloaibenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmloaibenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmloaibenhly in the database
        List<Dmloaibenhly> dmloaibenhlyList = dmloaibenhlyRepository.findAll();
        assertThat(dmloaibenhlyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmloaibenhly() throws Exception {
        // Initialize the database
        dmloaibenhlyRepository.saveAndFlush(dmloaibenhly);

        int databaseSizeBeforeDelete = dmloaibenhlyRepository.findAll().size();

        // Delete the dmloaibenhly
        restDmloaibenhlyMockMvc.perform(delete("/api/dmloaibenhlies/{id}", dmloaibenhly.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmloaibenhly> dmloaibenhlyList = dmloaibenhlyRepository.findAll();
        assertThat(dmloaibenhlyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
