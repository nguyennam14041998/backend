package com.api.backend.web.rest;

import com.api.backend.service.DmCDHAService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmCDHADTO;
import com.api.backend.service.dto.DmCDHACriteria;
import com.api.backend.service.DmCDHAQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.DmCDHA}.
 */
@RestController
@RequestMapping("/api")
public class DmCDHAResource {

    private final Logger log = LoggerFactory.getLogger(DmCDHAResource.class);

    private static final String ENTITY_NAME = "dmCDHA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmCDHAService dmCDHAService;

    private final DmCDHAQueryService dmCDHAQueryService;

    public DmCDHAResource(DmCDHAService dmCDHAService, DmCDHAQueryService dmCDHAQueryService) {
        this.dmCDHAService = dmCDHAService;
        this.dmCDHAQueryService = dmCDHAQueryService;
    }

    /**
     * {@code POST  /dm-cdhas} : Create a new dmCDHA.
     *
     * @param dmCDHADTO the dmCDHADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmCDHADTO, or with status {@code 400 (Bad Request)} if the dmCDHA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dm-cdhas")
    public ResponseEntity<DmCDHADTO> createDmCDHA(@RequestBody DmCDHADTO dmCDHADTO) throws URISyntaxException {
        log.debug("REST request to save DmCDHA : {}", dmCDHADTO);
        if (dmCDHADTO.getId() != null) {
            throw new BadRequestAlertException("A new dmCDHA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmCDHADTO result = dmCDHAService.save(dmCDHADTO);
        return ResponseEntity.created(new URI("/api/dm-cdhas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dm-cdhas} : Updates an existing dmCDHA.
     *
     * @param dmCDHADTO the dmCDHADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmCDHADTO,
     * or with status {@code 400 (Bad Request)} if the dmCDHADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmCDHADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dm-cdhas")
    public ResponseEntity<DmCDHADTO> updateDmCDHA(@RequestBody DmCDHADTO dmCDHADTO) throws URISyntaxException {
        log.debug("REST request to update DmCDHA : {}", dmCDHADTO);
        if (dmCDHADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmCDHADTO result = dmCDHAService.save(dmCDHADTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmCDHADTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dm-cdhas} : get all the dmCDHAS.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmCDHAS in body.
     */
    @GetMapping("/dm-cdhas")
    public ResponseEntity<List<DmCDHADTO>> getAllDmCDHAS(DmCDHACriteria criteria, Pageable pageable) {
        log.debug("REST request to get DmCDHAS by criteria: {}", criteria);
        Page<DmCDHADTO> page = dmCDHAQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dm-cdhas/count} : count all the dmCDHAS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dm-cdhas/count")
    public ResponseEntity<Long> countDmCDHAS(DmCDHACriteria criteria) {
        log.debug("REST request to count DmCDHAS by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmCDHAQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dm-cdhas/:id} : get the "id" dmCDHA.
     *
     * @param id the id of the dmCDHADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmCDHADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dm-cdhas/{id}")
    public ResponseEntity<DmCDHADTO> getDmCDHA(@PathVariable Long id) {
        log.debug("REST request to get DmCDHA : {}", id);
        Optional<DmCDHADTO> dmCDHADTO = dmCDHAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmCDHADTO);
    }

    /**
     * {@code DELETE  /dm-cdhas/:id} : delete the "id" dmCDHA.
     *
     * @param id the id of the dmCDHADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dm-cdhas/{id}")
    public ResponseEntity<Void> deleteDmCDHA(@PathVariable Long id) {
        log.debug("REST request to delete DmCDHA : {}", id);
        dmCDHAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
