package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmnhombenhlyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmnhombenhly} and its DTO {@link DmnhombenhlyDTO}.
 */
@Mapper(componentModel = "spring", uses = {DmloaibenhlyMapper.class})
public interface DmnhombenhlyMapper extends EntityMapper<DmnhombenhlyDTO, Dmnhombenhly> {

    @Mapping(source = "dmloaibenhly.id", target = "dmloaibenhlyId")
    DmnhombenhlyDTO toDto(Dmnhombenhly dmnhombenhly);

    @Mapping(target = "dmbenhlies", ignore = true)
    @Mapping(target = "removeDmbenhly", ignore = true)
    @Mapping(source = "dmloaibenhlyId", target = "dmloaibenhly")
    Dmnhombenhly toEntity(DmnhombenhlyDTO dmnhombenhlyDTO);

    default Dmnhombenhly fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmnhombenhly dmnhombenhly = new Dmnhombenhly();
        dmnhombenhly.setId(id);
        return dmnhombenhly;
    }
}
