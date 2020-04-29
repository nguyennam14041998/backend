package com.api.backend.repository;
import com.api.backend.domain.Xa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Xa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface XaRepository extends JpaRepository<Xa, Long>, JpaSpecificationExecutor<Xa> {

}
