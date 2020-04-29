package com.api.backend.web.rest;

import com.api.backend.service.DmnhomgiaiphaubenhService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;
import com.api.backend.service.dto.DmnhomgiaiphaubenhCriteria;
import com.api.backend.service.DmnhomgiaiphaubenhQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmnhomgiaiphaubenh}.
 */
@RestController
@RequestMapping("/api")
public class DmnhomgiaiphaubenhResource {

    private final Logger log = LoggerFactory.getLogger(DmnhomgiaiphaubenhResource.class);

    private static final String ENTITY_NAME = "dmnhomgiaiphaubenh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmnhomgiaiphaubenhService dmnhomgiaiphaubenhService;

    private final DmnhomgiaiphaubenhQueryService dmnhomgiaiphaubenhQueryService;

    public DmnhomgiaiphaubenhResource(DmnhomgiaiphaubenhService dmnhomgiaiphaubenhService, DmnhomgiaiphaubenhQueryService dmnhomgiaiphaubenhQueryService) {
        this.dmnhomgiaiphaubenhService = dmnhomgiaiphaubenhService;
        this.dmnhomgiaiphaubenhQueryService = dmnhomgiaiphaubenhQueryService;
    }

    /**
     * {@code POST  /dmnhomgiaiphaubenhs} : Create a new dmnhomgiaiphaubenh.
     *
     * @param dmnhomgiaiphaubenhDTO the dmnhomgiaiphaubenhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmnhomgiaiphaubenhDTO, or with status {@code 400 (Bad Request)} if the dmnhomgiaiphaubenh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmnhomgiaiphaubenhs")
    public ResponseEntity<DmnhomgiaiphaubenhDTO> createDmnhomgiaiphaubenh(@RequestBody DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO) throws URISyntaxException {
        log.debug("REST request to save Dmnhomgiaiphaubenh : {}", dmnhomgiaiphaubenhDTO);
        if (dmnhomgiaiphaubenhDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmnhomgiaiphaubenh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmnhomgiaiphaubenhDTO result = dmnhomgiaiphaubenhService.save(dmnhomgiaiphaubenhDTO);
        return ResponseEntity.created(new URI("/api/dmnhomgiaiphaubenhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmnhomgiaiphaubenhs} : Updates an existing dmnhomgiaiphaubenh.
     *
     * @param dmnhomgiaiphaubenhDTO the dmnhomgiaiphaubenhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmnhomgiaiphaubenhDTO,
     * or with status {@code 400 (Bad Request)} if the dmnhomgiaiphaubenhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmnhomgiaiphaubenhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmnhomgiaiphaubenhs")
    public ResponseEntity<DmnhomgiaiphaubenhDTO> updateDmnhomgiaiphaubenh(@RequestBody DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO) throws URISyntaxException {
        log.debug("REST request to update Dmnhomgiaiphaubenh : {}", dmnhomgiaiphaubenhDTO);
        if (dmnhomgiaiphaubenhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmnhomgiaiphaubenhDTO result = dmnhomgiaiphaubenhService.save(dmnhomgiaiphaubenhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmnhomgiaiphaubenhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmnhomgiaiphaubenhs} : get all the dmnhomgiaiphaubenhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmnhomgiaiphaubenhs in body.
     */
    @GetMapping("/dmnhomgiaiphaubenhs")
    public ResponseEntity<List<DmnhomgiaiphaubenhDTO>> getAllDmnhomgiaiphaubenhs(DmnhomgiaiphaubenhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmnhomgiaiphaubenhs by criteria: {}", criteria);
        Page<DmnhomgiaiphaubenhDTO> page = dmnhomgiaiphaubenhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmnhomgiaiphaubenhs/count} : count all the dmnhomgiaiphaubenhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmnhomgiaiphaubenhs/count")
    public ResponseEntity<Long> countDmnhomgiaiphaubenhs(DmnhomgiaiphaubenhCriteria criteria) {
        log.debug("REST request to count Dmnhomgiaiphaubenhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmnhomgiaiphaubenhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmnhomgiaiphaubenhs/:id} : get the "id" dmnhomgiaiphaubenh.
     *
     * @param id the id of the dmnhomgiaiphaubenhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmnhomgiaiphaubenhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmnhomgiaiphaubenhs/{id}")
    public ResponseEntity<DmnhomgiaiphaubenhDTO> getDmnhomgiaiphaubenh(@PathVariable Long id) {
        log.debug("REST request to get Dmnhomgiaiphaubenh : {}", id);
        Optional<DmnhomgiaiphaubenhDTO> dmnhomgiaiphaubenhDTO = dmnhomgiaiphaubenhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmnhomgiaiphaubenhDTO);
    }

    /**
     * {@code DELETE  /dmnhomgiaiphaubenhs/:id} : delete the "id" dmnhomgiaiphaubenh.
     *
     * @param id the id of the dmnhomgiaiphaubenhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmnhomgiaiphaubenhs/{id}")
    public ResponseEntity<Void> deleteDmnhomgiaiphaubenh(@PathVariable Long id) {
        log.debug("REST request to delete Dmnhomgiaiphaubenh : {}", id);
        dmnhomgiaiphaubenhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
