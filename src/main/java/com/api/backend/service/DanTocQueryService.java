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

import com.api.backend.domain.DanToc;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DanTocRepository;
import com.api.backend.service.dto.DanTocCriteria;
import com.api.backend.service.dto.DanTocDTO;
import com.api.backend.service.mapper.DanTocMapper;

/**
 * Service for executing complex queries for {@link DanToc} entities in the database.
 * The main input is a {@link DanTocCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanTocDTO} or a {@link Page} of {@link DanTocDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanTocQueryService extends QueryService<DanToc> {

    private final Logger log = LoggerFactory.getLogger(DanTocQueryService.class);

    private final DanTocRepository danTocRepository;

    private final DanTocMapper danTocMapper;

    public DanTocQueryService(DanTocRepository danTocRepository, DanTocMapper danTocMapper) {
        this.danTocRepository = danTocRepository;
        this.danTocMapper = danTocMapper;
    }

    /**
     * Return a {@link List} of {@link DanTocDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanTocDTO> findByCriteria(DanTocCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocMapper.toDto(danTocRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanTocDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanTocDTO> findByCriteria(DanTocCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocRepository.findAll(specification, page)
            .map(danTocMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanTocCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocRepository.count(specification);
    }

    /**
     * Function to convert {@link DanTocCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DanToc> createSpecification(DanTocCriteria criteria) {
        Specification<DanToc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DanToc_.id));
            }
            if (criteria.getMaDanToc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDanToc(), DanToc_.maDanToc));
            }
            if (criteria.getTenDanToc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDanToc(), DanToc_.tenDanToc));
            }
            if (criteria.getMoTa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoTa(), DanToc_.moTa));
            }
            if (criteria.getMaBHYT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBHYT(), DanToc_.maBHYT));
            }
            if (criteria.getMaBHXH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBHXH(), DanToc_.maBHXH));
            }
        }
        return specification;
    }
}
