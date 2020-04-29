package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmCDHADTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmCDHADTO.class);
        DmCDHADTO dmCDHADTO1 = new DmCDHADTO();
        dmCDHADTO1.setId(1L);
        DmCDHADTO dmCDHADTO2 = new DmCDHADTO();
        assertThat(dmCDHADTO1).isNotEqualTo(dmCDHADTO2);
        dmCDHADTO2.setId(dmCDHADTO1.getId());
        assertThat(dmCDHADTO1).isEqualTo(dmCDHADTO2);
        dmCDHADTO2.setId(2L);
        assertThat(dmCDHADTO1).isNotEqualTo(dmCDHADTO2);
        dmCDHADTO1.setId(null);
        assertThat(dmCDHADTO1).isNotEqualTo(dmCDHADTO2);
    }
}
