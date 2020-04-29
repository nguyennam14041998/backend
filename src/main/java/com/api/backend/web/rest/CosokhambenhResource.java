package com.api.backend.web.rest;

import com.api.backend.service.CosokhambenhService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.CosokhambenhDTO;
import com.api.backend.service.dto.CosokhambenhCriteria;
import com.api.backend.service.CosokhambenhQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Cosokhambenh}.
 */
@RestController
@RequestMapping("/api")
public class CosokhambenhResource {

    private final Logger log = LoggerFactory.getLogger(CosokhambenhResource.class);

    private static final String ENTITY_NAME = "cosokhambenh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CosokhambenhService cosokhambenhService;

    private final CosokhambenhQueryService cosokhambenhQueryService;

    public CosokhambenhResource(CosokhambenhService cosokhambenhService, CosokhambenhQueryService cosokhambenhQueryService) {
        this.cosokhambenhService = cosokhambenhService;
        this.cosokhambenhQueryService = cosokhambenhQueryService;
    }

    /**
     * {@code POST  /cosokhambenhs} : Create a new cosokhambenh.
     *
     * @param cosokhambenhDTO the cosokhambenhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cosokhambenhDTO, or with status {@code 400 (Bad Request)} if the cosokhambenh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cosokhambenhs")
    public ResponseEntity<CosokhambenhDTO> createCosokhambenh(@RequestBody CosokhambenhDTO cosokhambenhDTO) throws URISyntaxException {
        log.debug("REST request to save Cosokhambenh : {}", cosokhambenhDTO);
        if (cosokhambenhDTO.getId() != null) {
            throw new BadRequestAlertException("A new cosokhambenh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CosokhambenhDTO result = cosokhambenhService.save(cosokhambenhDTO);
        return ResponseEntity.created(new URI("/api/cosokhambenhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cosokhambenhs} : Updates an existing cosokhambenh.
     *
     * @param cosokhambenhDTO the cosokhambenhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cosokhambenhDTO,
     * or with status {@code 400 (Bad Request)} if the cosokhambenhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cosokhambenhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cosokhambenhs")
    public ResponseEntity<CosokhambenhDTO> updateCosokhambenh(@RequestBody CosokhambenhDTO cosokhambenhDTO) throws URISyntaxException {
        log.debug("REST request to update Cosokhambenh : {}", cosokhambenhDTO);
        if (cosokhambenhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CosokhambenhDTO result = cosokhambenhService.save(cosokhambenhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cosokhambenhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cosokhambenhs} : get all the cosokhambenhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cosokhambenhs in body.
     */
    @GetMapping("/cosokhambenhs")
    public ResponseEntity<List<CosokhambenhDTO>> getAllCosokhambenhs(CosokhambenhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Cosokhambenhs by criteria: {}", criteria);
        Page<CosokhambenhDTO> page = cosokhambenhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cosokhambenhs/count} : count all the cosokhambenhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cosokhambenhs/count")
    public ResponseEntity<Long> countCosokhambenhs(CosokhambenhCriteria criteria) {
        log.debug("REST request to count Cosokhambenhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(cosokhambenhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cosokhambenhs/:id} : get the "id" cosokhambenh.
     *
     * @param id the id of the cosokhambenhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cosokhambenhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cosokhambenhs/{id}")
    public ResponseEntity<CosokhambenhDTO> getCosokhambenh(@PathVariable Long id) {
        log.debug("REST request to get Cosokhambenh : {}", id);
        Optional<CosokhambenhDTO> cosokhambenhDTO = cosokhambenhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cosokhambenhDTO);
    }

    /**
     * {@code DELETE  /cosokhambenhs/:id} : delete the "id" cosokhambenh.
     *
     * @param id the id of the cosokhambenhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cosokhambenhs/{id}")
    public ResponseEntity<Void> deleteCosokhambenh(@PathVariable Long id) {
        log.debug("REST request to delete Cosokhambenh : {}", id);
        cosokhambenhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
