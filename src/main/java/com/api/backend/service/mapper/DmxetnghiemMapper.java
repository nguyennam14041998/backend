package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmxetnghiemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmxetnghiem} and its DTO {@link DmxetnghiemDTO}.
 */
@Mapper(componentModel = "spring", uses = {DmnhomxetnghiemMapper.class})
public interface DmxetnghiemMapper extends EntityMapper<DmxetnghiemDTO, Dmxetnghiem> {

    @Mapping(source = "dmnhomxetnghiem.id", target = "dmnhomxetnghiemId")
    DmxetnghiemDTO toDto(Dmxetnghiem dmxetnghiem);

    @Mapping(source = "dmnhomxetnghiemId", target = "dmnhomxetnghiem")
    Dmxetnghiem toEntity(DmxetnghiemDTO dmxetnghiemDTO);

    default Dmxetnghiem fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmxetnghiem dmxetnghiem = new Dmxetnghiem();
        dmxetnghiem.setId(id);
        return dmxetnghiem;
    }
}
