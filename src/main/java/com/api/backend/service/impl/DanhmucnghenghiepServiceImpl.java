package com.api.backend.service.impl;

import com.api.backend.service.DanhmucnghenghiepService;
import com.api.backend.domain.Danhmucnghenghiep;
import com.api.backend.repository.DanhmucnghenghiepRepository;
import com.api.backend.service.dto.DanhmucnghenghiepDTO;
import com.api.backend.service.mapper.DanhmucnghenghiepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Danhmucnghenghiep}.
 */
@Service
@Transactional
public class DanhmucnghenghiepServiceImpl implements DanhmucnghenghiepService {

    private final Logger log = LoggerFactory.getLogger(DanhmucnghenghiepServiceImpl.class);

    private final DanhmucnghenghiepRepository danhmucnghenghiepRepository;

    private final DanhmucnghenghiepMapper danhmucnghenghiepMapper;

    public DanhmucnghenghiepServiceImpl(DanhmucnghenghiepRepository danhmucnghenghiepRepository, DanhmucnghenghiepMapper danhmucnghenghiepMapper) {
        this.danhmucnghenghiepRepository = danhmucnghenghiepRepository;
        this.danhmucnghenghiepMapper = danhmucnghenghiepMapper;
    }

    /**
     * Save a danhmucnghenghiep.
     *
     * @param danhmucnghenghiepDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhmucnghenghiepDTO save(DanhmucnghenghiepDTO danhmucnghenghiepDTO) {
        log.debug("Request to save Danhmucnghenghiep : {}", danhmucnghenghiepDTO);
        Danhmucnghenghiep danhmucnghenghiep = danhmucnghenghiepMapper.toEntity(danhmucnghenghiepDTO);
        danhmucnghenghiep = danhmucnghenghiepRepository.save(danhmucnghenghiep);
        return danhmucnghenghiepMapper.toDto(danhmucnghenghiep);
    }

    /**
     * Get all the danhmucnghenghieps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhmucnghenghiepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Danhmucnghenghieps");
        return danhmucnghenghiepRepository.findAll(pageable)
            .map(danhmucnghenghiepMapper::toDto);
    }


    /**
     * Get one danhmucnghenghiep by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhmucnghenghiepDTO> findOne(Long id) {
        log.debug("Request to get Danhmucnghenghiep : {}", id);
        return danhmucnghenghiepRepository.findById(id)
            .map(danhmucnghenghiepMapper::toDto);
    }

    /**
     * Delete the danhmucnghenghiep by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Danhmucnghenghiep : {}", id);
        danhmucnghenghiepRepository.deleteById(id);
    }
}
