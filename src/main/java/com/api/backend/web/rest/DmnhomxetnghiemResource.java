package com.api.backend.web.rest;

import com.api.backend.service.DmnhomxetnghiemService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmnhomxetnghiemDTO;
import com.api.backend.service.dto.DmnhomxetnghiemCriteria;
import com.api.backend.service.DmnhomxetnghiemQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmnhomxetnghiem}.
 */
@RestController
@RequestMapping("/api")
public class DmnhomxetnghiemResource {

    private final Logger log = LoggerFactory.getLogger(DmnhomxetnghiemResource.class);

    private static final String ENTITY_NAME = "dmnhomxetnghiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmnhomxetnghiemService dmnhomxetnghiemService;

    private final DmnhomxetnghiemQueryService dmnhomxetnghiemQueryService;

    public DmnhomxetnghiemResource(DmnhomxetnghiemService dmnhomxetnghiemService, DmnhomxetnghiemQueryService dmnhomxetnghiemQueryService) {
        this.dmnhomxetnghiemService = dmnhomxetnghiemService;
        this.dmnhomxetnghiemQueryService = dmnhomxetnghiemQueryService;
    }

    /**
     * {@code POST  /dmnhomxetnghiems} : Create a new dmnhomxetnghiem.
     *
     * @param dmnhomxetnghiemDTO the dmnhomxetnghiemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmnhomxetnghiemDTO, or with status {@code 400 (Bad Request)} if the dmnhomxetnghiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmnhomxetnghiems")
    public ResponseEntity<DmnhomxetnghiemDTO> createDmnhomxetnghiem(@RequestBody DmnhomxetnghiemDTO dmnhomxetnghiemDTO) throws URISyntaxException {
        log.debug("REST request to save Dmnhomxetnghiem : {}", dmnhomxetnghiemDTO);
        if (dmnhomxetnghiemDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmnhomxetnghiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmnhomxetnghiemDTO result = dmnhomxetnghiemService.save(dmnhomxetnghiemDTO);
        return ResponseEntity.created(new URI("/api/dmnhomxetnghiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmnhomxetnghiems} : Updates an existing dmnhomxetnghiem.
     *
     * @param dmnhomxetnghiemDTO the dmnhomxetnghiemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmnhomxetnghiemDTO,
     * or with status {@code 400 (Bad Request)} if the dmnhomxetnghiemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmnhomxetnghiemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmnhomxetnghiems")
    public ResponseEntity<DmnhomxetnghiemDTO> updateDmnhomxetnghiem(@RequestBody DmnhomxetnghiemDTO dmnhomxetnghiemDTO) throws URISyntaxException {
        log.debug("REST request to update Dmnhomxetnghiem : {}", dmnhomxetnghiemDTO);
        if (dmnhomxetnghiemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmnhomxetnghiemDTO result = dmnhomxetnghiemService.save(dmnhomxetnghiemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmnhomxetnghiemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmnhomxetnghiems} : get all the dmnhomxetnghiems.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmnhomxetnghiems in body.
     */
    @GetMapping("/dmnhomxetnghiems")
    public ResponseEntity<List<DmnhomxetnghiemDTO>> getAllDmnhomxetnghiems(DmnhomxetnghiemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmnhomxetnghiems by criteria: {}", criteria);
        Page<DmnhomxetnghiemDTO> page = dmnhomxetnghiemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmnhomxetnghiems/count} : count all the dmnhomxetnghiems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmnhomxetnghiems/count")
    public ResponseEntity<Long> countDmnhomxetnghiems(DmnhomxetnghiemCriteria criteria) {
        log.debug("REST request to count Dmnhomxetnghiems by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmnhomxetnghiemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmnhomxetnghiems/:id} : get the "id" dmnhomxetnghiem.
     *
     * @param id the id of the dmnhomxetnghiemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmnhomxetnghiemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmnhomxetnghiems/{id}")
    public ResponseEntity<DmnhomxetnghiemDTO> getDmnhomxetnghiem(@PathVariable Long id) {
        log.debug("REST request to get Dmnhomxetnghiem : {}", id);
        Optional<DmnhomxetnghiemDTO> dmnhomxetnghiemDTO = dmnhomxetnghiemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmnhomxetnghiemDTO);
    }

    /**
     * {@code DELETE  /dmnhomxetnghiems/:id} : delete the "id" dmnhomxetnghiem.
     *
     * @param id the id of the dmnhomxetnghiemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmnhomxetnghiems/{id}")
    public ResponseEntity<Void> deleteDmnhomxetnghiem(@PathVariable Long id) {
        log.debug("REST request to delete Dmnhomxetnghiem : {}", id);
        dmnhomxetnghiemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
