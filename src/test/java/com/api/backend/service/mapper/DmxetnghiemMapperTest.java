package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmxetnghiemMapperTest {

    private DmxetnghiemMapper dmxetnghiemMapper;

    @BeforeEach
    public void setUp() {
        dmxetnghiemMapper = new DmxetnghiemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmxetnghiemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmxetnghiemMapper.fromId(null)).isNull();
    }
}
