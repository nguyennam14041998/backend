package com.api.backend.service;

import com.api.backend.service.dto.TinhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Tinh}.
 */
public interface TinhService {

    /**
     * Save a tinh.
     *
     * @param tinhDTO the entity to save.
     * @return the persisted entity.
     */
    TinhDTO save(TinhDTO tinhDTO);

    /**
     * Get all the tinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TinhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tinh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TinhDTO> findOne(Long id);

    /**
     * Delete the "id" tinh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
