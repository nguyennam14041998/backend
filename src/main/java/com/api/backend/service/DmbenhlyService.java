package com.api.backend.service;

import com.api.backend.service.dto.DmbenhlyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmbenhly}.
 */
public interface DmbenhlyService {

    /**
     * Save a dmbenhly.
     *
     * @param dmbenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    DmbenhlyDTO save(DmbenhlyDTO dmbenhlyDTO);

    /**
     * Get all the dmbenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmbenhlyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmbenhly.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmbenhlyDTO> findOne(Long id);

    /**
     * Delete the "id" dmbenhly.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
