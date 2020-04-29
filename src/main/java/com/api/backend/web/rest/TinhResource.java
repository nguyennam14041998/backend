package com.api.backend.web.rest;

import com.api.backend.service.TinhService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.TinhDTO;
import com.api.backend.service.dto.TinhCriteria;
import com.api.backend.service.TinhQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Tinh}.
 */
@RestController
@RequestMapping("/api")
public class TinhResource {

    private final Logger log = LoggerFactory.getLogger(TinhResource.class);

    private static final String ENTITY_NAME = "tinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TinhService tinhService;

    private final TinhQueryService tinhQueryService;

    public TinhResource(TinhService tinhService, TinhQueryService tinhQueryService) {
        this.tinhService = tinhService;
        this.tinhQueryService = tinhQueryService;
    }

    /**
     * {@code POST  /tinhs} : Create a new tinh.
     *
     * @param tinhDTO the tinhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tinhDTO, or with status {@code 400 (Bad Request)} if the tinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tinhs")
    public ResponseEntity<TinhDTO> createTinh(@RequestBody TinhDTO tinhDTO) throws URISyntaxException {
        log.debug("REST request to save Tinh : {}", tinhDTO);
        if (tinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new tinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TinhDTO result = tinhService.save(tinhDTO);
        return ResponseEntity.created(new URI("/api/tinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tinhs} : Updates an existing tinh.
     *
     * @param tinhDTO the tinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tinhDTO,
     * or with status {@code 400 (Bad Request)} if the tinhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tinhs")
    public ResponseEntity<TinhDTO> updateTinh(@RequestBody TinhDTO tinhDTO) throws URISyntaxException {
        log.debug("REST request to update Tinh : {}", tinhDTO);
        if (tinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TinhDTO result = tinhService.save(tinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tinhs} : get all the tinhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tinhs in body.
     */
    @GetMapping("/tinhs")
    public ResponseEntity<List<TinhDTO>> getAllTinhs(TinhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Tinhs by criteria: {}", criteria);
        Page<TinhDTO> page = tinhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tinhs/count} : count all the tinhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tinhs/count")
    public ResponseEntity<Long> countTinhs(TinhCriteria criteria) {
        log.debug("REST request to count Tinhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(tinhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tinhs/:id} : get the "id" tinh.
     *
     * @param id the id of the tinhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tinhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tinhs/{id}")
    public ResponseEntity<TinhDTO> getTinh(@PathVariable Long id) {
        log.debug("REST request to get Tinh : {}", id);
        Optional<TinhDTO> tinhDTO = tinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tinhDTO);
    }

    /**
     * {@code DELETE  /tinhs/:id} : delete the "id" tinh.
     *
     * @param id the id of the tinhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tinhs/{id}")
    public ResponseEntity<Void> deleteTinh(@PathVariable Long id) {
        log.debug("REST request to delete Tinh : {}", id);
        tinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
