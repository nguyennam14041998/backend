package com.api.backend.service.impl;

import com.api.backend.domain.Cosokhambenh;
import com.api.backend.repository.CosokhambenhRepository;
import com.api.backend.service.CosokhambenhService;
import com.api.backend.service.dto.CosokhambenhDTO;
import com.api.backend.service.mapper.MyCoSoKhamBenhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cosokhambenh}.
 */
@Service
@Transactional
public class CosokhambenhServiceImpl implements CosokhambenhService {

    private final Logger log = LoggerFactory.getLogger(CosokhambenhServiceImpl.class);

    private final CosokhambenhRepository cosokhambenhRepository;

    private final MyCoSoKhamBenhMapper cosokhambenhMapper;

    public CosokhambenhServiceImpl(CosokhambenhRepository cosokhambenhRepository, MyCoSoKhamBenhMapper cosokhambenhMapper) {
        this.cosokhambenhRepository = cosokhambenhRepository;
        this.cosokhambenhMapper = cosokhambenhMapper;
    }

    /**
     * Save a cosokhambenh.
     *
     * @param cosokhambenhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CosokhambenhDTO save(CosokhambenhDTO cosokhambenhDTO) {
        log.debug("Request to save Cosokhambenh : {}", cosokhambenhDTO);
        Cosokhambenh cosokhambenh = cosokhambenhMapper.toEntity(cosokhambenhDTO);
        cosokhambenh = cosokhambenhRepository.save(cosokhambenh);
        return cosokhambenhMapper.toDto(cosokhambenh);
    }

    /**
     * Get all the cosokhambenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CosokhambenhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cosokhambenhs");
        return cosokhambenhRepository.findAll(pageable)
            .map(cosokhambenhMapper::toDto);
    }


    /**
     * Get one cosokhambenh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CosokhambenhDTO> findOne(Long id) {
        log.debug("Request to get Cosokhambenh : {}", id);
        return cosokhambenhRepository.findById(id)
            .map(cosokhambenhMapper::toDto);
    }

    /**
     * Delete the cosokhambenh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cosokhambenh : {}", id);
        cosokhambenhRepository.deleteById(id);
    }
}
