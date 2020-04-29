package com.api.backend.web.rest;

import com.api.backend.service.DmbenhlyService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmbenhlyDTO;
import com.api.backend.service.dto.DmbenhlyCriteria;
import com.api.backend.service.DmbenhlyQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmbenhly}.
 */
@RestController
@RequestMapping("/api")
public class DmbenhlyResource {

    private final Logger log = LoggerFactory.getLogger(DmbenhlyResource.class);

    private static final String ENTITY_NAME = "dmbenhly";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmbenhlyService dmbenhlyService;

    private final DmbenhlyQueryService dmbenhlyQueryService;

    public DmbenhlyResource(DmbenhlyService dmbenhlyService, DmbenhlyQueryService dmbenhlyQueryService) {
        this.dmbenhlyService = dmbenhlyService;
        this.dmbenhlyQueryService = dmbenhlyQueryService;
    }

    /**
     * {@code POST  /dmbenhlies} : Create a new dmbenhly.
     *
     * @param dmbenhlyDTO the dmbenhlyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmbenhlyDTO, or with status {@code 400 (Bad Request)} if the dmbenhly has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmbenhlies")
    public ResponseEntity<DmbenhlyDTO> createDmbenhly(@RequestBody DmbenhlyDTO dmbenhlyDTO) throws URISyntaxException {
        log.debug("REST request to save Dmbenhly : {}", dmbenhlyDTO);
        if (dmbenhlyDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmbenhly cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmbenhlyDTO result = dmbenhlyService.save(dmbenhlyDTO);
        return ResponseEntity.created(new URI("/api/dmbenhlies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmbenhlies} : Updates an existing dmbenhly.
     *
     * @param dmbenhlyDTO the dmbenhlyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmbenhlyDTO,
     * or with status {@code 400 (Bad Request)} if the dmbenhlyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmbenhlyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmbenhlies")
    public ResponseEntity<DmbenhlyDTO> updateDmbenhly(@RequestBody DmbenhlyDTO dmbenhlyDTO) throws URISyntaxException {
        log.debug("REST request to update Dmbenhly : {}", dmbenhlyDTO);
        if (dmbenhlyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmbenhlyDTO result = dmbenhlyService.save(dmbenhlyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmbenhlyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmbenhlies} : get all the dmbenhlies.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmbenhlies in body.
     */
    @GetMapping("/dmbenhlies")
    public ResponseEntity<List<DmbenhlyDTO>> getAllDmbenhlies(DmbenhlyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmbenhlies by criteria: {}", criteria);
        Page<DmbenhlyDTO> page = dmbenhlyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmbenhlies/count} : count all the dmbenhlies.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmbenhlies/count")
    public ResponseEntity<Long> countDmbenhlies(DmbenhlyCriteria criteria) {
        log.debug("REST request to count Dmbenhlies by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmbenhlyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmbenhlies/:id} : get the "id" dmbenhly.
     *
     * @param id the id of the dmbenhlyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmbenhlyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmbenhlies/{id}")
    public ResponseEntity<DmbenhlyDTO> getDmbenhly(@PathVariable Long id) {
        log.debug("REST request to get Dmbenhly : {}", id);
        Optional<DmbenhlyDTO> dmbenhlyDTO = dmbenhlyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmbenhlyDTO);
    }

    /**
     * {@code DELETE  /dmbenhlies/:id} : delete the "id" dmbenhly.
     *
     * @param id the id of the dmbenhlyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmbenhlies/{id}")
    public ResponseEntity<Void> deleteDmbenhly(@PathVariable Long id) {
        log.debug("REST request to delete Dmbenhly : {}", id);
        dmbenhlyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
