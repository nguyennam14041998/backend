package com.api.backend.service;

import com.api.backend.service.dto.DanhmucptttDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Danhmucpttt}.
 */
public interface DanhmucptttService {

    /**
     * Save a danhmucpttt.
     *
     * @param danhmucptttDTO the entity to save.
     * @return the persisted entity.
     */
    DanhmucptttDTO save(DanhmucptttDTO danhmucptttDTO);

    /**
     * Get all the danhmucpttts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhmucptttDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhmucpttt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhmucptttDTO> findOne(Long id);

    /**
     * Delete the "id" danhmucpttt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
