package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanhmuchanhchinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Danhmuchanhchinh} and its DTO {@link DanhmuchanhchinhDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhmuchanhchinhMapper extends EntityMapper<DanhmuchanhchinhDTO, Danhmuchanhchinh> {



    default Danhmuchanhchinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Danhmuchanhchinh danhmuchanhchinh = new Danhmuchanhchinh();
        danhmuchanhchinh.setId(id);
        return danhmuchanhchinh;
    }
}
