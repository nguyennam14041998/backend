package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmbenhlyMapperTest {

    private DmbenhlyMapper dmbenhlyMapper;

    @BeforeEach
    public void setUp() {
        dmbenhlyMapper = new DmbenhlyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmbenhlyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmbenhlyMapper.fromId(null)).isNull();
    }
}
