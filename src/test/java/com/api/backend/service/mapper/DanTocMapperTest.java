package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanTocMapperTest {

    private DanTocMapper danTocMapper;

    @BeforeEach
    public void setUp() {
        danTocMapper = new DanTocMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danTocMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danTocMapper.fromId(null)).isNull();
    }
}
