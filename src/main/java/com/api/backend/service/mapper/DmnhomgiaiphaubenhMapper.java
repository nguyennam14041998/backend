package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmnhomgiaiphaubenhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmnhomgiaiphaubenh} and its DTO {@link DmnhomgiaiphaubenhDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmnhomgiaiphaubenhMapper extends EntityMapper<DmnhomgiaiphaubenhDTO, Dmnhomgiaiphaubenh> {


    @Mapping(target = "dmgiaiphaubenhs", ignore = true)
    @Mapping(target = "removeDmgiaiphaubenh", ignore = true)
    Dmnhomgiaiphaubenh toEntity(DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO);

    default Dmnhomgiaiphaubenh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh = new Dmnhomgiaiphaubenh();
        dmnhomgiaiphaubenh.setId(id);
        return dmnhomgiaiphaubenh;
    }
}
