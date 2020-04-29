package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmCDHAMapperTest {

    private DmCDHAMapper dmCDHAMapper;

    @BeforeEach
    public void setUp() {
        dmCDHAMapper = new DmCDHAMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmCDHAMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmCDHAMapper.fromId(null)).isNull();
    }
}
