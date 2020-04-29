package com.api.backend.service;

import com.api.backend.service.dto.DmTDCNDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.DmTDCN}.
 */
public interface DmTDCNService {

    /**
     * Save a dmTDCN.
     *
     * @param dmTDCNDTO the entity to save.
     * @return the persisted entity.
     */
    DmTDCNDTO save(DmTDCNDTO dmTDCNDTO);

    /**
     * Get all the dmTDCNS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmTDCNDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmTDCN.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmTDCNDTO> findOne(Long id);

    /**
     * Delete the "id" dmTDCN.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
