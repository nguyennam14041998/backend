package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmgiaiphaubenhDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmgiaiphaubenhDTO.class);
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO1 = new DmgiaiphaubenhDTO();
        dmgiaiphaubenhDTO1.setId(1L);
        DmgiaiphaubenhDTO dmgiaiphaubenhDTO2 = new DmgiaiphaubenhDTO();
        assertThat(dmgiaiphaubenhDTO1).isNotEqualTo(dmgiaiphaubenhDTO2);
        dmgiaiphaubenhDTO2.setId(dmgiaiphaubenhDTO1.getId());
        assertThat(dmgiaiphaubenhDTO1).isEqualTo(dmgiaiphaubenhDTO2);
        dmgiaiphaubenhDTO2.setId(2L);
        assertThat(dmgiaiphaubenhDTO1).isNotEqualTo(dmgiaiphaubenhDTO2);
        dmgiaiphaubenhDTO1.setId(null);
        assertThat(dmgiaiphaubenhDTO1).isNotEqualTo(dmgiaiphaubenhDTO2);
    }
}
