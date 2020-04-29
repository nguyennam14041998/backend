package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucnhomptttDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmucnhomptttDTO.class);
        DanhmucnhomptttDTO danhmucnhomptttDTO1 = new DanhmucnhomptttDTO();
        danhmucnhomptttDTO1.setId(1L);
        DanhmucnhomptttDTO danhmucnhomptttDTO2 = new DanhmucnhomptttDTO();
        assertThat(danhmucnhomptttDTO1).isNotEqualTo(danhmucnhomptttDTO2);
        danhmucnhomptttDTO2.setId(danhmucnhomptttDTO1.getId());
        assertThat(danhmucnhomptttDTO1).isEqualTo(danhmucnhomptttDTO2);
        danhmucnhomptttDTO2.setId(2L);
        assertThat(danhmucnhomptttDTO1).isNotEqualTo(danhmucnhomptttDTO2);
        danhmucnhomptttDTO1.setId(null);
        assertThat(danhmucnhomptttDTO1).isNotEqualTo(danhmucnhomptttDTO2);
    }
}
