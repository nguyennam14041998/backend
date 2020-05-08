package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhmuchanhchinhMapperTest {

    private DanhmuchanhchinhMapper danhmuchanhchinhMapper;

    @BeforeEach
    public void setUp() {
        danhmuchanhchinhMapper = new DanhmuchanhchinhMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhmuchanhchinhMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhmuchanhchinhMapper.fromId(null)).isNull();
    }
}
