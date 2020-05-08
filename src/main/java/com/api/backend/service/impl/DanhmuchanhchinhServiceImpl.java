package com.api.backend.service.impl;

import com.api.backend.service.DanhmuchanhchinhService;
import com.api.backend.domain.Danhmuchanhchinh;
import com.api.backend.repository.DanhmuchanhchinhRepository;
import com.api.backend.service.dto.DanhmuchanhchinhDTO;
import com.api.backend.service.mapper.DanhmuchanhchinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Danhmuchanhchinh}.
 */
@Service
@Transactional
public class DanhmuchanhchinhServiceImpl implements DanhmuchanhchinhService {

    private final Logger log = LoggerFactory.getLogger(DanhmuchanhchinhServiceImpl.class);

    private final DanhmuchanhchinhRepository danhmuchanhchinhRepository;

    private final DanhmuchanhchinhMapper danhmuchanhchinhMapper;

    public DanhmuchanhchinhServiceImpl(DanhmuchanhchinhRepository danhmuchanhchinhRepository, DanhmuchanhchinhMapper danhmuchanhchinhMapper) {
        this.danhmuchanhchinhRepository = danhmuchanhchinhRepository;
        this.danhmuchanhchinhMapper = danhmuchanhchinhMapper;
    }

    /**
     * Save a danhmuchanhchinh.
     *
     * @param danhmuchanhchinhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhmuchanhchinhDTO save(DanhmuchanhchinhDTO danhmuchanhchinhDTO) {
        log.debug("Request to save Danhmuchanhchinh : {}", danhmuchanhchinhDTO);
        Danhmuchanhchinh danhmuchanhchinh = danhmuchanhchinhMapper.toEntity(danhmuchanhchinhDTO);
        danhmuchanhchinh = danhmuchanhchinhRepository.save(danhmuchanhchinh);
        return danhmuchanhchinhMapper.toDto(danhmuchanhchinh);
    }

    /**
     * Get all the danhmuchanhchinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DanhmuchanhchinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Danhmuchanhchinhs");
        return danhmuchanhchinhRepository.findAll(pageable)
            .map(danhmuchanhchinhMapper::toDto);
    }


    /**
     * Get one danhmuchanhchinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhmuchanhchinhDTO> findOne(Long id) {
        log.debug("Request to get Danhmuchanhchinh : {}", id);
        return danhmuchanhchinhRepository.findById(id)
            .map(danhmuchanhchinhMapper::toDto);
    }

    /**
     * Delete the danhmuchanhchinh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Danhmuchanhchinh : {}", id);
        danhmuchanhchinhRepository.deleteById(id);
    }
}
