package com.api.backend.repository;
import com.api.backend.domain.Danhmucpttt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Danhmucpttt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhmucptttRepository extends JpaRepository<Danhmucpttt, Long>, JpaSpecificationExecutor<Danhmucpttt> {

}
