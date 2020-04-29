package com.api.backend.service.impl;

import com.api.backend.service.DanhmucptttService;
import com.api.backend.domain.Danhmucpttt;
import com.api.backend.repository.DanhmucptttRepository;
import com.api.backend.service.dto.DanhmucptttDTO;
import com.api.backend.service.mapper.DanhmucptttMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Danhmucpttt}.
 */
@Service
@Transactional
public class DanhmucptttServiceImpl implements DanhmucptttService {

    private final Logger log = LoggerFactory.getLogger(DanhmucptttServiceImpl.class);

    private final DanhmucptttRepository danhmucptttRepository;

    private final DanhmucptttMapper danhmucptttMapper;

    public DanhmucptttServiceImpl(DanhmucptttRepository danhmucptttRepository, DanhmucptttMapper danhmucptttMapper) {
        this.danhmucptttRepository = danhmucptttRepository;
        this.danhmucptttMapper = danhmucptttMapper;
    }

    /**
     * Save a danhmucpttt.
     *
     * @param danhmucptttDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhmucptttDTO save(DanhmucptttDTO danhmucptttDTO) {
        log.debug("Request to save Danhmucpttt : {}", danhmucptttDTO);
        Danhmucpttt danhmucpttt = danhmucptttMapper.toEntity(danhmucptttDTO);
        danhmucpttt = danhmucptttRepository.save(danhmucpttt);
        return danhmucptttMapper.toDto(danhmucpttt);
    }

    /**
     * Get all the danhmucpttts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhmucptttDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Danhmucpttts");
        return danhmucptttRepository.findAll(pageable)
            .map(danhmucptttMapper::toDto);
    }


    /**
     * Get one danhmucpttt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhmucptttDTO> findOne(Long id) {
        log.debug("Request to get Danhmucpttt : {}", id);
        return danhmucptttRepository.findById(id)
            .map(danhmucptttMapper::toDto);
    }

    /**
     * Delete the danhmucpttt by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Danhmucpttt : {}", id);
        danhmucptttRepository.deleteById(id);
    }
}
