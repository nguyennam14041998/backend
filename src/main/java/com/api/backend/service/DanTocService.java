package com.api.backend.service;

import com.api.backend.service.dto.DanTocDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.DanToc}.
 */
public interface DanTocService {

    /**
     * Save a danToc.
     *
     * @param danTocDTO the entity to save.
     * @return the persisted entity.
     */
    DanTocDTO save(DanTocDTO danTocDTO);

    /**
     * Get all the danTocs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanTocDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danToc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanTocDTO> findOne(Long id);

    /**
     * Delete the "id" danToc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
