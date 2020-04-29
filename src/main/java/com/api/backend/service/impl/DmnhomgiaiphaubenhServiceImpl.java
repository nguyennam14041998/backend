package com.api.backend.service.impl;

import com.api.backend.service.DmnhomgiaiphaubenhService;
import com.api.backend.domain.Dmnhomgiaiphaubenh;
import com.api.backend.repository.DmnhomgiaiphaubenhRepository;
import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;
import com.api.backend.service.mapper.DmnhomgiaiphaubenhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmnhomgiaiphaubenh}.
 */
@Service
@Transactional
public class DmnhomgiaiphaubenhServiceImpl implements DmnhomgiaiphaubenhService {

    private final Logger log = LoggerFactory.getLogger(DmnhomgiaiphaubenhServiceImpl.class);

    private final DmnhomgiaiphaubenhRepository dmnhomgiaiphaubenhRepository;

    private final DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper;

    public DmnhomgiaiphaubenhServiceImpl(DmnhomgiaiphaubenhRepository dmnhomgiaiphaubenhRepository, DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper) {
        this.dmnhomgiaiphaubenhRepository = dmnhomgiaiphaubenhRepository;
        this.dmnhomgiaiphaubenhMapper = dmnhomgiaiphaubenhMapper;
    }

    /**
     * Save a dmnhomgiaiphaubenh.
     *
     * @param dmnhomgiaiphaubenhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmnhomgiaiphaubenhDTO save(DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO) {
        log.debug("Request to save Dmnhomgiaiphaubenh : {}", dmnhomgiaiphaubenhDTO);
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh = dmnhomgiaiphaubenhMapper.toEntity(dmnhomgiaiphaubenhDTO);
        dmnhomgiaiphaubenh = dmnhomgiaiphaubenhRepository.save(dmnhomgiaiphaubenh);
        return dmnhomgiaiphaubenhMapper.toDto(dmnhomgiaiphaubenh);
    }

    /**
     * Get all the dmnhomgiaiphaubenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmnhomgiaiphaubenhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmnhomgiaiphaubenhs");
        return dmnhomgiaiphaubenhRepository.findAll(pageable)
            .map(dmnhomgiaiphaubenhMapper::toDto);
    }


    /**
     * Get one dmnhomgiaiphaubenh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmnhomgiaiphaubenhDTO> findOne(Long id) {
        log.debug("Request to get Dmnhomgiaiphaubenh : {}", id);
        return dmnhomgiaiphaubenhRepository.findById(id)
            .map(dmnhomgiaiphaubenhMapper::toDto);
    }

    /**
     * Delete the dmnhomgiaiphaubenh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmnhomgiaiphaubenh : {}", id);
        dmnhomgiaiphaubenhRepository.deleteById(id);
    }
}
