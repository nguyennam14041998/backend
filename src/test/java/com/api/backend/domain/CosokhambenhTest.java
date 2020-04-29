package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class CosokhambenhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cosokhambenh.class);
        Cosokhambenh cosokhambenh1 = new Cosokhambenh();
        cosokhambenh1.setId(1L);
        Cosokhambenh cosokhambenh2 = new Cosokhambenh();
        cosokhambenh2.setId(cosokhambenh1.getId());
        assertThat(cosokhambenh1).isEqualTo(cosokhambenh2);
        cosokhambenh2.setId(2L);
        assertThat(cosokhambenh1).isNotEqualTo(cosokhambenh2);
        cosokhambenh1.setId(null);
        assertThat(cosokhambenh1).isNotEqualTo(cosokhambenh2);
    }
}
