package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.CosokhambenhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cosokhambenh} and its DTO {@link CosokhambenhDTO}.
 */
@Mapper(componentModel = "spring", uses = {TinhMapper.class, HuyenMapper.class, XaMapper.class})
public interface CosokhambenhMapper extends EntityMapper<CosokhambenhDTO, Cosokhambenh> {

    @Mapping(source = "tinh.id", target = "tinhId")
    @Mapping(source = "huyen.id", target = "huyenId")
    @Mapping(source = "xa.id", target = "xaId")
    CosokhambenhDTO toDto(Cosokhambenh cosokhambenh);

    @Mapping(source = "tinhId", target = "tinh")
    @Mapping(source = "huyenId", target = "huyen")
    @Mapping(source = "xaId", target = "xa")
    Cosokhambenh toEntity(CosokhambenhDTO cosokhambenhDTO);

    default Cosokhambenh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cosokhambenh cosokhambenh = new Cosokhambenh();
        cosokhambenh.setId(id);
        return cosokhambenh;
    }
}
