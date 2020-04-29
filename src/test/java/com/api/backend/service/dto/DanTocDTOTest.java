package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DanTocDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanTocDTO.class);
        DanTocDTO danTocDTO1 = new DanTocDTO();
        danTocDTO1.setId(1L);
        DanTocDTO danTocDTO2 = new DanTocDTO();
        assertThat(danTocDTO1).isNotEqualTo(danTocDTO2);
        danTocDTO2.setId(danTocDTO1.getId());
        assertThat(danTocDTO1).isEqualTo(danTocDTO2);
        danTocDTO2.setId(2L);
        assertThat(danTocDTO1).isNotEqualTo(danTocDTO2);
        danTocDTO1.setId(null);
        assertThat(danTocDTO1).isNotEqualTo(danTocDTO2);
    }
}
