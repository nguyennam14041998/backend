package com.api.backend.service;

import com.api.backend.service.dto.DanhmucnhomptttDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Danhmucnhompttt}.
 */
public interface DanhmucnhomptttService {

    /**
     * Save a danhmucnhompttt.
     *
     * @param danhmucnhomptttDTO the entity to save.
     * @return the persisted entity.
     */
    DanhmucnhomptttDTO save(DanhmucnhomptttDTO danhmucnhomptttDTO);

    /**
     * Get all the danhmucnhompttts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhmucnhomptttDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhmucnhompttt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhmucnhomptttDTO> findOne(Long id);

    /**
     * Delete the "id" danhmucnhompttt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
