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

import com.api.backend.domain.Tinh;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.TinhRepository;
import com.api.backend.service.dto.TinhCriteria;
import com.api.backend.service.dto.TinhDTO;
import com.api.backend.service.mapper.TinhMapper;

/**
 * Service for executing complex queries for {@link Tinh} entities in the database.
 * The main input is a {@link TinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TinhDTO} or a {@link Page} of {@link TinhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TinhQueryService extends QueryService<Tinh> {

    private final Logger log = LoggerFactory.getLogger(TinhQueryService.class);

    private final TinhRepository tinhRepository;

    private final TinhMapper tinhMapper;

    public TinhQueryService(TinhRepository tinhRepository, TinhMapper tinhMapper) {
        this.tinhRepository = tinhRepository;
        this.tinhMapper = tinhMapper;
    }

    /**
     * Return a {@link List} of {@link TinhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TinhDTO> findByCriteria(TinhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Tinh> specification = createSpecification(criteria);
        return tinhMapper.toDto(tinhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TinhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TinhDTO> findByCriteria(TinhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Tinh> specification = createSpecification(criteria);
        return tinhRepository.findAll(specification, page)
            .map(tinhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TinhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Tinh> specification = createSpecification(criteria);
        return tinhRepository.count(specification);
    }

    /**
     * Function to convert {@link TinhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Tinh> createSpecification(TinhCriteria criteria) {
        Specification<Tinh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Tinh_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Tinh_.ten));
            }
            if (criteria.getHuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getHuyenId(),
                    root -> root.join(Tinh_.huyens, JoinType.LEFT).get(Huyen_.id)));
            }
            if (criteria.getCosokhambenhId() != null) {
                specification = specification.and(buildSpecification(criteria.getCosokhambenhId(),
                    root -> root.join(Tinh_.cosokhambenhs, JoinType.LEFT).get(Cosokhambenh_.id)));
            }
        }
        return specification;
    }
}
