package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.DmnhomCDHA;
import com.api.backend.domain.DmCDHA;
import com.api.backend.repository.DmnhomCDHARepository;
import com.api.backend.service.DmnhomCDHAService;
import com.api.backend.service.dto.DmnhomCDHADTO;
import com.api.backend.service.mapper.DmnhomCDHAMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
import com.api.backend.service.dto.DmnhomCDHACriteria;
import com.api.backend.service.DmnhomCDHAQueryService;

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
 * Integration tests for the {@link DmnhomCDHAResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmnhomCDHAResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAI = 1;
    private static final Integer UPDATED_LOAI = 2;
    private static final Integer SMALLER_LOAI = 1 - 1;

    private static final String DEFAULT_MA_BH = "AAAAAAAAAA";
    private static final String UPDATED_MA_BH = "BBBBBBBBBB";

    @Autowired
    private DmnhomCDHARepository dmnhomCDHARepository;

    @Autowired
    private DmnhomCDHAMapper dmnhomCDHAMapper;

    @Autowired
    private DmnhomCDHAService dmnhomCDHAService;

    @Autowired
    private DmnhomCDHAQueryService dmnhomCDHAQueryService;

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

    private MockMvc restDmnhomCDHAMockMvc;

    private DmnhomCDHA dmnhomCDHA;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmnhomCDHAResource dmnhomCDHAResource = new DmnhomCDHAResource(dmnhomCDHAService, dmnhomCDHAQueryService);
        this.restDmnhomCDHAMockMvc = MockMvcBuilders.standaloneSetup(dmnhomCDHAResource)
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
    public static DmnhomCDHA createEntity(EntityManager em) {
        DmnhomCDHA dmnhomCDHA = new DmnhomCDHA()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .loai(DEFAULT_LOAI)
            .maBH(DEFAULT_MA_BH);
        return dmnhomCDHA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmnhomCDHA createUpdatedEntity(EntityManager em) {
        DmnhomCDHA dmnhomCDHA = new DmnhomCDHA()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        return dmnhomCDHA;
    }

    @BeforeEach
    public void initTest() {
        dmnhomCDHA = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmnhomCDHA() throws Exception {
        int databaseSizeBeforeCreate = dmnhomCDHARepository.findAll().size();

        // Create the DmnhomCDHA
        DmnhomCDHADTO dmnhomCDHADTO = dmnhomCDHAMapper.toDto(dmnhomCDHA);
        restDmnhomCDHAMockMvc.perform(post("/api/dmnhom-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomCDHADTO)))
            .andExpect(status().isCreated());

        // Validate the DmnhomCDHA in the database
        List<DmnhomCDHA> dmnhomCDHAList = dmnhomCDHARepository.findAll();
        assertThat(dmnhomCDHAList).hasSize(databaseSizeBeforeCreate + 1);
        DmnhomCDHA testDmnhomCDHA = dmnhomCDHAList.get(dmnhomCDHAList.size() - 1);
        assertThat(testDmnhomCDHA.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmnhomCDHA.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmnhomCDHA.getLoai()).isEqualTo(DEFAULT_LOAI);
        assertThat(testDmnhomCDHA.getMaBH()).isEqualTo(DEFAULT_MA_BH);
    }

    @Test
    @Transactional
    public void createDmnhomCDHAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmnhomCDHARepository.findAll().size();

        // Create the DmnhomCDHA with an existing ID
        dmnhomCDHA.setId(1L);
        DmnhomCDHADTO dmnhomCDHADTO = dmnhomCDHAMapper.toDto(dmnhomCDHA);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmnhomCDHAMockMvc.perform(post("/api/dmnhom-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomCDHADTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmnhomCDHA in the database
        List<DmnhomCDHA> dmnhomCDHAList = dmnhomCDHARepository.findAll();
        assertThat(dmnhomCDHAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHAS() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomCDHA.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));
    }
    
    @Test
    @Transactional
    public void getDmnhomCDHA() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get the dmnhomCDHA
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas/{id}", dmnhomCDHA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmnhomCDHA.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.loai").value(DEFAULT_LOAI))
            .andExpect(jsonPath("$.maBH").value(DEFAULT_MA_BH));
    }


    @Test
    @Transactional
    public void getDmnhomCDHASByIdFiltering() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        Long id = dmnhomCDHA.getId();

        defaultDmnhomCDHAShouldBeFound("id.equals=" + id);
        defaultDmnhomCDHAShouldNotBeFound("id.notEquals=" + id);

        defaultDmnhomCDHAShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmnhomCDHAShouldNotBeFound("id.greaterThan=" + id);

        defaultDmnhomCDHAShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmnhomCDHAShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma equals to DEFAULT_MA
        defaultDmnhomCDHAShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmnhomCDHAList where ma equals to UPDATED_MA
        defaultDmnhomCDHAShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma not equals to DEFAULT_MA
        defaultDmnhomCDHAShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmnhomCDHAList where ma not equals to UPDATED_MA
        defaultDmnhomCDHAShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmnhomCDHAShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmnhomCDHAList where ma equals to UPDATED_MA
        defaultDmnhomCDHAShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma is not null
        defaultDmnhomCDHAShouldBeFound("ma.specified=true");

        // Get all the dmnhomCDHAList where ma is null
        defaultDmnhomCDHAShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomCDHASByMaContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma contains DEFAULT_MA
        defaultDmnhomCDHAShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmnhomCDHAList where ma contains UPDATED_MA
        defaultDmnhomCDHAShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ma does not contain DEFAULT_MA
        defaultDmnhomCDHAShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmnhomCDHAList where ma does not contain UPDATED_MA
        defaultDmnhomCDHAShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHASByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten equals to DEFAULT_TEN
        defaultDmnhomCDHAShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmnhomCDHAList where ten equals to UPDATED_TEN
        defaultDmnhomCDHAShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten not equals to DEFAULT_TEN
        defaultDmnhomCDHAShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmnhomCDHAList where ten not equals to UPDATED_TEN
        defaultDmnhomCDHAShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmnhomCDHAShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmnhomCDHAList where ten equals to UPDATED_TEN
        defaultDmnhomCDHAShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten is not null
        defaultDmnhomCDHAShouldBeFound("ten.specified=true");

        // Get all the dmnhomCDHAList where ten is null
        defaultDmnhomCDHAShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomCDHASByTenContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten contains DEFAULT_TEN
        defaultDmnhomCDHAShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmnhomCDHAList where ten contains UPDATED_TEN
        defaultDmnhomCDHAShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where ten does not contain DEFAULT_TEN
        defaultDmnhomCDHAShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmnhomCDHAList where ten does not contain UPDATED_TEN
        defaultDmnhomCDHAShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai equals to DEFAULT_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.equals=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai equals to UPDATED_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.equals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai not equals to DEFAULT_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.notEquals=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai not equals to UPDATED_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.notEquals=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai in DEFAULT_LOAI or UPDATED_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.in=" + DEFAULT_LOAI + "," + UPDATED_LOAI);

        // Get all the dmnhomCDHAList where loai equals to UPDATED_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.in=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai is not null
        defaultDmnhomCDHAShouldBeFound("loai.specified=true");

        // Get all the dmnhomCDHAList where loai is null
        defaultDmnhomCDHAShouldNotBeFound("loai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai is greater than or equal to DEFAULT_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.greaterThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai is greater than or equal to UPDATED_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.greaterThanOrEqual=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai is less than or equal to DEFAULT_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.lessThanOrEqual=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai is less than or equal to SMALLER_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.lessThanOrEqual=" + SMALLER_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai is less than DEFAULT_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.lessThan=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai is less than UPDATED_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.lessThan=" + UPDATED_LOAI);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where loai is greater than DEFAULT_LOAI
        defaultDmnhomCDHAShouldNotBeFound("loai.greaterThan=" + DEFAULT_LOAI);

        // Get all the dmnhomCDHAList where loai is greater than SMALLER_LOAI
        defaultDmnhomCDHAShouldBeFound("loai.greaterThan=" + SMALLER_LOAI);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH equals to DEFAULT_MA_BH
        defaultDmnhomCDHAShouldBeFound("maBH.equals=" + DEFAULT_MA_BH);

        // Get all the dmnhomCDHAList where maBH equals to UPDATED_MA_BH
        defaultDmnhomCDHAShouldNotBeFound("maBH.equals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH not equals to DEFAULT_MA_BH
        defaultDmnhomCDHAShouldNotBeFound("maBH.notEquals=" + DEFAULT_MA_BH);

        // Get all the dmnhomCDHAList where maBH not equals to UPDATED_MA_BH
        defaultDmnhomCDHAShouldBeFound("maBH.notEquals=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH in DEFAULT_MA_BH or UPDATED_MA_BH
        defaultDmnhomCDHAShouldBeFound("maBH.in=" + DEFAULT_MA_BH + "," + UPDATED_MA_BH);

        // Get all the dmnhomCDHAList where maBH equals to UPDATED_MA_BH
        defaultDmnhomCDHAShouldNotBeFound("maBH.in=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH is not null
        defaultDmnhomCDHAShouldBeFound("maBH.specified=true");

        // Get all the dmnhomCDHAList where maBH is null
        defaultDmnhomCDHAShouldNotBeFound("maBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH contains DEFAULT_MA_BH
        defaultDmnhomCDHAShouldBeFound("maBH.contains=" + DEFAULT_MA_BH);

        // Get all the dmnhomCDHAList where maBH contains UPDATED_MA_BH
        defaultDmnhomCDHAShouldNotBeFound("maBH.contains=" + UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void getAllDmnhomCDHASByMaBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        // Get all the dmnhomCDHAList where maBH does not contain DEFAULT_MA_BH
        defaultDmnhomCDHAShouldNotBeFound("maBH.doesNotContain=" + DEFAULT_MA_BH);

        // Get all the dmnhomCDHAList where maBH does not contain UPDATED_MA_BH
        defaultDmnhomCDHAShouldBeFound("maBH.doesNotContain=" + UPDATED_MA_BH);
    }


    @Test
    @Transactional
    public void getAllDmnhomCDHASByDmCDHAIsEqualToSomething() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);
        DmCDHA dmCDHA = DmCDHAResourceIT.createEntity(em);
        em.persist(dmCDHA);
        em.flush();
        dmnhomCDHA.addDmCDHA(dmCDHA);
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);
        Long dmCDHAId = dmCDHA.getId();

        // Get all the dmnhomCDHAList where dmCDHA equals to dmCDHAId
        defaultDmnhomCDHAShouldBeFound("dmCDHAId.equals=" + dmCDHAId);

        // Get all the dmnhomCDHAList where dmCDHA equals to dmCDHAId + 1
        defaultDmnhomCDHAShouldNotBeFound("dmCDHAId.equals=" + (dmCDHAId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmnhomCDHAShouldBeFound(String filter) throws Exception {
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmnhomCDHA.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].loai").value(hasItem(DEFAULT_LOAI)))
            .andExpect(jsonPath("$.[*].maBH").value(hasItem(DEFAULT_MA_BH)));

        // Check, that the count call also returns 1
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmnhomCDHAShouldNotBeFound(String filter) throws Exception {
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmnhomCDHA() throws Exception {
        // Get the dmnhomCDHA
        restDmnhomCDHAMockMvc.perform(get("/api/dmnhom-cdhas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmnhomCDHA() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        int databaseSizeBeforeUpdate = dmnhomCDHARepository.findAll().size();

        // Update the dmnhomCDHA
        DmnhomCDHA updatedDmnhomCDHA = dmnhomCDHARepository.findById(dmnhomCDHA.getId()).get();
        // Disconnect from session so that the updates on updatedDmnhomCDHA are not directly saved in db
        em.detach(updatedDmnhomCDHA);
        updatedDmnhomCDHA
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .loai(UPDATED_LOAI)
            .maBH(UPDATED_MA_BH);
        DmnhomCDHADTO dmnhomCDHADTO = dmnhomCDHAMapper.toDto(updatedDmnhomCDHA);

        restDmnhomCDHAMockMvc.perform(put("/api/dmnhom-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomCDHADTO)))
            .andExpect(status().isOk());

        // Validate the DmnhomCDHA in the database
        List<DmnhomCDHA> dmnhomCDHAList = dmnhomCDHARepository.findAll();
        assertThat(dmnhomCDHAList).hasSize(databaseSizeBeforeUpdate);
        DmnhomCDHA testDmnhomCDHA = dmnhomCDHAList.get(dmnhomCDHAList.size() - 1);
        assertThat(testDmnhomCDHA.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmnhomCDHA.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmnhomCDHA.getLoai()).isEqualTo(UPDATED_LOAI);
        assertThat(testDmnhomCDHA.getMaBH()).isEqualTo(UPDATED_MA_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmnhomCDHA() throws Exception {
        int databaseSizeBeforeUpdate = dmnhomCDHARepository.findAll().size();

        // Create the DmnhomCDHA
        DmnhomCDHADTO dmnhomCDHADTO = dmnhomCDHAMapper.toDto(dmnhomCDHA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmnhomCDHAMockMvc.perform(put("/api/dmnhom-cdhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmnhomCDHADTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmnhomCDHA in the database
        List<DmnhomCDHA> dmnhomCDHAList = dmnhomCDHARepository.findAll();
        assertThat(dmnhomCDHAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmnhomCDHA() throws Exception {
        // Initialize the database
        dmnhomCDHARepository.saveAndFlush(dmnhomCDHA);

        int databaseSizeBeforeDelete = dmnhomCDHARepository.findAll().size();

        // Delete the dmnhomCDHA
        restDmnhomCDHAMockMvc.perform(delete("/api/dmnhom-cdhas/{id}", dmnhomCDHA.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DmnhomCDHA> dmnhomCDHAList = dmnhomCDHARepository.findAll();
        assertThat(dmnhomCDHAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
