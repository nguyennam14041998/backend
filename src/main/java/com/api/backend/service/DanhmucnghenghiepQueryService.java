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

import com.api.backend.domain.Danhmucnghenghiep;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DanhmucnghenghiepRepository;
import com.api.backend.service.dto.DanhmucnghenghiepCriteria;
import com.api.backend.service.dto.DanhmucnghenghiepDTO;
import com.api.backend.service.mapper.DanhmucnghenghiepMapper;

/**
 * Service for executing complex queries for {@link Danhmucnghenghiep} entities in the database.
 * The main input is a {@link DanhmucnghenghiepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhmucnghenghiepDTO} or a {@link Page} of {@link DanhmucnghenghiepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhmucnghenghiepQueryService extends QueryService<Danhmucnghenghiep> {

    private final Logger log = LoggerFactory.getLogger(DanhmucnghenghiepQueryService.class);

    private final DanhmucnghenghiepRepository danhmucnghenghiepRepository;

    private final DanhmucnghenghiepMapper danhmucnghenghiepMapper;

    public DanhmucnghenghiepQueryService(DanhmucnghenghiepRepository danhmucnghenghiepRepository, DanhmucnghenghiepMapper danhmucnghenghiepMapper) {
        this.danhmucnghenghiepRepository = danhmucnghenghiepRepository;
        this.danhmucnghenghiepMapper = danhmucnghenghiepMapper;
    }

    /**
     * Return a {@link List} of {@link DanhmucnghenghiepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhmucnghenghiepDTO> findByCriteria(DanhmucnghenghiepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Danhmucnghenghiep> specification = createSpecification(criteria);
        return danhmucnghenghiepMapper.toDto(danhmucnghenghiepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhmucnghenghiepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhmucnghenghiepDTO> findByCriteria(DanhmucnghenghiepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Danhmucnghenghiep> specification = createSpecification(criteria);
        return danhmucnghenghiepRepository.findAll(specification, page)
            .map(danhmucnghenghiepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhmucnghenghiepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Danhmucnghenghiep> specification = createSpecification(criteria);
        return danhmucnghenghiepRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhmucnghenghiepCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Danhmucnghenghiep> createSpecification(DanhmucnghenghiepCriteria criteria) {
        Specification<Danhmucnghenghiep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Danhmucnghenghiep_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Danhmucnghenghiep_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Danhmucnghenghiep_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Danhmucnghenghiep_.mota));
            }
            if (criteria.getbYT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getbYT(), Danhmucnghenghiep_.bYT));
            }
            if (criteria.getSudung() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSudung(), Danhmucnghenghiep_.sudung));
            }
        }
        return specification;
    }
}
