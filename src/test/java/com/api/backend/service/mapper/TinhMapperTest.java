package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TinhMapperTest {

    private TinhMapper tinhMapper;

    @BeforeEach
    public void setUp() {
        tinhMapper = new TinhMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tinhMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tinhMapper.fromId(null)).isNull();
    }
}
