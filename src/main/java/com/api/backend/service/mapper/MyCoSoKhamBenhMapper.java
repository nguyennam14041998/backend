package com.api.backend.service.mapper;

import com.api.backend.domain.Cosokhambenh;
import com.api.backend.domain.Huyen;
import com.api.backend.domain.Tinh;
import com.api.backend.domain.Xa;
import com.api.backend.repository.HuyenRepository;
import com.api.backend.repository.TinhRepository;
import com.api.backend.repository.XaRepository;
import com.api.backend.service.dto.CosokhambenhDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyCoSoKhamBenhMapper {

    @Autowired private TinhRepository tinhRepository;
    @Autowired private HuyenRepository huyenRepository;
    @Autowired private XaRepository xaRepository;


    public List<Cosokhambenh> toEntity(List<CosokhambenhDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Cosokhambenh> list = new ArrayList<Cosokhambenh>( dtoList.size() );
        for ( CosokhambenhDTO cosokhambenhDTO : dtoList ) {
            list.add( toEntity( cosokhambenhDTO ) );
        }

        return list;
    }


    public List<CosokhambenhDTO> toDto(List<Cosokhambenh> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CosokhambenhDTO> list = new ArrayList<CosokhambenhDTO>( entityList.size() );
        for ( Cosokhambenh cosokhambenh : entityList ) {
            list.add( toDto( cosokhambenh ) );
        }

        return list;
    }


    public CosokhambenhDTO toDto(Cosokhambenh cosokhambenh) {
        if ( cosokhambenh == null ) {
            return null;
        }

        CosokhambenhDTO cosokhambenhDTO = new CosokhambenhDTO();


        if(cosokhambenh.getTinh() !=null) {
            cosokhambenhDTO.setTinhId(cosokhambenh.getTinh().getId());
            cosokhambenhDTO.setTenTinh(cosokhambenh.getTinh().getTen());
        }

        if(cosokhambenh.getXa() !=null) {
            cosokhambenhDTO.setXaId(cosokhambenh.getXa().getId());
            cosokhambenhDTO.setTenXa(cosokhambenh.getXa().getTen());
        }


        if(cosokhambenh.getHuyen() !=null) {
            cosokhambenhDTO.setHuyenId(cosokhambenh.getHuyen().getId());
            cosokhambenhDTO.setTenHuyen(cosokhambenh.getHuyen().getTen());
        }

        cosokhambenhDTO.setId( cosokhambenh.getId() );
        cosokhambenhDTO.setMa( cosokhambenh.getMa() );
        cosokhambenhDTO.setTen( cosokhambenh.getTen() );
        cosokhambenhDTO.setMaKCBBD( cosokhambenh.getMaKCBBD() );
        cosokhambenhDTO.setHang( cosokhambenh.getHang() );
        cosokhambenhDTO.setTuyen( cosokhambenh.getTuyen() );
        cosokhambenhDTO.setLoai( cosokhambenh.getLoai() );
        cosokhambenhDTO.setDiachi( cosokhambenh.getDiachi() );
        cosokhambenhDTO.setGhichu( cosokhambenh.getGhichu() );

        return cosokhambenhDTO;
    }


    public Cosokhambenh toEntity(CosokhambenhDTO cosokhambenhDTO) {
        if ( cosokhambenhDTO == null ) {
            return null;
        }

        Cosokhambenh cosokhambenh = new Cosokhambenh();


        if(cosokhambenhDTO.getTinhId() != null) {
            Tinh tinh = tinhRepository.getOne(cosokhambenhDTO.getTinhId());
            if(tinh != null) {
                cosokhambenh.setTinh(tinh);
            }
        }

        if(cosokhambenhDTO.getHuyenId() != null) {
            Huyen huyen = huyenRepository.getOne(cosokhambenhDTO.getHuyenId());
            if(huyen != null) {
                cosokhambenh.setHuyen(huyen);
            }
        }

        if(cosokhambenhDTO.getXaId() != null) {
            Xa xa = xaRepository.getOne(cosokhambenhDTO.getXaId());
            if(xa != null) {
                cosokhambenh.setXa(xa);
            }
        }


        cosokhambenh.setId( cosokhambenhDTO.getId() );
        cosokhambenh.setMa( cosokhambenhDTO.getMa() );
        cosokhambenh.setTen( cosokhambenhDTO.getTen() );
        cosokhambenh.setMaKCBBD( cosokhambenhDTO.getMaKCBBD() );
        cosokhambenh.setHang( cosokhambenhDTO.getHang() );
        cosokhambenh.setTuyen( cosokhambenhDTO.getTuyen() );
        cosokhambenh.setLoai( cosokhambenhDTO.getLoai() );
        cosokhambenh.setDiachi( cosokhambenhDTO.getDiachi() );
        cosokhambenh.setGhichu( cosokhambenhDTO.getGhichu() );

        return cosokhambenh;
    }
}
