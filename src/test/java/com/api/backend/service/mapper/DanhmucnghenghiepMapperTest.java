package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhmucnghenghiepMapperTest {

    private DanhmucnghenghiepMapper danhmucnghenghiepMapper;

    @BeforeEach
    public void setUp() {
        danhmucnghenghiepMapper = new DanhmucnghenghiepMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhmucnghenghiepMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhmucnghenghiepMapper.fromId(null)).isNull();
    }
}
