package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmnhomxetnghiemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmnhomxetnghiem} and its DTO {@link DmnhomxetnghiemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmnhomxetnghiemMapper extends EntityMapper<DmnhomxetnghiemDTO, Dmnhomxetnghiem> {


    @Mapping(target = "dmxetnghiems", ignore = true)
    @Mapping(target = "removeDmxetnghiem", ignore = true)
    Dmnhomxetnghiem toEntity(DmnhomxetnghiemDTO dmnhomxetnghiemDTO);

    default Dmnhomxetnghiem fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmnhomxetnghiem dmnhomxetnghiem = new Dmnhomxetnghiem();
        dmnhomxetnghiem.setId(id);
        return dmnhomxetnghiem;
    }
}
