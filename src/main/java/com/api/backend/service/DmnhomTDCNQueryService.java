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

import com.api.backend.domain.DmnhomTDCN;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmnhomTDCNRepository;
import com.api.backend.service.dto.DmnhomTDCNCriteria;
import com.api.backend.service.dto.DmnhomTDCNDTO;
import com.api.backend.service.mapper.DmnhomTDCNMapper;

/**
 * Service for executing complex queries for {@link DmnhomTDCN} entities in the database.
 * The main input is a {@link DmnhomTDCNCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmnhomTDCNDTO} or a {@link Page} of {@link DmnhomTDCNDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmnhomTDCNQueryService extends QueryService<DmnhomTDCN> {

    private final Logger log = LoggerFactory.getLogger(DmnhomTDCNQueryService.class);

    private final DmnhomTDCNRepository dmnhomTDCNRepository;

    private final DmnhomTDCNMapper dmnhomTDCNMapper;

    public DmnhomTDCNQueryService(DmnhomTDCNRepository dmnhomTDCNRepository, DmnhomTDCNMapper dmnhomTDCNMapper) {
        this.dmnhomTDCNRepository = dmnhomTDCNRepository;
        this.dmnhomTDCNMapper = dmnhomTDCNMapper;
    }

    /**
     * Return a {@link List} of {@link DmnhomTDCNDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmnhomTDCNDTO> findByCriteria(DmnhomTDCNCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DmnhomTDCN> specification = createSpecification(criteria);
        return dmnhomTDCNMapper.toDto(dmnhomTDCNRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmnhomTDCNDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmnhomTDCNDTO> findByCriteria(DmnhomTDCNCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmnhomTDCN> specification = createSpecification(criteria);
        return dmnhomTDCNRepository.findAll(specification, page)
            .map(dmnhomTDCNMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmnhomTDCNCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DmnhomTDCN> specification = createSpecification(criteria);
        return dmnhomTDCNRepository.count(specification);
    }

    /**
     * Function to convert {@link DmnhomTDCNCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmnhomTDCN> createSpecification(DmnhomTDCNCriteria criteria) {
        Specification<DmnhomTDCN> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DmnhomTDCN_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), DmnhomTDCN_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), DmnhomTDCN_.ten));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), DmnhomTDCN_.loai));
            }
            if (criteria.getMaBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBH(), DmnhomTDCN_.maBH));
            }
            if (criteria.getDmTDCNId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmTDCNId(),
                    root -> root.join(DmnhomTDCN_.dmTDCNS, JoinType.LEFT).get(DmTDCN_.id)));
            }
        }
        return specification;
    }
}
