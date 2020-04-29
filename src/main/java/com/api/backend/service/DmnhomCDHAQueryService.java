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

import com.api.backend.domain.DmnhomCDHA;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmnhomCDHARepository;
import com.api.backend.service.dto.DmnhomCDHACriteria;
import com.api.backend.service.dto.DmnhomCDHADTO;
import com.api.backend.service.mapper.DmnhomCDHAMapper;

/**
 * Service for executing complex queries for {@link DmnhomCDHA} entities in the database.
 * The main input is a {@link DmnhomCDHACriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmnhomCDHADTO} or a {@link Page} of {@link DmnhomCDHADTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmnhomCDHAQueryService extends QueryService<DmnhomCDHA> {

    private final Logger log = LoggerFactory.getLogger(DmnhomCDHAQueryService.class);

    private final DmnhomCDHARepository dmnhomCDHARepository;

    private final DmnhomCDHAMapper dmnhomCDHAMapper;

    public DmnhomCDHAQueryService(DmnhomCDHARepository dmnhomCDHARepository, DmnhomCDHAMapper dmnhomCDHAMapper) {
        this.dmnhomCDHARepository = dmnhomCDHARepository;
        this.dmnhomCDHAMapper = dmnhomCDHAMapper;
    }

    /**
     * Return a {@link List} of {@link DmnhomCDHADTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmnhomCDHADTO> findByCriteria(DmnhomCDHACriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DmnhomCDHA> specification = createSpecification(criteria);
        return dmnhomCDHAMapper.toDto(dmnhomCDHARepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmnhomCDHADTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmnhomCDHADTO> findByCriteria(DmnhomCDHACriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmnhomCDHA> specification = createSpecification(criteria);
        return dmnhomCDHARepository.findAll(specification, page)
            .map(dmnhomCDHAMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmnhomCDHACriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DmnhomCDHA> specification = createSpecification(criteria);
        return dmnhomCDHARepository.count(specification);
    }

    /**
     * Function to convert {@link DmnhomCDHACriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmnhomCDHA> createSpecification(DmnhomCDHACriteria criteria) {
        Specification<DmnhomCDHA> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DmnhomCDHA_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), DmnhomCDHA_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), DmnhomCDHA_.ten));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), DmnhomCDHA_.loai));
            }
            if (criteria.getMaBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBH(), DmnhomCDHA_.maBH));
            }
            if (criteria.getDmCDHAId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmCDHAId(),
                    root -> root.join(DmnhomCDHA_.dmCDHAS, JoinType.LEFT).get(DmCDHA_.id)));
            }
        }
        return specification;
    }
}
