package com.api.backend.service.impl;

import com.api.backend.service.DmTDCNService;
import com.api.backend.domain.DmTDCN;
import com.api.backend.repository.DmTDCNRepository;
import com.api.backend.service.dto.DmTDCNDTO;
import com.api.backend.service.mapper.DmTDCNMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DmTDCN}.
 */
@Service
@Transactional
public class DmTDCNServiceImpl implements DmTDCNService {

    private final Logger log = LoggerFactory.getLogger(DmTDCNServiceImpl.class);

    private final DmTDCNRepository dmTDCNRepository;

    private final DmTDCNMapper dmTDCNMapper;

    public DmTDCNServiceImpl(DmTDCNRepository dmTDCNRepository, DmTDCNMapper dmTDCNMapper) {
        this.dmTDCNRepository = dmTDCNRepository;
        this.dmTDCNMapper = dmTDCNMapper;
    }

    /**
     * Save a dmTDCN.
     *
     * @param dmTDCNDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmTDCNDTO save(DmTDCNDTO dmTDCNDTO) {
        log.debug("Request to save DmTDCN : {}", dmTDCNDTO);
        DmTDCN dmTDCN = dmTDCNMapper.toEntity(dmTDCNDTO);
        dmTDCN = dmTDCNRepository.save(dmTDCN);
        return dmTDCNMapper.toDto(dmTDCN);
    }

    /**
     * Get all the dmTDCNS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmTDCNDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmTDCNS");
        return dmTDCNRepository.findAll(pageable)
            .map(dmTDCNMapper::toDto);
    }


    /**
     * Get one dmTDCN by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmTDCNDTO> findOne(Long id) {
        log.debug("Request to get DmTDCN : {}", id);
        return dmTDCNRepository.findById(id)
            .map(dmTDCNMapper::toDto);
    }

    /**
     * Delete the dmTDCN by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DmTDCN : {}", id);
        dmTDCNRepository.deleteById(id);
    }
}
