package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dmgiaiphaubenh} and its DTO {@link DmgiaiphaubenhDTO}.
 */
@Mapper(componentModel = "spring", uses = {DmnhomgiaiphaubenhMapper.class})
public interface DmgiaiphaubenhMapper extends EntityMapper<DmgiaiphaubenhDTO, Dmgiaiphaubenh> {

    @Mapping(source = "dmnhomgiaiphaubenh.id", target = "dmnhomgiaiphaubenhId")
    DmgiaiphaubenhDTO toDto(Dmgiaiphaubenh dmgiaiphaubenh);

    @Mapping(source = "dmnhomgiaiphaubenhId", target = "dmnhomgiaiphaubenh")
    Dmgiaiphaubenh toEntity(DmgiaiphaubenhDTO dmgiaiphaubenhDTO);

    default Dmgiaiphaubenh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dmgiaiphaubenh dmgiaiphaubenh = new Dmgiaiphaubenh();
        dmgiaiphaubenh.setId(id);
        return dmgiaiphaubenh;
    }
}
