package com.api.backend.service.impl;

import com.api.backend.domain.Dmxetnghiem;
import com.api.backend.repository.DmxetnghiemRepository;
import com.api.backend.service.DmxetnghiemService;
import com.api.backend.service.dto.DmxetnghiemDTO;
import com.api.backend.service.mapper.MyDanhMucXetNghiemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dmxetnghiem}.
 */
@Service
@Transactional
public class DmxetnghiemServiceImpl implements DmxetnghiemService {

    private final Logger log = LoggerFactory.getLogger(DmxetnghiemServiceImpl.class);

    private final DmxetnghiemRepository dmxetnghiemRepository;

    private final MyDanhMucXetNghiemMapper dmxetnghiemMapper;

    public DmxetnghiemServiceImpl(DmxetnghiemRepository dmxetnghiemRepository, MyDanhMucXetNghiemMapper dmxetnghiemMapper) {
        this.dmxetnghiemRepository = dmxetnghiemRepository;
        this.dmxetnghiemMapper = dmxetnghiemMapper;
    }

    /**
     * Save a dmxetnghiem.
     *
     * @param dmxetnghiemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DmxetnghiemDTO save(DmxetnghiemDTO dmxetnghiemDTO) {
        log.debug("Request to save Dmxetnghiem : {}", dmxetnghiemDTO);
        Dmxetnghiem dmxetnghiem = dmxetnghiemMapper.toEntity(dmxetnghiemDTO);
        dmxetnghiem = dmxetnghiemRepository.save(dmxetnghiem);
        return dmxetnghiemMapper.toDto(dmxetnghiem);
    }

    /**
     * Get all the dmxetnghiems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DmxetnghiemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dmxetnghiems");
        return dmxetnghiemRepository.findAll(pageable)
            .map(dmxetnghiemMapper::toDto);
    }


    /**
     * Get one dmxetnghiem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DmxetnghiemDTO> findOne(Long id) {
        log.debug("Request to get Dmxetnghiem : {}", id);
        return dmxetnghiemRepository.findById(id)
            .map(dmxetnghiemMapper::toDto);
    }

    /**
     * Delete the dmxetnghiem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dmxetnghiem : {}", id);
        dmxetnghiemRepository.deleteById(id);
    }
}
