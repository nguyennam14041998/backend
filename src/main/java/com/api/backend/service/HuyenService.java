package com.api.backend.service;

import com.api.backend.service.dto.HuyenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Huyen}.
 */
public interface HuyenService {

    /**
     * Save a huyen.
     *
     * @param huyenDTO the entity to save.
     * @return the persisted entity.
     */
    HuyenDTO save(HuyenDTO huyenDTO);

    /**
     * Get all the huyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" huyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HuyenDTO> findOne(Long id);

    /**
     * Delete the "id" huyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
