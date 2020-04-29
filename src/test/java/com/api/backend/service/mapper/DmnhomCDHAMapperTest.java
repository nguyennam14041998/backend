package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmnhomCDHAMapperTest {

    private DmnhomCDHAMapper dmnhomCDHAMapper;

    @BeforeEach
    public void setUp() {
        dmnhomCDHAMapper = new DmnhomCDHAMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmnhomCDHAMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmnhomCDHAMapper.fromId(null)).isNull();
    }
}
