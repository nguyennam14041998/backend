package com.api.backend.service.impl;

import com.api.backend.service.XaService;
import com.api.backend.domain.Xa;
import com.api.backend.repository.XaRepository;
import com.api.backend.service.dto.XaDTO;
import com.api.backend.service.mapper.XaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Xa}.
 */
@Service
@Transactional
public class XaServiceImpl implements XaService {

    private final Logger log = LoggerFactory.getLogger(XaServiceImpl.class);

    private final XaRepository xaRepository;

    private final XaMapper xaMapper;

    public XaServiceImpl(XaRepository xaRepository, XaMapper xaMapper) {
        this.xaRepository = xaRepository;
        this.xaMapper = xaMapper;
    }

    /**
     * Save a xa.
     *
     * @param xaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public XaDTO save(XaDTO xaDTO) {
        log.debug("Request to save Xa : {}", xaDTO);
        Xa xa = xaMapper.toEntity(xaDTO);
        xa = xaRepository.save(xa);
        return xaMapper.toDto(xa);
    }

    /**
     * Get all the xas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<XaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Xas");
        return xaRepository.findAll(pageable)
            .map(xaMapper::toDto);
    }


    /**
     * Get one xa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<XaDTO> findOne(Long id) {
        log.debug("Request to get Xa : {}", id);
        return xaRepository.findById(id)
            .map(xaMapper::toDto);
    }

    /**
     * Delete the xa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Xa : {}", id);
        xaRepository.deleteById(id);
    }
}
