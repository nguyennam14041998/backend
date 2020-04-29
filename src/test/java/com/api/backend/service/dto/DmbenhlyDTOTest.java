package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmbenhlyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmbenhlyDTO.class);
        DmbenhlyDTO dmbenhlyDTO1 = new DmbenhlyDTO();
        dmbenhlyDTO1.setId(1L);
        DmbenhlyDTO dmbenhlyDTO2 = new DmbenhlyDTO();
        assertThat(dmbenhlyDTO1).isNotEqualTo(dmbenhlyDTO2);
        dmbenhlyDTO2.setId(dmbenhlyDTO1.getId());
        assertThat(dmbenhlyDTO1).isEqualTo(dmbenhlyDTO2);
        dmbenhlyDTO2.setId(2L);
        assertThat(dmbenhlyDTO1).isNotEqualTo(dmbenhlyDTO2);
        dmbenhlyDTO1.setId(null);
        assertThat(dmbenhlyDTO1).isNotEqualTo(dmbenhlyDTO2);
    }
}
