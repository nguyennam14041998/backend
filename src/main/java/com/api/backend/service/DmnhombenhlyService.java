package com.api.backend.service;

import com.api.backend.service.dto.DmnhombenhlyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmnhombenhly}.
 */
public interface DmnhombenhlyService {

    /**
     * Save a dmnhombenhly.
     *
     * @param dmnhombenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    DmnhombenhlyDTO save(DmnhombenhlyDTO dmnhombenhlyDTO);

    /**
     * Get all the dmnhombenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmnhombenhlyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmnhombenhly.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmnhombenhlyDTO> findOne(Long id);

    /**
     * Delete the "id" dmnhombenhly.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
