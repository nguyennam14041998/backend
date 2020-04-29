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

import com.api.backend.domain.Xa;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.XaRepository;
import com.api.backend.service.dto.XaCriteria;
import com.api.backend.service.dto.XaDTO;
import com.api.backend.service.mapper.XaMapper;

/**
 * Service for executing complex queries for {@link Xa} entities in the database.
 * The main input is a {@link XaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link XaDTO} or a {@link Page} of {@link XaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class XaQueryService extends QueryService<Xa> {

    private final Logger log = LoggerFactory.getLogger(XaQueryService.class);

    private final XaRepository xaRepository;

    private final XaMapper xaMapper;

    public XaQueryService(XaRepository xaRepository, XaMapper xaMapper) {
        this.xaRepository = xaRepository;
        this.xaMapper = xaMapper;
    }

    /**
     * Return a {@link List} of {@link XaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<XaDTO> findByCriteria(XaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Xa> specification = createSpecification(criteria);
        return xaMapper.toDto(xaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link XaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<XaDTO> findByCriteria(XaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Xa> specification = createSpecification(criteria);
        return xaRepository.findAll(specification, page)
            .map(xaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(XaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Xa> specification = createSpecification(criteria);
        return xaRepository.count(specification);
    }

    /**
     * Function to convert {@link XaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Xa> createSpecification(XaCriteria criteria) {
        Specification<Xa> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Xa_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Xa_.ten));
            }
            if (criteria.getCosokhambenhId() != null) {
                specification = specification.and(buildSpecification(criteria.getCosokhambenhId(),
                    root -> root.join(Xa_.cosokhambenhs, JoinType.LEFT).get(Cosokhambenh_.id)));
            }
            if (criteria.getHuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getHuyenId(),
                    root -> root.join(Xa_.huyen, JoinType.LEFT).get(Huyen_.id)));
            }
        }
        return specification;
    }
}
