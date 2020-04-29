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

import com.api.backend.domain.DmCDHA;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmCDHARepository;
import com.api.backend.service.dto.DmCDHACriteria;
import com.api.backend.service.dto.DmCDHADTO;
import com.api.backend.service.mapper.DmCDHAMapper;

/**
 * Service for executing complex queries for {@link DmCDHA} entities in the database.
 * The main input is a {@link DmCDHACriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmCDHADTO} or a {@link Page} of {@link DmCDHADTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmCDHAQueryService extends QueryService<DmCDHA> {

    private final Logger log = LoggerFactory.getLogger(DmCDHAQueryService.class);

    private final DmCDHARepository dmCDHARepository;

    private final DmCDHAMapper dmCDHAMapper;

    public DmCDHAQueryService(DmCDHARepository dmCDHARepository, DmCDHAMapper dmCDHAMapper) {
        this.dmCDHARepository = dmCDHARepository;
        this.dmCDHAMapper = dmCDHAMapper;
    }

    /**
     * Return a {@link List} of {@link DmCDHADTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmCDHADTO> findByCriteria(DmCDHACriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DmCDHA> specification = createSpecification(criteria);
        return dmCDHAMapper.toDto(dmCDHARepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmCDHADTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmCDHADTO> findByCriteria(DmCDHACriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DmCDHA> specification = createSpecification(criteria);
        return dmCDHARepository.findAll(specification, page)
            .map(dmCDHAMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmCDHACriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DmCDHA> specification = createSpecification(criteria);
        return dmCDHARepository.count(specification);
    }

    /**
     * Function to convert {@link DmCDHACriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DmCDHA> createSpecification(DmCDHACriteria criteria) {
        Specification<DmCDHA> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DmCDHA_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), DmCDHA_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), DmCDHA_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), DmCDHA_.mota));
            }
            if (criteria.getGioitinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGioitinh(), DmCDHA_.gioitinh));
            }
            if (criteria.getMaBYT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBYT(), DmCDHA_.maBYT));
            }
            if (criteria.getManhomBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManhomBH(), DmCDHA_.manhomBH));
            }
            if (criteria.getDmnhomCDHAId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhomCDHAId(),
                    root -> root.join(DmCDHA_.dmnhomCDHA, JoinType.LEFT).get(DmnhomCDHA_.id)));
            }
        }
        return specification;
    }
}
