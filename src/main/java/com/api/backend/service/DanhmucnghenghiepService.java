package com.api.backend.service;

import com.api.backend.service.dto.DanhmucnghenghiepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Danhmucnghenghiep}.
 */
public interface DanhmucnghenghiepService {

    /**
     * Save a danhmucnghenghiep.
     *
     * @param danhmucnghenghiepDTO the entity to save.
     * @return the persisted entity.
     */
    DanhmucnghenghiepDTO save(DanhmucnghenghiepDTO danhmucnghenghiepDTO);

    /**
     * Get all the danhmucnghenghieps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhmucnghenghiepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhmucnghenghiep.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhmucnghenghiepDTO> findOne(Long id);

    /**
     * Delete the "id" danhmucnghenghiep.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
