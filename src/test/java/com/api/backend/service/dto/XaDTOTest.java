package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class XaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(XaDTO.class);
        XaDTO xaDTO1 = new XaDTO();
        xaDTO1.setId(1L);
        XaDTO xaDTO2 = new XaDTO();
        assertThat(xaDTO1).isNotEqualTo(xaDTO2);
        xaDTO2.setId(xaDTO1.getId());
        assertThat(xaDTO1).isEqualTo(xaDTO2);
        xaDTO2.setId(2L);
        assertThat(xaDTO1).isNotEqualTo(xaDTO2);
        xaDTO1.setId(null);
        assertThat(xaDTO1).isNotEqualTo(xaDTO2);
    }
}
