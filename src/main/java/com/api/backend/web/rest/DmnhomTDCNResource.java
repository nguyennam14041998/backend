package com.api.backend.web.rest;

import com.api.backend.service.DmnhomTDCNService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmnhomTDCNDTO;
import com.api.backend.service.dto.DmnhomTDCNCriteria;
import com.api.backend.service.DmnhomTDCNQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.DmnhomTDCN}.
 */
@RestController
@RequestMapping("/api")
public class DmnhomTDCNResource {

    private final Logger log = LoggerFactory.getLogger(DmnhomTDCNResource.class);

    private static final String ENTITY_NAME = "dmnhomTDCN";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmnhomTDCNService dmnhomTDCNService;

    private final DmnhomTDCNQueryService dmnhomTDCNQueryService;

    public DmnhomTDCNResource(DmnhomTDCNService dmnhomTDCNService, DmnhomTDCNQueryService dmnhomTDCNQueryService) {
        this.dmnhomTDCNService = dmnhomTDCNService;
        this.dmnhomTDCNQueryService = dmnhomTDCNQueryService;
    }

    /**
     * {@code POST  /dmnhom-tdcns} : Create a new dmnhomTDCN.
     *
     * @param dmnhomTDCNDTO the dmnhomTDCNDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmnhomTDCNDTO, or with status {@code 400 (Bad Request)} if the dmnhomTDCN has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmnhom-tdcns")
    public ResponseEntity<DmnhomTDCNDTO> createDmnhomTDCN(@RequestBody DmnhomTDCNDTO dmnhomTDCNDTO) throws URISyntaxException {
        log.debug("REST request to save DmnhomTDCN : {}", dmnhomTDCNDTO);
        if (dmnhomTDCNDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmnhomTDCN cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmnhomTDCNDTO result = dmnhomTDCNService.save(dmnhomTDCNDTO);
        return ResponseEntity.created(new URI("/api/dmnhom-tdcns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmnhom-tdcns} : Updates an existing dmnhomTDCN.
     *
     * @param dmnhomTDCNDTO the dmnhomTDCNDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmnhomTDCNDTO,
     * or with status {@code 400 (Bad Request)} if the dmnhomTDCNDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmnhomTDCNDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmnhom-tdcns")
    public ResponseEntity<DmnhomTDCNDTO> updateDmnhomTDCN(@RequestBody DmnhomTDCNDTO dmnhomTDCNDTO) throws URISyntaxException {
        log.debug("REST request to update DmnhomTDCN : {}", dmnhomTDCNDTO);
        if (dmnhomTDCNDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmnhomTDCNDTO result = dmnhomTDCNService.save(dmnhomTDCNDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmnhomTDCNDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmnhom-tdcns} : get all the dmnhomTDCNS.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmnhomTDCNS in body.
     */
    @GetMapping("/dmnhom-tdcns")
    public ResponseEntity<List<DmnhomTDCNDTO>> getAllDmnhomTDCNS(DmnhomTDCNCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DmnhomTDCNS by criteria: {}", criteria);
        Page<DmnhomTDCNDTO> page = dmnhomTDCNQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmnhom-tdcns/count} : count all the dmnhomTDCNS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmnhom-tdcns/count")
    public ResponseEntity<Long> countDmnhomTDCNS(DmnhomTDCNCriteria criteria) {
        log.debug("REST request to count DmnhomTDCNS by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmnhomTDCNQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmnhom-tdcns/:id} : get the "id" dmnhomTDCN.
     *
     * @param id the id of the dmnhomTDCNDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmnhomTDCNDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmnhom-tdcns/{id}")
    public ResponseEntity<DmnhomTDCNDTO> getDmnhomTDCN(@PathVariable Long id) {
        log.debug("REST request to get DmnhomTDCN : {}", id);
        Optional<DmnhomTDCNDTO> dmnhomTDCNDTO = dmnhomTDCNService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmnhomTDCNDTO);
    }

    /**
     * {@code DELETE  /dmnhom-tdcns/:id} : delete the "id" dmnhomTDCN.
     *
     * @param id the id of the dmnhomTDCNDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmnhom-tdcns/{id}")
    public ResponseEntity<Void> deleteDmnhomTDCN(@PathVariable Long id) {
        log.debug("REST request to delete DmnhomTDCN : {}", id);
        dmnhomTDCNService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
