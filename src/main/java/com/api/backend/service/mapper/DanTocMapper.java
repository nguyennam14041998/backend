package com.api.backend.service.mapper;

import com.api.backend.domain.*;
import com.api.backend.service.dto.DanTocDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DanToc} and its DTO {@link DanTocDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanTocMapper extends EntityMapper<DanTocDTO, DanToc> {



    default DanToc fromId(Long id) {
        if (id == null) {
            return null;
        }
        DanToc danToc = new DanToc();
        danToc.setId(id);
        return danToc;
    }
}
