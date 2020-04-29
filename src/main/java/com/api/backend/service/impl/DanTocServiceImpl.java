package com.api.backend.service.impl;

import com.api.backend.service.DanTocService;
import com.api.backend.domain.DanToc;
import com.api.backend.repository.DanTocRepository;
import com.api.backend.service.dto.DanTocDTO;
import com.api.backend.service.mapper.DanTocMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DanToc}.
 */
@Service
@Transactional
public class DanTocServiceImpl implements DanTocService {

    private final Logger log = LoggerFactory.getLogger(DanTocServiceImpl.class);

    private final DanTocRepository danTocRepository;

    private final DanTocMapper danTocMapper;

    public DanTocServiceImpl(DanTocRepository danTocRepository, DanTocMapper danTocMapper) {
        this.danTocRepository = danTocRepository;
        this.danTocMapper = danTocMapper;
    }

    /**
     * Save a danToc.
     *
     * @param danTocDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanTocDTO save(DanTocDTO danTocDTO) {
        log.debug("Request to save DanToc : {}", danTocDTO);
        DanToc danToc = danTocMapper.toEntity(danTocDTO);
        danToc = danTocRepository.save(danToc);
        return danTocMapper.toDto(danToc);
    }

    /**
     * Get all the danTocs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanTocDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanTocs");
        return danTocRepository.findAll(pageable)
            .map(danTocMapper::toDto);
    }


    /**
     * Get one danToc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanTocDTO> findOne(Long id) {
        log.debug("Request to get DanToc : {}", id);
        return danTocRepository.findById(id)
            .map(danTocMapper::toDto);
    }

    /**
     * Delete the danToc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanToc : {}", id);
        danTocRepository.deleteById(id);
    }
}
