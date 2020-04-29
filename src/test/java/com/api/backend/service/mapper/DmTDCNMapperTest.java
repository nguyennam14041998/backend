package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmTDCNMapperTest {

    private DmTDCNMapper dmTDCNMapper;

    @BeforeEach
    public void setUp() {
        dmTDCNMapper = new DmTDCNMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmTDCNMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmTDCNMapper.fromId(null)).isNull();
    }
}
