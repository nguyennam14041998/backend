package com.api.backend.web.rest;

import com.api.backend.service.XaService;
import com.api.backend.web.rest.errors.BadRequestAlertException;
import com.api.backend.service.dto.XaDTO;
import com.api.backend.service.dto.XaCriteria;
import com.api.backend.service.XaQueryService;

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
 * REST controller for managing {@link com.api.backend.domain.Xa}.
 */
@RestController
@RequestMapping("/api")
public class XaResource {

    private final Logger log = LoggerFactory.getLogger(XaResource.class);

    private static final String ENTITY_NAME = "xa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final XaService xaService;

    private final XaQueryService xaQueryService;

    public XaResource(XaService xaService, XaQueryService xaQueryService) {
        this.xaService = xaService;
        this.xaQueryService = xaQueryService;
    }

    /**
     * {@code POST  /xas} : Create a new xa.
     *
     * @param xaDTO the xaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new xaDTO, or with status {@code 400 (Bad Request)} if the xa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/xas")
    public ResponseEntity<XaDTO> createXa(@RequestBody XaDTO xaDTO) throws URISyntaxException {
        log.debug("REST request to save Xa : {}", xaDTO);
        if (xaDTO.getId() != null) {
            throw new BadRequestAlertException("A new xa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        XaDTO result = xaService.save(xaDTO);
        return ResponseEntity.created(new URI("/api/xas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /xas} : Updates an existing xa.
     *
     * @param xaDTO the xaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated xaDTO,
     * or with status {@code 400 (Bad Request)} if the xaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the xaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/xas")
    public ResponseEntity<XaDTO> updateXa(@RequestBody XaDTO xaDTO) throws URISyntaxException {
        log.debug("REST request to update Xa : {}", xaDTO);
        if (xaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        XaDTO result = xaService.save(xaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, xaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /xas} : get all the xas.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of xas in body.
     */
    @GetMapping("/xas")
    public ResponseEntity<List<XaDTO>> getAllXas(XaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Xas by criteria: {}", criteria);
        Page<XaDTO> page = xaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /xas/count} : count all the xas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/xas/count")
    public ResponseEntity<Long> countXas(XaCriteria criteria) {
        log.debug("REST request to count Xas by criteria: {}", criteria);
        return ResponseEntity.ok().body(xaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /xas/:id} : get the "id" xa.
     *
     * @param id the id of the xaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the xaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/xas/{id}")
    public ResponseEntity<XaDTO> getXa(@PathVariable Long id) {
        log.debug("REST request to get Xa : {}", id);
        Optional<XaDTO> xaDTO = xaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(xaDTO);
    }

    /**
     * {@code DELETE  /xas/:id} : delete the "id" xa.
     *
     * @param id the id of the xaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/xas/{id}")
    public ResponseEntity<Void> deleteXa(@PathVariable Long id) {
        log.debug("REST request to delete Xa : {}", id);
        xaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
