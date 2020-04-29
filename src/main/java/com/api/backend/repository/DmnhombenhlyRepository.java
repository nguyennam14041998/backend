package com.api.backend.repository;
import com.api.backend.domain.Dmnhombenhly;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmnhombenhly entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmnhombenhlyRepository extends JpaRepository<Dmnhombenhly, Long>, JpaSpecificationExecutor<Dmnhombenhly> {

}
