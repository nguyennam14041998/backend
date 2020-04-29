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

import com.api.backend.domain.Dmnhomgiaiphaubenh;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmnhomgiaiphaubenhRepository;
import com.api.backend.service.dto.DmnhomgiaiphaubenhCriteria;
import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;
import com.api.backend.service.mapper.DmnhomgiaiphaubenhMapper;

/**
 * Service for executing complex queries for {@link Dmnhomgiaiphaubenh} entities in the database.
 * The main input is a {@link DmnhomgiaiphaubenhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmnhomgiaiphaubenhDTO} or a {@link Page} of {@link DmnhomgiaiphaubenhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmnhomgiaiphaubenhQueryService extends QueryService<Dmnhomgiaiphaubenh> {

    private final Logger log = LoggerFactory.getLogger(DmnhomgiaiphaubenhQueryService.class);

    private final DmnhomgiaiphaubenhRepository dmnhomgiaiphaubenhRepository;

    private final DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper;

    public DmnhomgiaiphaubenhQueryService(DmnhomgiaiphaubenhRepository dmnhomgiaiphaubenhRepository, DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper) {
        this.dmnhomgiaiphaubenhRepository = dmnhomgiaiphaubenhRepository;
        this.dmnhomgiaiphaubenhMapper = dmnhomgiaiphaubenhMapper;
    }

    /**
     * Return a {@link List} of {@link DmnhomgiaiphaubenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmnhomgiaiphaubenhDTO> findByCriteria(DmnhomgiaiphaubenhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmnhomgiaiphaubenh> specification = createSpecification(criteria);
        return dmnhomgiaiphaubenhMapper.toDto(dmnhomgiaiphaubenhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmnhomgiaiphaubenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmnhomgiaiphaubenhDTO> findByCriteria(DmnhomgiaiphaubenhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmnhomgiaiphaubenh> specification = createSpecification(criteria);
        return dmnhomgiaiphaubenhRepository.findAll(specification, page)
            .map(dmnhomgiaiphaubenhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmnhomgiaiphaubenhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmnhomgiaiphaubenh> specification = createSpecification(criteria);
        return dmnhomgiaiphaubenhRepository.count(specification);
    }

    /**
     * Function to convert {@link DmnhomgiaiphaubenhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmnhomgiaiphaubenh> createSpecification(DmnhomgiaiphaubenhCriteria criteria) {
        Specification<Dmnhomgiaiphaubenh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmnhomgiaiphaubenh_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmnhomgiaiphaubenh_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmnhomgiaiphaubenh_.ten));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), Dmnhomgiaiphaubenh_.loai));
            }
            if (criteria.getMaBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBH(), Dmnhomgiaiphaubenh_.maBH));
            }
            if (criteria.getDmgiaiphaubenhId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmgiaiphaubenhId(),
                    root -> root.join(Dmnhomgiaiphaubenh_.dmgiaiphaubenhs, JoinType.LEFT).get(Dmgiaiphaubenh_.id)));
            }
        }
        return specification;
    }
}
