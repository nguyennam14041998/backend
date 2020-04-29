package com.api.backend.service.impl;

import com.api.backend.service.DmloaibenhlyService;
import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.repository.DmloaibenhlyRepository;
import com.api.backend.service.dto.DmloaibenhlyDTO;
import com.api.backend.service.mapper.DmloaibenhlyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmloaibenhly}.
 */
@Service
@Transactional
public class DmloaibenhlyServiceImpl implements DmloaibenhlyService {

    private final Logger log = LoggerFactory.getLogger(DmloaibenhlyServiceImpl.class);

    private final DmloaibenhlyRepository dmloaibenhlyRepository;

    private final DmloaibenhlyMapper dmloaibenhlyMapper;

    public DmloaibenhlyServiceImpl(DmloaibenhlyRepository dmloaibenhlyRepository, DmloaibenhlyMapper dmloaibenhlyMapper) {
        this.dmloaibenhlyRepository = dmloaibenhlyRepository;
        this.dmloaibenhlyMapper = dmloaibenhlyMapper;
    }

    /**
     * Save a dmloaibenhly.
     *
     * @param dmloaibenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmloaibenhlyDTO save(DmloaibenhlyDTO dmloaibenhlyDTO) {
        log.debug("Request to save Dmloaibenhly : {}", dmloaibenhlyDTO);
        Dmloaibenhly dmloaibenhly = dmloaibenhlyMapper.toEntity(dmloaibenhlyDTO);
        dmloaibenhly = dmloaibenhlyRepository.save(dmloaibenhly);
        return dmloaibenhlyMapper.toDto(dmloaibenhly);
    }

    /**
     * Get all the dmloaibenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmloaibenhlyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmloaibenhlies");
        return dmloaibenhlyRepository.findAll(pageable)
            .map(dmloaibenhlyMapper::toDto);
    }


    /**
     * Get one dmloaibenhly by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmloaibenhlyDTO> findOne(Long id) {
        log.debug("Request to get Dmloaibenhly : {}", id);
        return dmloaibenhlyRepository.findById(id)
            .map(dmloaibenhlyMapper::toDto);
    }

    /**
     * Delete the dmloaibenhly by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmloaibenhly : {}", id);
        dmloaibenhlyRepository.deleteById(id);
    }
}
