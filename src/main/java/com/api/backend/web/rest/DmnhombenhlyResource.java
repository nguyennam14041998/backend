package com.api.backend.web.rest;

import com.api.backend.service.DmnhombenhlyService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmnhombenhlyDTO;
import com.api.backend.service.dto.DmnhombenhlyCriteria;
import com.api.backend.service.DmnhombenhlyQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmnhombenhly}.
 */
@RestController
@RequestMapping("/api")
public class DmnhombenhlyResource {

    private final Logger log = LoggerFactory.getLogger(DmnhombenhlyResource.class);

    private static final String ENTITY_NAME = "dmnhombenhly";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmnhombenhlyService dmnhombenhlyService;

    private final DmnhombenhlyQueryService dmnhombenhlyQueryService;

    public DmnhombenhlyResource(DmnhombenhlyService dmnhombenhlyService, DmnhombenhlyQueryService dmnhombenhlyQueryService) {
        this.dmnhombenhlyService = dmnhombenhlyService;
        this.dmnhombenhlyQueryService = dmnhombenhlyQueryService;
    }

    /**
     * {@code POST  /dmnhombenhlies} : Create a new dmnhombenhly.
     *
     * @param dmnhombenhlyDTO the dmnhombenhlyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmnhombenhlyDTO, or with status {@code 400 (Bad Request)} if the dmnhombenhly has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmnhombenhlies")
    public ResponseEntity<DmnhombenhlyDTO> createDmnhombenhly(@RequestBody DmnhombenhlyDTO dmnhombenhlyDTO) throws URISyntaxException {
        log.debug("REST request to save Dmnhombenhly : {}", dmnhombenhlyDTO);
        if (dmnhombenhlyDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmnhombenhly cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmnhombenhlyDTO result = dmnhombenhlyService.save(dmnhombenhlyDTO);
        return ResponseEntity.created(new URI("/api/dmnhombenhlies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmnhombenhlies} : Updates an existing dmnhombenhly.
     *
     * @param dmnhombenhlyDTO the dmnhombenhlyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmnhombenhlyDTO,
     * or with status {@code 400 (Bad Request)} if the dmnhombenhlyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmnhombenhlyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmnhombenhlies")
    public ResponseEntity<DmnhombenhlyDTO> updateDmnhombenhly(@RequestBody DmnhombenhlyDTO dmnhombenhlyDTO) throws URISyntaxException {
        log.debug("REST request to update Dmnhombenhly : {}", dmnhombenhlyDTO);
        if (dmnhombenhlyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmnhombenhlyDTO result = dmnhombenhlyService.save(dmnhombenhlyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmnhombenhlyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmnhombenhlies} : get all the dmnhombenhlies.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmnhombenhlies in body.
     */
    @GetMapping("/dmnhombenhlies")
    public ResponseEntity<List<DmnhombenhlyDTO>> getAllDmnhombenhlies(DmnhombenhlyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmnhombenhlies by criteria: {}", criteria);
        Page<DmnhombenhlyDTO> page = dmnhombenhlyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmnhombenhlies/count} : count all the dmnhombenhlies.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmnhombenhlies/count")
    public ResponseEntity<Long> countDmnhombenhlies(DmnhombenhlyCriteria criteria) {
        log.debug("REST request to count Dmnhombenhlies by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmnhombenhlyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmnhombenhlies/:id} : get the "id" dmnhombenhly.
     *
     * @param id the id of the dmnhombenhlyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmnhombenhlyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmnhombenhlies/{id}")
    public ResponseEntity<DmnhombenhlyDTO> getDmnhombenhly(@PathVariable Long id) {
        log.debug("REST request to get Dmnhombenhly : {}", id);
        Optional<DmnhombenhlyDTO> dmnhombenhlyDTO = dmnhombenhlyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmnhombenhlyDTO);
    }

    /**
     * {@code DELETE  /dmnhombenhlies/:id} : delete the "id" dmnhombenhly.
     *
     * @param id the id of the dmnhombenhlyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmnhombenhlies/{id}")
    public ResponseEntity<Void> deleteDmnhombenhly(@PathVariable Long id) {
        log.debug("REST request to delete Dmnhombenhly : {}", id);
        dmnhombenhlyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
