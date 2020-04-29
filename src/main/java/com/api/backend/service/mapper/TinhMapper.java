package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.TinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tinh} and its DTO {@link TinhDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TinhMapper extends EntityMapper<TinhDTO, Tinh> {


    @Mapping(target = "huyens", ignore = true)
    @Mapping(target = "removeHuyen", ignore = true)
    @Mapping(target = "cosokhambenhs", ignore = true)
    @Mapping(target = "removeCosokhambenh", ignore = true)
    Tinh toEntity(TinhDTO tinhDTO);

    default Tinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tinh tinh = new Tinh();
        tinh.setId(id);
        return tinh;
    }
}
