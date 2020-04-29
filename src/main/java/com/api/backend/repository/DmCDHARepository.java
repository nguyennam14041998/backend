package com.api.backend.repository;
import com.api.backend.domain.DmCDHA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DmCDHA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmCDHARepository extends JpaRepository<DmCDHA, Long>, JpaSpecificationExecutor<DmCDHA> {

}
