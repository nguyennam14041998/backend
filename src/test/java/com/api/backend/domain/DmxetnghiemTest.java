package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmxetnghiemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmxetnghiem.class);
        Dmxetnghiem dmxetnghiem1 = new Dmxetnghiem();
        dmxetnghiem1.setId(1L);
        Dmxetnghiem dmxetnghiem2 = new Dmxetnghiem();
        dmxetnghiem2.setId(dmxetnghiem1.getId());
        assertThat(dmxetnghiem1).isEqualTo(dmxetnghiem2);
        dmxetnghiem2.setId(2L);
        assertThat(dmxetnghiem1).isNotEqualTo(dmxetnghiem2);
        dmxetnghiem1.setId(null);
        assertThat(dmxetnghiem1).isNotEqualTo(dmxetnghiem2);
    }
}
