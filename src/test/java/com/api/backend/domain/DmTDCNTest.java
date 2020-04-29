package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmTDCNTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmTDCN.class);
        DmTDCN dmTDCN1 = new DmTDCN();
        dmTDCN1.setId(1L);
        DmTDCN dmTDCN2 = new DmTDCN();
        dmTDCN2.setId(dmTDCN1.getId());
        assertThat(dmTDCN1).isEqualTo(dmTDCN2);
        dmTDCN2.setId(2L);
        assertThat(dmTDCN1).isNotEqualTo(dmTDCN2);
        dmTDCN1.setId(null);
        assertThat(dmTDCN1).isNotEqualTo(dmTDCN2);
    }
}
