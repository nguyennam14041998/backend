package com.api.backend.repository;
import com.api.backend.domain.Danhmucnhompttt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Danhmucnhompttt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhmucnhomptttRepository extends JpaRepository<Danhmucnhompttt, Long>, JpaSpecificationExecutor<Danhmucnhompttt> {

}
