package com.api.backend.service;

import com.api.backend.service.dto.DmnhomTDCNDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.DmnhomTDCN}.
 */
public interface DmnhomTDCNService {

    /**
     * Save a dmnhomTDCN.
     *
     * @param dmnhomTDCNDTO the entity to save.
     * @return the persisted entity.
     */
    DmnhomTDCNDTO save(DmnhomTDCNDTO dmnhomTDCNDTO);

    /**
     * Get all the dmnhomTDCNS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmnhomTDCNDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmnhomTDCN.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmnhomTDCNDTO> findOne(Long id);

    /**
     * Delete the "id" dmnhomTDCN.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
