package com.api.backend.service.impl;

import com.api.backend.service.TinhService;
import com.api.backend.domain.Tinh;
import com.api.backend.repository.TinhRepository;
import com.api.backend.service.dto.TinhDTO;
import com.api.backend.service.mapper.TinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tinh}.
 */
@Service
@Transactional
public class TinhServiceImpl implements TinhService {

    private final Logger log = LoggerFactory.getLogger(TinhServiceImpl.class);

    private final TinhRepository tinhRepository;

    private final TinhMapper tinhMapper;

    public TinhServiceImpl(TinhRepository tinhRepository, TinhMapper tinhMapper) {
        this.tinhRepository = tinhRepository;
        this.tinhMapper = tinhMapper;
    }

    /**
     * Save a tinh.
     *
     * @param tinhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TinhDTO save(TinhDTO tinhDTO) {
        log.debug("Request to save Tinh : {}", tinhDTO);
        Tinh tinh = tinhMapper.toEntity(tinhDTO);
        tinh = tinhRepository.save(tinh);
        return tinhMapper.toDto(tinh);
    }

    /**
     * Get all the tinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tinhs");
        return tinhRepository.findAll(pageable)
            .map(tinhMapper::toDto);
    }


    /**
     * Get one tinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TinhDTO> findOne(Long id) {
        log.debug("Request to get Tinh : {}", id);
        return tinhRepository.findById(id)
            .map(tinhMapper::toDto);
    }

    /**
     * Delete the tinh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tinh : {}", id);
        tinhRepository.deleteById(id);
    }
}
