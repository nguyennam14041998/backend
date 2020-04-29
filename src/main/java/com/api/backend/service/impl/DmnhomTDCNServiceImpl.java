package com.api.backend.service.impl;

import com.api.backend.service.DmnhomTDCNService;
import com.api.backend.domain.DmnhomTDCN;
import com.api.backend.repository.DmnhomTDCNRepository;
import com.api.backend.service.dto.DmnhomTDCNDTO;
import com.api.backend.service.mapper.DmnhomTDCNMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DmnhomTDCN}.
 */
@Service
@Transactional
public class DmnhomTDCNServiceImpl implements DmnhomTDCNService {

    private final Logger log = LoggerFactory.getLogger(DmnhomTDCNServiceImpl.class);

    private final DmnhomTDCNRepository dmnhomTDCNRepository;

    private final DmnhomTDCNMapper dmnhomTDCNMapper;

    public DmnhomTDCNServiceImpl(DmnhomTDCNRepository dmnhomTDCNRepository, DmnhomTDCNMapper dmnhomTDCNMapper) {
        this.dmnhomTDCNRepository = dmnhomTDCNRepository;
        this.dmnhomTDCNMapper = dmnhomTDCNMapper;
    }

    /**
     * Save a dmnhomTDCN.
     *
     * @param dmnhomTDCNDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmnhomTDCNDTO save(DmnhomTDCNDTO dmnhomTDCNDTO) {
        log.debug("Request to save DmnhomTDCN : {}", dmnhomTDCNDTO);
        DmnhomTDCN dmnhomTDCN = dmnhomTDCNMapper.toEntity(dmnhomTDCNDTO);
        dmnhomTDCN = dmnhomTDCNRepository.save(dmnhomTDCN);
        return dmnhomTDCNMapper.toDto(dmnhomTDCN);
    }

    /**
     * Get all the dmnhomTDCNS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmnhomTDCNDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmnhomTDCNS");
        return dmnhomTDCNRepository.findAll(pageable)
            .map(dmnhomTDCNMapper::toDto);
    }


    /**
     * Get one dmnhomTDCN by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmnhomTDCNDTO> findOne(Long id) {
        log.debug("Request to get DmnhomTDCN : {}", id);
        return dmnhomTDCNRepository.findById(id)
            .map(dmnhomTDCNMapper::toDto);
    }

    /**
     * Delete the dmnhomTDCN by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DmnhomTDCN : {}", id);
        dmnhomTDCNRepository.deleteById(id);
    }
}
