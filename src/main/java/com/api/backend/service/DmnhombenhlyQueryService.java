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

import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmnhombenhlyRepository;
import com.api.backend.service.dto.DmnhombenhlyCriteria;
import com.api.backend.service.dto.DmnhombenhlyDTO;
import com.api.backend.service.mapper.DmnhombenhlyMapper;

/**
 * Service for executing complex queries for {@link Dmnhombenhly} entities in the database.
 * The main input is a {@link DmnhombenhlyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmnhombenhlyDTO} or a {@link Page} of {@link DmnhombenhlyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmnhombenhlyQueryService extends QueryService<Dmnhombenhly> {

    private final Logger log = LoggerFactory.getLogger(DmnhombenhlyQueryService.class);

    private final DmnhombenhlyRepository dmnhombenhlyRepository;

    private final DmnhombenhlyMapper dmnhombenhlyMapper;

    public DmnhombenhlyQueryService(DmnhombenhlyRepository dmnhombenhlyRepository, DmnhombenhlyMapper dmnhombenhlyMapper) {
        this.dmnhombenhlyRepository = dmnhombenhlyRepository;
        this.dmnhombenhlyMapper = dmnhombenhlyMapper;
    }

    /**
     * Return a {@link List} of {@link DmnhombenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmnhombenhlyDTO> findByCriteria(DmnhombenhlyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmnhombenhly> specification = createSpecification(criteria);
        return dmnhombenhlyMapper.toDto(dmnhombenhlyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmnhombenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmnhombenhlyDTO> findByCriteria(DmnhombenhlyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmnhombenhly> specification = createSpecification(criteria);
        return dmnhombenhlyRepository.findAll(specification, page)
            .map(dmnhombenhlyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmnhombenhlyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmnhombenhly> specification = createSpecification(criteria);
        return dmnhombenhlyRepository.count(specification);
    }

    /**
     * Function to convert {@link DmnhombenhlyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmnhombenhly> createSpecification(DmnhombenhlyCriteria criteria) {
        Specification<Dmnhombenhly> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmnhombenhly_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmnhombenhly_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmnhombenhly_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Dmnhombenhly_.mota));
            }
            if (criteria.getDmbenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmbenhlyId(),
                    root -> root.join(Dmnhombenhly_.dmbenhlies, JoinType.LEFT).get(Dmbenhly_.id)));
            }
            if (criteria.getDmloaibenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmloaibenhlyId(),
                    root -> root.join(Dmnhombenhly_.dmloaibenhly, JoinType.LEFT).get(Dmloaibenhly_.id)));
            }
        }
        return specification;
    }
}
