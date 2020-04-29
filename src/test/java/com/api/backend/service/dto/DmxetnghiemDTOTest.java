package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmxetnghiemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmxetnghiemDTO.class);
        DmxetnghiemDTO dmxetnghiemDTO1 = new DmxetnghiemDTO();
        dmxetnghiemDTO1.setId(1L);
        DmxetnghiemDTO dmxetnghiemDTO2 = new DmxetnghiemDTO();
        assertThat(dmxetnghiemDTO1).isNotEqualTo(dmxetnghiemDTO2);
        dmxetnghiemDTO2.setId(dmxetnghiemDTO1.getId());
        assertThat(dmxetnghiemDTO1).isEqualTo(dmxetnghiemDTO2);
        dmxetnghiemDTO2.setId(2L);
        assertThat(dmxetnghiemDTO1).isNotEqualTo(dmxetnghiemDTO2);
        dmxetnghiemDTO1.setId(null);
        assertThat(dmxetnghiemDTO1).isNotEqualTo(dmxetnghiemDTO2);
    }
}
