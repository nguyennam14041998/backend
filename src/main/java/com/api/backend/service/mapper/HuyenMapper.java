package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.HuyenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Huyen} and its DTO {@link HuyenDTO}.
 */
@Mapper(componentModel = "spring", uses = {TinhMapper.class})
public interface HuyenMapper extends EntityMapper<HuyenDTO, Huyen> {

    @Mapping(source = "tinh.id", target = "tinhId")
    HuyenDTO toDto(Huyen huyen);

    @Mapping(target = "xas", ignore = true)
    @Mapping(target = "removeXa", ignore = true)
    @Mapping(target = "cosokhambenhs", ignore = true)
    @Mapping(target = "removeCosokhambenh", ignore = true)
    @Mapping(source = "tinhId", target = "tinh")
    Huyen toEntity(HuyenDTO huyenDTO);

    default Huyen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Huyen huyen = new Huyen();
        huyen.setId(id);
        return huyen;
    }
}
