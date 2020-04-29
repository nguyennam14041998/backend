package com.api.backend.service.mapper;

import com.api.backend.domain.Dmbenhly;
import com.api.backend.domain.Dmloaibenhly;
import com.api.backend.domain.Dmnhombenhly;
import com.api.backend.repository.DmloaibenhlyRepository;
import com.api.backend.repository.DmnhombenhlyRepository;
import com.api.backend.service.dto.DmbenhlyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucBenhLyMapper {

    @Autowired private DmnhombenhlyRepository dmnhombenhlyRepository;
    @Autowired private DmloaibenhlyRepository dmloaibenhlyRepository;

    public List<Dmbenhly> toEntity(List<DmbenhlyDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Dmbenhly> list = new ArrayList<Dmbenhly>( dtoList.size() );
        for ( DmbenhlyDTO dmbenhlyDTO : dtoList ) {
            list.add( toEntity( dmbenhlyDTO ) );
        }

        return list;
    }


    public List<DmbenhlyDTO> toDto(List<Dmbenhly> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmbenhlyDTO> list = new ArrayList<DmbenhlyDTO>( entityList.size() );
        for ( Dmbenhly dmbenhly : entityList ) {
            list.add( toDto( dmbenhly ) );
        }

        return list;
    }


    public DmbenhlyDTO toDto(Dmbenhly dmbenhly) {
        if ( dmbenhly == null ) {
            return null;
        }

        DmbenhlyDTO dmbenhlyDTO = new DmbenhlyDTO();

        if(dmbenhly.getDmloaibenhly() != null) {
            dmbenhlyDTO.setDmloaibenhlyId(dmbenhly.getDmloaibenhly().getId());
            dmbenhlyDTO.setTenLoaiBenhLy(dmbenhly.getDmloaibenhly().getTen());
        }

        if(dmbenhly.getDmnhombenhly() != null) {
            dmbenhlyDTO.setDmnhombenhlyId(dmbenhly.getDmnhombenhly().getId());
            dmbenhlyDTO.setTenNhomBenhLy(dmbenhly.getDmnhombenhly().getTen());
        }
        dmbenhlyDTO.setId( dmbenhly.getId() );
        dmbenhlyDTO.setiCD( dmbenhly.getiCD() );
        dmbenhlyDTO.setTenICD10( dmbenhly.getTenICD10() );
        dmbenhlyDTO.setTentienganh( dmbenhly.getTentienganh() );
        dmbenhlyDTO.setTenthuonggoi( dmbenhly.getTenthuonggoi() );
        dmbenhlyDTO.setiCDcha( dmbenhly.getiCDcha() );
        dmbenhlyDTO.setNgayAD( dmbenhly.getNgayAD() );
        dmbenhlyDTO.setTrangthai( dmbenhly.getTrangthai() );

        return dmbenhlyDTO;
    }


    public Dmbenhly toEntity(DmbenhlyDTO dmbenhlyDTO) {
        if ( dmbenhlyDTO == null ) {
            return null;
        }

        Dmbenhly dmbenhly = new Dmbenhly();

        if(dmbenhlyDTO.getDmloaibenhlyId() != null) {
            Dmloaibenhly loaiBenhLy = dmloaibenhlyRepository.getOne(dmbenhlyDTO.getDmloaibenhlyId());
            dmbenhly.setDmloaibenhly( loaiBenhLy );
        }
        if(dmbenhlyDTO.getDmnhombenhlyId() != null) {
            Dmnhombenhly nhomBenhLy = dmnhombenhlyRepository.getOne(dmbenhlyDTO.getDmnhombenhlyId());
            if(nhomBenhLy!= null ){
                dmbenhly.setDmnhombenhly( nhomBenhLy);
            }
        }

        dmbenhly.setId( dmbenhlyDTO.getId() );
        dmbenhly.setiCD( dmbenhlyDTO.getiCD() );
        dmbenhly.setTenICD10( dmbenhlyDTO.getTenICD10() );
        dmbenhly.setTentienganh( dmbenhlyDTO.getTentienganh() );
        dmbenhly.setTenthuonggoi( dmbenhlyDTO.getTenthuonggoi() );
        dmbenhly.setiCDcha( dmbenhlyDTO.getiCDcha() );
        dmbenhly.setNgayAD( dmbenhlyDTO.getNgayAD() );
        dmbenhly.setTrangthai( dmbenhlyDTO.getTrangthai() );

        return dmbenhly;
    }
}
