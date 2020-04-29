package com.api.backend.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.api.backend.domain.Dmxetnghiem;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmxetnghiemRepository;
import com.api.backend.service.dto.DmxetnghiemCriteria;
import com.api.backend.service.dto.DmxetnghiemDTO;
import com.api.backend.service.mapper.DmxetnghiemMapper;

/**
 * Service for executing complex queries for {@link Dmxetnghiem} entities in the database.
 * The main input is a {@link DmxetnghiemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmxetnghiemDTO} or a {@link Page} of {@link DmxetnghiemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmxetnghiemQueryService extends QueryService<Dmxetnghiem> {

    private final Logger log = LoggerFactory.getLogger(DmxetnghiemQueryService.class);

    private final DmxetnghiemRepository dmxetnghiemRepository;

    private final DmxetnghiemMapper dmxetnghiemMapper;

    public DmxetnghiemQueryService(DmxetnghiemRepository dmxetnghiemRepository, DmxetnghiemMapper dmxetnghiemMapper) {
        this.dmxetnghiemRepository = dmxetnghiemRepository;
        this.dmxetnghiemMapper = dmxetnghiemMapper;
    }

    /**
     * Return a {@link List} of {@link DmxetnghiemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmxetnghiemDTO> findByCriteria(DmxetnghiemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmxetnghiem> specification = createSpecification(criteria);
        return dmxetnghiemMapper.toDto(dmxetnghiemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmxetnghiemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmxetnghiemDTO> findByCriteria(DmxetnghiemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmxetnghiem> specification = createSpecification(criteria);
        return dmxetnghiemRepository.findAll(specification, page)
            .map(dmxetnghiemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmxetnghiemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmxetnghiem> specification = createSpecification(criteria);
        return dmxetnghiemRepository.count(specification);
    }

    /**
     * Function to convert {@link DmxetnghiemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmxetnghiem> createSpecification(DmxetnghiemCriteria criteria) {
        Specification<Dmxetnghiem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmxetnghiem_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmxetnghiem_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmxetnghiem_.ten));
            }
            if (criteria.getCha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCha(), Dmxetnghiem_.cha));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Dmxetnghiem_.mota));
            }
            if (criteria.getGioitinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGioitinh(), Dmxetnghiem_.gioitinh));
            }
            if (criteria.getCanduoi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCanduoi(), Dmxetnghiem_.canduoi));
            }
            if (criteria.getCantren() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCantren(), Dmxetnghiem_.cantren));
            }
            if (criteria.getDonvido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDonvido(), Dmxetnghiem_.donvido));
            }
            if (criteria.getMaByt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaByt(), Dmxetnghiem_.maByt));
            }
            if (criteria.getManhomBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManhomBH(), Dmxetnghiem_.manhomBH));
            }
            if (criteria.getDmnhomxetnghiemId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhomxetnghiemId(),
                    root -> root.join(Dmxetnghiem_.dmnhomxetnghiem, JoinType.LEFT).get(Dmnhomxetnghiem_.id)));
            }
        }
        return specification;
    }
}
