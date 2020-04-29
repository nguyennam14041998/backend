package com.api.backend.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DmnhomgiaiphaubenhMapperTest {

    private DmnhomgiaiphaubenhMapper dmnhomgiaiphaubenhMapper;

    @BeforeEach
    public void setUp() {
        dmnhomgiaiphaubenhMapper = new DmnhomgiaiphaubenhMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dmnhomgiaiphaubenhMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dmnhomgiaiphaubenhMapper.fromId(null)).isNull();
    }
}
