package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhombenhlyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhombenhlyDTO.class);
        DmnhombenhlyDTO dmnhombenhlyDTO1 = new DmnhombenhlyDTO();
        dmnhombenhlyDTO1.setId(1L);
        DmnhombenhlyDTO dmnhombenhlyDTO2 = new DmnhombenhlyDTO();
        assertThat(dmnhombenhlyDTO1).isNotEqualTo(dmnhombenhlyDTO2);
        dmnhombenhlyDTO2.setId(dmnhombenhlyDTO1.getId());
        assertThat(dmnhombenhlyDTO1).isEqualTo(dmnhombenhlyDTO2);
        dmnhombenhlyDTO2.setId(2L);
        assertThat(dmnhombenhlyDTO1).isNotEqualTo(dmnhombenhlyDTO2);
        dmnhombenhlyDTO1.setId(null);
        assertThat(dmnhombenhlyDTO1).isNotEqualTo(dmnhombenhlyDTO2);
    }
}
