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

import com.api.backend.domain.Huyen;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.HuyenRepository;
import com.api.backend.service.dto.HuyenCriteria;
import com.api.backend.service.dto.HuyenDTO;
import com.api.backend.service.mapper.HuyenMapper;

/**
 * Service for executing complex queries for {@link Huyen} entities in the database.
 * The main input is a {@link HuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HuyenDTO} or a {@link Page} of {@link HuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HuyenQueryService extends QueryService<Huyen> {

    private final Logger log = LoggerFactory.getLogger(HuyenQueryService.class);

    private final HuyenRepository huyenRepository;

    private final HuyenMapper huyenMapper;

    public HuyenQueryService(HuyenRepository huyenRepository, HuyenMapper huyenMapper) {
        this.huyenRepository = huyenRepository;
        this.huyenMapper = huyenMapper;
    }

    /**
     * Return a {@link List} of {@link HuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HuyenDTO> findByCriteria(HuyenCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Huyen> specification = createSpecification(criteria);
        return huyenMapper.toDto(huyenRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HuyenDTO> findByCriteria(HuyenCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Huyen> specification = createSpecification(criteria);
        return huyenRepository.findAll(specification, page)
            .map(huyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HuyenCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Huyen> specification = createSpecification(criteria);
        return huyenRepository.count(specification);
    }

    /**
     * Function to convert {@link HuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Huyen> createSpecification(HuyenCriteria criteria) {
        Specification<Huyen> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Huyen_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Huyen_.ten));
            }
            if (criteria.getXaId() != null) {
                specification = specification.and(buildSpecification(criteria.getXaId(),
                    root -> root.join(Huyen_.xas, JoinType.LEFT).get(Xa_.id)));
            }
            if (criteria.getCosokhambenhId() != null) {
                specification = specification.and(buildSpecification(criteria.getCosokhambenhId(),
                    root -> root.join(Huyen_.cosokhambenhs, JoinType.LEFT).get(Cosokhambenh_.id)));
            }
            if (criteria.getTinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getTinhId(),
                    root -> root.join(Huyen_.tinh, JoinType.LEFT).get(Tinh_.id)));
            }
        }
        return specification;
    }
}
