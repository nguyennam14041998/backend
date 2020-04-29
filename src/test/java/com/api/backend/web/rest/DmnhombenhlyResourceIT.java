package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.domain.Dmbenhly;
import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.repository.DmnhombenhlyRepository;
import com.api.backend.service.DmnhombenhlyService;
import com.api.backend.service.dto.DmnhombenhlyDTO;
import com.api.backend.service.mapper.DmnhombenhlyMapper;
import com.api.backend.service.mapper.MyDanhMucNhomBenhLyMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmnhombenhlyCriteria;
import com.api.backend.service.DmnhombenhlyQueryService;

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
 * Integration tests for the {@link DmnhombenhlyResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmnhombenhlyResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    @Autowired
    private DmnhombenhlyRepository dmnhombenhlyRepository;

    @Autowired
    private MyDanhMucNhomBenhLyMapper dmnhombenhlyMapper;

    @Autowired
    private DmnhombenhlyService dmnhombenhlyService;

    @Autowired
    private DmnhombenhlyQueryService dmnhombenhlyQueryService;

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

    private MockMvc restDmnhombenhlyMockMvc;

    private Dmnhombenhly dmnhombenhly;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmnhombenhlyResource dmnhombenhlyResource = new DmnhombenhlyResource(dmnhombenhlyService, dmnhombenhlyQueryService);
        this.restDmnhombenhlyMockMvc = MockMvcBuilders.standaloneSetup(dmnhombenhlyResource)
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
    public static Dmnhombenhly createEntity(EntityManager em) {
        Dmnhombenhly dmnhombenhly = new Dmnhombenhly()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .mota(DEFAULT_MOTA);
        return dmnhombenhly;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmnhombenhly createUpdatedEntity(EntityManager em) {
        Dmnhombenhly dmnhombenhly = new Dmnhombenhly()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA);
        return dmnhombenhly;
    }

    @BeforeEach
    public void initTest() {
        dmnhombenhly = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmnhombenhly() throws Exception {
        int databaseSizeBeforeCreate = dmnhombenhlyRepository.findAll().size();

        // Create the Dmnhombenhly
        DmnhombenhlyDTO dmnhombenhlyDTO = dmnhombenhlyMapper.toDto(dmnhombenhly);
        restDmnhombenhlyMockMvc.perform(post("/api/dmnhombenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhombenhlyDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmnhombenhly in the database
        List<Dmnhombenhly> dmnhombenhlyList = dmnhombenhlyRepository.findAll();
        assertThat(dmnhombenhlyList).hasSize(databaseSizeBeforeCreate + 1);
        Dmnhombenhly testDmnhombenhly = dmnhombenhlyList.get(dmnhombenhlyList.size() - 1);
        assertThat(testDmnhombenhly.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmnhombenhly.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmnhombenhly.getMota()).isEqualTo(DEFAULT_MOTA);
    }

    @Test
    @Transactional
    public void createDmnhombenhlyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmnhombenhlyRepository.findAll().size();

        // Create the Dmnhombenhly with an existing ID
        dmnhombenhly.setId(1L);
        DmnhombenhlyDTO dmnhombenhlyDTO = dmnhombenhlyMapper.toDto(dmnhombenhly);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmnhombenhlyMockMvc.perform(post("/api/dmnhombenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhombenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhombenhly in the database
        List<Dmnhombenhly> dmnhombenhlyList = dmnhombenhlyRepository.findAll();
        assertThat(dmnhombenhlyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmnhombenhlies() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhombenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)));
    }

    @Test
    @Transactional
    public void getDmnhombenhly() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get the dmnhombenhly
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies/{id}", dmnhombenhly.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmnhombenhly.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA));
    }


    @Test
    @Transactional
    public void getDmnhombenhliesByIdFiltering() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        Long id = dmnhombenhly.getId();

        defaultDmnhombenhlyShouldBeFound("id.equals=" + id);
        defaultDmnhombenhlyShouldNotBeFound("id.notEquals=" + id);

        defaultDmnhombenhlyShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmnhombenhlyShouldNotBeFound("id.greaterThan=" + id);

        defaultDmnhombenhlyShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmnhombenhlyShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmnhombenhliesByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma equals to DEFAULT_MA
        defaultDmnhombenhlyShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmnhombenhlyList where ma equals to UPDATED_MA
        defaultDmnhombenhlyShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma not equals to DEFAULT_MA
        defaultDmnhombenhlyShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmnhombenhlyList where ma not equals to UPDATED_MA
        defaultDmnhombenhlyShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmnhombenhlyShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmnhombenhlyList where ma equals to UPDATED_MA
        defaultDmnhombenhlyShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma is not null
        defaultDmnhombenhlyShouldBeFound("ma.specified=true");

        // Get all the dmnhombenhlyList where ma is null
        defaultDmnhombenhlyShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhombenhliesByMaContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma contains DEFAULT_MA
        defaultDmnhombenhlyShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmnhombenhlyList where ma contains UPDATED_MA
        defaultDmnhombenhlyShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ma does not contain DEFAULT_MA
        defaultDmnhombenhlyShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmnhombenhlyList where ma does not contain UPDATED_MA
        defaultDmnhombenhlyShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmnhombenhliesByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten equals to DEFAULT_TEN
        defaultDmnhombenhlyShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmnhombenhlyList where ten equals to UPDATED_TEN
        defaultDmnhombenhlyShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten not equals to DEFAULT_TEN
        defaultDmnhombenhlyShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmnhombenhlyList where ten not equals to UPDATED_TEN
        defaultDmnhombenhlyShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmnhombenhlyShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmnhombenhlyList where ten equals to UPDATED_TEN
        defaultDmnhombenhlyShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten is not null
        defaultDmnhombenhlyShouldBeFound("ten.specified=true");

        // Get all the dmnhombenhlyList where ten is null
        defaultDmnhombenhlyShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhombenhliesByTenContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten contains DEFAULT_TEN
        defaultDmnhombenhlyShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmnhombenhlyList where ten contains UPDATED_TEN
        defaultDmnhombenhlyShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where ten does not contain DEFAULT_TEN
        defaultDmnhombenhlyShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmnhombenhlyList where ten does not contain UPDATED_TEN
        defaultDmnhombenhlyShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota equals to DEFAULT_MOTA
        defaultDmnhombenhlyShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmnhombenhlyList where mota equals to UPDATED_MOTA
        defaultDmnhombenhlyShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota not equals to DEFAULT_MOTA
        defaultDmnhombenhlyShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmnhombenhlyList where mota not equals to UPDATED_MOTA
        defaultDmnhombenhlyShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmnhombenhlyShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmnhombenhlyList where mota equals to UPDATED_MOTA
        defaultDmnhombenhlyShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota is not null
        defaultDmnhombenhlyShouldBeFound("mota.specified=true");

        // Get all the dmnhombenhlyList where mota is null
        defaultDmnhombenhlyShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota contains DEFAULT_MOTA
        defaultDmnhombenhlyShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmnhombenhlyList where mota contains UPDATED_MOTA
        defaultDmnhombenhlyShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmnhombenhliesByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        // Get all the dmnhombenhlyList where mota does not contain DEFAULT_MOTA
        defaultDmnhombenhlyShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmnhombenhlyList where mota does not contain UPDATED_MOTA
        defaultDmnhombenhlyShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmnhombenhliesByDmbenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);
        Dmbenhly dmbenhly = DmbenhlyResourceIT.createEntity(em);
        em.persist(dmbenhly);
        em.flush();
        dmnhombenhly.addDmbenhly(dmbenhly);
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);
        Long dmbenhlyId = dmbenhly.getId();

        // Get all the dmnhombenhlyList where dmbenhly equals to dmbenhlyId
        defaultDmnhombenhlyShouldBeFound("dmbenhlyId.equals=" + dmbenhlyId);

        // Get all the dmnhombenhlyList where dmbenhly equals to dmbenhlyId + 1
        defaultDmnhombenhlyShouldNotBeFound("dmbenhlyId.equals=" + (dmbenhlyId + 1));
    }


    @Test
    @Transactional
    public void getAllDmnhombenhliesByDmloaibenhlyIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);
        Dmloaibenhly dmloaibenhly = DmloaibenhlyResourceIT.createEntity(em);
        em.persist(dmloaibenhly);
        em.flush();
        dmnhombenhly.setDmloaibenhly(dmloaibenhly);
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);
        Long dmloaibenhlyId = dmloaibenhly.getId();

        // Get all the dmnhombenhlyList where dmloaibenhly equals to dmloaibenhlyId
        defaultDmnhombenhlyShouldBeFound("dmloaibenhlyId.equals=" + dmloaibenhlyId);

        // Get all the dmnhombenhlyList where dmloaibenhly equals to dmloaibenhlyId + 1
        defaultDmnhombenhlyShouldNotBeFound("dmloaibenhlyId.equals=" + (dmloaibenhlyId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmnhombenhlyShouldBeFound(String filter) throws Exception {
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhombenhly.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)));

        // Check, that the count call also returns 1
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmnhombenhlyShouldNotBeFound(String filter) throws Exception {
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmnhombenhly() throws Exception {
        // Get the dmnhombenhly
        restDmnhombenhlyMockMvc.perform(get("/api/dmnhombenhlies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmnhombenhly() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        int databaseSizeBeforeUpdate = dmnhombenhlyRepository.findAll().size();

        // Update the dmnhombenhly
        Dmnhombenhly updatedDmnhombenhly = dmnhombenhlyRepository.findById(dmnhombenhly.getId()).get();
        // Disconnect from session so that the updates on updatedDmnhombenhly are not directly saved in db
        em.detach(updatedDmnhombenhly);
        updatedDmnhombenhly
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .mota(UPDATED_MOTA);
        DmnhombenhlyDTO dmnhombenhlyDTO = dmnhombenhlyMapper.toDto(updatedDmnhombenhly);

        restDmnhombenhlyMockMvc.perform(put("/api/dmnhombenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhombenhlyDTO)))
            .andExpect(status().isOk());

        // Validate the Dmnhombenhly in the database
        List<Dmnhombenhly> dmnhombenhlyList = dmnhombenhlyRepository.findAll();
        assertThat(dmnhombenhlyList).hasSize(databaseSizeBeforeUpdate);
        Dmnhombenhly testDmnhombenhly = dmnhombenhlyList.get(dmnhombenhlyList.size() - 1);
        assertThat(testDmnhombenhly.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmnhombenhly.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmnhombenhly.getMota()).isEqualTo(UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void updateNonExistingDmnhombenhly() throws Exception {
        int databaseSizeBeforeUpdate = dmnhombenhlyRepository.findAll().size();

        // Create the Dmnhombenhly
        DmnhombenhlyDTO dmnhombenhlyDTO = dmnhombenhlyMapper.toDto(dmnhombenhly);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmnhombenhlyMockMvc.perform(put("/api/dmnhombenhlies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhombenhlyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmnhombenhly in the database
        List<Dmnhombenhly> dmnhombenhlyList = dmnhombenhlyRepository.findAll();
        assertThat(dmnhombenhlyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmnhombenhly() throws Exception {
        // Initialize the database
        dmnhombenhlyRepository.saveAndFlush(dmnhombenhly);

        int databaseSizeBeforeDelete = dmnhombenhlyRepository.findAll().size();

        // Delete the dmnhombenhly
        restDmnhombenhlyMockMvc.perform(delete("/api/dmnhombenhlies/{id}", dmnhombenhly.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmnhombenhly> dmnhombenhlyList = dmnhombenhlyRepository.findAll();
        assertThat(dmnhombenhlyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
