package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomTDCNTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomTDCN.class);
        DmnhomTDCN dmnhomTDCN1 = new DmnhomTDCN();
        dmnhomTDCN1.setId(1L);
        DmnhomTDCN dmnhomTDCN2 = new DmnhomTDCN();
        dmnhomTDCN2.setId(dmnhomTDCN1.getId());
        assertThat(dmnhomTDCN1).isEqualTo(dmnhomTDCN2);
        dmnhomTDCN2.setId(2L);
        assertThat(dmnhomTDCN1).isNotEqualTo(dmnhomTDCN2);
        dmnhomTDCN1.setId(null);
        assertThat(dmnhomTDCN1).isNotEqualTo(dmnhomTDCN2);
    }
}
