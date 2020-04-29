package com.api.backend.service.impl;

import com.api.backend.service.DmnhomxetnghiemService;
import com.api.backend.domain.Dmnhomxetnghiem;
import com.api.backend.repository.DmnhomxetnghiemRepository;
import com.api.backend.service.dto.DmnhomxetnghiemDTO;
import com.api.backend.service.mapper.DmnhomxetnghiemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmnhomxetnghiem}.
 */
@Service
@Transactional
public class DmnhomxetnghiemServiceImpl implements DmnhomxetnghiemService {

    private final Logger log = LoggerFactory.getLogger(DmnhomxetnghiemServiceImpl.class);

    private final DmnhomxetnghiemRepository dmnhomxetnghiemRepository;

    private final DmnhomxetnghiemMapper dmnhomxetnghiemMapper;

    public DmnhomxetnghiemServiceImpl(DmnhomxetnghiemRepository dmnhomxetnghiemRepository, DmnhomxetnghiemMapper dmnhomxetnghiemMapper) {
        this.dmnhomxetnghiemRepository = dmnhomxetnghiemRepository;
        this.dmnhomxetnghiemMapper = dmnhomxetnghiemMapper;
    }

    /**
     * Save a dmnhomxetnghiem.
     *
     * @param dmnhomxetnghiemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmnhomxetnghiemDTO save(DmnhomxetnghiemDTO dmnhomxetnghiemDTO) {
        log.debug("Request to save Dmnhomxetnghiem : {}", dmnhomxetnghiemDTO);
        Dmnhomxetnghiem dmnhomxetnghiem = dmnhomxetnghiemMapper.toEntity(dmnhomxetnghiemDTO);
        dmnhomxetnghiem = dmnhomxetnghiemRepository.save(dmnhomxetnghiem);
        return dmnhomxetnghiemMapper.toDto(dmnhomxetnghiem);
    }

    /**
     * Get all the dmnhomxetnghiems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmnhomxetnghiemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmnhomxetnghiems");
        return dmnhomxetnghiemRepository.findAll(pageable)
            .map(dmnhomxetnghiemMapper::toDto);
    }


    /**
     * Get one dmnhomxetnghiem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmnhomxetnghiemDTO> findOne(Long id) {
        log.debug("Request to get Dmnhomxetnghiem : {}", id);
        return dmnhomxetnghiemRepository.findById(id)
            .map(dmnhomxetnghiemMapper::toDto);
    }

    /**
     * Delete the dmnhomxetnghiem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmnhomxetnghiem : {}", id);
        dmnhomxetnghiemRepository.deleteById(id);
    }
}
