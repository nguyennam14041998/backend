package com.api.backend.web.rest;

import com.api.backend.BackendApp;
import com.api.backend.domain.Dmnhomxetnghiem;
import com.api.backend.domain.Dmxetnghiem;
import com.api.backend.repository.DmxetnghiemRepository;
import com.api.backend.service.DmxetnghiemQueryService;
import com.api.backend.service.DmxetnghiemService;
import com.api.backend.service.dto.DmxetnghiemDTO;
import com.api.backend.service.mapper.MyDanhMucXetNghiemMapper;
import com.api.backend.web.rest.errors.ExceptionTranslator;
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
 * Integration tests for the {@link DmxetnghiemResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class DmxetnghiemResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHA = 1;
    private static final Integer UPDATED_CHA = 2;
    private static final Integer SMALLER_CHA = 1 - 1;

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final Integer DEFAULT_GIOITINH = 1;
    private static final Integer UPDATED_GIOITINH = 2;
    private static final Integer SMALLER_GIOITINH = 1 - 1;

    private static final String DEFAULT_CANDUOI = "AAAAAAAAAA";
    private static final String UPDATED_CANDUOI = "BBBBBBBBBB";

    private static final String DEFAULT_CANTREN = "AAAAAAAAAA";
    private static final String UPDATED_CANTREN = "BBBBBBBBBB";

    private static final String DEFAULT_DONVIDO = "AAAAAAAAAA";
    private static final String UPDATED_DONVIDO = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_BYT = "BBBBBBBBBB";

    private static final String DEFAULT_MANHOM_BH = "AAAAAAAAAA";
    private static final String UPDATED_MANHOM_BH = "BBBBBBBBBB";

    @Autowired
    private DmxetnghiemRepository dmxetnghiemRepository;

    @Autowired
    private MyDanhMucXetNghiemMapper dmxetnghiemMapper;

    @Autowired
    private DmxetnghiemService dmxetnghiemService;

    @Autowired
    private DmxetnghiemQueryService dmxetnghiemQueryService;

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

    private MockMvc restDmxetnghiemMockMvc;

    private Dmxetnghiem dmxetnghiem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DmxetnghiemResource dmxetnghiemResource = new DmxetnghiemResource(dmxetnghiemService, dmxetnghiemQueryService);
        this.restDmxetnghiemMockMvc = MockMvcBuilders.standaloneSetup(dmxetnghiemResource)
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
    public static Dmxetnghiem createEntity(EntityManager em) {
        Dmxetnghiem dmxetnghiem = new Dmxetnghiem()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .cha(DEFAULT_CHA)
            .mota(DEFAULT_MOTA)
            .gioitinh(DEFAULT_GIOITINH)
            .canduoi(DEFAULT_CANDUOI)
            .cantren(DEFAULT_CANTREN)
            .donvido(DEFAULT_DONVIDO)
            .maByt(DEFAULT_MA_BYT)
            .manhomBH(DEFAULT_MANHOM_BH);
        return dmxetnghiem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dmxetnghiem createUpdatedEntity(EntityManager em) {
        Dmxetnghiem dmxetnghiem = new Dmxetnghiem()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .cha(UPDATED_CHA)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .canduoi(UPDATED_CANDUOI)
            .cantren(UPDATED_CANTREN)
            .donvido(UPDATED_DONVIDO)
            .maByt(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        return dmxetnghiem;
    }

    @BeforeEach
    public void initTest() {
        dmxetnghiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmxetnghiem() throws Exception {
        int databaseSizeBeforeCreate = dmxetnghiemRepository.findAll().size();

        // Create the Dmxetnghiem
        DmxetnghiemDTO dmxetnghiemDTO = dmxetnghiemMapper.toDto(dmxetnghiem);
        restDmxetnghiemMockMvc.perform(post("/api/dmxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmxetnghiemDTO)))
            .andExpect(status().isCreated());

        // Validate the Dmxetnghiem in the database
        List<Dmxetnghiem> dmxetnghiemList = dmxetnghiemRepository.findAll();
        assertThat(dmxetnghiemList).hasSize(databaseSizeBeforeCreate + 1);
        Dmxetnghiem testDmxetnghiem = dmxetnghiemList.get(dmxetnghiemList.size() - 1);
        assertThat(testDmxetnghiem.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmxetnghiem.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDmxetnghiem.getCha()).isEqualTo(DEFAULT_CHA);
        assertThat(testDmxetnghiem.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testDmxetnghiem.getGioitinh()).isEqualTo(DEFAULT_GIOITINH);
        assertThat(testDmxetnghiem.getCanduoi()).isEqualTo(DEFAULT_CANDUOI);
        assertThat(testDmxetnghiem.getCantren()).isEqualTo(DEFAULT_CANTREN);
        assertThat(testDmxetnghiem.getDonvido()).isEqualTo(DEFAULT_DONVIDO);
        assertThat(testDmxetnghiem.getMaByt()).isEqualTo(DEFAULT_MA_BYT);
        assertThat(testDmxetnghiem.getManhomBH()).isEqualTo(DEFAULT_MANHOM_BH);
    }

    @Test
    @Transactional
    public void createDmxetnghiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmxetnghiemRepository.findAll().size();

        // Create the Dmxetnghiem with an existing ID
        dmxetnghiem.setId(1L);
        DmxetnghiemDTO dmxetnghiemDTO = dmxetnghiemMapper.toDto(dmxetnghiem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmxetnghiemMockMvc.perform(post("/api/dmxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmxetnghiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmxetnghiem in the database
        List<Dmxetnghiem> dmxetnghiemList = dmxetnghiemRepository.findAll();
        assertThat(dmxetnghiemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiems() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmxetnghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].cha").value(hasItem(DEFAULT_CHA)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].canduoi").value(hasItem(DEFAULT_CANDUOI)))
            .andExpect(jsonPath("$.[*].cantren").value(hasItem(DEFAULT_CANTREN)))
            .andExpect(jsonPath("$.[*].donvido").value(hasItem(DEFAULT_DONVIDO)))
            .andExpect(jsonPath("$.[*].maByt").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));
    }

    @Test
    @Transactional
    public void getDmxetnghiem() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get the dmxetnghiem
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems/{id}", dmxetnghiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmxetnghiem.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.cha").value(DEFAULT_CHA))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.gioitinh").value(DEFAULT_GIOITINH))
            .andExpect(jsonPath("$.canduoi").value(DEFAULT_CANDUOI))
            .andExpect(jsonPath("$.cantren").value(DEFAULT_CANTREN))
            .andExpect(jsonPath("$.donvido").value(DEFAULT_DONVIDO))
            .andExpect(jsonPath("$.maByt").value(DEFAULT_MA_BYT))
            .andExpect(jsonPath("$.manhomBH").value(DEFAULT_MANHOM_BH));
    }


    @Test
    @Transactional
    public void getDmxetnghiemsByIdFiltering() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        Long id = dmxetnghiem.getId();

        defaultDmxetnghiemShouldBeFound("id.equals=" + id);
        defaultDmxetnghiemShouldNotBeFound("id.notEquals=" + id);

        defaultDmxetnghiemShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDmxetnghiemShouldNotBeFound("id.greaterThan=" + id);

        defaultDmxetnghiemShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDmxetnghiemShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma equals to DEFAULT_MA
        defaultDmxetnghiemShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the dmxetnghiemList where ma equals to UPDATED_MA
        defaultDmxetnghiemShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma not equals to DEFAULT_MA
        defaultDmxetnghiemShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the dmxetnghiemList where ma not equals to UPDATED_MA
        defaultDmxetnghiemShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma in DEFAULT_MA or UPDATED_MA
        defaultDmxetnghiemShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the dmxetnghiemList where ma equals to UPDATED_MA
        defaultDmxetnghiemShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma is not null
        defaultDmxetnghiemShouldBeFound("ma.specified=true");

        // Get all the dmxetnghiemList where ma is null
        defaultDmxetnghiemShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByMaContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma contains DEFAULT_MA
        defaultDmxetnghiemShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the dmxetnghiemList where ma contains UPDATED_MA
        defaultDmxetnghiemShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ma does not contain DEFAULT_MA
        defaultDmxetnghiemShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the dmxetnghiemList where ma does not contain UPDATED_MA
        defaultDmxetnghiemShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten equals to DEFAULT_TEN
        defaultDmxetnghiemShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the dmxetnghiemList where ten equals to UPDATED_TEN
        defaultDmxetnghiemShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten not equals to DEFAULT_TEN
        defaultDmxetnghiemShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the dmxetnghiemList where ten not equals to UPDATED_TEN
        defaultDmxetnghiemShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDmxetnghiemShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the dmxetnghiemList where ten equals to UPDATED_TEN
        defaultDmxetnghiemShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten is not null
        defaultDmxetnghiemShouldBeFound("ten.specified=true");

        // Get all the dmxetnghiemList where ten is null
        defaultDmxetnghiemShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByTenContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten contains DEFAULT_TEN
        defaultDmxetnghiemShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the dmxetnghiemList where ten contains UPDATED_TEN
        defaultDmxetnghiemShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where ten does not contain DEFAULT_TEN
        defaultDmxetnghiemShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the dmxetnghiemList where ten does not contain UPDATED_TEN
        defaultDmxetnghiemShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha equals to DEFAULT_CHA
        defaultDmxetnghiemShouldBeFound("cha.equals=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha equals to UPDATED_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.equals=" + UPDATED_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha not equals to DEFAULT_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.notEquals=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha not equals to UPDATED_CHA
        defaultDmxetnghiemShouldBeFound("cha.notEquals=" + UPDATED_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha in DEFAULT_CHA or UPDATED_CHA
        defaultDmxetnghiemShouldBeFound("cha.in=" + DEFAULT_CHA + "," + UPDATED_CHA);

        // Get all the dmxetnghiemList where cha equals to UPDATED_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.in=" + UPDATED_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha is not null
        defaultDmxetnghiemShouldBeFound("cha.specified=true");

        // Get all the dmxetnghiemList where cha is null
        defaultDmxetnghiemShouldNotBeFound("cha.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha is greater than or equal to DEFAULT_CHA
        defaultDmxetnghiemShouldBeFound("cha.greaterThanOrEqual=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha is greater than or equal to UPDATED_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.greaterThanOrEqual=" + UPDATED_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha is less than or equal to DEFAULT_CHA
        defaultDmxetnghiemShouldBeFound("cha.lessThanOrEqual=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha is less than or equal to SMALLER_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.lessThanOrEqual=" + SMALLER_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsLessThanSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha is less than DEFAULT_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.lessThan=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha is less than UPDATED_CHA
        defaultDmxetnghiemShouldBeFound("cha.lessThan=" + UPDATED_CHA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByChaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cha is greater than DEFAULT_CHA
        defaultDmxetnghiemShouldNotBeFound("cha.greaterThan=" + DEFAULT_CHA);

        // Get all the dmxetnghiemList where cha is greater than SMALLER_CHA
        defaultDmxetnghiemShouldBeFound("cha.greaterThan=" + SMALLER_CHA);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota equals to DEFAULT_MOTA
        defaultDmxetnghiemShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the dmxetnghiemList where mota equals to UPDATED_MOTA
        defaultDmxetnghiemShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota not equals to DEFAULT_MOTA
        defaultDmxetnghiemShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the dmxetnghiemList where mota not equals to UPDATED_MOTA
        defaultDmxetnghiemShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultDmxetnghiemShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the dmxetnghiemList where mota equals to UPDATED_MOTA
        defaultDmxetnghiemShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota is not null
        defaultDmxetnghiemShouldBeFound("mota.specified=true");

        // Get all the dmxetnghiemList where mota is null
        defaultDmxetnghiemShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota contains DEFAULT_MOTA
        defaultDmxetnghiemShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the dmxetnghiemList where mota contains UPDATED_MOTA
        defaultDmxetnghiemShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where mota does not contain DEFAULT_MOTA
        defaultDmxetnghiemShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the dmxetnghiemList where mota does not contain UPDATED_MOTA
        defaultDmxetnghiemShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh equals to DEFAULT_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.equals=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh equals to UPDATED_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.equals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh not equals to DEFAULT_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.notEquals=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh not equals to UPDATED_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.notEquals=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh in DEFAULT_GIOITINH or UPDATED_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.in=" + DEFAULT_GIOITINH + "," + UPDATED_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh equals to UPDATED_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.in=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh is not null
        defaultDmxetnghiemShouldBeFound("gioitinh.specified=true");

        // Get all the dmxetnghiemList where gioitinh is null
        defaultDmxetnghiemShouldNotBeFound("gioitinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh is greater than or equal to DEFAULT_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.greaterThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh is greater than or equal to UPDATED_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.greaterThanOrEqual=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh is less than or equal to DEFAULT_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.lessThanOrEqual=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh is less than or equal to SMALLER_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.lessThanOrEqual=" + SMALLER_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsLessThanSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh is less than DEFAULT_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.lessThan=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh is less than UPDATED_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.lessThan=" + UPDATED_GIOITINH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByGioitinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where gioitinh is greater than DEFAULT_GIOITINH
        defaultDmxetnghiemShouldNotBeFound("gioitinh.greaterThan=" + DEFAULT_GIOITINH);

        // Get all the dmxetnghiemList where gioitinh is greater than SMALLER_GIOITINH
        defaultDmxetnghiemShouldBeFound("gioitinh.greaterThan=" + SMALLER_GIOITINH);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi equals to DEFAULT_CANDUOI
        defaultDmxetnghiemShouldBeFound("canduoi.equals=" + DEFAULT_CANDUOI);

        // Get all the dmxetnghiemList where canduoi equals to UPDATED_CANDUOI
        defaultDmxetnghiemShouldNotBeFound("canduoi.equals=" + UPDATED_CANDUOI);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi not equals to DEFAULT_CANDUOI
        defaultDmxetnghiemShouldNotBeFound("canduoi.notEquals=" + DEFAULT_CANDUOI);

        // Get all the dmxetnghiemList where canduoi not equals to UPDATED_CANDUOI
        defaultDmxetnghiemShouldBeFound("canduoi.notEquals=" + UPDATED_CANDUOI);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi in DEFAULT_CANDUOI or UPDATED_CANDUOI
        defaultDmxetnghiemShouldBeFound("canduoi.in=" + DEFAULT_CANDUOI + "," + UPDATED_CANDUOI);

        // Get all the dmxetnghiemList where canduoi equals to UPDATED_CANDUOI
        defaultDmxetnghiemShouldNotBeFound("canduoi.in=" + UPDATED_CANDUOI);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi is not null
        defaultDmxetnghiemShouldBeFound("canduoi.specified=true");

        // Get all the dmxetnghiemList where canduoi is null
        defaultDmxetnghiemShouldNotBeFound("canduoi.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi contains DEFAULT_CANDUOI
        defaultDmxetnghiemShouldBeFound("canduoi.contains=" + DEFAULT_CANDUOI);

        // Get all the dmxetnghiemList where canduoi contains UPDATED_CANDUOI
        defaultDmxetnghiemShouldNotBeFound("canduoi.contains=" + UPDATED_CANDUOI);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCanduoiNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where canduoi does not contain DEFAULT_CANDUOI
        defaultDmxetnghiemShouldNotBeFound("canduoi.doesNotContain=" + DEFAULT_CANDUOI);

        // Get all the dmxetnghiemList where canduoi does not contain UPDATED_CANDUOI
        defaultDmxetnghiemShouldBeFound("canduoi.doesNotContain=" + UPDATED_CANDUOI);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren equals to DEFAULT_CANTREN
        defaultDmxetnghiemShouldBeFound("cantren.equals=" + DEFAULT_CANTREN);

        // Get all the dmxetnghiemList where cantren equals to UPDATED_CANTREN
        defaultDmxetnghiemShouldNotBeFound("cantren.equals=" + UPDATED_CANTREN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren not equals to DEFAULT_CANTREN
        defaultDmxetnghiemShouldNotBeFound("cantren.notEquals=" + DEFAULT_CANTREN);

        // Get all the dmxetnghiemList where cantren not equals to UPDATED_CANTREN
        defaultDmxetnghiemShouldBeFound("cantren.notEquals=" + UPDATED_CANTREN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren in DEFAULT_CANTREN or UPDATED_CANTREN
        defaultDmxetnghiemShouldBeFound("cantren.in=" + DEFAULT_CANTREN + "," + UPDATED_CANTREN);

        // Get all the dmxetnghiemList where cantren equals to UPDATED_CANTREN
        defaultDmxetnghiemShouldNotBeFound("cantren.in=" + UPDATED_CANTREN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren is not null
        defaultDmxetnghiemShouldBeFound("cantren.specified=true");

        // Get all the dmxetnghiemList where cantren is null
        defaultDmxetnghiemShouldNotBeFound("cantren.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren contains DEFAULT_CANTREN
        defaultDmxetnghiemShouldBeFound("cantren.contains=" + DEFAULT_CANTREN);

        // Get all the dmxetnghiemList where cantren contains UPDATED_CANTREN
        defaultDmxetnghiemShouldNotBeFound("cantren.contains=" + UPDATED_CANTREN);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByCantrenNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where cantren does not contain DEFAULT_CANTREN
        defaultDmxetnghiemShouldNotBeFound("cantren.doesNotContain=" + DEFAULT_CANTREN);

        // Get all the dmxetnghiemList where cantren does not contain UPDATED_CANTREN
        defaultDmxetnghiemShouldBeFound("cantren.doesNotContain=" + UPDATED_CANTREN);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido equals to DEFAULT_DONVIDO
        defaultDmxetnghiemShouldBeFound("donvido.equals=" + DEFAULT_DONVIDO);

        // Get all the dmxetnghiemList where donvido equals to UPDATED_DONVIDO
        defaultDmxetnghiemShouldNotBeFound("donvido.equals=" + UPDATED_DONVIDO);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido not equals to DEFAULT_DONVIDO
        defaultDmxetnghiemShouldNotBeFound("donvido.notEquals=" + DEFAULT_DONVIDO);

        // Get all the dmxetnghiemList where donvido not equals to UPDATED_DONVIDO
        defaultDmxetnghiemShouldBeFound("donvido.notEquals=" + UPDATED_DONVIDO);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido in DEFAULT_DONVIDO or UPDATED_DONVIDO
        defaultDmxetnghiemShouldBeFound("donvido.in=" + DEFAULT_DONVIDO + "," + UPDATED_DONVIDO);

        // Get all the dmxetnghiemList where donvido equals to UPDATED_DONVIDO
        defaultDmxetnghiemShouldNotBeFound("donvido.in=" + UPDATED_DONVIDO);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido is not null
        defaultDmxetnghiemShouldBeFound("donvido.specified=true");

        // Get all the dmxetnghiemList where donvido is null
        defaultDmxetnghiemShouldNotBeFound("donvido.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido contains DEFAULT_DONVIDO
        defaultDmxetnghiemShouldBeFound("donvido.contains=" + DEFAULT_DONVIDO);

        // Get all the dmxetnghiemList where donvido contains UPDATED_DONVIDO
        defaultDmxetnghiemShouldNotBeFound("donvido.contains=" + UPDATED_DONVIDO);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByDonvidoNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where donvido does not contain DEFAULT_DONVIDO
        defaultDmxetnghiemShouldNotBeFound("donvido.doesNotContain=" + DEFAULT_DONVIDO);

        // Get all the dmxetnghiemList where donvido does not contain UPDATED_DONVIDO
        defaultDmxetnghiemShouldBeFound("donvido.doesNotContain=" + UPDATED_DONVIDO);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt equals to DEFAULT_MA_BYT
        defaultDmxetnghiemShouldBeFound("maByt.equals=" + DEFAULT_MA_BYT);

        // Get all the dmxetnghiemList where maByt equals to UPDATED_MA_BYT
        defaultDmxetnghiemShouldNotBeFound("maByt.equals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt not equals to DEFAULT_MA_BYT
        defaultDmxetnghiemShouldNotBeFound("maByt.notEquals=" + DEFAULT_MA_BYT);

        // Get all the dmxetnghiemList where maByt not equals to UPDATED_MA_BYT
        defaultDmxetnghiemShouldBeFound("maByt.notEquals=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt in DEFAULT_MA_BYT or UPDATED_MA_BYT
        defaultDmxetnghiemShouldBeFound("maByt.in=" + DEFAULT_MA_BYT + "," + UPDATED_MA_BYT);

        // Get all the dmxetnghiemList where maByt equals to UPDATED_MA_BYT
        defaultDmxetnghiemShouldNotBeFound("maByt.in=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt is not null
        defaultDmxetnghiemShouldBeFound("maByt.specified=true");

        // Get all the dmxetnghiemList where maByt is null
        defaultDmxetnghiemShouldNotBeFound("maByt.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt contains DEFAULT_MA_BYT
        defaultDmxetnghiemShouldBeFound("maByt.contains=" + DEFAULT_MA_BYT);

        // Get all the dmxetnghiemList where maByt contains UPDATED_MA_BYT
        defaultDmxetnghiemShouldNotBeFound("maByt.contains=" + UPDATED_MA_BYT);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByMaBytNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where maByt does not contain DEFAULT_MA_BYT
        defaultDmxetnghiemShouldNotBeFound("maByt.doesNotContain=" + DEFAULT_MA_BYT);

        // Get all the dmxetnghiemList where maByt does not contain UPDATED_MA_BYT
        defaultDmxetnghiemShouldBeFound("maByt.doesNotContain=" + UPDATED_MA_BYT);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH equals to DEFAULT_MANHOM_BH
        defaultDmxetnghiemShouldBeFound("manhomBH.equals=" + DEFAULT_MANHOM_BH);

        // Get all the dmxetnghiemList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldNotBeFound("manhomBH.equals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH not equals to DEFAULT_MANHOM_BH
        defaultDmxetnghiemShouldNotBeFound("manhomBH.notEquals=" + DEFAULT_MANHOM_BH);

        // Get all the dmxetnghiemList where manhomBH not equals to UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldBeFound("manhomBH.notEquals=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHIsInShouldWork() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH in DEFAULT_MANHOM_BH or UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldBeFound("manhomBH.in=" + DEFAULT_MANHOM_BH + "," + UPDATED_MANHOM_BH);

        // Get all the dmxetnghiemList where manhomBH equals to UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldNotBeFound("manhomBH.in=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHIsNullOrNotNull() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH is not null
        defaultDmxetnghiemShouldBeFound("manhomBH.specified=true");

        // Get all the dmxetnghiemList where manhomBH is null
        defaultDmxetnghiemShouldNotBeFound("manhomBH.specified=false");
    }
                @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH contains DEFAULT_MANHOM_BH
        defaultDmxetnghiemShouldBeFound("manhomBH.contains=" + DEFAULT_MANHOM_BH);

        // Get all the dmxetnghiemList where manhomBH contains UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldNotBeFound("manhomBH.contains=" + UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void getAllDmxetnghiemsByManhomBHNotContainsSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        // Get all the dmxetnghiemList where manhomBH does not contain DEFAULT_MANHOM_BH
        defaultDmxetnghiemShouldNotBeFound("manhomBH.doesNotContain=" + DEFAULT_MANHOM_BH);

        // Get all the dmxetnghiemList where manhomBH does not contain UPDATED_MANHOM_BH
        defaultDmxetnghiemShouldBeFound("manhomBH.doesNotContain=" + UPDATED_MANHOM_BH);
    }


    @Test
    @Transactional
    public void getAllDmxetnghiemsByDmnhomxetnghiemIsEqualToSomething() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);
        Dmnhomxetnghiem dmnhomxetnghiem = DmnhomxetnghiemResourceIT.createEntity(em);
        em.persist(dmnhomxetnghiem);
        em.flush();
        dmxetnghiem.setDmnhomxetnghiem(dmnhomxetnghiem);
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);
        Long dmnhomxetnghiemId = dmnhomxetnghiem.getId();

        // Get all the dmxetnghiemList where dmnhomxetnghiem equals to dmnhomxetnghiemId
        defaultDmxetnghiemShouldBeFound("dmnhomxetnghiemId.equals=" + dmnhomxetnghiemId);

        // Get all the dmxetnghiemList where dmnhomxetnghiem equals to dmnhomxetnghiemId + 1
        defaultDmxetnghiemShouldNotBeFound("dmnhomxetnghiemId.equals=" + (dmnhomxetnghiemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmxetnghiemShouldBeFound(String filter) throws Exception {
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmxetnghiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].cha").value(hasItem(DEFAULT_CHA)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].gioitinh").value(hasItem(DEFAULT_GIOITINH)))
            .andExpect(jsonPath("$.[*].canduoi").value(hasItem(DEFAULT_CANDUOI)))
            .andExpect(jsonPath("$.[*].cantren").value(hasItem(DEFAULT_CANTREN)))
            .andExpect(jsonPath("$.[*].donvido").value(hasItem(DEFAULT_DONVIDO)))
            .andExpect(jsonPath("$.[*].maByt").value(hasItem(DEFAULT_MA_BYT)))
            .andExpect(jsonPath("$.[*].manhomBH").value(hasItem(DEFAULT_MANHOM_BH)));

        // Check, that the count call also returns 1
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmxetnghiemShouldNotBeFound(String filter) throws Exception {
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDmxetnghiem() throws Exception {
        // Get the dmxetnghiem
        restDmxetnghiemMockMvc.perform(get("/api/dmxetnghiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmxetnghiem() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        int databaseSizeBeforeUpdate = dmxetnghiemRepository.findAll().size();

        // Update the dmxetnghiem
        Dmxetnghiem updatedDmxetnghiem = dmxetnghiemRepository.findById(dmxetnghiem.getId()).get();
        // Disconnect from session so that the updates on updatedDmxetnghiem are not directly saved in db
        em.detach(updatedDmxetnghiem);
        updatedDmxetnghiem
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .cha(UPDATED_CHA)
            .mota(UPDATED_MOTA)
            .gioitinh(UPDATED_GIOITINH)
            .canduoi(UPDATED_CANDUOI)
            .cantren(UPDATED_CANTREN)
            .donvido(UPDATED_DONVIDO)
            .maByt(UPDATED_MA_BYT)
            .manhomBH(UPDATED_MANHOM_BH);
        DmxetnghiemDTO dmxetnghiemDTO = dmxetnghiemMapper.toDto(updatedDmxetnghiem);

        restDmxetnghiemMockMvc.perform(put("/api/dmxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmxetnghiemDTO)))
            .andExpect(status().isOk());

        // Validate the Dmxetnghiem in the database
        List<Dmxetnghiem> dmxetnghiemList = dmxetnghiemRepository.findAll();
        assertThat(dmxetnghiemList).hasSize(databaseSizeBeforeUpdate);
        Dmxetnghiem testDmxetnghiem = dmxetnghiemList.get(dmxetnghiemList.size() - 1);
        assertThat(testDmxetnghiem.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmxetnghiem.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDmxetnghiem.getCha()).isEqualTo(UPDATED_CHA);
        assertThat(testDmxetnghiem.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testDmxetnghiem.getGioitinh()).isEqualTo(UPDATED_GIOITINH);
        assertThat(testDmxetnghiem.getCanduoi()).isEqualTo(UPDATED_CANDUOI);
        assertThat(testDmxetnghiem.getCantren()).isEqualTo(UPDATED_CANTREN);
        assertThat(testDmxetnghiem.getDonvido()).isEqualTo(UPDATED_DONVIDO);
        assertThat(testDmxetnghiem.getMaByt()).isEqualTo(UPDATED_MA_BYT);
        assertThat(testDmxetnghiem.getManhomBH()).isEqualTo(UPDATED_MANHOM_BH);
    }

    @Test
    @Transactional
    public void updateNonExistingDmxetnghiem() throws Exception {
        int databaseSizeBeforeUpdate = dmxetnghiemRepository.findAll().size();

        // Create the Dmxetnghiem
        DmxetnghiemDTO dmxetnghiemDTO = dmxetnghiemMapper.toDto(dmxetnghiem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmxetnghiemMockMvc.perform(put("/api/dmxetnghiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmxetnghiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dmxetnghiem in the database
        List<Dmxetnghiem> dmxetnghiemList = dmxetnghiemRepository.findAll();
        assertThat(dmxetnghiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDmxetnghiem() throws Exception {
        // Initialize the database
        dmxetnghiemRepository.saveAndFlush(dmxetnghiem);

        int databaseSizeBeforeDelete = dmxetnghiemRepository.findAll().size();

        // Delete the dmxetnghiem
        restDmxetnghiemMockMvc.perform(delete("/api/dmxetnghiems/{id}", dmxetnghiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dmxetnghiem> dmxetnghiemList = dmxetnghiemRepository.findAll();
        assertThat(dmxetnghiemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
