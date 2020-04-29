package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CosokhambenhMapperTest {

    private CosokhambenhMapper cosokhambenhMapper;

    @BeforeEach
    public void setUp() {
        cosokhambenhMapper = new CosokhambenhMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cosokhambenhMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cosokhambenhMapper.fromId(null)).isNull();
    }
}
