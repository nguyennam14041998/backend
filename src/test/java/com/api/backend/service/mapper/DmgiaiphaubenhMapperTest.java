package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmgiaiphaubenhMapperTest {

    private DmgiaiphaubenhMapper dmgiaiphaubenhMapper;

    @BeforeEach
    public void setUp() {
        dmgiaiphaubenhMapper = new DmgiaiphaubenhMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmgiaiphaubenhMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmgiaiphaubenhMapper.fromId(null)).isNull();
    }
}
