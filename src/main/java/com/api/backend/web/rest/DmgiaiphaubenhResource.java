package com.api.backend.web.rest;

import com.api.backend.service.DmgiaiphaubenhService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;
import com.api.backend.service.dto.DmgiaiphaubenhCriteria;
import com.api.backend.service.DmgiaiphaubenhQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmgiaiphaubenh}.
 */
@RestController
@RequestMapping("/api")
public class DmgiaiphaubenhResource {

    private final Logger log = LoggerFactory.getLogger(DmgiaiphaubenhResource.class);

    private static final String ENTITY_NAME = "dmgiaiphaubenh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmgiaiphaubenhService dmgiaiphaubenhService;

    private final DmgiaiphaubenhQueryService dmgiaiphaubenhQueryService;

    public DmgiaiphaubenhResource(DmgiaiphaubenhService dmgiaiphaubenhService, DmgiaiphaubenhQueryService dmgiaiphaubenhQueryService) {
        this.dmgiaiphaubenhService = dmgiaiphaubenhService;
        this.dmgiaiphaubenhQueryService = dmgiaiphaubenhQueryService;
    }

    /**
     * {@code POST  /dmgiaiphaubenhs} : Create a new dmgiaiphaubenh.
     *
     * @param dmgiaiphaubenhDTO the dmgiaiphaubenhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmgiaiphaubenhDTO, or with status {@code 400 (Bad Request)} if the dmgiaiphaubenh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmgiaiphaubenhs")
    public ResponseEntity<DmgiaiphaubenhDTO> createDmgiaiphaubenh(@RequestBody DmgiaiphaubenhDTO dmgiaiphaubenhDTO) throws URISyntaxException {
        log.debug("REST request to save Dmgiaiphaubenh : {}", dmgiaiphaubenhDTO);
        if (dmgiaiphaubenhDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmgiaiphaubenh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmgiaiphaubenhDTO result = dmgiaiphaubenhService.save(dmgiaiphaubenhDTO);
        return ResponseEntity.created(new URI("/api/dmgiaiphaubenhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmgiaiphaubenhs} : Updates an existing dmgiaiphaubenh.
     *
     * @param dmgiaiphaubenhDTO the dmgiaiphaubenhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmgiaiphaubenhDTO,
     * or with status {@code 400 (Bad Request)} if the dmgiaiphaubenhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmgiaiphaubenhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmgiaiphaubenhs")
    public ResponseEntity<DmgiaiphaubenhDTO> updateDmgiaiphaubenh(@RequestBody DmgiaiphaubenhDTO dmgiaiphaubenhDTO) throws URISyntaxException {
        log.debug("REST request to update Dmgiaiphaubenh : {}", dmgiaiphaubenhDTO);
        if (dmgiaiphaubenhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmgiaiphaubenhDTO result = dmgiaiphaubenhService.save(dmgiaiphaubenhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmgiaiphaubenhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmgiaiphaubenhs} : get all the dmgiaiphaubenhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmgiaiphaubenhs in body.
     */
    @GetMapping("/dmgiaiphaubenhs")
    public ResponseEntity<List<DmgiaiphaubenhDTO>> getAllDmgiaiphaubenhs(DmgiaiphaubenhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmgiaiphaubenhs by criteria: {}", criteria);
        Page<DmgiaiphaubenhDTO> page = dmgiaiphaubenhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmgiaiphaubenhs/count} : count all the dmgiaiphaubenhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmgiaiphaubenhs/count")
    public ResponseEntity<Long> countDmgiaiphaubenhs(DmgiaiphaubenhCriteria criteria) {
        log.debug("REST request to count Dmgiaiphaubenhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmgiaiphaubenhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmgiaiphaubenhs/:id} : get the "id" dmgiaiphaubenh.
     *
     * @param id the id of the dmgiaiphaubenhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmgiaiphaubenhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmgiaiphaubenhs/{id}")
    public ResponseEntity<DmgiaiphaubenhDTO> getDmgiaiphaubenh(@PathVariable Long id) {
        log.debug("REST request to get Dmgiaiphaubenh : {}", id);
        Optional<DmgiaiphaubenhDTO> dmgiaiphaubenhDTO = dmgiaiphaubenhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmgiaiphaubenhDTO);
    }

    /**
     * {@code DELETE  /dmgiaiphaubenhs/:id} : delete the "id" dmgiaiphaubenh.
     *
     * @param id the id of the dmgiaiphaubenhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmgiaiphaubenhs/{id}")
    public ResponseEntity<Void> deleteDmgiaiphaubenh(@PathVariable Long id) {
        log.debug("REST request to delete Dmgiaiphaubenh : {}", id);
        dmgiaiphaubenhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
