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

import com.api.backend.domain.Cosokhambenh;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.CosokhambenhRepository;
import com.api.backend.service.dto.CosokhambenhCriteria;
import com.api.backend.service.dto.CosokhambenhDTO;
import com.api.backend.service.mapper.CosokhambenhMapper;

/**
 * Service for executing complex queries for {@link Cosokhambenh} entities in the database.
 * The main input is a {@link CosokhambenhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CosokhambenhDTO} or a {@link Page} of {@link CosokhambenhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CosokhambenhQueryService extends QueryService<Cosokhambenh> {

    private final Logger log = LoggerFactory.getLogger(CosokhambenhQueryService.class);

    private final CosokhambenhRepository cosokhambenhRepository;

    private final CosokhambenhMapper cosokhambenhMapper;

    public CosokhambenhQueryService(CosokhambenhRepository cosokhambenhRepository, CosokhambenhMapper cosokhambenhMapper) {
        this.cosokhambenhRepository = cosokhambenhRepository;
        this.cosokhambenhMapper = cosokhambenhMapper;
    }

    /**
     * Return a {@link List} of {@link CosokhambenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CosokhambenhDTO> findByCriteria(CosokhambenhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cosokhambenh> specification = createSpecification(criteria);
        return cosokhambenhMapper.toDto(cosokhambenhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CosokhambenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CosokhambenhDTO> findByCriteria(CosokhambenhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cosokhambenh> specification = createSpecification(criteria);
        return cosokhambenhRepository.findAll(specification, page)
            .map(cosokhambenhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CosokhambenhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cosokhambenh> specification = createSpecification(criteria);
        return cosokhambenhRepository.count(specification);
    }

    /**
     * Function to convert {@link CosokhambenhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Cosokhambenh> createSpecification(CosokhambenhCriteria criteria) {
        Specification<Cosokhambenh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Cosokhambenh_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Cosokhambenh_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Cosokhambenh_.ten));
            }
            if (criteria.getMaKCBBD() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaKCBBD(), Cosokhambenh_.maKCBBD));
            }
            if (criteria.getHang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHang(), Cosokhambenh_.hang));
            }
            if (criteria.getTuyen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTuyen(), Cosokhambenh_.tuyen));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLoai(), Cosokhambenh_.loai));
            }
            if (criteria.getDiachi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiachi(), Cosokhambenh_.diachi));
            }
            if (criteria.getGhichu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhichu(), Cosokhambenh_.ghichu));
            }
            if (criteria.getTinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getTinhId(),
                    root -> root.join(Cosokhambenh_.tinh, JoinType.LEFT).get(Tinh_.id)));
            }
            if (criteria.getHuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getHuyenId(),
                    root -> root.join(Cosokhambenh_.huyen, JoinType.LEFT).get(Huyen_.id)));
            }
            if (criteria.getXaId() != null) {
                specification = specification.and(buildSpecification(criteria.getXaId(),
                    root -> root.join(Cosokhambenh_.xa, JoinType.LEFT).get(Xa_.id)));
            }
        }
        return specification;
    }
}
