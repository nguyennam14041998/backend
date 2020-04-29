package com.api.backend.service.impl;

import com.api.backend.service.DmnhombenhlyService;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.repository.DmnhombenhlyRepository;
import com.api.backend.service.dto.DmnhombenhlyDTO;
import com.api.backend.service.mapper.DmnhombenhlyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmnhombenhly}.
 */
@Service
@Transactional
public class DmnhombenhlyServiceImpl implements DmnhombenhlyService {

    private final Logger log = LoggerFactory.getLogger(DmnhombenhlyServiceImpl.class);

    private final DmnhombenhlyRepository dmnhombenhlyRepository;

    private final DmnhombenhlyMapper dmnhombenhlyMapper;

    public DmnhombenhlyServiceImpl(DmnhombenhlyRepository dmnhombenhlyRepository, DmnhombenhlyMapper dmnhombenhlyMapper) {
        this.dmnhombenhlyRepository = dmnhombenhlyRepository;
        this.dmnhombenhlyMapper = dmnhombenhlyMapper;
    }

    /**
     * Save a dmnhombenhly.
     *
     * @param dmnhombenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmnhombenhlyDTO save(DmnhombenhlyDTO dmnhombenhlyDTO) {
        log.debug("Request to save Dmnhombenhly : {}", dmnhombenhlyDTO);
        Dmnhombenhly dmnhombenhly = dmnhombenhlyMapper.toEntity(dmnhombenhlyDTO);
        dmnhombenhly = dmnhombenhlyRepository.save(dmnhombenhly);
        return dmnhombenhlyMapper.toDto(dmnhombenhly);
    }

    /**
     * Get all the dmnhombenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmnhombenhlyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmnhombenhlies");
        return dmnhombenhlyRepository.findAll(pageable)
            .map(dmnhombenhlyMapper::toDto);
    }


    /**
     * Get one dmnhombenhly by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmnhombenhlyDTO> findOne(Long id) {
        log.debug("Request to get Dmnhombenhly : {}", id);
        return dmnhombenhlyRepository.findById(id)
            .map(dmnhombenhlyMapper::toDto);
    }

    /**
     * Delete the dmnhombenhly by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmnhombenhly : {}", id);
        dmnhombenhlyRepository.deleteById(id);
    }
}
