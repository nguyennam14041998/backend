package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class XaMapperTest {

    private XaMapper xaMapper;

    @BeforeEach
    public void setUp() {
        xaMapper = new XaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(xaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(xaMapper.fromId(null)).isNull();
    }
}
