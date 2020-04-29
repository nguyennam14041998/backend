package com.api.backend.repository;
import com.api.backend.domain.DmnhomCDHA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DmnhomCDHA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmnhomCDHARepository extends JpaRepository<DmnhomCDHA, Long>, JpaSpecificationExecutor<DmnhomCDHA> {

}
