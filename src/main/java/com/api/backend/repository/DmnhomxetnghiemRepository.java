package com.api.backend.repository;
import com.api.backend.domain.Dmnhomxetnghiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmnhomxetnghiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmnhomxetnghiemRepository extends JpaRepository<Dmnhomxetnghiem, Long>, JpaSpecificationExecutor<Dmnhomxetnghiem> {

}
