package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhombenhlyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmnhombenhly.class);
        Dmnhombenhly dmnhombenhly1 = new Dmnhombenhly();
        dmnhombenhly1.setId(1L);
        Dmnhombenhly dmnhombenhly2 = new Dmnhombenhly();
        dmnhombenhly2.setId(dmnhombenhly1.getId());
        assertThat(dmnhombenhly1).isEqualTo(dmnhombenhly2);
        dmnhombenhly2.setId(2L);
        assertThat(dmnhombenhly1).isNotEqualTo(dmnhombenhly2);
        dmnhombenhly1.setId(null);
        assertThat(dmnhombenhly1).isNotEqualTo(dmnhombenhly2);
    }
}
