package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmCDHATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmCDHA.class);
        DmCDHA dmCDHA1 = new DmCDHA();
        dmCDHA1.setId(1L);
        DmCDHA dmCDHA2 = new DmCDHA();
        dmCDHA2.setId(dmCDHA1.getId());
        assertThat(dmCDHA1).isEqualTo(dmCDHA2);
        dmCDHA2.setId(2L);
        assertThat(dmCDHA1).isNotEqualTo(dmCDHA2);
        dmCDHA1.setId(null);
        assertThat(dmCDHA1).isNotEqualTo(dmCDHA2);
    }
}
