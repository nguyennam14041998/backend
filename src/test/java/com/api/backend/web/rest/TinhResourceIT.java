package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Tinh;
import com.api.backend.domain.Huyen;
import com.api.backend.domain.Cosokhambenh;
import com.api.backend.repository.TinhRepository;
import com.api.backend.service.TinhService;
import com.api.backend.service.dto.TinhDTO;
import com.api.backend.service.mapper.TinhMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.TinhCriteria;
import com.api.backend.service.TinhQueryService;

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
 * Integration tests for the {@link TinhResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class TinhResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private TinhRepository tinhRepository;

    @Autowired
    private TinhMapper tinhMapper;

    @Autowired
    private TinhService tinhService;

    @Autowired
    private TinhQueryService tinhQueryService;

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

    private MockMvc restTinhMockMvc;

    private Tinh tinh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TinhResource tinhResource = new TinhResource(tinhService, tinhQueryService);
        this.restTinhMockMvc = MockMvcBuilders.standaloneSetup(tinhResource)
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
    public static Tinh createEntity(EntityManager em) {
        Tinh tinh = new Tinh()
            .ten(DEFAULT_TEN);
        return tinh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tinh createUpdatedEntity(EntityManager em) {
        Tinh tinh = new Tinh()
            .ten(UPDATED_TEN);
        return tinh;
    }

    @BeforeEach
    public void initTest() {
        tinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createTinh() throws Exception {
        int databaseSizeBeforeCreate = tinhRepository.findAll().size();

        // Create the Tinh
        TinhDTO tinhDTO = tinhMapper.toDto(tinh);
        restTinhMockMvc.perform(post("/api/tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhDTO)))
            .andExpect(status().isCreated());

        // Validate the Tinh in the database
        List<Tinh> tinhList = tinhRepository.findAll();
        assertThat(tinhList).hasSize(databaseSizeBeforeCreate + 1);
        Tinh testTinh = tinhList.get(tinhList.size() - 1);
        assertThat(testTinh.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createTinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tinhRepository.findAll().size();

        // Create the Tinh with an existing ID
        tinh.setId(1L);
        TinhDTO tinhDTO = tinhMapper.toDto(tinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTinhMockMvc.perform(post("/api/tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tinh in the database
        List<Tinh> tinhList = tinhRepository.findAll();
        assertThat(tinhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTinhs() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList
        restTinhMockMvc.perform(get("/api/tinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getTinh() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get the tinh
        restTinhMockMvc.perform(get("/api/tinhs/{id}", tinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tinh.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }


    @Test
    @Transactional
    public void getTinhsByIdFiltering() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        Long id = tinh.getId();

        defaultTinhShouldBeFound("id.equals=" + id);
        defaultTinhShouldNotBeFound("id.notEquals=" + id);

        defaultTinhShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTinhShouldNotBeFound("id.greaterThan=" + id);

        defaultTinhShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTinhShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTinhsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten equals to DEFAULT_TEN
        defaultTinhShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the tinhList where ten equals to UPDATED_TEN
        defaultTinhShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllTinhsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten not equals to DEFAULT_TEN
        defaultTinhShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the tinhList where ten not equals to UPDATED_TEN
        defaultTinhShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllTinhsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultTinhShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the tinhList where ten equals to UPDATED_TEN
        defaultTinhShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllTinhsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten is not null
        defaultTinhShouldBeFound("ten.specified=true");

        // Get all the tinhList where ten is null
        defaultTinhShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllTinhsByTenContainsSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten contains DEFAULT_TEN
        defaultTinhShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the tinhList where ten contains UPDATED_TEN
        defaultTinhShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllTinhsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        // Get all the tinhList where ten does not contain DEFAULT_TEN
        defaultTinhShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the tinhList where ten does not contain UPDATED_TEN
        defaultTinhShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllTinhsByHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);
        Huyen huyen = HuyenResourceIT.createEntity(em);
        em.persist(huyen);
        em.flush();
        tinh.addHuyen(huyen);
        tinhRepository.saveAndFlush(tinh);
        Long huyenId = huyen.getId();

        // Get all the tinhList where huyen equals to huyenId
        defaultTinhShouldBeFound("huyenId.equals=" + huyenId);

        // Get all the tinhList where huyen equals to huyenId + 1
        defaultTinhShouldNotBeFound("huyenId.equals=" + (huyenId + 1));
    }


    @Test
    @Transactional
    public void getAllTinhsByCosokhambenhIsEqualToSomething() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);
        Cosokhambenh cosokhambenh = CosokhambenhResourceIT.createEntity(em);
        em.persist(cosokhambenh);
        em.flush();
        tinh.addCosokhambenh(cosokhambenh);
        tinhRepository.saveAndFlush(tinh);
        Long cosokhambenhId = cosokhambenh.getId();

        // Get all the tinhList where cosokhambenh equals to cosokhambenhId
        defaultTinhShouldBeFound("cosokhambenhId.equals=" + cosokhambenhId);

        // Get all the tinhList where cosokhambenh equals to cosokhambenhId + 1
        defaultTinhShouldNotBeFound("cosokhambenhId.equals=" + (cosokhambenhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTinhShouldBeFound(String filter) throws Exception {
        restTinhMockMvc.perform(get("/api/tinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));

        // Check, that the count call also returns 1
        restTinhMockMvc.perform(get("/api/tinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTinhShouldNotBeFound(String filter) throws Exception {
        restTinhMockMvc.perform(get("/api/tinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTinhMockMvc.perform(get("/api/tinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTinh() throws Exception {
        // Get the tinh
        restTinhMockMvc.perform(get("/api/tinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTinh() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        int databaseSizeBeforeUpdate = tinhRepository.findAll().size();

        // Update the tinh
        Tinh updatedTinh = tinhRepository.findById(tinh.getId()).get();
        // Disconnect from session so that the updates on updatedTinh are not directly saved in db
        em.detach(updatedTinh);
        updatedTinh
            .ten(UPDATED_TEN);
        TinhDTO tinhDTO = tinhMapper.toDto(updatedTinh);

        restTinhMockMvc.perform(put("/api/tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhDTO)))
            .andExpect(status().isOk());

        // Validate the Tinh in the database
        List<Tinh> tinhList = tinhRepository.findAll();
        assertThat(tinhList).hasSize(databaseSizeBeforeUpdate);
        Tinh testTinh = tinhList.get(tinhList.size() - 1);
        assertThat(testTinh.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingTinh() throws Exception {
        int databaseSizeBeforeUpdate = tinhRepository.findAll().size();

        // Create the Tinh
        TinhDTO tinhDTO = tinhMapper.toDto(tinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTinhMockMvc.perform(put("/api/tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tinh in the database
        List<Tinh> tinhList = tinhRepository.findAll();
        assertThat(tinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTinh() throws Exception {
        // Initialize the database
        tinhRepository.saveAndFlush(tinh);

        int databaseSizeBeforeDelete = tinhRepository.findAll().size();

        // Delete the tinh
        restTinhMockMvc.perform(delete("/api/tinhs/{id}", tinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tinh> tinhList = tinhRepository.findAll();
        assertThat(tinhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
