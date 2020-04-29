package com.api.backend.service;

import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.api.backend.domain.Dmnhomgiaiphaubenh}.
 */
public interface DmnhomgiaiphaubenhService {

    /**
     * Save a dmnhomgiaiphaubenh.
     *
     * @param dmnhomgiaiphaubenhDTO the entity to save.
     * @return the persisted entity.
     */
    DmnhomgiaiphaubenhDTO save(DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO);

    /**
     * Get all the dmnhomgiaiphaubenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DmnhomgiaiphaubenhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dmnhomgiaiphaubenh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DmnhomgiaiphaubenhDTO> findOne(Long id);

    /**
     * Delete the "id" dmnhomgiaiphaubenh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
