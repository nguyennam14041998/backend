package com.api.backend.web.rest;

import com.api.backend.service.HuyenService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.HuyenDTO;
import com.api.backend.service.dto.HuyenCriteria;
import com.api.backend.service.HuyenQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Huyen}.
 */
@RestController
@RequestMapping("/api")
public class HuyenResource {

    private final Logger log = LoggerFactory.getLogger(HuyenResource.class);

    private static final String ENTITY_NAME = "huyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HuyenService huyenService;

    private final HuyenQueryService huyenQueryService;

    public HuyenResource(HuyenService huyenService, HuyenQueryService huyenQueryService) {
        this.huyenService = huyenService;
        this.huyenQueryService = huyenQueryService;
    }

    /**
     * {@code POST  /huyens} : Create a new huyen.
     *
     * @param huyenDTO the huyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new huyenDTO, or with status {@code 400 (Bad Request)} if the huyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/huyens")
    public ResponseEntity<HuyenDTO> createHuyen(@RequestBody HuyenDTO huyenDTO) throws URISyntaxException {
        log.debug("REST request to save Huyen : {}", huyenDTO);
        if (huyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new huyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HuyenDTO result = huyenService.save(huyenDTO);
        return ResponseEntity.created(new URI("/api/huyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /huyens} : Updates an existing huyen.
     *
     * @param huyenDTO the huyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated huyenDTO,
     * or with status {@code 400 (Bad Request)} if the huyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the huyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/huyens")
    public ResponseEntity<HuyenDTO> updateHuyen(@RequestBody HuyenDTO huyenDTO) throws URISyntaxException {
        log.debug("REST request to update Huyen : {}", huyenDTO);
        if (huyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HuyenDTO result = huyenService.save(huyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, huyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /huyens} : get all the huyens.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of huyens in body.
     */
    @GetMapping("/huyens")
    public ResponseEntity<List<HuyenDTO>> getAllHuyens(HuyenCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Huyens by criteria: {}", criteria);
        Page<HuyenDTO> page = huyenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /huyens/count} : count all the huyens.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/huyens/count")
    public ResponseEntity<Long> countHuyens(HuyenCriteria criteria) {
        log.debug("REST request to count Huyens by criteria: {}", criteria);
        return ResponseEntity.ok().body(huyenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /huyens/:id} : get the "id" huyen.
     *
     * @param id the id of the huyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the huyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/huyens/{id}")
    public ResponseEntity<HuyenDTO> getHuyen(@PathVariable Long id) {
        log.debug("REST request to get Huyen : {}", id);
        Optional<HuyenDTO> huyenDTO = huyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huyenDTO);
    }

    /**
     * {@code DELETE  /huyens/:id} : delete the "id" huyen.
     *
     * @param id the id of the huyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/huyens/{id}")
    public ResponseEntity<Void> deleteHuyen(@PathVariable Long id) {
        log.debug("REST request to delete Huyen : {}", id);
        huyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
