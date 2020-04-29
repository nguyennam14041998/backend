package com.api.backend.service.mapper;

import com.api.backend.domain.Dmnhomxetnghiem;
import com.api.backend.domain.Dmxetnghiem;
import com.api.backend.repository.DmnhomxetnghiemRepository;
import com.api.backend.service.dto.DmxetnghiemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyDanhMucXetNghiemMapper {

    @Autowired private DmnhomxetnghiemRepository repository;

    public List<Dmxetnghiem> toEntity(List<DmxetnghiemDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Dmxetnghiem> list = new ArrayList<Dmxetnghiem>( dtoList.size() );
        for ( DmxetnghiemDTO dmxetnghiemDTO : dtoList ) {
            list.add( toEntity( dmxetnghiemDTO ) );
        }

        return list;
    }


    public List<DmxetnghiemDTO> toDto(List<Dmxetnghiem> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DmxetnghiemDTO> list = new ArrayList<DmxetnghiemDTO>( entityList.size() );
        for ( Dmxetnghiem dmxetnghiem : entityList ) {
            list.add( toDto( dmxetnghiem ) );
        }

        return list;
    }


    public DmxetnghiemDTO toDto(Dmxetnghiem dmxetnghiem) {
        if ( dmxetnghiem == null ) {
            return null;
        }

        DmxetnghiemDTO dmxetnghiemDTO = new DmxetnghiemDTO();

        if(dmxetnghiem.getDmnhomxetnghiem() != null) {
            dmxetnghiemDTO.setDmnhomxetnghiemId( dmxetnghiem.getDmnhomxetnghiem().getId());
            dmxetnghiemDTO.setTenNhom(dmxetnghiem.getDmnhomxetnghiem().getTen());
        }



        dmxetnghiemDTO.setId( dmxetnghiem.getId() );
        dmxetnghiemDTO.setMa( dmxetnghiem.getMa() );
        dmxetnghiemDTO.setTen( dmxetnghiem.getTen() );
        dmxetnghiemDTO.setCha( dmxetnghiem.getCha() );
        dmxetnghiemDTO.setMota( dmxetnghiem.getMota() );
        dmxetnghiemDTO.setGioitinh( dmxetnghiem.getGioitinh() );
        dmxetnghiemDTO.setCanduoi( dmxetnghiem.getCanduoi() );
        dmxetnghiemDTO.setCantren( dmxetnghiem.getCantren() );
        dmxetnghiemDTO.setDonvido( dmxetnghiem.getDonvido() );
        dmxetnghiemDTO.setMaByt( dmxetnghiem.getMaByt() );
        dmxetnghiemDTO.setManhomBH( dmxetnghiem.getManhomBH() );

        return dmxetnghiemDTO;
    }


    public Dmxetnghiem toEntity(DmxetnghiemDTO dmxetnghiemDTO) {
        if ( dmxetnghiemDTO == null ) {
            return null;
        }

        Dmxetnghiem dmxetnghiem = new Dmxetnghiem();

        if(dmxetnghiemDTO.getDmnhomxetnghiemId() != null) {
            Dmnhomxetnghiem dmnhomxetnghiem = repository.getOne(dmxetnghiemDTO.getDmnhomxetnghiemId());
            if(dmnhomxetnghiem != null) {
                dmxetnghiem.setDmnhomxetnghiem(dmnhomxetnghiem);
            }
        }
        dmxetnghiem.setId( dmxetnghiemDTO.getId() );
        dmxetnghiem.setMa( dmxetnghiemDTO.getMa() );
        dmxetnghiem.setTen( dmxetnghiemDTO.getTen() );
        dmxetnghiem.setCha( dmxetnghiemDTO.getCha() );
        dmxetnghiem.setMota( dmxetnghiemDTO.getMota() );
        dmxetnghiem.setGioitinh( dmxetnghiemDTO.getGioitinh() );
        dmxetnghiem.setCanduoi( dmxetnghiemDTO.getCanduoi() );
        dmxetnghiem.setCantren( dmxetnghiemDTO.getCantren() );
        dmxetnghiem.setDonvido( dmxetnghiemDTO.getDonvido() );
        dmxetnghiem.setMaByt( dmxetnghiemDTO.getMaByt() );
        dmxetnghiem.setManhomBH( dmxetnghiemDTO.getManhomBH() );

        return dmxetnghiem;
    }
}
