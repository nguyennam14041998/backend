package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomCDHATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomCDHA.class);
        DmnhomCDHA dmnhomCDHA1 = new DmnhomCDHA();
        dmnhomCDHA1.setId(1L);
        DmnhomCDHA dmnhomCDHA2 = new DmnhomCDHA();
        dmnhomCDHA2.setId(dmnhomCDHA1.getId());
        assertThat(dmnhomCDHA1).isEqualTo(dmnhomCDHA2);
        dmnhomCDHA2.setId(2L);
        assertThat(dmnhomCDHA1).isNotEqualTo(dmnhomCDHA2);
        dmnhomCDHA1.setId(null);
        assertThat(dmnhomCDHA1).isNotEqualTo(dmnhomCDHA2);
    }
}
