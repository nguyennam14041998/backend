package com.api.backend.service.mapper;

import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.repository.DmloaibenhlyRepository;
import com.api.backend.service.dto.DmnhombenhlyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucNhomBenhLyMapper {
    @Autowired private DmloaibenhlyRepository repository;


    public List<Dmnhombenhly> toEntity(List<DmnhombenhlyDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Dmnhombenhly> list = new ArrayList<Dmnhombenhly>( dtoList.size() );
        for ( DmnhombenhlyDTO dmnhombenhlyDTO : dtoList ) {
            list.add( toEntity( dmnhombenhlyDTO ) );
        }

        return list;
    }


    public List<DmnhombenhlyDTO> toDto(List<Dmnhombenhly> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmnhombenhlyDTO> list = new ArrayList<DmnhombenhlyDTO>( entityList.size() );
        for ( Dmnhombenhly dmnhombenhly : entityList ) {
            list.add( toDto( dmnhombenhly ) );
        }

        return list;
    }


    public DmnhombenhlyDTO toDto(Dmnhombenhly dmnhombenhly) {
        if ( dmnhombenhly == null ) {
            return null;
        }

        DmnhombenhlyDTO dmnhombenhlyDTO = new DmnhombenhlyDTO();

        if(dmnhombenhly.getDmloaibenhly()!= null) {
            dmnhombenhlyDTO.setDmloaibenhlyId(dmnhombenhly.getDmloaibenhly().getId());
            dmnhombenhlyDTO.setTenBenhLy(dmnhombenhly.getDmloaibenhly().getTen());
        }
        dmnhombenhlyDTO.setId( dmnhombenhly.getId() );
        dmnhombenhlyDTO.setMa( dmnhombenhly.getMa() );
        dmnhombenhlyDTO.setTen( dmnhombenhly.getTen() );
        dmnhombenhlyDTO.setMota( dmnhombenhly.getMota() );

        return dmnhombenhlyDTO;
    }


    public Dmnhombenhly toEntity(DmnhombenhlyDTO dmnhombenhlyDTO) {
        if ( dmnhombenhlyDTO == null ) {
            return null;
        }

        Dmnhombenhly dmnhombenhly = new Dmnhombenhly();
        if(dmnhombenhlyDTO.getDmloaibenhlyId()!=null) {
            Dmloaibenhly benhly = repository.getOne(dmnhombenhlyDTO.getDmloaibenhlyId());
            if(benhly != null) {
                dmnhombenhly.setDmloaibenhly( benhly );
            }
        }

        dmnhombenhly.setId( dmnhombenhlyDTO.getId() );
        dmnhombenhly.setMa( dmnhombenhlyDTO.getMa() );
        dmnhombenhly.setTen( dmnhombenhlyDTO.getTen() );
        dmnhombenhly.setMota( dmnhombenhlyDTO.getMota() );

        return dmnhombenhly;
    }

}
