package com.api.backend.service;

import com.api.backend.service.dto.DanhmuchanhchinhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Danhmuchanhchinh}.
 */
public interface DanhmuchanhchinhService {

    /**
     * Save a danhmuchanhchinh.
     *
     * @param danhmuchanhchinhDTO the entity to save.
     * @return the persisted entity.
     */
    DanhmuchanhchinhDTO save(DanhmuchanhchinhDTO danhmuchanhchinhDTO);

    /**
     * Get all the danhmuchanhchinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhmuchanhchinhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhmuchanhchinh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhmuchanhchinhDTO> findOne(Long id);

    /**
     * Delete the "id" danhmuchanhchinh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
