package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanhmucnhomptttDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Danhmucnhompttt} and its DTO {@link DanhmucnhomptttDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhmucnhomptttMapper extends EntityMapper<DanhmucnhomptttDTO, Danhmucnhompttt> {


    @Mapping(target = "danhmucpttts", ignore = true)
    @Mapping(target = "removeDanhmucpttt", ignore = true)
    Danhmucnhompttt toEntity(DanhmucnhomptttDTO danhmucnhomptttDTO);

    default Danhmucnhompttt fromId(Long id) {
        if (id == null) {
            return null;
        }
        Danhmucnhompttt danhmucnhompttt = new Danhmucnhompttt();
        danhmucnhompttt.setId(id);
        return danhmucnhompttt;
    }
}
