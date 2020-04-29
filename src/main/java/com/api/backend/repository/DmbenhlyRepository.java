package com.api.backend.repository;
import com.api.backend.domain.Dmbenhly;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmbenhly entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmbenhlyRepository extends JpaRepository<Dmbenhly, Long>, JpaSpecificationExecutor<Dmbenhly> {

}
