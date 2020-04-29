package com.api.backend.service.mapper;

import com.api.backend.domain.DmCDHA;
import com.api.backend.domain.DmnhomCDHA;
import com.api.backend.repository.DmnhomCDHARepository;
import com.api.backend.service.dto.DmCDHADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucCHDAMapper {

    @Autowired private DmnhomCDHARepository repository;

    public List<DmCDHA> toEntity(List<DmCDHADTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DmCDHA> list = new ArrayList<DmCDHA>( dtoList.size() );
        for ( DmCDHADTO dmCDHADTO : dtoList ) {
            list.add( toEntity( dmCDHADTO ) );
        }

        return list;
    }


    public List<DmCDHADTO> toDto(List<DmCDHA> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmCDHADTO> list = new ArrayList<DmCDHADTO>( entityList.size() );
        for ( DmCDHA dmCDHA : entityList ) {
            list.add( toDto( dmCDHA ) );
        }

        return list;
    }


    public DmCDHADTO toDto(DmCDHA dmCDHA) {
        if ( dmCDHA == null ) {
            return null;
        }

        DmCDHADTO dmCDHADTO = new DmCDHADTO();

        if(dmCDHA.getDmnhomCDHA() != null) {
            dmCDHADTO.setDmnhomCDHAId(dmCDHA.getDmnhomCDHA().getId());
            dmCDHADTO.setTenNhom(dmCDHA.getDmnhomCDHA().getTen());
        }

        dmCDHADTO.setId( dmCDHA.getId() );
        dmCDHADTO.setMa( dmCDHA.getMa() );
        dmCDHADTO.setTen( dmCDHA.getTen() );
        dmCDHADTO.setMota( dmCDHA.getMota() );
        dmCDHADTO.setGioitinh( dmCDHA.getGioitinh() );
        dmCDHADTO.setMaBYT( dmCDHA.getMaBYT() );
        dmCDHADTO.setManhomBH( dmCDHA.getManhomBH() );

        return dmCDHADTO;
    }


    public DmCDHA toEntity(DmCDHADTO dmCDHADTO) {
        if ( dmCDHADTO == null ) {
            return null;
        }

        DmCDHA dmCDHA = new DmCDHA();

        if(dmCDHADTO.getDmnhomCDHAId() != null) {
            DmnhomCDHA dmnhomCDHA = repository.getOne(dmCDHADTO.getDmnhomCDHAId());
            if(dmnhomCDHA!= null) {
                dmCDHA.setDmnhomCDHA(dmnhomCDHA);
            }
        }

        dmCDHA.setId( dmCDHADTO.getId() );
        dmCDHA.setMa( dmCDHADTO.getMa() );
        dmCDHA.setTen( dmCDHADTO.getTen() );
        dmCDHA.setMota( dmCDHADTO.getMota() );
        dmCDHA.setGioitinh( dmCDHADTO.getGioitinh() );
        dmCDHA.setMaBYT( dmCDHADTO.getMaBYT() );
        dmCDHA.setManhomBH( dmCDHADTO.getManhomBH() );

        return dmCDHA;
    }
}
