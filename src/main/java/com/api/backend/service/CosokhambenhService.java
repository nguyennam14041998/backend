package com.api.backend.service;

import com.api.backend.service.dto.CosokhambenhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Cosokhambenh}.
 */
public interface CosokhambenhService {

    /**
     * Save a cosokhambenh.
     *
     * @param cosokhambenhDTO the entity to save.
     * @return the persisted entity.
     */
    CosokhambenhDTO save(CosokhambenhDTO cosokhambenhDTO);

    /**
     * Get all the cosokhambenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CosokhambenhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cosokhambenh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CosokhambenhDTO> findOne(Long id);

    /**
     * Delete the "id" cosokhambenh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
