package com.api.backend.web.rest;

import com.api.backend.service.DanhmucptttService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DanhmucptttDTO;
import com.api.backend.service.dto.DanhmucptttCriteria;
import com.api.backend.service.DanhmucptttQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Danhmucpttt}.
 */
@RestController
@RequestMapping("/api")
public class DanhmucptttResource {

    private final Logger log = LoggerFactory.getLogger(DanhmucptttResource.class);

    private static final String ENTITY_NAME = "danhmucpttt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhmucptttService danhmucptttService;

    private final DanhmucptttQueryService danhmucptttQueryService;

    public DanhmucptttResource(DanhmucptttService danhmucptttService, DanhmucptttQueryService danhmucptttQueryService) {
        this.danhmucptttService = danhmucptttService;
        this.danhmucptttQueryService = danhmucptttQueryService;
    }

    /**
     * {@code POST  /danhmucpttts} : Create a new danhmucpttt.
     *
     * @param danhmucptttDTO the danhmucptttDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhmucptttDTO, or with status {@code 400 (Bad Request)} if the danhmucpttt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danhmucpttts")
    public ResponseEntity<DanhmucptttDTO> createDanhmucpttt(@RequestBody DanhmucptttDTO danhmucptttDTO) throws URISyntaxException {
        log.debug("REST request to save Danhmucpttt : {}", danhmucptttDTO);
        if (danhmucptttDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhmucpttt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhmucptttDTO result = danhmucptttService.save(danhmucptttDTO);
        return ResponseEntity.created(new URI("/api/danhmucpttts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danhmucpttts} : Updates an existing danhmucpttt.
     *
     * @param danhmucptttDTO the danhmucptttDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhmucptttDTO,
     * or with status {@code 400 (Bad Request)} if the danhmucptttDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhmucptttDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danhmucpttts")
    public ResponseEntity<DanhmucptttDTO> updateDanhmucpttt(@RequestBody DanhmucptttDTO danhmucptttDTO) throws URISyntaxException {
        log.debug("REST request to update Danhmucpttt : {}", danhmucptttDTO);
        if (danhmucptttDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhmucptttDTO result = danhmucptttService.save(danhmucptttDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhmucptttDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danhmucpttts} : get all the danhmucpttts.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhmucpttts in body.
     */
    @GetMapping("/danhmucpttts")
    public ResponseEntity<List<DanhmucptttDTO>> getAllDanhmucpttts(DanhmucptttCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Danhmucpttts by criteria: {}", criteria);
        Page<DanhmucptttDTO> page = danhmucptttQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danhmucpttts/count} : count all the danhmucpttts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danhmucpttts/count")
    public ResponseEntity<Long> countDanhmucpttts(DanhmucptttCriteria criteria) {
        log.debug("REST request to count Danhmucpttts by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhmucptttQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danhmucpttts/:id} : get the "id" danhmucpttt.
     *
     * @param id the id of the danhmucptttDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhmucptttDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danhmucpttts/{id}")
    public ResponseEntity<DanhmucptttDTO> getDanhmucpttt(@PathVariable Long id) {
        log.debug("REST request to get Danhmucpttt : {}", id);
        Optional<DanhmucptttDTO> danhmucptttDTO = danhmucptttService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhmucptttDTO);
    }

    /**
     * {@code DELETE  /danhmucpttts/:id} : delete the "id" danhmucpttt.
     *
     * @param id the id of the danhmucptttDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danhmucpttts/{id}")
    public ResponseEntity<Void> deleteDanhmucpttt(@PathVariable Long id) {
        log.debug("REST request to delete Danhmucpttt : {}", id);
        danhmucptttService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
