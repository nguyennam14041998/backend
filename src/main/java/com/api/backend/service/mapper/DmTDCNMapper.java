package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmTDCNDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DmTDCN} and its DTO {@link DmTDCNDTO}.
 */
@Mapper(componentModel = "spring", uses = {DmnhomTDCNMapper.class})
public interface DmTDCNMapper extends EntityMapper<DmTDCNDTO, DmTDCN> {

    @Mapping(source = "dmnhomTDCN.id", target = "dmnhomTDCNId")
    DmTDCNDTO toDto(DmTDCN dmTDCN);

    @Mapping(source = "dmnhomTDCNId", target = "dmnhomTDCN")
    DmTDCN toEntity(DmTDCNDTO dmTDCNDTO);

    default DmTDCN fromId(Long id) {
        if (id == null) {
            return null;
        }
        DmTDCN dmTDCN = new DmTDCN();
        dmTDCN.setId(id);
        return dmTDCN;
    }
}
