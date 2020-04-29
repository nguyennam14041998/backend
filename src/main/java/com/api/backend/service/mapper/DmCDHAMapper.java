package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmCDHADTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DmCDHA} and its DTO {@link DmCDHADTO}.
 */
@Mapper(componentModel = "spring", uses = {DmnhomCDHAMapper.class})
public interface DmCDHAMapper extends EntityMapper<DmCDHADTO, DmCDHA> {

    @Mapping(source = "dmnhomCDHA.id", target = "dmnhomCDHAId")
    DmCDHADTO toDto(DmCDHA dmCDHA);

    @Mapping(source = "dmnhomCDHAId", target = "dmnhomCDHA")
    DmCDHA toEntity(DmCDHADTO dmCDHADTO);

    default DmCDHA fromId(Long id) {
        if (id == null) {
            return null;
        }
        DmCDHA dmCDHA = new DmCDHA();
        dmCDHA.setId(id);
        return dmCDHA;
    }
}
