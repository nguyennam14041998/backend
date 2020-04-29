package com.api.backend.service;

import com.api.backend.service.dto.DmloaibenhlyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmloaibenhly}.
 */
public interface DmloaibenhlyService {

    /**
     * Save a dmloaibenhly.
     *
     * @param dmloaibenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    DmloaibenhlyDTO save(DmloaibenhlyDTO dmloaibenhlyDTO);

    /**
     * Get all the dmloaibenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmloaibenhlyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmloaibenhly.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmloaibenhlyDTO> findOne(Long id);

    /**
     * Delete the "id" dmloaibenhly.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
