package com.api.backend.web.rest;

import com.api.backend.service.DmTDCNService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmTDCNDTO;
import com.api.backend.service.dto.DmTDCNCriteria;
import com.api.backend.service.DmTDCNQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.DmTDCN}.
 */
@RestController
@RequestMapping("/api")
public class DmTDCNResource {

    private final Logger log = LoggerFactory.getLogger(DmTDCNResource.class);

    private static final String ENTITY_NAME = "dmTDCN";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmTDCNService dmTDCNService;

    private final DmTDCNQueryService dmTDCNQueryService;

    public DmTDCNResource(DmTDCNService dmTDCNService, DmTDCNQueryService dmTDCNQueryService) {
        this.dmTDCNService = dmTDCNService;
        this.dmTDCNQueryService = dmTDCNQueryService;
    }

    /**
     * {@code POST  /dm-tdcns} : Create a new dmTDCN.
     *
     * @param dmTDCNDTO the dmTDCNDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmTDCNDTO, or with status {@code 400 (Bad Request)} if the dmTDCN has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dm-tdcns")
    public ResponseEntity<DmTDCNDTO> createDmTDCN(@RequestBody DmTDCNDTO dmTDCNDTO) throws URISyntaxException {
        log.debug("REST request to save DmTDCN : {}", dmTDCNDTO);
        if (dmTDCNDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmTDCN cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmTDCNDTO result = dmTDCNService.save(dmTDCNDTO);
        return ResponseEntity.created(new URI("/api/dm-tdcns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dm-tdcns} : Updates an existing dmTDCN.
     *
     * @param dmTDCNDTO the dmTDCNDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmTDCNDTO,
     * or with status {@code 400 (Bad Request)} if the dmTDCNDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmTDCNDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dm-tdcns")
    public ResponseEntity<DmTDCNDTO> updateDmTDCN(@RequestBody DmTDCNDTO dmTDCNDTO) throws URISyntaxException {
        log.debug("REST request to update DmTDCN : {}", dmTDCNDTO);
        if (dmTDCNDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmTDCNDTO result = dmTDCNService.save(dmTDCNDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmTDCNDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dm-tdcns} : get all the dmTDCNS.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmTDCNS in body.
     */
    @GetMapping("/dm-tdcns")
    public ResponseEntity<List<DmTDCNDTO>> getAllDmTDCNS(DmTDCNCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DmTDCNS by criteria: {}", criteria);
        Page<DmTDCNDTO> page = dmTDCNQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dm-tdcns/count} : count all the dmTDCNS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dm-tdcns/count")
    public ResponseEntity<Long> countDmTDCNS(DmTDCNCriteria criteria) {
        log.debug("REST request to count DmTDCNS by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmTDCNQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dm-tdcns/:id} : get the "id" dmTDCN.
     *
     * @param id the id of the dmTDCNDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmTDCNDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dm-tdcns/{id}")
    public ResponseEntity<DmTDCNDTO> getDmTDCN(@PathVariable Long id) {
        log.debug("REST request to get DmTDCN : {}", id);
        Optional<DmTDCNDTO> dmTDCNDTO = dmTDCNService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmTDCNDTO);
    }

    /**
     * {@code DELETE  /dm-tdcns/:id} : delete the "id" dmTDCN.
     *
     * @param id the id of the dmTDCNDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dm-tdcns/{id}")
    public ResponseEntity<Void> deleteDmTDCN(@PathVariable Long id) {
        log.debug("REST request to delete DmTDCN : {}", id);
        dmTDCNService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
