package com.api.backend.repository;
import com.api.backend.domain.Dmxetnghiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmxetnghiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmxetnghiemRepository extends JpaRepository<Dmxetnghiem, Long>, JpaSpecificationExecutor<Dmxetnghiem> {

}
