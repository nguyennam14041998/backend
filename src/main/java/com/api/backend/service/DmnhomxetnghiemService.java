package com.api.backend.service;

import com.api.backend.service.dto.DmnhomxetnghiemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmnhomxetnghiem}.
 */
public interface DmnhomxetnghiemService {

    /**
     * Save a dmnhomxetnghiem.
     *
     * @param dmnhomxetnghiemDTO the entity to save.
     * @return the persisted entity.
     */
    DmnhomxetnghiemDTO save(DmnhomxetnghiemDTO dmnhomxetnghiemDTO);

    /**
     * Get all the dmnhomxetnghiems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmnhomxetnghiemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmnhomxetnghiem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmnhomxetnghiemDTO> findOne(Long id);

    /**
     * Delete the "id" dmnhomxetnghiem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
