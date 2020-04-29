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

import com.api.backend.domain.Dmbenhly;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmbenhlyRepository;
import com.api.backend.service.dto.DmbenhlyCriteria;
import com.api.backend.service.dto.DmbenhlyDTO;
import com.api.backend.service.mapper.DmbenhlyMapper;

/**
 * Service for executing complex queries for {@link Dmbenhly} entities in the database.
 * The main input is a {@link DmbenhlyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmbenhlyDTO} or a {@link Page} of {@link DmbenhlyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmbenhlyQueryService extends QueryService<Dmbenhly> {

    private final Logger log = LoggerFactory.getLogger(DmbenhlyQueryService.class);

    private final DmbenhlyRepository dmbenhlyRepository;

    private final DmbenhlyMapper dmbenhlyMapper;

    public DmbenhlyQueryService(DmbenhlyRepository dmbenhlyRepository, DmbenhlyMapper dmbenhlyMapper) {
        this.dmbenhlyRepository = dmbenhlyRepository;
        this.dmbenhlyMapper = dmbenhlyMapper;
    }

    /**
     * Return a {@link List} of {@link DmbenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmbenhlyDTO> findByCriteria(DmbenhlyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmbenhly> specification = createSpecification(criteria);
        return dmbenhlyMapper.toDto(dmbenhlyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmbenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmbenhlyDTO> findByCriteria(DmbenhlyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmbenhly> specification = createSpecification(criteria);
        return dmbenhlyRepository.findAll(specification, page)
            .map(dmbenhlyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmbenhlyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmbenhly> specification = createSpecification(criteria);
        return dmbenhlyRepository.count(specification);
    }

    /**
     * Function to convert {@link DmbenhlyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmbenhly> createSpecification(DmbenhlyCriteria criteria) {
        Specification<Dmbenhly> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmbenhly_.id));
            }
            if (criteria.getiCD() != null) {
                specification = specification.and(buildStringSpecification(criteria.getiCD(), Dmbenhly_.iCD));
            }
            if (criteria.getTenICD10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenICD10(), Dmbenhly_.tenICD10));
            }
            if (criteria.getTentienganh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTentienganh(), Dmbenhly_.tentienganh));
            }
            if (criteria.getTenthuonggoi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenthuonggoi(), Dmbenhly_.tenthuonggoi));
            }
            if (criteria.getiCDcha() != null) {
                specification = specification.and(buildStringSpecification(criteria.getiCDcha(), Dmbenhly_.iCDcha));
            }
            if (criteria.getNgayAD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayAD(), Dmbenhly_.ngayAD));
            }
            if (criteria.getTrangthai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrangthai(), Dmbenhly_.trangthai));
            }
            if (criteria.getDmloaibenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmloaibenhlyId(),
                    root -> root.join(Dmbenhly_.dmloaibenhly, JoinType.LEFT).get(Dmloaibenhly_.id)));
            }
            if (criteria.getDmnhombenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhombenhlyId(),
                    root -> root.join(Dmbenhly_.dmnhombenhly, JoinType.LEFT).get(Dmnhombenhly_.id)));
            }
        }
        return specification;
    }
}
