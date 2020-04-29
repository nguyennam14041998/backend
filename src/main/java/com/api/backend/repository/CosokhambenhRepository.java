package com.api.backend.repository;
import com.api.backend.domain.Cosokhambenh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Cosokhambenh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CosokhambenhRepository extends JpaRepository<Cosokhambenh, Long>, JpaSpecificationExecutor<Cosokhambenh> {

}
