package com.api.backend.repository;
import com.api.backend.domain.DmnhomTDCN;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DmnhomTDCN entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmnhomTDCNRepository extends JpaRepository<DmnhomTDCN, Long>, JpaSpecificationExecutor<DmnhomTDCN> {

}
