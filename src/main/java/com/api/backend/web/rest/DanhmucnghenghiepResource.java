package com.api.backend.web.rest;

import com.api.backend.service.DanhmucnghenghiepService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.DanhmucnghenghiepDTO;
import com.api.backend.service.dto.DanhmucnghenghiepCriteria;
import com.api.backend.service.DanhmucnghenghiepQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Danhmucnghenghiep}.
 */
@RestController
@RequestMapping("/api")
public class DanhmucnghenghiepResource {

    private final Logger log = LoggerFactory.getLogger(DanhmucnghenghiepResource.class);

    private static final String ENTITY_NAME = "danhmucnghenghiep";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhmucnghenghiepService danhmucnghenghiepService;

    private final DanhmucnghenghiepQueryService danhmucnghenghiepQueryService;

    public DanhmucnghenghiepResource(DanhmucnghenghiepService danhmucnghenghiepService, DanhmucnghenghiepQueryService danhmucnghenghiepQueryService) {
        this.danhmucnghenghiepService = danhmucnghenghiepService;
        this.danhmucnghenghiepQueryService = danhmucnghenghiepQueryService;
    }

    /**
     * {@code POST  /danhmucnghenghieps} : Create a new danhmucnghenghiep.
     *
     * @param danhmucnghenghiepDTO the danhmucnghenghiepDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhmucnghenghiepDTO, or with status {@code 400 (Bad Request)} if the danhmucnghenghiep has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danhmucnghenghieps")
    public ResponseEntity<DanhmucnghenghiepDTO> createDanhmucnghenghiep(@RequestBody DanhmucnghenghiepDTO danhmucnghenghiepDTO) throws URISyntaxException {
        log.debug("REST request to save Danhmucnghenghiep : {}", danhmucnghenghiepDTO);
        if (danhmucnghenghiepDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhmucnghenghiep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhmucnghenghiepDTO result = danhmucnghenghiepService.save(danhmucnghenghiepDTO);
        return ResponseEntity.created(new URI("/api/danhmucnghenghieps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danhmucnghenghieps} : Updates an existing danhmucnghenghiep.
     *
     * @param danhmucnghenghiepDTO the danhmucnghenghiepDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhmucnghenghiepDTO,
     * or with status {@code 400 (Bad Request)} if the danhmucnghenghiepDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhmucnghenghiepDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danhmucnghenghieps")
    public ResponseEntity<DanhmucnghenghiepDTO> updateDanhmucnghenghiep(@RequestBody DanhmucnghenghiepDTO danhmucnghenghiepDTO) throws URISyntaxException {
        log.debug("REST request to update Danhmucnghenghiep : {}", danhmucnghenghiepDTO);
        if (danhmucnghenghiepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhmucnghenghiepDTO result = danhmucnghenghiepService.save(danhmucnghenghiepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhmucnghenghiepDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danhmucnghenghieps} : get all the danhmucnghenghieps.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhmucnghenghieps in body.
     */
    @GetMapping("/danhmucnghenghieps")
    public ResponseEntity<List<DanhmucnghenghiepDTO>> getAllDanhmucnghenghieps(DanhmucnghenghiepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Danhmucnghenghieps by criteria: {}", criteria);
        Page<DanhmucnghenghiepDTO> page = danhmucnghenghiepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danhmucnghenghieps/count} : count all the danhmucnghenghieps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danhmucnghenghieps/count")
    public ResponseEntity<Long> countDanhmucnghenghieps(DanhmucnghenghiepCriteria criteria) {
        log.debug("REST request to count Danhmucnghenghieps by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhmucnghenghiepQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danhmucnghenghieps/:id} : get the "id" danhmucnghenghiep.
     *
     * @param id the id of the danhmucnghenghiepDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhmucnghenghiepDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danhmucnghenghieps/{id}")
    public ResponseEntity<DanhmucnghenghiepDTO> getDanhmucnghenghiep(@PathVariable Long id) {
        log.debug("REST request to get Danhmucnghenghiep : {}", id);
        Optional<DanhmucnghenghiepDTO> danhmucnghenghiepDTO = danhmucnghenghiepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhmucnghenghiepDTO);
    }

    /**
     * {@code DELETE  /danhmucnghenghieps/:id} : delete the "id" danhmucnghenghiep.
     *
     * @param id the id of the danhmucnghenghiepDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danhmucnghenghieps/{id}")
    public ResponseEntity<Void> deleteDanhmucnghenghiep(@PathVariable Long id) {
        log.debug("REST request to delete Danhmucnghenghiep : {}", id);
        danhmucnghenghiepService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
