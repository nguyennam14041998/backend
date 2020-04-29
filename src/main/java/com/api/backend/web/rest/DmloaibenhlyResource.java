package com.api.backend.web.rest;

import com.api.backend.service.DmloaibenhlyService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmloaibenhlyDTO;
import com.api.backend.service.dto.DmloaibenhlyCriteria;
import com.api.backend.service.DmloaibenhlyQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmloaibenhly}.
 */
@RestController
@RequestMapping("/api")
public class DmloaibenhlyResource {

    private final Logger log = LoggerFactory.getLogger(DmloaibenhlyResource.class);

    private static final String ENTITY_NAME = "dmloaibenhly";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmloaibenhlyService dmloaibenhlyService;

    private final DmloaibenhlyQueryService dmloaibenhlyQueryService;

    public DmloaibenhlyResource(DmloaibenhlyService dmloaibenhlyService, DmloaibenhlyQueryService dmloaibenhlyQueryService) {
        this.dmloaibenhlyService = dmloaibenhlyService;
        this.dmloaibenhlyQueryService = dmloaibenhlyQueryService;
    }

    /**
     * {@code POST  /dmloaibenhlies} : Create a new dmloaibenhly.
     *
     * @param dmloaibenhlyDTO the dmloaibenhlyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmloaibenhlyDTO, or with status {@code 400 (Bad Request)} if the dmloaibenhly has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmloaibenhlies")
    public ResponseEntity<DmloaibenhlyDTO> createDmloaibenhly(@RequestBody DmloaibenhlyDTO dmloaibenhlyDTO) throws URISyntaxException {
        log.debug("REST request to save Dmloaibenhly : {}", dmloaibenhlyDTO);
        if (dmloaibenhlyDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmloaibenhly cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmloaibenhlyDTO result = dmloaibenhlyService.save(dmloaibenhlyDTO);
        return ResponseEntity.created(new URI("/api/dmloaibenhlies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmloaibenhlies} : Updates an existing dmloaibenhly.
     *
     * @param dmloaibenhlyDTO the dmloaibenhlyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmloaibenhlyDTO,
     * or with status {@code 400 (Bad Request)} if the dmloaibenhlyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmloaibenhlyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmloaibenhlies")
    public ResponseEntity<DmloaibenhlyDTO> updateDmloaibenhly(@RequestBody DmloaibenhlyDTO dmloaibenhlyDTO) throws URISyntaxException {
        log.debug("REST request to update Dmloaibenhly : {}", dmloaibenhlyDTO);
        if (dmloaibenhlyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmloaibenhlyDTO result = dmloaibenhlyService.save(dmloaibenhlyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmloaibenhlyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmloaibenhlies} : get all the dmloaibenhlies.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmloaibenhlies in body.
     */
    @GetMapping("/dmloaibenhlies")
    public ResponseEntity<List<DmloaibenhlyDTO>> getAllDmloaibenhlies(DmloaibenhlyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmloaibenhlies by criteria: {}", criteria);
        Page<DmloaibenhlyDTO> page = dmloaibenhlyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmloaibenhlies/count} : count all the dmloaibenhlies.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmloaibenhlies/count")
    public ResponseEntity<Long> countDmloaibenhlies(DmloaibenhlyCriteria criteria) {
        log.debug("REST request to count Dmloaibenhlies by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmloaibenhlyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmloaibenhlies/:id} : get the "id" dmloaibenhly.
     *
     * @param id the id of the dmloaibenhlyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmloaibenhlyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmloaibenhlies/{id}")
    public ResponseEntity<DmloaibenhlyDTO> getDmloaibenhly(@PathVariable Long id) {
        log.debug("REST request to get Dmloaibenhly : {}", id);
        Optional<DmloaibenhlyDTO> dmloaibenhlyDTO = dmloaibenhlyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmloaibenhlyDTO);
    }

    /**
     * {@code DELETE  /dmloaibenhlies/:id} : delete the "id" dmloaibenhly.
     *
     * @param id the id of the dmloaibenhlyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmloaibenhlies/{id}")
    public ResponseEntity<Void> deleteDmloaibenhly(@PathVariable Long id) {
        log.debug("REST request to delete Dmloaibenhly : {}", id);
        dmloaibenhlyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
