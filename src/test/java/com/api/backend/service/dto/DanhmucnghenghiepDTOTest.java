package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucnghenghiepDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmucnghenghiepDTO.class);
        DanhmucnghenghiepDTO danhmucnghenghiepDTO1 = new DanhmucnghenghiepDTO();
        danhmucnghenghiepDTO1.setId(1L);
        DanhmucnghenghiepDTO danhmucnghenghiepDTO2 = new DanhmucnghenghiepDTO();
        assertThat(danhmucnghenghiepDTO1).isNotEqualTo(danhmucnghenghiepDTO2);
        danhmucnghenghiepDTO2.setId(danhmucnghenghiepDTO1.getId());
        assertThat(danhmucnghenghiepDTO1).isEqualTo(danhmucnghenghiepDTO2);
        danhmucnghenghiepDTO2.setId(2L);
        assertThat(danhmucnghenghiepDTO1).isNotEqualTo(danhmucnghenghiepDTO2);
        danhmucnghenghiepDTO1.setId(null);
        assertThat(danhmucnghenghiepDTO1).isNotEqualTo(danhmucnghenghiepDTO2);
    }
}
