package com.api.backend.repository;
import com.api.backend.domain.Huyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Huyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HuyenRepository extends JpaRepository<Huyen, Long>, JpaSpecificationExecutor<Huyen> {

}
