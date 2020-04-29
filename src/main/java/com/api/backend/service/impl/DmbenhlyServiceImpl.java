package com.api.backend.service.impl;

import com.api.backend.domain.Dmbenhly;
import com.api.backend.repository.DmbenhlyRepository;
import com.api.backend.service.DmbenhlyService;
import com.api.backend.service.dto.DmbenhlyDTO;
import com.api.backend.service.mapper.MyDanhMucBenhLyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmbenhly}.
 */
@Service
@Transactional
public class DmbenhlyServiceImpl implements DmbenhlyService {

    private final Logger log = LoggerFactory.getLogger(DmbenhlyServiceImpl.class);

    private final DmbenhlyRepository dmbenhlyRepository;

    private final MyDanhMucBenhLyMapper dmbenhlyMapper;

    public DmbenhlyServiceImpl(DmbenhlyRepository dmbenhlyRepository, MyDanhMucBenhLyMapper dmbenhlyMapper) {
        this.dmbenhlyRepository = dmbenhlyRepository;
        this.dmbenhlyMapper = dmbenhlyMapper;
    }

    /**
     * Save a dmbenhly.
     *
     * @param dmbenhlyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmbenhlyDTO save(DmbenhlyDTO dmbenhlyDTO) {
        log.debug("Request to save Dmbenhly : {}", dmbenhlyDTO);
        Dmbenhly dmbenhly = dmbenhlyMapper.toEntity(dmbenhlyDTO);
        dmbenhly = dmbenhlyRepository.save(dmbenhly);
        return dmbenhlyMapper.toDto(dmbenhly);
    }

    /**
     * Get all the dmbenhlies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmbenhlyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmbenhlies");
        return dmbenhlyRepository.findAll(pageable)
            .map(dmbenhlyMapper::toDto);
    }


    /**
     * Get one dmbenhly by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmbenhlyDTO> findOne(Long id) {
        log.debug("Request to get Dmbenhly : {}", id);
        return dmbenhlyRepository.findById(id)
            .map(dmbenhlyMapper::toDto);
    }

    /**
     * Delete the dmbenhly by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmbenhly : {}", id);
        dmbenhlyRepository.deleteById(id);
    }
}
