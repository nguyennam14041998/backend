package com.api.backend.service.impl;

import com.api.backend.service.DmnhomCDHAService;
import com.api.backend.domain.DmnhomCDHA;
import com.api.backend.repository.DmnhomCDHARepository;
import com.api.backend.service.dto.DmnhomCDHADTO;
import com.api.backend.service.mapper.DmnhomCDHAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DmnhomCDHA}.
 */
@Service
@Transactional
public class DmnhomCDHAServiceImpl implements DmnhomCDHAService {

    private final Logger log = LoggerFactory.getLogger(DmnhomCDHAServiceImpl.class);

    private final DmnhomCDHARepository dmnhomCDHARepository;

    private final DmnhomCDHAMapper dmnhomCDHAMapper;

    public DmnhomCDHAServiceImpl(DmnhomCDHARepository dmnhomCDHARepository, DmnhomCDHAMapper dmnhomCDHAMapper) {
        this.dmnhomCDHARepository = dmnhomCDHARepository;
        this.dmnhomCDHAMapper = dmnhomCDHAMapper;
    }

    /**
     * Save a dmnhomCDHA.
     *
     * @param dmnhomCDHADTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmnhomCDHADTO save(DmnhomCDHADTO dmnhomCDHADTO) {
        log.debug("Request to save DmnhomCDHA : {}", dmnhomCDHADTO);
        DmnhomCDHA dmnhomCDHA = dmnhomCDHAMapper.toEntity(dmnhomCDHADTO);
        dmnhomCDHA = dmnhomCDHARepository.save(dmnhomCDHA);
        return dmnhomCDHAMapper.toDto(dmnhomCDHA);
    }

    /**
     * Get all the dmnhomCDHAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmnhomCDHADTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmnhomCDHAS");
        return dmnhomCDHARepository.findAll(pageable)
            .map(dmnhomCDHAMapper::toDto);
    }


    /**
     * Get one dmnhomCDHA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmnhomCDHADTO> findOne(Long id) {
        log.debug("Request to get DmnhomCDHA : {}", id);
        return dmnhomCDHARepository.findById(id)
            .map(dmnhomCDHAMapper::toDto);
    }

    /**
     * Delete the dmnhomCDHA by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DmnhomCDHA : {}", id);
        dmnhomCDHARepository.deleteById(id);
    }
}
