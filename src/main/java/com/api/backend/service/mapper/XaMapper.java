package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.XaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Xa} and its DTO {@link XaDTO}.
 */
@Mapper(componentModel = "spring", uses = {HuyenMapper.class})
public interface XaMapper extends EntityMapper<XaDTO, Xa> {

    @Mapping(source = "huyen.id", target = "huyenId")
    XaDTO toDto(Xa xa);

    @Mapping(target = "cosokhambenhs", ignore = true)
    @Mapping(target = "removeCosokhambenh", ignore = true)
    @Mapping(source = "huyenId", target = "huyen")
    Xa toEntity(XaDTO xaDTO);

    default Xa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Xa xa = new Xa();
        xa.setId(id);
        return xa;
    }
}
