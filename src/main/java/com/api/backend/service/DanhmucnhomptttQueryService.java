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

import com.api.backend.domain.Danhmucnhompttt;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DanhmucnhomptttRepository;
import com.api.backend.service.dto.DanhmucnhomptttCriteria;
import com.api.backend.service.dto.DanhmucnhomptttDTO;
import com.api.backend.service.mapper.DanhmucnhomptttMapper;

/**
 * Service for executing complex queries for {@link Danhmucnhompttt} entities in the database.
 * The main input is a {@link DanhmucnhomptttCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhmucnhomptttDTO} or a {@link Page} of {@link DanhmucnhomptttDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhmucnhomptttQueryService extends QueryService<Danhmucnhompttt> {

    private final Logger log = LoggerFactory.getLogger(DanhmucnhomptttQueryService.class);

    private final DanhmucnhomptttRepository danhmucnhomptttRepository;

    private final DanhmucnhomptttMapper danhmucnhomptttMapper;

    public DanhmucnhomptttQueryService(DanhmucnhomptttRepository danhmucnhomptttRepository, DanhmucnhomptttMapper danhmucnhomptttMapper) {
        this.danhmucnhomptttRepository = danhmucnhomptttRepository;
        this.danhmucnhomptttMapper = danhmucnhomptttMapper;
    }

    /**
     * Return a {@link List} of {@link DanhmucnhomptttDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhmucnhomptttDTO> findByCriteria(DanhmucnhomptttCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Danhmucnhompttt> specification = createSpecification(criteria);
        return danhmucnhomptttMapper.toDto(danhmucnhomptttRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhmucnhomptttDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhmucnhomptttDTO> findByCriteria(DanhmucnhomptttCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Danhmucnhompttt> specification = createSpecification(criteria);
        return danhmucnhomptttRepository.findAll(specification, page)
            .map(danhmucnhomptttMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhmucnhomptttCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Danhmucnhompttt> specification = createSpecification(criteria);
        return danhmucnhomptttRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhmucnhomptttCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Danhmucnhompttt> createSpecification(DanhmucnhomptttCriteria criteria) {
        Specification<Danhmucnhompttt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Danhmucnhompttt_.id));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), Danhmucnhompttt_.loai));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Danhmucnhompttt_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Danhmucnhompttt_.ten));
            }
            if (criteria.getDanhmucptttId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhmucptttId(),
                    root -> root.join(Danhmucnhompttt_.danhmucpttts, JoinType.LEFT).get(Danhmucpttt_.id)));
            }
        }
        return specification;
    }
}
