package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class HuyenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HuyenDTO.class);
        HuyenDTO huyenDTO1 = new HuyenDTO();
        huyenDTO1.setId(1L);
        HuyenDTO huyenDTO2 = new HuyenDTO();
        assertThat(huyenDTO1).isNotEqualTo(huyenDTO2);
        huyenDTO2.setId(huyenDTO1.getId());
        assertThat(huyenDTO1).isEqualTo(huyenDTO2);
        huyenDTO2.setId(2L);
        assertThat(huyenDTO1).isNotEqualTo(huyenDTO2);
        huyenDTO1.setId(null);
        assertThat(huyenDTO1).isNotEqualTo(huyenDTO2);
    }
}
