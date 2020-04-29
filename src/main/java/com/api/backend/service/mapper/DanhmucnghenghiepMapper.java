package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanhmucnghenghiepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Danhmucnghenghiep} and its DTO {@link DanhmucnghenghiepDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhmucnghenghiepMapper extends EntityMapper<DanhmucnghenghiepDTO, Danhmucnghenghiep> {



    default Danhmucnghenghiep fromId(Long id) {
        if (id == null) {
            return null;
        }
        Danhmucnghenghiep danhmucnghenghiep = new Danhmucnghenghiep();
        danhmucnghenghiep.setId(id);
        return danhmucnghenghiep;
    }
}
