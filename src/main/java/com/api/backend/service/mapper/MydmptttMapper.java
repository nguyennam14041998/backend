package com.api.backend.service.mapper;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.backend.domain.*;
import com.api.backend.repository.DanhmucnhomptttRepository;
import com.api.backend.service.dto.DanhmucptttDTO;

@Component
public class MydmptttMapper {
   @Autowired private DanhmucnhomptttRepository dmRepository;
    public List<Danhmucpttt> toEntity(List<DanhmucptttDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Danhmucpttt> list = new ArrayList<Danhmucpttt>( dtoList.size() );
        for ( DanhmucptttDTO danhmucptttDTO : dtoList ) {
            list.add( toEntity( danhmucptttDTO ) );
        }

        return list;
    }


    public List<DanhmucptttDTO> toDto(List<Danhmucpttt> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DanhmucptttDTO> list = new ArrayList<DanhmucptttDTO>( entityList.size() );
        for ( Danhmucpttt danhmucpttt : entityList ) {
            list.add( toDto( danhmucpttt ) );
        }

        return list;
    }


    public DanhmucptttDTO toDto(Danhmucpttt danhmucpttt) {
        if ( danhmucpttt == null ) {
            return null;
        }

        DanhmucptttDTO danhmucptttDTO = new DanhmucptttDTO();

        danhmucptttDTO.setId( danhmucpttt.getId() );
        danhmucptttDTO.setLoai( danhmucpttt.getLoai() );
        danhmucptttDTO.setMa( danhmucpttt.getMa() );
        danhmucptttDTO.setTen( danhmucpttt.getTen() );
        danhmucptttDTO.setMota( danhmucpttt.getMota() );
        danhmucptttDTO.setMaByt( danhmucpttt.getMaByt() );
        if(danhmucpttt.getDanhmucnhompttt() != null) {
            danhmucptttDTO.setDanhmucnhomptttId(danhmucpttt.getDanhmucnhompttt().getId());
            danhmucptttDTO.setTennhom(danhmucpttt.getDanhmucnhompttt().getTen());
        }
        return danhmucptttDTO;
    }


    public Danhmucpttt toEntity(DanhmucptttDTO danhmucptttDTO) {
        if ( danhmucptttDTO == null ) {
            return null;
        }

        Danhmucpttt danhmucpttt = new Danhmucpttt();

        if(danhmucptttDTO.getDanhmucnhomptttId() != null) {
            danhmucpttt.setDanhmucnhompttt(dmRepository.findById(danhmucptttDTO.getDanhmucnhomptttId()).get());
        }
        danhmucpttt.setId( danhmucptttDTO.getId() );
        danhmucpttt.setLoai( danhmucptttDTO.getLoai() );
        danhmucpttt.setMa( danhmucptttDTO.getMa() );
        danhmucpttt.setTen( danhmucptttDTO.getTen() );
        danhmucpttt.setMota( danhmucptttDTO.getMota() );
        danhmucpttt.setMaByt( danhmucptttDTO.getMaByt() );

        return danhmucpttt;
    }
}
