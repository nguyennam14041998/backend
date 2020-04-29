package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmnhomTDCNDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DmnhomTDCN} and its DTO {@link DmnhomTDCNDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmnhomTDCNMapper extends EntityMapper<DmnhomTDCNDTO, DmnhomTDCN> {


    @Mapping(target = "dmTDCNS", ignore = true)
    @Mapping(target = "removeDmTDCN", ignore = true)
    DmnhomTDCN toEntity(DmnhomTDCNDTO dmnhomTDCNDTO);

    default DmnhomTDCN fromId(Long id) {
        if (id == null) {
            return null;
        }
        DmnhomTDCN dmnhomTDCN = new DmnhomTDCN();
        dmnhomTDCN.setId(id);
        return dmnhomTDCN;
    }
}
