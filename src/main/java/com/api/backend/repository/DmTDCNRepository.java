package com.api.backend.repository;
import com.api.backend.domain.DmTDCN;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DmTDCN entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmTDCNRepository extends JpaRepository<DmTDCN, Long>, JpaSpecificationExecutor<DmTDCN> {

}
