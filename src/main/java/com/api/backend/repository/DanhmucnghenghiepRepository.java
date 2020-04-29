package com.api.backend.repository;
import com.api.backend.domain.Danhmucnghenghiep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Danhmucnghenghiep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhmucnghenghiepRepository extends JpaRepository<Danhmucnghenghiep, Long>, JpaSpecificationExecutor<Danhmucnghenghiep> {

}
