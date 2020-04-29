package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmloaibenhlyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmloaibenhlyDTO.class);
        DmloaibenhlyDTO dmloaibenhlyDTO1 = new DmloaibenhlyDTO();
        dmloaibenhlyDTO1.setId(1L);
        DmloaibenhlyDTO dmloaibenhlyDTO2 = new DmloaibenhlyDTO();
        assertThat(dmloaibenhlyDTO1).isNotEqualTo(dmloaibenhlyDTO2);
        dmloaibenhlyDTO2.setId(dmloaibenhlyDTO1.getId());
        assertThat(dmloaibenhlyDTO1).isEqualTo(dmloaibenhlyDTO2);
        dmloaibenhlyDTO2.setId(2L);
        assertThat(dmloaibenhlyDTO1).isNotEqualTo(dmloaibenhlyDTO2);
        dmloaibenhlyDTO1.setId(null);
        assertThat(dmloaibenhlyDTO1).isNotEqualTo(dmloaibenhlyDTO2);
    }
}
