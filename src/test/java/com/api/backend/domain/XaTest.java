package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class XaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Xa.class);
        Xa xa1 = new Xa();
        xa1.setId(1L);
        Xa xa2 = new Xa();
        xa2.setId(xa1.getId());
        assertThat(xa1).isEqualTo(xa2);
        xa2.setId(2L);
        assertThat(xa1).isNotEqualTo(xa2);
        xa1.setId(null);
        assertThat(xa1).isNotEqualTo(xa2);
    }
}
