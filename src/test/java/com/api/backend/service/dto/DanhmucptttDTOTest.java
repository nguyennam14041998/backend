package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanhmucptttDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhmucptttDTO.class);
        DanhmucptttDTO danhmucptttDTO1 = new DanhmucptttDTO();
        danhmucptttDTO1.setId(1L);
        DanhmucptttDTO danhmucptttDTO2 = new DanhmucptttDTO();
        assertThat(danhmucptttDTO1).isNotEqualTo(danhmucptttDTO2);
        danhmucptttDTO2.setId(danhmucptttDTO1.getId());
        assertThat(danhmucptttDTO1).isEqualTo(danhmucptttDTO2);
        danhmucptttDTO2.setId(2L);
        assertThat(danhmucptttDTO1).isNotEqualTo(danhmucptttDTO2);
        danhmucptttDTO1.setId(null);
        assertThat(danhmucptttDTO1).isNotEqualTo(danhmucptttDTO2);
    }
}
