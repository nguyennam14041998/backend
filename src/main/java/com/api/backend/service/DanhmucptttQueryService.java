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

import com.api.backend.domain.Danhmucpttt;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DanhmucptttRepository;
import com.api.backend.service.dto.DanhmucptttCriteria;
import com.api.backend.service.dto.DanhmucptttDTO;
import com.api.backend.service.mapper.DanhmucptttMapper;
import com.api.backend.service.mapper.MydmptttMapper;

/**
 * Service for executing complex queries for {@link Danhmucpttt} entities in the database.
 * The main input is a {@link DanhmucptttCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanhmucptttDTO} or a {@link Page} of {@link DanhmucptttDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanhmucptttQueryService extends QueryService<Danhmucpttt> {

    private final Logger log = LoggerFactory.getLogger(DanhmucptttQueryService.class);

    private final DanhmucptttRepository danhmucptttRepository;

    private final MydmptttMapper danhmucptttMapper;

    public DanhmucptttQueryService(DanhmucptttRepository danhmucptttRepository, MydmptttMapper danhmucptttMapper) {
        this.danhmucptttRepository = danhmucptttRepository;
        this.danhmucptttMapper = danhmucptttMapper;
    }

    /**
     * Return a {@link List} of {@link DanhmucptttDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanhmucptttDTO> findByCriteria(DanhmucptttCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Danhmucpttt> specification = createSpecification(criteria);
        return danhmucptttMapper.toDto(danhmucptttRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DanhmucptttDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhmucptttDTO> findByCriteria(DanhmucptttCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Danhmucpttt> specification = createSpecification(criteria);
        return danhmucptttRepository.findAll(specification, page)
            .map(danhmucptttMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanhmucptttCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Danhmucpttt> specification = createSpecification(criteria);
        return danhmucptttRepository.count(specification);
    }

    /**
     * Function to convert {@link DanhmucptttCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Danhmucpttt> createSpecification(DanhmucptttCriteria criteria) {
        Specification<Danhmucpttt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Danhmucpttt_.id));
            }
            if (criteria.getLoai() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoai(), Danhmucpttt_.loai));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Danhmucpttt_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Danhmucpttt_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Danhmucpttt_.mota));
            }
            if (criteria.getMaByt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaByt(), Danhmucpttt_.maByt));
            }
            if (criteria.getDanhmucnhomptttId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanhmucnhomptttId(),
                    root -> root.join(Danhmucpttt_.danhmucnhompttt, JoinType.LEFT).get(Danhmucnhompttt_.id)));
            }
        }
        return specification;
    }
}
