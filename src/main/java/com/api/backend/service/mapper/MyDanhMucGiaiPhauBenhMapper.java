package com.api.backend.service.mapper;

import com.api.backend.domain.Dmgiaiphaubenh;
import com.api.backend.domain.Dmnhomgiaiphaubenh;
import com.api.backend.repository.DmnhomgiaiphaubenhRepository;
import com.api.backend.service.dto.DmgiaiphaubenhDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucGiaiPhauBenhMapper {
    @Autowired private DmnhomgiaiphaubenhRepository repository;

    public List<Dmgiaiphaubenh> toEntity(List<DmgiaiphaubenhDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Dmgiaiphaubenh> list = new ArrayList<Dmgiaiphaubenh>( dtoList.size() );
        for ( DmgiaiphaubenhDTO dmgiaiphaubenhDTO : dtoList ) {
            list.add( toEntity( dmgiaiphaubenhDTO ) );
        }

        return list;
    }


    public List<DmgiaiphaubenhDTO> toDto(List<Dmgiaiphaubenh> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmgiaiphaubenhDTO> list = new ArrayList<DmgiaiphaubenhDTO>( entityList.size() );
        for ( Dmgiaiphaubenh dmgiaiphaubenh : entityList ) {
            list.add( toDto( dmgiaiphaubenh ) );
        }

        return list;
    }


    public DmgiaiphaubenhDTO toDto(Dmgiaiphaubenh dmgiaiphaubenh) {
        if ( dmgiaiphaubenh == null ) {
            return null;
        }

        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = new DmgiaiphaubenhDTO();

        if(dmgiaiphaubenh.getDmnhomgiaiphaubenh()!= null) {
            dmgiaiphaubenhDTO.setDmnhomgiaiphaubenhId(dmgiaiphaubenh.getDmnhomgiaiphaubenh().getId());
            dmgiaiphaubenhDTO.setTenNhom(dmgiaiphaubenh.getDmnhomgiaiphaubenh().getTen());
        }

        dmgiaiphaubenhDTO.setId( dmgiaiphaubenh.getId() );
        dmgiaiphaubenhDTO.setMa( dmgiaiphaubenh.getMa() );
        dmgiaiphaubenhDTO.setTen( dmgiaiphaubenh.getTen() );
        dmgiaiphaubenhDTO.setMota( dmgiaiphaubenh.getMota() );
        dmgiaiphaubenhDTO.setGioitinh( dmgiaiphaubenh.getGioitinh() );
        dmgiaiphaubenhDTO.setMaBYT( dmgiaiphaubenh.getMaBYT() );
        dmgiaiphaubenhDTO.setManhomBH( dmgiaiphaubenh.getManhomBH() );

        return dmgiaiphaubenhDTO;
    }


    public Dmgiaiphaubenh toEntity(DmgiaiphaubenhDTO dmgiaiphaubenhDTO) {
        if ( dmgiaiphaubenhDTO == null ) {
            return null;
        }

        Dmgiaiphaubenh dmgiaiphaubenh = new Dmgiaiphaubenh();

        if(dmgiaiphaubenhDTO.getDmnhomgiaiphaubenhId()!=null) {
            Dmnhomgiaiphaubenh nhom = repository.getOne(dmgiaiphaubenhDTO.getDmnhomgiaiphaubenhId());
            if(nhom != null) {
                dmgiaiphaubenh.setDmnhomgiaiphaubenh( nhom );
            }
        }

        dmgiaiphaubenh.setId( dmgiaiphaubenhDTO.getId() );
        dmgiaiphaubenh.setMa( dmgiaiphaubenhDTO.getMa() );
        dmgiaiphaubenh.setTen( dmgiaiphaubenhDTO.getTen() );
        dmgiaiphaubenh.setMota( dmgiaiphaubenhDTO.getMota() );
        dmgiaiphaubenh.setGioitinh( dmgiaiphaubenhDTO.getGioitinh() );
        dmgiaiphaubenh.setMaBYT( dmgiaiphaubenhDTO.getMaBYT() );
        dmgiaiphaubenh.setManhomBH( dmgiaiphaubenhDTO.getManhomBH() );

        return dmgiaiphaubenh;
    }
}
