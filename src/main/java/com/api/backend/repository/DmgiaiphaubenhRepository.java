package com.api.backend.repository;
import com.api.backend.domain.Dmgiaiphaubenh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmgiaiphaubenh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmgiaiphaubenhRepository extends JpaRepository<Dmgiaiphaubenh, Long>, JpaSpecificationExecutor<Dmgiaiphaubenh> {

}
