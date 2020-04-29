package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmbenhlyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmbenhly.class);
        Dmbenhly dmbenhly1 = new Dmbenhly();
        dmbenhly1.setId(1L);
        Dmbenhly dmbenhly2 = new Dmbenhly();
        dmbenhly2.setId(dmbenhly1.getId());
        assertThat(dmbenhly1).isEqualTo(dmbenhly2);
        dmbenhly2.setId(2L);
        assertThat(dmbenhly1).isNotEqualTo(dmbenhly2);
        dmbenhly1.setId(null);
        assertThat(dmbenhly1).isNotEqualTo(dmbenhly2);
    }
}
