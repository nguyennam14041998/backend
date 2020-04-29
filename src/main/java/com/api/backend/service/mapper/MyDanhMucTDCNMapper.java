package com.api.backend.service.mapper;

import com.api.backend.domain.DmTDCN;
import com.api.backend.domain.DmnhomTDCN;
import com.api.backend.repository.DmnhomTDCNRepository;
import com.api.backend.service.dto.DmTDCNDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucTDCNMapper {
    @Autowired private DmnhomTDCNRepository repository;

    public List<DmTDCN> toEntity(List<DmTDCNDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DmTDCN> list = new ArrayList<DmTDCN>( dtoList.size() );
        for ( DmTDCNDTO dmTDCNDTO : dtoList ) {
            list.add( toEntity( dmTDCNDTO ) );
        }

        return list;
    }


    public List<DmTDCNDTO> toDto(List<DmTDCN> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmTDCNDTO> list = new ArrayList<DmTDCNDTO>( entityList.size() );
        for ( DmTDCN dmTDCN : entityList ) {
            list.add( toDto( dmTDCN ) );
        }

        return list;
    }


    public DmTDCNDTO toDto(DmTDCN dmTDCN) {
        if ( dmTDCN == null ) {
            return null;
        }

        DmTDCNDTO dmTDCNDTO = new DmTDCNDTO();

        if(dmTDCN.getDmnhomTDCN() != null) {
            dmTDCNDTO.setDmnhomTDCNId(dmTDCN.getDmnhomTDCN().getId());
            dmTDCNDTO.setTenNhom(dmTDCN.getDmnhomTDCN().getTen());
        }

        dmTDCNDTO.setId( dmTDCN.getId() );
        dmTDCNDTO.setMa( dmTDCN.getMa() );
        dmTDCNDTO.setTen( dmTDCN.getTen() );
        dmTDCNDTO.setMota( dmTDCN.getMota() );
        dmTDCNDTO.setGioitinh( dmTDCN.getGioitinh() );
        dmTDCNDTO.setMaBYT( dmTDCN.getMaBYT() );
        dmTDCNDTO.setManhomBH( dmTDCN.getManhomBH() );

        return dmTDCNDTO;
    }


    public DmTDCN toEntity(DmTDCNDTO dmTDCNDTO) {
        if ( dmTDCNDTO == null ) {
            return null;
        }

        DmTDCN dmTDCN = new DmTDCN();

        if(dmTDCNDTO.getDmnhomTDCNId() != null) {
            DmnhomTDCN dmnhomTDCN = repository.getOne(dmTDCNDTO.getDmnhomTDCNId());
            if(dmnhomTDCN != null) {
                dmTDCN.setDmnhomTDCN(dmnhomTDCN);
            }

        }
        dmTDCN.setId( dmTDCNDTO.getId() );
        dmTDCN.setMa( dmTDCNDTO.getMa() );
        dmTDCN.setTen( dmTDCNDTO.getTen() );
        dmTDCN.setMota( dmTDCNDTO.getMota() );
        dmTDCN.setGioitinh( dmTDCNDTO.getGioitinh() );
        dmTDCN.setMaBYT( dmTDCNDTO.getMaBYT() );
        dmTDCN.setManhomBH( dmTDCNDTO.getManhomBH() );

        return dmTDCN;
    }
}
