package com.api.backend.service.impl;

import com.api.backend.service.DmCDHAService;
import com.api.backend.domain.DmCDHA;
import com.api.backend.repository.DmCDHARepository;
import com.api.backend.service.dto.DmCDHADTO;
import com.api.backend.service.mapper.DmCDHAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DmCDHA}.
 */
@Service
@Transactional
public class DmCDHAServiceImpl implements DmCDHAService {

    private final Logger log = LoggerFactory.getLogger(DmCDHAServiceImpl.class);

    private final DmCDHARepository dmCDHARepository;

    private final DmCDHAMapper dmCDHAMapper;

    public DmCDHAServiceImpl(DmCDHARepository dmCDHARepository, DmCDHAMapper dmCDHAMapper) {
        this.dmCDHARepository = dmCDHARepository;
        this.dmCDHAMapper = dmCDHAMapper;
    }

    /**
     * Save a dmCDHA.
     *
     * @param dmCDHADTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmCDHADTO save(DmCDHADTO dmCDHADTO) {
        log.debug("Request to save DmCDHA : {}", dmCDHADTO);
        DmCDHA dmCDHA = dmCDHAMapper.toEntity(dmCDHADTO);
        dmCDHA = dmCDHARepository.save(dmCDHA);
        return dmCDHAMapper.toDto(dmCDHA);
    }

    /**
     * Get all the dmCDHAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmCDHADTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmCDHAS");
        return dmCDHARepository.findAll(pageable)
            .map(dmCDHAMapper::toDto);
    }


    /**
     * Get one dmCDHA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmCDHADTO> findOne(Long id) {
        log.debug("Request to get DmCDHA : {}", id);
        return dmCDHARepository.findById(id)
            .map(dmCDHAMapper::toDto);
    }

    /**
     * Delete the dmCDHA by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DmCDHA : {}", id);
        dmCDHARepository.deleteById(id);
    }
}
