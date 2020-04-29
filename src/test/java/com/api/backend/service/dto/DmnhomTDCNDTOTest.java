package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomTDCNDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomTDCNDTO.class);
        DmnhomTDCNDTO dmnhomTDCNDTO1 = new DmnhomTDCNDTO();
        dmnhomTDCNDTO1.setId(1L);
        DmnhomTDCNDTO dmnhomTDCNDTO2 = new DmnhomTDCNDTO();
        assertThat(dmnhomTDCNDTO1).isNotEqualTo(dmnhomTDCNDTO2);
        dmnhomTDCNDTO2.setId(dmnhomTDCNDTO1.getId());
        assertThat(dmnhomTDCNDTO1).isEqualTo(dmnhomTDCNDTO2);
        dmnhomTDCNDTO2.setId(2L);
        assertThat(dmnhomTDCNDTO1).isNotEqualTo(dmnhomTDCNDTO2);
        dmnhomTDCNDTO1.setId(null);
        assertThat(dmnhomTDCNDTO1).isNotEqualTo(dmnhomTDCNDTO2);
    }
}
