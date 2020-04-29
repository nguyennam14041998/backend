package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmnhombenhlyMapperTest {

    private DmnhombenhlyMapper dmnhombenhlyMapper;

    @BeforeEach
    public void setUp() {
        dmnhombenhlyMapper = new DmnhombenhlyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmnhombenhlyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmnhombenhlyMapper.fromId(null)).isNull();
    }
}
