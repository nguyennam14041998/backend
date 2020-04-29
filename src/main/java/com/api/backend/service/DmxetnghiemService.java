package com.api.backend.service;

import com.api.backend.service.dto.DmxetnghiemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmxetnghiem}.
 */
public interface DmxetnghiemService {

    /**
     * Save a dmxetnghiem.
     *
     * @param dmxetnghiemDTO the entity to save.
     * @return the persisted entity.
     */
    DmxetnghiemDTO save(DmxetnghiemDTO dmxetnghiemDTO);

    /**
     * Get all the dmxetnghiems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmxetnghiemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmxetnghiem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmxetnghiemDTO> findOne(Long id);

    /**
     * Delete the "id" dmxetnghiem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
