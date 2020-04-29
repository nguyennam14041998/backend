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

import com.api.backend.domain.Dmgiaiphaubenh;
import com.api.backend.domain.*; // for static metamodels
import com.api.backend.repository.DmgiaiphaubenhRepository;
import com.api.backend.service.dto.DmgiaiphaubenhCriteria;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;
import com.api.backend.service.mapper.DmgiaiphaubenhMapper;

/**
 * Service for executing complex queries for {@link Dmgiaiphaubenh} entities in the database.
 * The main input is a {@link DmgiaiphaubenhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DmgiaiphaubenhDTO} or a {@link Page} of {@link DmgiaiphaubenhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DmgiaiphaubenhQueryService extends QueryService<Dmgiaiphaubenh> {

    private final Logger log = LoggerFactory.getLogger(DmgiaiphaubenhQueryService.class);

    private final DmgiaiphaubenhRepository dmgiaiphaubenhRepository;

    private final DmgiaiphaubenhMapper dmgiaiphaubenhMapper;

    public DmgiaiphaubenhQueryService(DmgiaiphaubenhRepository dmgiaiphaubenhRepository, DmgiaiphaubenhMapper dmgiaiphaubenhMapper) {
        this.dmgiaiphaubenhRepository = dmgiaiphaubenhRepository;
        this.dmgiaiphaubenhMapper = dmgiaiphaubenhMapper;
    }

    /**
     * Return a {@link List} of {@link DmgiaiphaubenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DmgiaiphaubenhDTO> findByCriteria(DmgiaiphaubenhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dmgiaiphaubenh> specification = createSpecification(criteria);
        return dmgiaiphaubenhMapper.toDto(dmgiaiphaubenhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DmgiaiphaubenhDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DmgiaiphaubenhDTO> findByCriteria(DmgiaiphaubenhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dmgiaiphaubenh> specification = createSpecification(criteria);
        return dmgiaiphaubenhRepository.findAll(specification, page)
            .map(dmgiaiphaubenhMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DmgiaiphaubenhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dmgiaiphaubenh> specification = createSpecification(criteria);
        return dmgiaiphaubenhRepository.count(specification);
    }

    /**
     * Function to convert {@link DmgiaiphaubenhCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dmgiaiphaubenh> createSpecification(DmgiaiphaubenhCriteria criteria) {
        Specification<Dmgiaiphaubenh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dmgiaiphaubenh_.id));
            }
            if (criteria.getMa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMa(), Dmgiaiphaubenh_.ma));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), Dmgiaiphaubenh_.ten));
            }
            if (criteria.getMota() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMota(), Dmgiaiphaubenh_.mota));
            }
            if (criteria.getGioitinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGioitinh(), Dmgiaiphaubenh_.gioitinh));
            }
            if (criteria.getMaBYT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBYT(), Dmgiaiphaubenh_.maBYT));
            }
            if (criteria.getManhomBH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManhomBH(), Dmgiaiphaubenh_.manhomBH));
            }
            if (criteria.getDmnhomgiaiphaubenhId() != null) {
                specification = specification.and(buildSpecification(criteria.getDmnhomgiaiphaubenhId(),
                    root -> root.join(Dmgiaiphaubenh_.dmnhomgiaiphaubenh, JoinType.LEFT).get(Dmnhomgiaiphaubenh_.id)));
            }
        }
        return specification;
    }
}
