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

import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmloaibenhlyRepository;
import com.api.backend.service.dto.DmloaibenhlyCriteria;
import com.api.backend.service.dto.DmloaibenhlyDTO;
import com.api.backend.service.mapper.DmloaibenhlyMapper;

/**
 * Service for executing complex queries for {@link Dmloaibenhly} entities in the database.
 * The main input is a {@link DmloaibenhlyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmloaibenhlyDTO} or a {@link Page} of {@link DmloaibenhlyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmloaibenhlyQueryService extends QueryService<Dmloaibenhly> {

    private final Logger log = LoggerFactory.getLogger(DmloaibenhlyQueryService.class);

    private final DmloaibenhlyRepository dmloaibenhlyRepository;

    private final DmloaibenhlyMapper dmloaibenhlyMapper;

    public DmloaibenhlyQueryService(DmloaibenhlyRepository dmloaibenhlyRepository, DmloaibenhlyMapper dmloaibenhlyMapper) {
        this.dmloaibenhlyRepository = dmloaibenhlyRepository;
        this.dmloaibenhlyMapper = dmloaibenhlyMapper;
    }

    /**
     * Return a {@link List} of {@link DmloaibenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmloaibenhlyDTO> findByCriteria(DmloaibenhlyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmloaibenhly> specification = createSpecification(criteria);
        return dmloaibenhlyMapper.toDto(dmloaibenhlyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmloaibenhlyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmloaibenhlyDTO> findByCriteria(DmloaibenhlyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmloaibenhly> specification = createSpecification(criteria);
        return dmloaibenhlyRepository.findAll(specification, page)
            .map(dmloaibenhlyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmloaibenhlyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmloaibenhly> specification = createSpecification(criteria);
        return dmloaibenhlyRepository.count(specification);
    }

    /**
     * Function to convert {@link DmloaibenhlyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmloaibenhly> createSpecification(DmloaibenhlyCriteria criteria) {
        Specification<Dmloaibenhly> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmloaibenhly_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmloaibenhly_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmloaibenhly_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Dmloaibenhly_.mota));
            }
            if (criteria.getChuong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChuong(), Dmloaibenhly_.chuong));
            }
            if (criteria.getDmnhombenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhombenhlyId(),
                    root -> root.join(Dmloaibenhly_.dmnhombenhlies, JoinType.LEFT).get(Dmnhombenhly_.id)));
            }
            if (criteria.getDmbenhlyId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmbenhlyId(),
                    root -> root.join(Dmloaibenhly_.dmbenhlies, JoinType.LEFT).get(Dmbenhly_.id)));
            }
        }
        return specification;
    }
}
