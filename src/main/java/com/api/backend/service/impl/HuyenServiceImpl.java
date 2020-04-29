package com.api.backend.service.impl;

import com.api.backend.service.HuyenService;
import com.api.backend.domain.Huyen;
import com.api.backend.repository.HuyenRepository;
import com.api.backend.service.dto.HuyenDTO;
import com.api.backend.service.mapper.HuyenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Huyen}.
 */
@Service
@Transactional
public class HuyenServiceImpl implements HuyenService {

    private final Logger log = LoggerFactory.getLogger(HuyenServiceImpl.class);

    private final HuyenRepository huyenRepository;

    private final HuyenMapper huyenMapper;

    public HuyenServiceImpl(HuyenRepository huyenRepository, HuyenMapper huyenMapper) {
        this.huyenRepository = huyenRepository;
        this.huyenMapper = huyenMapper;
    }

    /**
     * Save a huyen.
     *
     * @param huyenDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HuyenDTO save(HuyenDTO huyenDTO) {
        log.debug("Request to save Huyen : {}", huyenDTO);
        Huyen huyen = huyenMapper.toEntity(huyenDTO);
        huyen = huyenRepository.save(huyen);
        return huyenMapper.toDto(huyen);
    }

    /**
     * Get all the huyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Huyens");
        return huyenRepository.findAll(pageable)
            .map(huyenMapper::toDto);
    }


    /**
     * Get one huyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HuyenDTO> findOne(Long id) {
        log.debug("Request to get Huyen : {}", id);
        return huyenRepository.findById(id)
            .map(huyenMapper::toDto);
    }

    /**
     * Delete the huyen by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Huyen : {}", id);
        huyenRepository.deleteById(id);
    }
}
