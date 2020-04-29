package com.api.backend.service.impl;

import com.api.backend.domain.Dmgiaiphaubenh;
import com.api.backend.repository.DmgiaiphaubenhRepository;
import com.api.backend.service.DmgiaiphaubenhService;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;
import com.api.backend.service.mapper.MyDanhMucGiaiPhauBenhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmgiaiphaubenh}.
 */
@Service
@Transactional
public class DmgiaiphaubenhServiceImpl implements DmgiaiphaubenhService {

    private final Logger log = LoggerFactory.getLogger(DmgiaiphaubenhServiceImpl.class);

    private final DmgiaiphaubenhRepository dmgiaiphaubenhRepository;

    private final MyDanhMucGiaiPhauBenhMapper dmgiaiphaubenhMapper;

    public DmgiaiphaubenhServiceImpl(DmgiaiphaubenhRepository dmgiaiphaubenhRepository, MyDanhMucGiaiPhauBenhMapper dmgiaiphaubenhMapper) {
        this.dmgiaiphaubenhRepository = dmgiaiphaubenhRepository;
        this.dmgiaiphaubenhMapper = dmgiaiphaubenhMapper;
    }

    /**
     * Save a dmgiaiphaubenh.
     *
     * @param dmgiaiphaubenhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmgiaiphaubenhDTO save(DmgiaiphaubenhDTO dmgiaiphaubenhDTO) {
        log.debug("Request to save Dmgiaiphaubenh : {}", dmgiaiphaubenhDTO);
        Dmgiaiphaubenh dmgiaiphaubenh = dmgiaiphaubenhMapper.toEntity(dmgiaiphaubenhDTO);
        dmgiaiphaubenh = dmgiaiphaubenhRepository.save(dmgiaiphaubenh);
        return dmgiaiphaubenhMapper.toDto(dmgiaiphaubenh);
    }

    /**
     * Get all the dmgiaiphaubenhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmgiaiphaubenhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmgiaiphaubenhs");
        return dmgiaiphaubenhRepository.findAll(pageable)
            .map(dmgiaiphaubenhMapper::toDto);
    }


    /**
     * Get one dmgiaiphaubenh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmgiaiphaubenhDTO> findOne(Long id) {
        log.debug("Request to get Dmgiaiphaubenh : {}", id);
        return dmgiaiphaubenhRepository.findById(id)
            .map(dmgiaiphaubenhMapper::toDto);
    }

    /**
     * Delete the dmgiaiphaubenh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmgiaiphaubenh : {}", id);
        dmgiaiphaubenhRepository.deleteById(id);
    }
}
