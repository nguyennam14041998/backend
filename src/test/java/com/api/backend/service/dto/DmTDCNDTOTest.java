package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmTDCNDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTDCNDTO.class);
        DmTDCNDTO dmTDCNDTO1 = new DmTDCNDTO();
        dmTDCNDTO1.setId(1L);
        DmTDCNDTO dmTDCNDTO2 = new DmTDCNDTO();
        assertThat(dmTDCNDTO1).isNotEqualTo(dmTDCNDTO2);
        dmTDCNDTO2.setId(dmTDCNDTO1.getId());
        assertThat(dmTDCNDTO1).isEqualTo(dmTDCNDTO2);
        dmTDCNDTO2.setId(2L);
        assertThat(dmTDCNDTO1).isNotEqualTo(dmTDCNDTO2);
        dmTDCNDTO1.setId(null);
        assertThat(dmTDCNDTO1).isNotEqualTo(dmTDCNDTO2);
    }
}
