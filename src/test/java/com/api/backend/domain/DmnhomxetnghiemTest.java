package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomxetnghiemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmnhomxetnghiem.class);
        Dmnhomxetnghiem dmnhomxetnghiem1 = new Dmnhomxetnghiem();
        dmnhomxetnghiem1.setId(1L);
        Dmnhomxetnghiem dmnhomxetnghiem2 = new Dmnhomxetnghiem();
        dmnhomxetnghiem2.setId(dmnhomxetnghiem1.getId());
        assertThat(dmnhomxetnghiem1).isEqualTo(dmnhomxetnghiem2);
        dmnhomxetnghiem2.setId(2L);
        assertThat(dmnhomxetnghiem1).isNotEqualTo(dmnhomxetnghiem2);
        dmnhomxetnghiem1.setId(null);
        assertThat(dmnhomxetnghiem1).isNotEqualTo(dmnhomxetnghiem2);
    }
}
