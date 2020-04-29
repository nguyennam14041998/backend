package com.api.backend.service;

import com.api.backend.service.dto.DmnhomCDHADTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.DmnhomCDHA}.
 */
public interface DmnhomCDHAService {

    /**
     * Save a dmnhomCDHA.
     *
     * @param dmnhomCDHADTO the entity to save.
     * @return the persisted entity.
     */
    DmnhomCDHADTO save(DmnhomCDHADTO dmnhomCDHADTO);

    /**
     * Get all the dmnhomCDHAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmnhomCDHADTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmnhomCDHA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmnhomCDHADTO> findOne(Long id);

    /**
     * Delete the "id" dmnhomCDHA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
