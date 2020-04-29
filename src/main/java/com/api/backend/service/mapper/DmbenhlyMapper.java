package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmbenhlyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmbenhly} and its DTO {@link DmbenhlyDTO}.
 */
@Mapper(componentModel = "spring", uses = {DmloaibenhlyMapper.class, DmnhombenhlyMapper.class})
public interface DmbenhlyMapper extends EntityMapper<DmbenhlyDTO, Dmbenhly> {

    @Mapping(source = "dmloaibenhly.id", target = "dmloaibenhlyId")
    @Mapping(source = "dmnhombenhly.id", target = "dmnhombenhlyId")
    DmbenhlyDTO toDto(Dmbenhly dmbenhly);

    @Mapping(source = "dmloaibenhlyId", target = "dmloaibenhly")
    @Mapping(source = "dmnhombenhlyId", target = "dmnhombenhly")
    Dmbenhly toEntity(DmbenhlyDTO dmbenhlyDTO);

    default Dmbenhly fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmbenhly dmbenhly = new Dmbenhly();
        dmbenhly.setId(id);
        return dmbenhly;
    }
}
