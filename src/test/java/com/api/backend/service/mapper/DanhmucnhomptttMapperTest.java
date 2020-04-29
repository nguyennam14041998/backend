package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhmucnhomptttMapperTest {

    private DanhmucnhomptttMapper danhmucnhomptttMapper;

    @BeforeEach
    public void setUp() {
        danhmucnhomptttMapper = new DanhmucnhomptttMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhmucnhomptttMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhmucnhomptttMapper.fromId(null)).isNull();
    }
}
