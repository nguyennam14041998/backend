package com.api.backend.repository;
import com.api.backend.domain.Dmnhomgiaiphaubenh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dmnhomgiaiphaubenh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmnhomgiaiphaubenhRepository extends JpaRepository<Dmnhomgiaiphaubenh, Long>, JpaSpecificationExecutor<Dmnhomgiaiphaubenh> {

}
