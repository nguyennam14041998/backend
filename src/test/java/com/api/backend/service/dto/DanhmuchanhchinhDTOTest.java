package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmuchanhchinhDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmuchanhchinhDTO.class);
        DanhmuchanhchinhDTO danhmuchanhchinhDTO1 = new DanhmuchanhchinhDTO();
        danhmuchanhchinhDTO1.setId(1L);
        DanhmuchanhchinhDTO danhmuchanhchinhDTO2 = new DanhmuchanhchinhDTO();
        assertThat(danhmuchanhchinhDTO1).isNotEqualTo(danhmuchanhchinhDTO2);
        danhmuchanhchinhDTO2.setId(danhmuchanhchinhDTO1.getId());
        assertThat(danhmuchanhchinhDTO1).isEqualTo(danhmuchanhchinhDTO2);
        danhmuchanhchinhDTO2.setId(2L);
        assertThat(danhmuchanhchinhDTO1).isNotEqualTo(danhmuchanhchinhDTO2);
        danhmuchanhchinhDTO1.setId(null);
        assertThat(danhmuchanhchinhDTO1).isNotEqualTo(danhmuchanhchinhDTO2);
    }
}
