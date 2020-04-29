package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class TinhDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TinhDTO.class);
        TinhDTO tinhDTO1 = new TinhDTO();
        tinhDTO1.setId(1L);
        TinhDTO tinhDTO2 = new TinhDTO();
        assertThat(tinhDTO1).isNotEqualTo(tinhDTO2);
        tinhDTO2.setId(tinhDTO1.getId());
        assertThat(tinhDTO1).isEqualTo(tinhDTO2);
        tinhDTO2.setId(2L);
        assertThat(tinhDTO1).isNotEqualTo(tinhDTO2);
        tinhDTO1.setId(null);
        assertThat(tinhDTO1).isNotEqualTo(tinhDTO2);
    }
}
