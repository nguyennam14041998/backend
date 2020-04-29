package com.api.backend.repository;
import com.api.backend.domain.Tinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TinhRepository extends JpaRepository<Tinh, Long>, JpaSpecificationExecutor<Tinh> {

}
