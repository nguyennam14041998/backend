package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Huyen;
import com.api.backend.domain.Xa;
import com.api.backend.domain.Cosokhambenh;
import com.api.backend.domain.Tinh;
import com.api.backend.repository.HuyenRepository;
import com.api.backend.service.HuyenService;
import com.api.backend.service.dto.HuyenDTO;
import com.api.backend.service.mapper.HuyenMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.HuyenCriteria;
import com.api.backend.service.HuyenQueryService;

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
 * Integration tests for the {@link HuyenResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class HuyenResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private HuyenRepository huyenRepository;

    @Autowired
    private HuyenMapper huyenMapper;

    @Autowired
    private HuyenService huyenService;

    @Autowired
    private HuyenQueryService huyenQueryService;

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

    private MockMvc restHuyenMockMvc;

    private Huyen huyen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HuyenResource huyenResource = new HuyenResource(huyenService, huyenQueryService);
        this.restHuyenMockMvc = MockMvcBuilders.standaloneSetup(huyenResource)
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
    public static Huyen createEntity(EntityManager em) {
        Huyen huyen = new Huyen()
            .ten(DEFAULT_TEN);
        return huyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Huyen createUpdatedEntity(EntityManager em) {
        Huyen huyen = new Huyen()
            .ten(UPDATED_TEN);
        return huyen;
    }

    @BeforeEach
    public void initTest() {
        huyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createHuyen() throws Exception {
        int databaseSizeBeforeCreate = huyenRepository.findAll().size();

        // Create the Huyen
        HuyenDTO huyenDTO = huyenMapper.toDto(huyen);
        restHuyenMockMvc.perform(post("/api/huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huyenDTO)))
            .andExpect(status().isCreated());

        // Validate the Huyen in the database
        List<Huyen> huyenList = huyenRepository.findAll();
        assertThat(huyenList).hasSize(databaseSizeBeforeCreate + 1);
        Huyen testHuyen = huyenList.get(huyenList.size() - 1);
        assertThat(testHuyen.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createHuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = huyenRepository.findAll().size();

        // Create the Huyen with an existing ID
        huyen.setId(1L);
        HuyenDTO huyenDTO = huyenMapper.toDto(huyen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHuyenMockMvc.perform(post("/api/huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Huyen in the database
        List<Huyen> huyenList = huyenRepository.findAll();
        assertThat(huyenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHuyens() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList
        restHuyenMockMvc.perform(get("/api/huyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(huyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getHuyen() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get the huyen
        restHuyenMockMvc.perform(get("/api/huyens/{id}", huyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(huyen.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }


    @Test
    @Transactional
    public void getHuyensByIdFiltering() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        Long id = huyen.getId();

        defaultHuyenShouldBeFound("id.equals=" + id);
        defaultHuyenShouldNotBeFound("id.notEquals=" + id);

        defaultHuyenShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHuyenShouldNotBeFound("id.greaterThan=" + id);

        defaultHuyenShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHuyenShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllHuyensByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten equals to DEFAULT_TEN
        defaultHuyenShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the huyenList where ten equals to UPDATED_TEN
        defaultHuyenShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllHuyensByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten not equals to DEFAULT_TEN
        defaultHuyenShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the huyenList where ten not equals to UPDATED_TEN
        defaultHuyenShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllHuyensByTenIsInShouldWork() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultHuyenShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the huyenList where ten equals to UPDATED_TEN
        defaultHuyenShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllHuyensByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten is not null
        defaultHuyenShouldBeFound("ten.specified=true");

        // Get all the huyenList where ten is null
        defaultHuyenShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllHuyensByTenContainsSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten contains DEFAULT_TEN
        defaultHuyenShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the huyenList where ten contains UPDATED_TEN
        defaultHuyenShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllHuyensByTenNotContainsSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        // Get all the huyenList where ten does not contain DEFAULT_TEN
        defaultHuyenShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the huyenList where ten does not contain UPDATED_TEN
        defaultHuyenShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllHuyensByXaIsEqualToSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);
        Xa xa = XaResourceIT.createEntity(em);
        em.persist(xa);
        em.flush();
        huyen.addXa(xa);
        huyenRepository.saveAndFlush(huyen);
        Long xaId = xa.getId();

        // Get all the huyenList where xa equals to xaId
        defaultHuyenShouldBeFound("xaId.equals=" + xaId);

        // Get all the huyenList where xa equals to xaId + 1
        defaultHuyenShouldNotBeFound("xaId.equals=" + (xaId + 1));
    }


    @Test
    @Transactional
    public void getAllHuyensByCosokhambenhIsEqualToSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);
        Cosokhambenh cosokhambenh = CosokhambenhResourceIT.createEntity(em);
        em.persist(cosokhambenh);
        em.flush();
        huyen.addCosokhambenh(cosokhambenh);
        huyenRepository.saveAndFlush(huyen);
        Long cosokhambenhId = cosokhambenh.getId();

        // Get all the huyenList where cosokhambenh equals to cosokhambenhId
        defaultHuyenShouldBeFound("cosokhambenhId.equals=" + cosokhambenhId);

        // Get all the huyenList where cosokhambenh equals to cosokhambenhId + 1
        defaultHuyenShouldNotBeFound("cosokhambenhId.equals=" + (cosokhambenhId + 1));
    }


    @Test
    @Transactional
    public void getAllHuyensByTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);
        Tinh tinh = TinhResourceIT.createEntity(em);
        em.persist(tinh);
        em.flush();
        huyen.setTinh(tinh);
        huyenRepository.saveAndFlush(huyen);
        Long tinhId = tinh.getId();

        // Get all the huyenList where tinh equals to tinhId
        defaultHuyenShouldBeFound("tinhId.equals=" + tinhId);

        // Get all the huyenList where tinh equals to tinhId + 1
        defaultHuyenShouldNotBeFound("tinhId.equals=" + (tinhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHuyenShouldBeFound(String filter) throws Exception {
        restHuyenMockMvc.perform(get("/api/huyens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(huyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));

        // Check, that the count call also returns 1
        restHuyenMockMvc.perform(get("/api/huyens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHuyenShouldNotBeFound(String filter) throws Exception {
        restHuyenMockMvc.perform(get("/api/huyens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHuyenMockMvc.perform(get("/api/huyens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingHuyen() throws Exception {
        // Get the huyen
        restHuyenMockMvc.perform(get("/api/huyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHuyen() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        int databaseSizeBeforeUpdate = huyenRepository.findAll().size();

        // Update the huyen
        Huyen updatedHuyen = huyenRepository.findById(huyen.getId()).get();
        // Disconnect from session so that the updates on updatedHuyen are not directly saved in db
        em.detach(updatedHuyen);
        updatedHuyen
            .ten(UPDATED_TEN);
        HuyenDTO huyenDTO = huyenMapper.toDto(updatedHuyen);

        restHuyenMockMvc.perform(put("/api/huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huyenDTO)))
            .andExpect(status().isOk());

        // Validate the Huyen in the database
        List<Huyen> huyenList = huyenRepository.findAll();
        assertThat(huyenList).hasSize(databaseSizeBeforeUpdate);
        Huyen testHuyen = huyenList.get(huyenList.size() - 1);
        assertThat(testHuyen.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingHuyen() throws Exception {
        int databaseSizeBeforeUpdate = huyenRepository.findAll().size();

        // Create the Huyen
        HuyenDTO huyenDTO = huyenMapper.toDto(huyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHuyenMockMvc.perform(put("/api/huyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Huyen in the database
        List<Huyen> huyenList = huyenRepository.findAll();
        assertThat(huyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHuyen() throws Exception {
        // Initialize the database
        huyenRepository.saveAndFlush(huyen);

        int databaseSizeBeforeDelete = huyenRepository.findAll().size();

        // Delete the huyen
        restHuyenMockMvc.perform(delete("/api/huyens/{id}", huyen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Huyen> huyenList = huyenRepository.findAll();
        assertThat(huyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
