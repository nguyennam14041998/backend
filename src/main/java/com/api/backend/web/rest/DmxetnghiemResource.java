package com.api.backend.web.rest;

import com.api.backend.service.DmxetnghiemService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DmxetnghiemDTO;
import com.api.backend.service.dto.DmxetnghiemCriteria;
import com.api.backend.service.DmxetnghiemQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Dmxetnghiem}.
 */
@RestController
@RequestMapping("/api")
public class DmxetnghiemResource {

    private final Logger log = LoggerFactory.getLogger(DmxetnghiemResource.class);

    private static final String ENTITY_NAME = "dmxetnghiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmxetnghiemService dmxetnghiemService;

    private final DmxetnghiemQueryService dmxetnghiemQueryService;

    public DmxetnghiemResource(DmxetnghiemService dmxetnghiemService, DmxetnghiemQueryService dmxetnghiemQueryService) {
        this.dmxetnghiemService = dmxetnghiemService;
        this.dmxetnghiemQueryService = dmxetnghiemQueryService;
    }

    /**
     * {@code POST  /dmxetnghiems} : Create a new dmxetnghiem.
     *
     * @param dmxetnghiemDTO the dmxetnghiemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dmxetnghiemDTO, or with status {@code 400 (Bad Request)} if the dmxetnghiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dmxetnghiems")
    public ResponseEntity<DmxetnghiemDTO> createDmxetnghiem(@RequestBody DmxetnghiemDTO dmxetnghiemDTO) throws URISyntaxException {
        log.debug("REST request to save Dmxetnghiem : {}", dmxetnghiemDTO);
        if (dmxetnghiemDTO.getId() != null) {
            throw new BadRequestAlertException("A new dmxetnghiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DmxetnghiemDTO result = dmxetnghiemService.save(dmxetnghiemDTO);
        return ResponseEntity.created(new URI("/api/dmxetnghiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dmxetnghiems} : Updates an existing dmxetnghiem.
     *
     * @param dmxetnghiemDTO the dmxetnghiemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dmxetnghiemDTO,
     * or with status {@code 400 (Bad Request)} if the dmxetnghiemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dmxetnghiemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dmxetnghiems")
    public ResponseEntity<DmxetnghiemDTO> updateDmxetnghiem(@RequestBody DmxetnghiemDTO dmxetnghiemDTO) throws URISyntaxException {
        log.debug("REST request to update Dmxetnghiem : {}", dmxetnghiemDTO);
        if (dmxetnghiemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DmxetnghiemDTO result = dmxetnghiemService.save(dmxetnghiemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dmxetnghiemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dmxetnghiems} : get all the dmxetnghiems.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmxetnghiems in body.
     */
    @GetMapping("/dmxetnghiems")
    public ResponseEntity<List<DmxetnghiemDTO>> getAllDmxetnghiems(DmxetnghiemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Dmxetnghiems by criteria: {}", criteria);
        Page<DmxetnghiemDTO> page = dmxetnghiemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dmxetnghiems/count} : count all the dmxetnghiems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dmxetnghiems/count")
    public ResponseEntity<Long> countDmxetnghiems(DmxetnghiemCriteria criteria) {
        log.debug("REST request to count Dmxetnghiems by criteria: {}", criteria);
        return ResponseEntity.ok().body(dmxetnghiemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dmxetnghiems/:id} : get the "id" dmxetnghiem.
     *
     * @param id the id of the dmxetnghiemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmxetnghiemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dmxetnghiems/{id}")
    public ResponseEntity<DmxetnghiemDTO> getDmxetnghiem(@PathVariable Long id) {
        log.debug("REST request to get Dmxetnghiem : {}", id);
        Optional<DmxetnghiemDTO> dmxetnghiemDTO = dmxetnghiemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmxetnghiemDTO);
    }

    /**
     * {@code DELETE  /dmxetnghiems/:id} : delete the "id" dmxetnghiem.
     *
     * @param id the id of the dmxetnghiemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dmxetnghiems/{id}")
    public ResponseEntity<Void> deleteDmxetnghiem(@PathVariable Long id) {
        log.debug("REST request to delete Dmxetnghiem : {}", id);
        dmxetnghiemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
