package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmloaibenhlyMapperTest {

    private DmloaibenhlyMapper dmloaibenhlyMapper;

    @BeforeEach
    public void setUp() {
        dmloaibenhlyMapper = new DmloaibenhlyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmloaibenhlyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmloaibenhlyMapper.fromId(null)).isNull();
    }
}
