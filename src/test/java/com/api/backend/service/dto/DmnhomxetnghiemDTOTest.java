package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomxetnghiemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomxetnghiemDTO.class);
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO1 = new DmnhomxetnghiemDTO();
        dmnhomxetnghiemDTO1.setId(1L);
        DmnhomxetnghiemDTO dmnhomxetnghiemDTO2 = new DmnhomxetnghiemDTO();
        assertThat(dmnhomxetnghiemDTO1).isNotEqualTo(dmnhomxetnghiemDTO2);
        dmnhomxetnghiemDTO2.setId(dmnhomxetnghiemDTO1.getId());
        assertThat(dmnhomxetnghiemDTO1).isEqualTo(dmnhomxetnghiemDTO2);
        dmnhomxetnghiemDTO2.setId(2L);
        assertThat(dmnhomxetnghiemDTO1).isNotEqualTo(dmnhomxetnghiemDTO2);
        dmnhomxetnghiemDTO1.setId(null);
        assertThat(dmnhomxetnghiemDTO1).isNotEqualTo(dmnhomxetnghiemDTO2);
    }
}
