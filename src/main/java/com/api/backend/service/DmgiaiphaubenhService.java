package com.api.backend.service;

import com.api.backend.service.dto.DmgiaiphaubenhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmgiaiphaubenh}.
 */
public interface DmgiaiphaubenhService {

    /**
     * Save a dmgiaiphaubenh.
     *
     * @param dmgiaiphaubenhDTO the entity to save.
     * @return the persisted entity.
     */
    DmgiaiphaubenhDTO save(DmgiaiphaubenhDTO dmgiaiphaubenhDTO);

    /**
     * Get all the dmgiaiphaubenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmgiaiphaubenhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmgiaiphaubenh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmgiaiphaubenhDTO> findOne(Long id);

    /**
     * Delete the "id" dmgiaiphaubenh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
