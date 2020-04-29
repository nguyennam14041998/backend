package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmloaibenhlyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmloaibenhly} and its DTO {@link DmloaibenhlyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmloaibenhlyMapper extends EntityMapper<DmloaibenhlyDTO, Dmloaibenhly> {


    @Mapping(target = "dmnhombenhlies", ignore = true)
    @Mapping(target = "removeDmnhombenhly", ignore = true)
    @Mapping(target = "dmbenhlies", ignore = true)
    @Mapping(target = "removeDmbenhly", ignore = true)
    Dmloaibenhly toEntity(DmloaibenhlyDTO dmloaibenhlyDTO);

    default Dmloaibenhly fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmloaibenhly dmloaibenhly = new Dmloaibenhly();
        dmloaibenhly.setId(id);
        return dmloaibenhly;
    }
}
