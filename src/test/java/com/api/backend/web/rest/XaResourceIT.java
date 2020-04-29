package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Xa;
import com.api.backend.domain.Cosokhambenh;
import com.api.backend.domain.Huyen;
import com.api.backend.repository.XaRepository;
import com.api.backend.service.XaService;
import com.api.backend.service.dto.XaDTO;
import com.api.backend.service.mapper.XaMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.XaCriteria;
import com.api.backend.service.XaQueryService;

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
 * Integration tests for the {@link XaResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class XaResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private XaRepository xaRepository;

    @Autowired
    private XaMapper xaMapper;

    @Autowired
    private XaService xaService;

    @Autowired
    private XaQueryService xaQueryService;

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

    private MockMvc restXaMockMvc;

    private Xa xa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final XaResource xaResource = new XaResource(xaService, xaQueryService);
        this.restXaMockMvc = MockMvcBuilders.standaloneSetup(xaResource)
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
    public static Xa createEntity(EntityManager em) {
        Xa xa = new Xa()
            .ten(DEFAULT_TEN);
        return xa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Xa createUpdatedEntity(EntityManager em) {
        Xa xa = new Xa()
            .ten(UPDATED_TEN);
        return xa;
    }

    @BeforeEach
    public void initTest() {
        xa = createEntity(em);
    }

    @Test
    @Transactional
    public void createXa() throws Exception {
        int databaseSizeBeforeCreate = xaRepository.findAll().size();

        // Create the Xa
        XaDTO xaDTO = xaMapper.toDto(xa);
        restXaMockMvc.perform(post("/api/xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xaDTO)))
            .andExpect(status().isCreated());

        // Validate the Xa in the database
        List<Xa> xaList = xaRepository.findAll();
        assertThat(xaList).hasSize(databaseSizeBeforeCreate + 1);
        Xa testXa = xaList.get(xaList.size() - 1);
        assertThat(testXa.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createXaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = xaRepository.findAll().size();

        // Create the Xa with an existing ID
        xa.setId(1L);
        XaDTO xaDTO = xaMapper.toDto(xa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restXaMockMvc.perform(post("/api/xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Xa in the database
        List<Xa> xaList = xaRepository.findAll();
        assertThat(xaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllXas() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList
        restXaMockMvc.perform(get("/api/xas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(xa.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getXa() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get the xa
        restXaMockMvc.perform(get("/api/xas/{id}", xa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(xa.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }


    @Test
    @Transactional
    public void getXasByIdFiltering() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        Long id = xa.getId();

        defaultXaShouldBeFound("id.equals=" + id);
        defaultXaShouldNotBeFound("id.notEquals=" + id);

        defaultXaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultXaShouldNotBeFound("id.greaterThan=" + id);

        defaultXaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultXaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllXasByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten equals to DEFAULT_TEN
        defaultXaShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the xaList where ten equals to UPDATED_TEN
        defaultXaShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllXasByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten not equals to DEFAULT_TEN
        defaultXaShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the xaList where ten not equals to UPDATED_TEN
        defaultXaShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllXasByTenIsInShouldWork() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultXaShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the xaList where ten equals to UPDATED_TEN
        defaultXaShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllXasByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten is not null
        defaultXaShouldBeFound("ten.specified=true");

        // Get all the xaList where ten is null
        defaultXaShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllXasByTenContainsSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten contains DEFAULT_TEN
        defaultXaShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the xaList where ten contains UPDATED_TEN
        defaultXaShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllXasByTenNotContainsSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        // Get all the xaList where ten does not contain DEFAULT_TEN
        defaultXaShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the xaList where ten does not contain UPDATED_TEN
        defaultXaShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllXasByCosokhambenhIsEqualToSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);
        Cosokhambenh cosokhambenh = CosokhambenhResourceIT.createEntity(em);
        em.persist(cosokhambenh);
        em.flush();
        xa.addCosokhambenh(cosokhambenh);
        xaRepository.saveAndFlush(xa);
        Long cosokhambenhId = cosokhambenh.getId();

        // Get all the xaList where cosokhambenh equals to cosokhambenhId
        defaultXaShouldBeFound("cosokhambenhId.equals=" + cosokhambenhId);

        // Get all the xaList where cosokhambenh equals to cosokhambenhId + 1
        defaultXaShouldNotBeFound("cosokhambenhId.equals=" + (cosokhambenhId + 1));
    }


    @Test
    @Transactional
    public void getAllXasByHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);
        Huyen huyen = HuyenResourceIT.createEntity(em);
        em.persist(huyen);
        em.flush();
        xa.setHuyen(huyen);
        xaRepository.saveAndFlush(xa);
        Long huyenId = huyen.getId();

        // Get all the xaList where huyen equals to huyenId
        defaultXaShouldBeFound("huyenId.equals=" + huyenId);

        // Get all the xaList where huyen equals to huyenId + 1
        defaultXaShouldNotBeFound("huyenId.equals=" + (huyenId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultXaShouldBeFound(String filter) throws Exception {
        restXaMockMvc.perform(get("/api/xas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(xa.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));

        // Check, that the count call also returns 1
        restXaMockMvc.perform(get("/api/xas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultXaShouldNotBeFound(String filter) throws Exception {
        restXaMockMvc.perform(get("/api/xas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restXaMockMvc.perform(get("/api/xas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingXa() throws Exception {
        // Get the xa
        restXaMockMvc.perform(get("/api/xas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateXa() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        int databaseSizeBeforeUpdate = xaRepository.findAll().size();

        // Update the xa
        Xa updatedXa = xaRepository.findById(xa.getId()).get();
        // Disconnect from session so that the updates on updatedXa are not directly saved in db
        em.detach(updatedXa);
        updatedXa
            .ten(UPDATED_TEN);
        XaDTO xaDTO = xaMapper.toDto(updatedXa);

        restXaMockMvc.perform(put("/api/xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xaDTO)))
            .andExpect(status().isOk());

        // Validate the Xa in the database
        List<Xa> xaList = xaRepository.findAll();
        assertThat(xaList).hasSize(databaseSizeBeforeUpdate);
        Xa testXa = xaList.get(xaList.size() - 1);
        assertThat(testXa.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingXa() throws Exception {
        int databaseSizeBeforeUpdate = xaRepository.findAll().size();

        // Create the Xa
        XaDTO xaDTO = xaMapper.toDto(xa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restXaMockMvc.perform(put("/api/xas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Xa in the database
        List<Xa> xaList = xaRepository.findAll();
        assertThat(xaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteXa() throws Exception {
        // Initialize the database
        xaRepository.saveAndFlush(xa);

        int databaseSizeBeforeDelete = xaRepository.findAll().size();

        // Delete the xa
        restXaMockMvc.perform(delete("/api/xas/{id}", xa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Xa> xaList = xaRepository.findAll();
        assertThat(xaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
