package com.api.backend.service;

import com.api.backend.service.dto.XaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Xa}.
 */
public interface XaService {

    /**
     * Save a xa.
     *
     * @param xaDTO the entity to save.
     * @return the persisted entity.
     */
    XaDTO save(XaDTO xaDTO);

    /**
     * Get all the xas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<XaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" xa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<XaDTO> findOne(Long id);

    /**
     * Delete the "id" xa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
