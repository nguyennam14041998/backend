package com.api.backend.service.impl;

import com.api.backend.service.DanhmucnhomptttService;
import com.api.backend.domain.Danhmucnhompttt;
import com.api.backend.repository.DanhmucnhomptttRepository;
import com.api.backend.service.dto.DanhmucnhomptttDTO;
import com.api.backend.service.mapper.DanhmucnhomptttMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Danhmucnhompttt}.
 */
@Service
@Transactional
public class DanhmucnhomptttServiceImpl implements DanhmucnhomptttService {

    private final Logger log = LoggerFactory.getLogger(DanhmucnhomptttServiceImpl.class);

    private final DanhmucnhomptttRepository danhmucnhomptttRepository;

    private final DanhmucnhomptttMapper danhmucnhomptttMapper;

    public DanhmucnhomptttServiceImpl(DanhmucnhomptttRepository danhmucnhomptttRepository, DanhmucnhomptttMapper danhmucnhomptttMapper) {
        this.danhmucnhomptttRepository = danhmucnhomptttRepository;
        this.danhmucnhomptttMapper = danhmucnhomptttMapper;
    }

    /**
     * Save a danhmucnhompttt.
     *
     * @param danhmucnhomptttDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhmucnhomptttDTO save(DanhmucnhomptttDTO danhmucnhomptttDTO) {
        log.debug("Request to save Danhmucnhompttt : {}", danhmucnhomptttDTO);
        Danhmucnhompttt danhmucnhompttt = danhmucnhomptttMapper.toEntity(danhmucnhomptttDTO);
        danhmucnhompttt = danhmucnhomptttRepository.save(danhmucnhompttt);
        return danhmucnhomptttMapper.toDto(danhmucnhompttt);
    }

    /**
     * Get all the danhmucnhompttts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhmucnhomptttDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Danhmucnhompttts");
        return danhmucnhomptttRepository.findAll(pageable)
            .map(danhmucnhomptttMapper::toDto);
    }


    /**
     * Get one danhmucnhompttt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhmucnhomptttDTO> findOne(Long id) {
        log.debug("Request to get Danhmucnhompttt : {}", id);
        return danhmucnhomptttRepository.findById(id)
            .map(danhmucnhomptttMapper::toDto);
    }

    /**
     * Delete the danhmucnhompttt by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Danhmucnhompttt : {}", id);
        danhmucnhomptttRepository.deleteById(id);
    }
}
