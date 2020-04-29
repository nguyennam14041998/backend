package com.api.backend.web.rest;

import com.api.backend.service.DmnhomCDHAService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmnhomCDHADTO;
import com.api.backend.service.dto.DmnhomCDHACriteria;
import com.api.backend.service.DmnhomCDHAQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.DmnhomCDHA}.
 */
@RestController
@RequestMapping("/api")
public class DmnhomCDHAResource {

    private final Logger log = LoggerFactory.getLogger(DmnhomCDHAResource.class);

    private static final String ENTITY_NAME = "dmnhomCDHA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmnhomCDHAService dmnhomCDHAService;

    private final DmnhomCDHAQueryService dmnhomCDHAQueryService;

    public DmnhomCDHAResource(DmnhomCDHAService dmnhomCDHAService, DmnhomCDHAQueryService dmnhomCDHAQueryService) {
        this.dmnhomCDHAService = dmnhomCDHAService;
        this.dmnhomCDHAQueryService = dmnhomCDHAQueryService;
    }

    /**
     * {@code POST  /dmnhom-cdhas} : Create a new dmnhomCDHA.
     *
     * @param dmnhomCDHADTO the dmnhomCDHADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmnhomCDHADTO, or with status {@code 400 (Bad Request)} if the dmnhomCDHA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmnhom-cdhas")
    public ResponseEntity<DmnhomCDHADTO> createDmnhomCDHA(@RequestBody DmnhomCDHADTO dmnhomCDHADTO) throws URISyntaxException {
        log.debug("REST request to save DmnhomCDHA : {}", dmnhomCDHADTO);
        if (dmnhomCDHADTO.getId() != null) {
            throw new BadRequestAlertException("A new dmnhomCDHA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmnhomCDHADTO result = dmnhomCDHAService.save(dmnhomCDHADTO);
        return ResponseEntity.created(new URI("/api/dmnhom-cdhas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmnhom-cdhas} : Updates an existing dmnhomCDHA.
     *
     * @param dmnhomCDHADTO the dmnhomCDHADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmnhomCDHADTO,
     * or with status {@code 400 (Bad Request)} if the dmnhomCDHADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmnhomCDHADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmnhom-cdhas")
    public ResponseEntity<DmnhomCDHADTO> updateDmnhomCDHA(@RequestBody DmnhomCDHADTO dmnhomCDHADTO) throws URISyntaxException {
        log.debug("REST request to update DmnhomCDHA : {}", dmnhomCDHADTO);
        if (dmnhomCDHADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmnhomCDHADTO result = dmnhomCDHAService.save(dmnhomCDHADTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmnhomCDHADTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmnhom-cdhas} : get all the dmnhomCDHAS.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmnhomCDHAS in body.
     */
    @GetMapping("/dmnhom-cdhas")
    public ResponseEntity<List<DmnhomCDHADTO>> getAllDmnhomCDHAS(DmnhomCDHACriteria criteria, Pageable pageable) {
        log.debug("REST request to get DmnhomCDHAS by criteria: {}", criteria);
        Page<DmnhomCDHADTO> page = dmnhomCDHAQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmnhom-cdhas/count} : count all the dmnhomCDHAS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmnhom-cdhas/count")
    public ResponseEntity<Long> countDmnhomCDHAS(DmnhomCDHACriteria criteria) {
        log.debug("REST request to count DmnhomCDHAS by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmnhomCDHAQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmnhom-cdhas/:id} : get the "id" dmnhomCDHA.
     *
     * @param id the id of the dmnhomCDHADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmnhomCDHADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmnhom-cdhas/{id}")
    public ResponseEntity<DmnhomCDHADTO> getDmnhomCDHA(@PathVariable Long id) {
        log.debug("REST request to get DmnhomCDHA : {}", id);
        Optional<DmnhomCDHADTO> dmnhomCDHADTO = dmnhomCDHAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmnhomCDHADTO);
    }

    /**
     * {@code DELETE  /dmnhom-cdhas/:id} : delete the "id" dmnhomCDHA.
     *
     * @param id the id of the dmnhomCDHADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmnhom-cdhas/{id}")
    public ResponseEntity<Void> deleteDmnhomCDHA(@PathVariable Long id) {
        log.debug("REST request to delete DmnhomCDHA : {}", id);
        dmnhomCDHAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
