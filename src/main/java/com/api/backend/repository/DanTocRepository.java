package com.api.backend.repository;
import com.api.backend.domain.DanToc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DanToc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanTocRepository extends JpaRepository<DanToc, Long>, JpaSpecificationExecutor<DanToc> {

}
