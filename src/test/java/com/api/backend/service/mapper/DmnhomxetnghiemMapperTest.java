package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmnhomxetnghiemMapperTest {

    private DmnhomxetnghiemMapper dmnhomxetnghiemMapper;

    @BeforeEach
    public void setUp() {
        dmnhomxetnghiemMapper = new DmnhomxetnghiemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmnhomxetnghiemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmnhomxetnghiemMapper.fromId(null)).isNull();
    }
}
