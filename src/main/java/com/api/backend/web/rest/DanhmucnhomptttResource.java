package com.api.backend.web.rest;

import com.api.backend.service.DanhmucnhomptttService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DanhmucnhomptttDTO;
import com.api.backend.service.dto.DanhmucnhomptttCriteria;
import com.api.backend.service.DanhmucnhomptttQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Danhmucnhompttt}.
 */
@RestController
@RequestMapping("/api")
public class DanhmucnhomptttResource {

    private final Logger log = LoggerFactory.getLogger(DanhmucnhomptttResource.class);

    private static final String ENTITY_NAME = "danhmucnhompttt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhmucnhomptttService danhmucnhomptttService;

    private final DanhmucnhomptttQueryService danhmucnhomptttQueryService;

    public DanhmucnhomptttResource(DanhmucnhomptttService danhmucnhomptttService, DanhmucnhomptttQueryService danhmucnhomptttQueryService) {
        this.danhmucnhomptttService = danhmucnhomptttService;
        this.danhmucnhomptttQueryService = danhmucnhomptttQueryService;
    }

    /**
     * {@code POST  /danhmucnhompttts} : Create a new danhmucnhompttt.
     *
     * @param danhmucnhomptttDTO the danhmucnhomptttDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhmucnhomptttDTO, or with status {@code 400 (Bad Request)} if the danhmucnhompttt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danhmucnhompttts")
    public ResponseEntity<DanhmucnhomptttDTO> createDanhmucnhompttt(@RequestBody DanhmucnhomptttDTO danhmucnhomptttDTO) throws URISyntaxException {
        log.debug("REST request to save Danhmucnhompttt : {}", danhmucnhomptttDTO);
        if (danhmucnhomptttDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhmucnhompttt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhmucnhomptttDTO result = danhmucnhomptttService.save(danhmucnhomptttDTO);
        return ResponseEntity.created(new URI("/api/danhmucnhompttts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danhmucnhompttts} : Updates an existing danhmucnhompttt.
     *
     * @param danhmucnhomptttDTO the danhmucnhomptttDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhmucnhomptttDTO,
     * or with status {@code 400 (Bad Request)} if the danhmucnhomptttDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhmucnhomptttDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danhmucnhompttts")
    public ResponseEntity<DanhmucnhomptttDTO> updateDanhmucnhompttt(@RequestBody DanhmucnhomptttDTO danhmucnhomptttDTO) throws URISyntaxException {
        log.debug("REST request to update Danhmucnhompttt : {}", danhmucnhomptttDTO);
        if (danhmucnhomptttDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhmucnhomptttDTO result = danhmucnhomptttService.save(danhmucnhomptttDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhmucnhomptttDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danhmucnhompttts} : get all the danhmucnhompttts.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhmucnhompttts in body.
     */
    @GetMapping("/danhmucnhompttts")
    public ResponseEntity<List<DanhmucnhomptttDTO>> getAllDanhmucnhompttts(DanhmucnhomptttCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Danhmucnhompttts by criteria: {}", criteria);
        Page<DanhmucnhomptttDTO> page = danhmucnhomptttQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danhmucnhompttts/count} : count all the danhmucnhompttts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danhmucnhompttts/count")
    public ResponseEntity<Long> countDanhmucnhompttts(DanhmucnhomptttCriteria criteria) {
        log.debug("REST request to count Danhmucnhompttts by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhmucnhomptttQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danhmucnhompttts/:id} : get the "id" danhmucnhompttt.
     *
     * @param id the id of the danhmucnhomptttDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhmucnhomptttDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danhmucnhompttts/{id}")
    public ResponseEntity<DanhmucnhomptttDTO> getDanhmucnhompttt(@PathVariable Long id) {
        log.debug("REST request to get Danhmucnhompttt : {}", id);
        Optional<DanhmucnhomptttDTO> danhmucnhomptttDTO = danhmucnhomptttService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhmucnhomptttDTO);
    }

    /**
     * {@code DELETE  /danhmucnhompttts/:id} : delete the "id" danhmucnhompttt.
     *
     * @param id the id of the danhmucnhomptttDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danhmucnhompttts/{id}")
    public ResponseEntity<Void> deleteDanhmucnhompttt(@PathVariable Long id) {
        log.debug("REST request to delete Danhmucnhompttt : {}", id);
        danhmucnhomptttService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
