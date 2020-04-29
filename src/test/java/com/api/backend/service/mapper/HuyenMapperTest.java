package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HuyenMapperTest {

    private HuyenMapper huyenMapper;

    @BeforeEach
    public void setUp() {
        huyenMapper = new HuyenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(huyenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(huyenMapper.fromId(null)).isNull();
    }
}
