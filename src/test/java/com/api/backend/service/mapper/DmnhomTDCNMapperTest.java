package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmnhomTDCNMapperTest {

    private DmnhomTDCNMapper dmnhomTDCNMapper;

    @BeforeEach
    public void setUp() {
        dmnhomTDCNMapper = new DmnhomTDCNMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmnhomTDCNMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmnhomTDCNMapper.fromId(null)).isNull();
    }
}
