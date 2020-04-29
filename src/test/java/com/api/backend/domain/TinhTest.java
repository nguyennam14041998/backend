package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class TinhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tinh.class);
        Tinh tinh1 = new Tinh();
        tinh1.setId(1L);
        Tinh tinh2 = new Tinh();
        tinh2.setId(tinh1.getId());
        assertThat(tinh1).isEqualTo(tinh2);
        tinh2.setId(2L);
        assertThat(tinh1).isNotEqualTo(tinh2);
        tinh1.setId(null);
        assertThat(tinh1).isNotEqualTo(tinh2);
    }
}
