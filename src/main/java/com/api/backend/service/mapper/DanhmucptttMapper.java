package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanhmucptttDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Danhmucpttt} and its DTO {@link DanhmucptttDTO}.
 */
@Mapper(componentModel = "spring", uses = {DanhmucnhomptttMapper.class})
public interface DanhmucptttMapper extends EntityMapper<DanhmucptttDTO, Danhmucpttt> {

    @Mapping(source = "danhmucnhompttt.id", target = "danhmucnhomptttId")
    DanhmucptttDTO toDto(Danhmucpttt danhmucpttt);

    @Mapping(source = "danhmucnhomptttId", target = "danhmucnhompttt")
    Danhmucpttt toEntity(DanhmucptttDTO danhmucptttDTO);

    default Danhmucpttt fromId(Long id) {
        if (id == null) {
            return null;
        }
        Danhmucpttt danhmucpttt = new Danhmucpttt();
        danhmucpttt.setId(id);
        return danhmucpttt;
    }
}
