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

import com.api.backend.domain.Dmnhomxetnghiem;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmnhomxetnghiemRepository;
import com.api.backend.service.dto.DmnhomxetnghiemCriteria;
import com.api.backend.service.dto.DmnhomxetnghiemDTO;
import com.api.backend.service.mapper.DmnhomxetnghiemMapper;

/**
 * Service for executing complex queries for {@link Dmnhomxetnghiem} entities in the database.
 * The main input is a {@link DmnhomxetnghiemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmnhomxetnghiemDTO} or a {@link Page} of {@link DmnhomxetnghiemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmnhomxetnghiemQueryService extends QueryService<Dmnhomxetnghiem> {

    private final Logger log = LoggerFactory.getLogger(DmnhomxetnghiemQueryService.class);

    private final DmnhomxetnghiemRepository dmnhomxetnghiemRepository;

    private final DmnhomxetnghiemMapper dmnhomxetnghiemMapper;

    public DmnhomxetnghiemQueryService(DmnhomxetnghiemRepository dmnhomxetnghiemRepository, DmnhomxetnghiemMapper dmnhomxetnghiemMapper) {
        this.dmnhomxetnghiemRepository = dmnhomxetnghiemRepository;
        this.dmnhomxetnghiemMapper = dmnhomxetnghiemMapper;
    }

    /**
     * Return a {@link List} of {@link DmnhomxetnghiemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmnhomxetnghiemDTO> findByCriteria(DmnhomxetnghiemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmnhomxetnghiem> specification = createSpecification(criteria);
        return dmnhomxetnghiemMapper.toDto(dmnhomxetnghiemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmnhomxetnghiemDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmnhomxetnghiemDTO> findByCriteria(DmnhomxetnghiemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmnhomxetnghiem> specification = createSpecification(criteria);
        return dmnhomxetnghiemRepository.findAll(specification, page)
            .map(dmnhomxetnghiemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmnhomxetnghiemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmnhomxetnghiem> specification = createSpecification(criteria);
        return dmnhomxetnghiemRepository.count(specification);
    }

    /**
     * Function to convert {@link DmnhomxetnghiemCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmnhomxetnghiem> createSpecification(DmnhomxetnghiemCriteria criteria) {
        Specification<Dmnhomxetnghiem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmnhomxetnghiem_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmnhomxetnghiem_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmnhomxetnghiem_.ten));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), Dmnhomxetnghiem_.loai));
            }
            if (criteria.getMabh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMabh(), Dmnhomxetnghiem_.mabh));
            }
            if (criteria.getDmxetnghiemId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmxetnghiemId(),
                    root -> root.join(Dmnhomxetnghiem_.dmxetnghiems, JoinType.LEFT).get(Dmxetnghiem_.id)));
            }
        }
        return specification;
    }
}
