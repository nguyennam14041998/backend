package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomCDHADTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomCDHADTO.class);
        DmnhomCDHADTO dmnhomCDHADTO1 = new DmnhomCDHADTO();
        dmnhomCDHADTO1.setId(1L);
        DmnhomCDHADTO dmnhomCDHADTO2 = new DmnhomCDHADTO();
        assertThat(dmnhomCDHADTO1).isNotEqualTo(dmnhomCDHADTO2);
        dmnhomCDHADTO2.setId(dmnhomCDHADTO1.getId());
        assertThat(dmnhomCDHADTO1).isEqualTo(dmnhomCDHADTO2);
        dmnhomCDHADTO2.setId(2L);
        assertThat(dmnhomCDHADTO1).isNotEqualTo(dmnhomCDHADTO2);
        dmnhomCDHADTO1.setId(null);
        assertThat(dmnhomCDHADTO1).isNotEqualTo(dmnhomCDHADTO2);
    }
}
