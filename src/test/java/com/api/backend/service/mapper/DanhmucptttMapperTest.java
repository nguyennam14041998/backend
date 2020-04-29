package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhmucptttMapperTest {

    private DanhmucptttMapper danhmucptttMapper;

    @BeforeEach
    public void setUp() {
        danhmucptttMapper = new DanhmucptttMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhmucptttMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhmucptttMapper.fromId(null)).isNull();
    }
}
