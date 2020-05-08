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

import com.api.backend.domain.Danhmuchanhchinh;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DanhmuchanhchinhRepository;
import com.api.backend.service.dto.DanhmuchanhchinhCriteria;
import com.api.backend.service.dto.DanhmuchanhchinhDTO;
import com.api.backend.service.mapper.DanhmuchanhchinhMapper;

/**
 * Service for executing complex queries for {@link Danhmuchanhchinh} entities in the database.
 * The main input is a {@link DanhmuchanhchinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhmuchanhchinhDTO} or a {@link Page} of {@link DanhmuchanhchinhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhmuchanhchinhQueryService extends QueryService<Danhmuchanhchinh> {

    private final Logger log = LoggerFactory.getLogger(DanhmuchanhchinhQueryService.class);

    private final DanhmuchanhchinhRepository danhmuchanhchinhRepository;

    private final DanhmuchanhchinhMapper danhmuchanhchinhMapper;

    public DanhmuchanhchinhQueryService(DanhmuchanhchinhRepository danhmuchanhchinhRepository, DanhmuchanhchinhMapper danhmuchanhchinhMapper) {
        this.danhmuchanhchinhRepository = danhmuchanhchinhRepository;
        this.danhmuchanhchinhMapper = danhmuchanhchinhMapper;
    }

    /**
     * Return a {@link List} of {@link DanhmuchanhchinhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhmuchanhchinhDTO> findByCriteria(DanhmuchanhchinhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Danhmuchanhchinh> specification = createSpecification(criteria);
        return danhmuchanhchinhMapper.toDto(danhmuchanhchinhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhmuchanhchinhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhmuchanhchinhDTO> findByCriteria(DanhmuchanhchinhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Danhmuchanhchinh> specification = createSpecification(criteria);
        return danhmuchanhchinhRepository.findAll(specification, page)
            .map(danhmuchanhchinhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhmuchanhchinhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Danhmuchanhchinh> specification = createSpecification(criteria);
        return danhmuchanhchinhRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhmuchanhchinhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Danhmuchanhchinh> createSpecification(DanhmuchanhchinhCriteria criteria) {
        Specification<Danhmuchanhchinh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Danhmuchanhchinh_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Danhmuchanhchinh_.ten));
            }
            if (criteria.getTendaydu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTendaydu(), Danhmuchanhchinh_.tendaydu));
            }
            if (criteria.getTenviettat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenviettat(), Danhmuchanhchinh_.tenviettat));
            }
            if (criteria.getTags() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTags(), Danhmuchanhchinh_.tags));
            }
            if (criteria.getDiadanhcha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiadanhcha(), Danhmuchanhchinh_.diadanhcha));
            }
            if (criteria.getCap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCap(), Danhmuchanhchinh_.cap));
            }
            if (criteria.getThanhthi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThanhthi(), Danhmuchanhchinh_.thanhthi));
            }
            if (criteria.getHoatdong() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHoatdong(), Danhmuchanhchinh_.hoatdong));
            }
        }
        return specification;
    }
}
