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

import com.api.backend.domain.DmTDCN;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmTDCNRepository;
import com.api.backend.service.dto.DmTDCNCriteria;
import com.api.backend.service.dto.DmTDCNDTO;
import com.api.backend.service.mapper.DmTDCNMapper;

/**
 * Service for executing complex queries for {@link DmTDCN} entities in the database.
 * The main input is a {@link DmTDCNCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmTDCNDTO} or a {@link Page} of {@link DmTDCNDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmTDCNQueryService extends QueryService<DmTDCN> {

    private final Logger log = LoggerFactory.getLogger(DmTDCNQueryService.class);

    private final DmTDCNRepository dmTDCNRepository;

    private final DmTDCNMapper dmTDCNMapper;

    public DmTDCNQueryService(DmTDCNRepository dmTDCNRepository, DmTDCNMapper dmTDCNMapper) {
        this.dmTDCNRepository = dmTDCNRepository;
        this.dmTDCNMapper = dmTDCNMapper;
    }

    /**
     * Return a {@link List} of {@link DmTDCNDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmTDCNDTO> findByCriteria(DmTDCNCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DmTDCN> specification = createSpecification(criteria);
        return dmTDCNMapper.toDto(dmTDCNRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmTDCNDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmTDCNDTO> findByCriteria(DmTDCNCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmTDCN> specification = createSpecification(criteria);
        return dmTDCNRepository.findAll(specification, page)
            .map(dmTDCNMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmTDCNCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DmTDCN> specification = createSpecification(criteria);
        return dmTDCNRepository.count(specification);
    }

    /**
     * Function to convert {@link DmTDCNCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmTDCN> createSpecification(DmTDCNCriteria criteria) {
        Specification<DmTDCN> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DmTDCN_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), DmTDCN_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), DmTDCN_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), DmTDCN_.mota));
            }
            if (criteria.getGioitinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGioitinh(), DmTDCN_.gioitinh));
            }
            if (criteria.getMaBYT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBYT(), DmTDCN_.maBYT));
            }
            if (criteria.getManhomBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManhomBH(), DmTDCN_.manhomBH));
            }
            if (criteria.getDmnhomTDCNId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhomTDCNId(),
                    root -> root.join(DmTDCN_.dmnhomTDCN, JoinType.LEFT).get(DmnhomTDCN_.id)));
            }
        }
        return specification;
    }
}
