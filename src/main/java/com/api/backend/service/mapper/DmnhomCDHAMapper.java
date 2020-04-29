package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmnhomCDHADTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DmnhomCDHA} and its DTO {@link DmnhomCDHADTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmnhomCDHAMapper extends EntityMapper<DmnhomCDHADTO, DmnhomCDHA> {


    @Mapping(target = "dmCDHAS", ignore = true)
    @Mapping(target = "removeDmCDHA", ignore = true)
    DmnhomCDHA toEntity(DmnhomCDHADTO dmnhomCDHADTO);

    default DmnhomCDHA fromId(Long id) {
        if (id == null) {
            return null;
        }
        DmnhomCDHA dmnhomCDHA = new DmnhomCDHA();
        dmnhomCDHA.setId(id);
        return dmnhomCDHA;
    }
}
