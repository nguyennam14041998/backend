package com.api.backend.service;

import com.api.backend.service.dto.DmCDHADTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.DmCDHA}.
 */
public interface DmCDHAService {

    /**
     * Save a dmCDHA.
     *
     * @param dmCDHADTO the entity to save.
     * @return the persisted entity.
     */
    DmCDHADTO save(DmCDHADTO dmCDHADTO);

    /**
     * Get all the dmCDHAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmCDHADTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmCDHA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmCDHADTO> findOne(Long id);

    /**
     * Delete the "id" dmCDHA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
