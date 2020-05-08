package com.api.backend.repository;
import com.api.backend.domain.Danhmuchanhchinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Danhmuchanhchinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhmuchanhchinhRepository extends JpaRepository<Danhmuchanhchinh, Long>, JpaSpecificationExecutor<Danhmuchanhchinh> {

}
