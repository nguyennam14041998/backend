package com.api.backend.web.rest;

import com.api.backend.service.DanhmuchanhchinhService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DanhmuchanhchinhDTO;
import com.api.backend.service.dto.DanhmuchanhchinhCriteria;
import com.api.backend.service.DanhmuchanhchinhQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.api.backend.domain.Danhmuchanhchinh}.
 */
@RestController
@RequestMapping("/api")
public class DanhmuchanhchinhResource {

    private final Logger log = LoggerFactory.getLogger(DanhmuchanhchinhResource.class);

    private static final String ENTITY_NAME = "danhmuchanhchinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhmuchanhchinhService danhmuchanhchinhService;

    private final DanhmuchanhchinhQueryService danhmuchanhchinhQueryService;

    public DanhmuchanhchinhResource(DanhmuchanhchinhService danhmuchanhchinhService, DanhmuchanhchinhQueryService danhmuchanhchinhQueryService) {
        this.danhmuchanhchinhService = danhmuchanhchinhService;
        this.danhmuchanhchinhQueryService = danhmuchanhchinhQueryService;
    }

    /**
     * {@code POST  /danhmuchanhchinhs} : Create a new danhmuchanhchinh.
     *
     * @param danhmuchanhchinhDTO the danhmuchanhchinhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhmuchanhchinhDTO, or with status {@code 400 (Bad Request)} if the danhmuchanhchinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danhmuchanhchinhs")
    public ResponseEntity<DanhmuchanhchinhDTO> createDanhmuchanhchinh(@RequestBody DanhmuchanhchinhDTO danhmuchanhchinhDTO) throws URISyntaxException {
        log.debug("REST request to save Danhmuchanhchinh : {}", danhmuchanhchinhDTO);
        if (danhmuchanhchinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhmuchanhchinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhmuchanhchinhDTO result = danhmuchanhchinhService.save(danhmuchanhchinhDTO);
        return ResponseEntity.created(new URI("/api/danhmuchanhchinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danhmuchanhchinhs} : Updates an existing danhmuchanhchinh.
     *
     * @param danhmuchanhchinhDTO the danhmuchanhchinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhmuchanhchinhDTO,
     * or with status {@code 400 (Bad Request)} if the danhmuchanhchinhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhmuchanhchinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danhmuchanhchinhs")
    public ResponseEntity<DanhmuchanhchinhDTO> updateDanhmuchanhchinh(@RequestBody DanhmuchanhchinhDTO danhmuchanhchinhDTO) throws URISyntaxException {
        log.debug("REST request to update Danhmuchanhchinh : {}", danhmuchanhchinhDTO);
        if (danhmuchanhchinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhmuchanhchinhDTO result = danhmuchanhchinhService.save(danhmuchanhchinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhmuchanhchinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danhmuchanhchinhs} : get all the danhmuchanhchinhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhmuchanhchinhs in body.
     */
    @GetMapping("/danhmuchanhchinhs")
    public ResponseEntity<List<DanhmuchanhchinhDTO>> getAllDanhmuchanhchinhs(DanhmuchanhchinhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Danhmuchanhchinhs by criteria: {}", criteria);
        Page<DanhmuchanhchinhDTO> page = danhmuchanhchinhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danhmuchanhchinhs/count} : count all the danhmuchanhchinhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danhmuchanhchinhs/count")
    public ResponseEntity<Long> countDanhmuchanhchinhs(DanhmuchanhchinhCriteria criteria) {
        log.debug("REST request to count Danhmuchanhchinhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhmuchanhchinhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danhmuchanhchinhs/:id} : get the "id" danhmuchanhchinh.
     *
     * @param id the id of the danhmuchanhchinhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhmuchanhchinhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danhmuchanhchinhs/{id}")
    public ResponseEntity<DanhmuchanhchinhDTO> getDanhmuchanhchinh(@PathVariable Long id) {
        log.debug("REST request to get Danhmuchanhchinh : {}", id);
        Optional<DanhmuchanhchinhDTO> danhmuchanhchinhDTO = danhmuchanhchinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhmuchanhchinhDTO);
    }

    /**
     * {@code DELETE  /danhmuchanhchinhs/:id} : delete the "id" danhmuchanhchinh.
     *
     * @param id the id of the danhmuchanhchinhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danhmuchanhchinhs/{id}")
    public ResponseEntity<Void> deleteDanhmuchanhchinh(@PathVariable Long id) {
        log.debug("REST request to delete Danhmuchanhchinh : {}", id);
        danhmuchanhchinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
