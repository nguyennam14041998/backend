package com.api.backend.repository;
import com.api.backend.domain.Dmloaibenhly;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmloaibenhly entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmloaibenhlyRepository extends JpaRepository<Dmloaibenhly, Long>, JpaSpecificationExecutor<Dmloaibenhly> {

}
